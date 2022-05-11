package com.example.basketballscoretools.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basketballscoretools.R;

public class Notifications_Suggestion extends AppCompatActivity {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_suggestion);

        editText = findViewById(R.id.Et_question);
        button = findViewById(R.id.Bt_ok_send);

        //输入文字自动变小
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                } else {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(Notifications_Suggestion.this, Notifications_SendSuccess.class);
                startActivity(i);
            }
        });
    }
}