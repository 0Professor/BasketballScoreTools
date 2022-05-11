package com.example.basketballscoretools.ui.notifications.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basketballscoretools.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifierPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_back_login;//返回登录
    private EditText et_user,password,passwordtwo;//输入账号跟密码

    private String username;
    private String pwd;
    private String pwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_password);

        bt_back_login = findViewById(R.id.modifier_bt_backlogin);

        et_user = findViewById(R.id.user_account);
        password = findViewById(R.id.ed_newpassword);
        passwordtwo = findViewById(R.id.et_passwordagain);

        bt_back_login.setOnClickListener(this);
        et_user.setOnClickListener(this);
        password.setOnClickListener(this);
        passwordtwo.setOnClickListener(this);

        et_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(et_user.getText().toString().trim())) {
                    bt_back_login.setBackgroundResource(R.drawable.bt_color_false);
                } else {
                    bt_back_login.setBackgroundResource(R.drawable.login_radius);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    /*修改密码返回登录*/
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //返回
            case R.id.bt_backlogin:
                username = et_user.getText().toString().trim();
                pwd = password.getText().toString().trim();
                pwd2 = passwordtwo.getText().toString().trim();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2)){
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!pwd.equals(pwd2)){
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }else {

                    String url = "";

                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("username",username)
                            .add("password",pwd)
                            .build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();

                    Call call = client.newCall(request);

                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(ModifierPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();

                            try {

                                JSONObject object = new JSONObject(result);

                                JSONObject data = object.getJSONObject("data");
                                final String string = object.getString("");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //成功
                                        Toast.makeText(ModifierPasswordActivity.this, "", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        intent.setClass(ModifierPasswordActivity.this,HomeLoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}