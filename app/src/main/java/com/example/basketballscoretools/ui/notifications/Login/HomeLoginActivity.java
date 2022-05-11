package com.example.basketballscoretools.ui.notifications.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketballscoretools.MainActivity;
import com.example.basketballscoretools.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText id,password;//输入账号，密码
    private Button login;//登录
    private TextView register,bt_forgetPassword;//注册，忘记密码

    private String username, pwd;//
    private SharedPreferences sp;//存储数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);

        id = findViewById(R.id.edit_login);
        password = findViewById(R.id.edit_password);

        login = findViewById(R.id.bt_login);
        register = findViewById(R.id.no_register);
        bt_forgetPassword = findViewById(R.id.forget_password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        bt_forgetPassword.setOnClickListener(this);

        sp = getSharedPreferences("config", 0);
        String username = sp.getString("username", "");
        String password = sp.getString("password", "");

        id.setText(username);
        this.password.setText(password);

        //只有在输入数据时才会改变按钮颜色
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(id.getText().toString().trim())) {
                    login.setBackgroundResource(R.drawable.bt_color_false);
                } else {
                    login.setBackgroundResource(R.drawable.login_radius);
                    //setContentView(new LightView(HomeLoginActivity.this));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_login:
                username = id.getText().toString().trim();
                pwd = password.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("result", "----->");
                    //异步请求
                    String url = "http://3e3811554r.qicp.vip/milktea/login";
                    Log.i("result", "34534");
                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username)
                            .add("password", pwd)
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

                                Log.i("result", "----->" + result);
                                try {

                                    final JSONObject jsonObject = new JSONObject(result);

                                    final int code = jsonObject.getInt("code");
                                    final String msg1 = jsonObject.getString("msg");

                                    if(code == 200){
                                        JSONObject data = jsonObject.getJSONObject("data");
                                        final String msg = data.getString("msg");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.i("TAG","msg" + msg);
                                                Toast.makeText(HomeLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(HomeLoginActivity.this,MainActivity.class);
                                                intent.putExtra("position",1);
                                                startActivity(intent);
                                            }
                                        });
                                    }else if(code == 100){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(HomeLoginActivity.this, msg1, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
        }
        break;
        //异步请求
                  /*  call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(HomeLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(result);

                                final String msg = jsonObject.getString("errorMsg");
                                runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      Toast.makeText(HomeLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                  }
                                              });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });*/
            case R.id.no_register:
                //跳转到注册页面
                Intent intent = new Intent(this, RegisterActiviy.class);
                startActivity(intent);
                break;

            //忘记或修改密码
            case R.id.forget_password:
                Intent intent2 = new Intent(this, ModifierPasswordActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
}
}