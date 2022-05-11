package com.example.basketballscoretools.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.basketballscoretools.database.DataBaseUtils;

public abstract  class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(),container,false);
        //DataBaseUtils.getInstance(mActivity);
        initView(view);
        initData();
        return view;
    }
    public abstract int initLayout();

    public abstract void initView(View view);

    public abstract void initData();
}
