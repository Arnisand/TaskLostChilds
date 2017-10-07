package com.example.playful.tasklostchilds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.playful.tasklostchilds.utility.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String USER_LOGIN = "UserLogin";
    private static final String USER_PASS = "UserPassword";
    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadUser();
    }

    @OnClick(R.id.btnOK)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOK:
                if (validLogin() && validPass()) {
                    MyApp.initServerApi(etLogin.getText().toString(), etPassword.getText().toString());
                    MyApp.getApi().login().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.message().equals("OK") && response.isSuccessful()){
                                saveUser();
                                startActivity(new Intent(MainActivity.this, SecondScreen.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d(getClass().getName(), "error" + t.getMessage());
                        }
                    });
                }
                break;
        }
    }

    private void saveUser() {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(USER_LOGIN, etLogin.getText().toString());
        ed.putString(USER_PASS, etPassword.getText().toString());
        ed.apply();
    }

    private void loadUser() {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        if(sPref.contains(USER_LOGIN) && sPref.contains(USER_PASS)) {
            etLogin.setText(sPref.getString(USER_LOGIN, ""));
            etPassword.setText(sPref.getString(USER_PASS, ""));
        }
    }

    private boolean validPass() {
        if (etPassword.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean validLogin() {
        if (etLogin.length() > 0) {
            return true;
        }
        return false;
    }
}
