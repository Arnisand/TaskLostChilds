package com.example.playful.tasklostchilds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.playful.tasklostchilds.Child.AdapterChildCard;
import com.example.playful.tasklostchilds.Child.Child;
import com.example.playful.tasklostchilds.Child.ListChild;
import com.example.playful.tasklostchilds.Child.OnCardClick;
import com.example.playful.tasklostchilds.utility.MyApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondScreen extends AppCompatActivity {

    @BindView(R.id.listChild)
    RecyclerView rvChild;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    private int setOff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        ButterKnife.bind(this);

        rvChild.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvChild.setAdapter(new AdapterChildCard(ListChild.getInstance().getChildren(), clickCard()));
        reloadChild();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadChild();
            }
        });
    }

    private OnCardClick clickCard() {
        return new OnCardClick() {
            @Override
            public void onItemClick(Child child) {
                Intent intent = new Intent(SecondScreen.this, ThirdScreen.class);
                intent.putExtra("childIndex", ListChild.getInstance().getIndex(child));
                startActivity(intent);
            }
        };
    }

    private void reloadChild() {
        setOff = 0;
        MyApp.getApi().getChildren(setOff, 10).enqueue(new Callback<List<Child>>() {
            @Override
            public void onResponse(Call<List<Child>> call, Response<List<Child>> response) {
                if (response.body() != null) {
                    Log.d(getClass().getName(), response.message());
                    ListChild.getInstance().refreshChild(response.body());
                    ((AdapterChildCard) rvChild.getAdapter()).updateList(ListChild.getInstance().getChildren());
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Child>> call, Throwable t) {
                Log.d(getClass().getName(), "ERROR " + t.getMessage());
                Log.d(getClass().getName(), "CAUSE " + t.getCause().getMessage());
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
