package com.example.basketballscoretools.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basketballscoretools.R;

public class Notifications_SendSuccess extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_sendsuccess);

        button = findViewById(R.id.Bt_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch (R.id.Bt_back){
                    case R.id.Bt_back:finish();
                        Intent i = new Intent();
                        i.setClass(Notifications_SendSuccess.this, NotificationsFragment.class);
                        startActivity(i);
                }
            }
        });

    }
}