package com.example.basketballscoretools.ui.notifications.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basketballscoretools.MainActivity;
import com.example.basketballscoretools.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActiviy extends AppCompatActivity implements View.OnClickListener{

    private Button register,backlogin;//注册，返回登录
    private EditText username; //账号
    private EditText pwd_1; //密码
    private EditText pwd_2; //再次输入密码

    private String username2;
    private String pwd1;
    private String pwd2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activiy);

        register = findViewById(R.id.bt_register);
        backlogin = findViewById(R.id.bt_backlogin);
        username = findViewById(R.id.register_edit_login);
        pwd_1 = findViewById(R.id.register_edit_password);
        pwd_2 = findViewById(R.id.register_edit_passwordagain);

        register.setOnClickListener(this);
        backlogin.setOnClickListener(this);
        username.setOnClickListener(this);
        pwd_1.setOnClickListener(this);
        pwd_2.setOnClickListener(this);

        //输入账号按钮发亮
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(username.getText().toString().trim())) {
                    register.setBackgroundResource(R.drawable.bt_color_false);
                    backlogin.setBackgroundResource(R.drawable.bt_color_false);
                } else {
                    register.setBackgroundResource(R.drawable.login_radius);
                    backlogin.setBackgroundResource(R.drawable.login_radius);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            //注册按钮
            case R.id.bt_register:

                username2 = username.getText().toString().trim();
                pwd1 = pwd_1.getText().toString().trim();
                pwd2 = pwd_2.getText().toString().trim();

                if(TextUtils.isEmpty(username2) || TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)){
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!pwd1.equals(pwd2)) {
                    Toast.makeText(RegisterActiviy.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                } else {

                    String url = "http://3e3811554r.qicp.vip/milktea/register";

                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username2)
                            .add("password", pwd1)
                            .build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();

                    final Call call = client.newCall(request);

                    //同步请求
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                Response response = call.execute();
                                String result = response.body().string();

                                Log.w("TAG","result ==== " + result);
                                try {

                                    JSONObject jsonObject = new JSONObject(result);

                                    final JSONObject data = jsonObject.getJSONObject("data");

                                    final int code = jsonObject.getInt("code");
                                    final String dataString = jsonObject.getString("msg");
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.e("TAG", "----->" + data);
                                            Log.e("TAG", "----->" + code);

                                            //!TextUtils.isEmpty(username2) && !TextUtils.isEmpty(pwd1) && !TextUtils.isEmpty(pwd2)
                                            //成功
                                            if (code != 500) {
                                                Log.e("TAG", "----->" + code);
                                                Log.e("TAG", "----->" + code);
                                                Toast.makeText(RegisterActiviy.this,code, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                intent.setClass(RegisterActiviy.this, HomeLoginActivity.class);
                                                startActivity(intent);
                                            } else{
                                                //失败
                                                Toast.makeText(RegisterActiviy.this, dataString, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
                }
                //返回登录
            case R.id.bt_backlogin:
                Intent intent = new Intent(RegisterActiviy.this,HomeLoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    };
}