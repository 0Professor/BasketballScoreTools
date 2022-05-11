package com.example.basketballscoretools;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.basketballscoretools.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*if (requestCode == 1) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                *//*Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show();
            }else if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                return;*//*
        }
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        int position = getIntent().getIntExtra("position",1);
        if(position ==1){
            navController.navigate(R.id.navigation_home);
        }else if(position ==2){
            navController.navigate(R.id.navigation_dashboard);
        }else if(position ==3){
            navController.navigate(R.id.navigation_notifications);
        }

    }

    //1，怎么判断是否登录
    //当你登录成功之后，会拿到一个tokenId，将这个tokenId保存到SP里面
    //当点击应用图标，进入到Activity之前，判断tokenId是否有值，如果有值，则说明登录了，否则没有登录，判断是否登录，如果登录了，直接跳转到MainActivity,否则跳转到登录界面
    //点击退出登录按钮的时候，跳转到登录界面，同时将SP的tokenId清空

}