package com.example.playful.tasklostchilds.Child;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.playful.tasklostchilds.Child.Enums.SearchStatus;
import com.example.playful.tasklostchilds.R;
import com.example.playful.tasklostchilds.utility.MyApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterChildCard extends RecyclerView.Adapter<AdapterChildCard.ChildViewHolder> {

    private ArrayList<Child> children = new ArrayList<>();
    private OnCardClick listener;
    private int setOff = 0;

    public AdapterChildCard(ArrayList<Child> children, OnCardClick listener) {
        this.children = children;
//        Collections.reverse(this.children);
        this.listener = listener;
    }

    @Override
    public AdapterChildCard.ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_card, parent, false);
        return new ChildViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterChildCard.ChildViewHolder holder, int position) {
        Child child = children.get(position);
        holder.setChild(child, listener);
        if (position==children.size()-1){
            downloadChild();
        }
    }

    public void updateList(ArrayList<Child> children){
        setOff=0;
        this.children = children;
        notifyDataSetChanged();
    }

    private void addToList(ArrayList<Child> children) {
        this.children = children;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    private void downloadChild() {
        setOff += 10;
        MyApp.getApi().getChildren(setOff, 10).enqueue(new Callback<List<Child>>() {
            @Override
            public void onResponse(Call<List<Child>> call, Response<List<Child>> response) {
                if (response.body() != null) {
                    ListChild.getInstance().addChild(response.body());
                    addToList(ListChild.getInstance().getChildren());
                }
            }

            @Override
            public void onFailure(Call<List<Child>> call, Throwable t) {
                Log.d(getClass().getName(), "ERROR " + t.getMessage());
                Log.d(getClass().getName(), "CAUSE " + t.getCause().getMessage());
            }
        });
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvAge)
        TextView tvAge;
        @BindView(R.id.tvGender)
        TextView tvGender;
        @BindView(R.id.tvRegion)
        TextView tvRegion;
        @BindView(R.id.cardViewChild)
        CardView cardView;

        Child child;

        ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setName(String name) {
            this.tvName.setText(name);
        }

        public void setAge(String age){
            tvAge.setText(age);
        }

        public void setGender(String gender){
            tvGender.setText(gender);
        }
        public void setRegion(String region){
            tvRegion.setText(region);
        }

        public void setStatus(SearchStatus status){
            switch (status){
                case SEARCHING:
                    cardView.setCardBackgroundColor(Color.argb(100, 0, 0, 255));
                    break;
                case FOUND:
                    cardView.setCardBackgroundColor(Color.argb(100, 0, 255 , 0));
                    break;
                case REJECTED:
                    cardView.setCardBackgroundColor(Color.argb(100, 150, 150, 150));
                    break;
            }
        }

        public void setChild(final Child child, final OnCardClick listener) {
            this.child = child;
            setName(child.getName());
            setGender(child.getGender().toString());
            setRegion(child.getRegion());

            Date date = new Date();
            int age = date.getYear() - child.getDateOfBirth().getYear();
            setAge(String.valueOf(age));
            setStatus(child.getStatus());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(child);
                }
            });
        }
    }
}
