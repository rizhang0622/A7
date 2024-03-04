package com.example.zhangassignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {

    EditText usernameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        usernameEt = findViewById(R.id.signin_et_username);

        findViewById(R.id.signin_btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninActivity.this, A7Activity.class)
                        .putExtra("username", usernameEt.getText().toString()));
            }
        });
    }
}