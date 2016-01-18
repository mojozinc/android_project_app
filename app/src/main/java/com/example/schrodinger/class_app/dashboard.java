package com.example.schrodinger.class_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {
    private TextView text_name;
    private TextView text_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent i = getIntent();

        text_name = (TextView)findViewById(R.id.text_name_dashboard);
        text_email = (TextView)findViewById(R.id.text_email_dashboard);
        text_name.setText(i.getStringExtra("username"));
        text_email.setText(i.getStringExtra("password"));
    }
}
