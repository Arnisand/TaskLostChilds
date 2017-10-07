package com.example.playful.tasklostchilds.utility;

import android.app.Application;

import com.example.playful.tasklostchilds.Retrofit.ServerApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {

    private static ServerApi serverApi;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void initServerApi(final String login, final String pass){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization", Credentials.basic(login, pass));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://35.157.117.160:80").
                client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serverApi = retrofit.create(ServerApi.class);
    }

    public static ServerApi getApi() {
        return serverApi;
    }
}
