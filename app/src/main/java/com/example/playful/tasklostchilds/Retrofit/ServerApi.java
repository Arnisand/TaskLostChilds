package com.example.playful.tasklostchilds.Retrofit;

import com.example.playful.tasklostchilds.Child.Child;
import com.example.playful.tasklostchilds.Comments.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ServerApi {
    @GET("/")
    Call<String> login();

    @GET("/search")
    Call<List<Child>> getChildren(@Query("offset") int resourceName, @Query("limit") int count);

    @GET
    Call<List<Comment>> getComments(@Url String url, @Query("offset") int resourceName, @Query("limit") int count);
}
