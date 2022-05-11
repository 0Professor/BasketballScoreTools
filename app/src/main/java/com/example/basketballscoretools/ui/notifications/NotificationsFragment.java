package com.example.basketballscoretools.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.basketballscoretools.R;
import com.example.basketballscoretools.database.DataBaseUtils;
import com.example.basketballscoretools.ui.notifications.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private List<Bean> PieceList = new ArrayList<>();
    private NotificationsViewModel notificationsViewModel;
    private Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.Text_v);
        TextView aboutmeButton = root.findViewById(R.id.Bt_about_me);

        init();

        //创建适配器，在适配器中导入数据 1.当前类 2.listview一行的布局 3.数据集合
        Notifications_ImageAdapter imAdapter = new Notifications_ImageAdapter(getActivity(), R.layout.fragment_notifications_item, PieceList);
        final ListView listView = root.findViewById(R.id.listview); //将适配器导入Listview
        listView.setAdapter(imAdapter);

        aboutmeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), Notifications_AboutMe.class);
                startActivity(intent2);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("提醒!");
                    builder.setMessage("清除后无法找回！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int result = DataBaseUtils.delete("score");
                            Toast.makeText(getActivity(),result ==-1?"删除失败":"删除成功",Toast.LENGTH_SHORT).show();
                        }

                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    builder.show();
                }
                else if(position==1){
                    Intent i = new Intent();
                    i.setClass(getActivity(), Notifications_Suggestion.class);
                    startActivity(i);
                }else if(position==2) {
                    Intent i2 = new Intent();
                    i2.setClass(getActivity(), Notifications_CommonQuestion.class);
                    startActivity(i2);
                }else if(position==3){
                    Intent i3 = new Intent();
                    i3.setClass(getActivity(), Notifications_important.class);
                    startActivity(i3);
                }else if(position==4){
                    Intent i4 = new Intent();
                    i4.setClass(getActivity(), UserActivity.class);
                    startActivity(i4);
                }else if(position == 5){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("提醒!");
                    builder.setMessage("确认退出应用？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    builder.show();
                }
            }
        });
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void init(){
        Bean test = new Bean("清除所有数据",R.drawable.deleteall);
        Bean test2 = new Bean("反馈与建议",R.drawable.suggestion);
        Bean test3 = new Bean("常见问题",R.drawable.question);
        Bean test4 = new Bean("重要说明",R.drawable.important);
        Bean user = new Bean("关于账号",R.drawable.user);
        Bean test5 = new Bean("退出应用",R.drawable.exit_app);

        PieceList.add(test);
        PieceList.add(test2);
        PieceList.add(test3);
        PieceList.add(test4);
        PieceList.add(user);
        PieceList.add(test5);
    }

}