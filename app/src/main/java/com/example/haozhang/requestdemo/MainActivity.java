package com.example.haozhang.requestdemo;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.haozhang.requestdemo.api.Acre;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.login);
        Button logoutButton = (Button) findViewById(R.id.logout);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskCompat.executeParallel(new LoginTest());
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskCompat.executeParallel(new LogoutTest());
            }
        });
    }

    private class LoginTest extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // this method is executed on non-UI thread
            try {
                return Acre.loginRequest();
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {
            // this method is executed on UI thread!!!!
            if (data != null) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LogoutTest extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // this method is executed on non-UI thread
            try {
                return Acre.logoutRequest();
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {
            // this method is executed on UI thread!!!!
            if (data != null) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
