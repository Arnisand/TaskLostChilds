package com.example.playful.tasklostchilds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.playful.tasklostchilds.Comments.AdapterComment;
import com.example.playful.tasklostchilds.Comments.Comment;
import com.example.playful.tasklostchilds.utility.MyApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourthScreen extends AppCompatActivity {

    @BindView(R.id.rvComments)
    RecyclerView rvComments;

    private ArrayList<Comment> listComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);
        ButterKnife.bind(this);

        listComments = new ArrayList<>();
        long childID;
        if (getIntent().hasExtra("childID")){
            childID  = getIntent().getLongExtra("childID", 0);
            String url = "http://35.157.117.160/search/" + childID + "/comments";
            Log.d(getClass().getName(), "URL  " + url);

            MyApp.getApi().getComments(url, 0,10).enqueue(new Callback<List<Comment>>() {
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    Log.d(getClass().getName(), response.body().toString());
                    listComments.addAll(response.body());
                    rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvComments.setAdapter(new AdapterComment(listComments));
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    Log.d(getClass().getName(), "ERROR " + t.getMessage());
                    Log.d(getClass().getName(), "CAUSE " + t.getCause().getMessage());
                }
            });
        }
    }
}
