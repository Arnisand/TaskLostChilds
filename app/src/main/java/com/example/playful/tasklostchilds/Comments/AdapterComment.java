package com.example.playful.tasklostchilds.Comments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.playful.tasklostchilds.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.CommentViewHolder> {

    private ArrayList<Comment> listComments;

    public AdapterComment(ArrayList<Comment> listComments) {
        this.listComments = listComments;
        Collections.reverse(this.listComments);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = listComments.get(position);
        holder.fillInfo(comment.getSenderName(), comment.getTimeOfUpdate(), comment.getText());
    }

    @Override
    public int getItemCount() {
        return listComments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvText)
        TextView tvText;

        CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void fillInfo(String name, String date, String text){
            tvName.setText(name);
            tvDate.setText(date);
            tvText.setText(text);
        }

    }
}
