package com.example.basketballscoretools.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basketballscoretools.R;

import java.util.List;

public class Notifications_ImageAdapter extends ArrayAdapter<Bean> {
    private int recourceId;//资源id

    public Notifications_ImageAdapter(Context context, int resource, List<Bean> objects) {
        super(context,resource,objects);
        recourceId = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Bean ImageTest1 = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(recourceId,parent,false);
            ImageView imageView = view.findViewById(R.id.Im_v);
            TextView textView = view.findViewById(R.id.Text_v);
            imageView.setImageResource(ImageTest1.getImageid());
            textView.setText(ImageTest1.getName());
            return view;
    }
}
