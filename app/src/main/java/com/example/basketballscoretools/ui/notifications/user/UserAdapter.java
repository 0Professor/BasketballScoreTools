package com.example.basketballscoretools.ui.notifications.user;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basketballscoretools.R;
import com.example.basketballscoretools.ui.notifications.Login.HomeLoginActivity;
import com.example.basketballscoretools.ui.notifications.Login.ModifierPasswordActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private List<User> list;

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_item,null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getAccount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tx_change_password);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    switch (p){
                            //更改密码
                        case 0:
                            Intent intent = new Intent(itemView.getContext(), ModifierPasswordActivity.class);
                            context.startActivity(intent);
                            break;
                            //退出账号
                        case 1:
                            Intent intent2 = new Intent(itemView.getContext(), HomeLoginActivity.class);
                            context.startActivity(intent2);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
