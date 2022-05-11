package com.example.basketballscoretools.ui.dashboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basketballscoretools.R;

import java.util.List;

public class Dashboard_ListAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private List<ScoreBean> datas;

    public Dashboard_ListAdapter(Context context, int resource, List<ScoreBean> datas) {
        this.resource = resource;
        this.context =context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        //1.第一次，加载数据的页面
        if(convertView == null){
            convertView = View.inflate(context,resource,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_date.setText(datas.get(position).getDate());
        viewHolder.tv_team.setText(datas.get(position).getTeam());
        viewHolder.tv_score.setText(datas.get(position).getScore());
        return convertView;
    }
    class ViewHolder{
        private TextView tv_date,tv_team,tv_score;
        public ViewHolder(View view){
            tv_date = view.findViewById(R.id.Tv_date);
            tv_team = view.findViewById(R.id.Tv_team);
            tv_score = view.findViewById(R.id.Tv_score);
        }
    }
}
