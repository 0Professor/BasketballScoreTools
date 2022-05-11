package com.example.basketballscoretools.ui.dashboard;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.basketballscoretools.R;
import com.example.basketballscoretools.base.BaseFragment;
import com.example.basketballscoretools.database.DataBaseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DashboardFragment extends BaseFragment {

    private List<ScoreBean> list = new ArrayList<>();
   /* private DashboardViewModel dashboardViewModel;
    private DatabaseHelper databaseHelper;
    public SQLiteDatabase database;
    private Cursor cursor;*/

    private Dashboard_ListAdapter dashboardListAdapter;
    private ListView listView;
    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Dashboard_ListAdapter dashboardListAdapter = new Dashboard_ListAdapter(getActivity(),
                R.layout.fragment_dashboard_list, list);
        *//*list = getDatabaseData();*//*
        final ListView listView = root.findViewById(R.id.dashboard_list);
        listView.setAdapter(dashboardListAdapter);
       *//* databaseHelper = DatabaseHelper.getInstance(getContext());
        database = databaseHelper.getWritableDatabase();*//*

        return root;
    }*/

    @Override
    public int initLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void initView(View view) {
        dashboardListAdapter = new Dashboard_ListAdapter(getActivity(),
                R.layout.fragment_dashboard_list, list);
        listView = view.findViewById(R.id.dashboard_list);
        listView.setAdapter(dashboardListAdapter);
    }

    @Override
    public void initData() {
        list.addAll(getDatabaseData());
        Log.w("TAG","list ===>>> " + Arrays.asList(list));
        dashboardListAdapter.notifyDataSetChanged();
    }


    private List<ScoreBean> getDatabaseData() {
        ArrayList<ScoreBean> array = new ArrayList<>();
       /* cursor = database.query("score",
                null,
                null, null, null, null, null);*/
        Cursor cursor = DataBaseUtils.query("score");
        if (cursor.moveToFirst()) {
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String team_a = cursor.getString(cursor.getColumnIndex("team_a"));
                String team_b = cursor.getString(cursor.getColumnIndex("team_b"));
                String score_a = cursor.getString(cursor.getColumnIndex("score_a"));
                String score_b = cursor.getString(cursor.getColumnIndex("score_b"));

                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH) + 1;
                int day = now.get(Calendar.DAY_OF_MONTH);
                Log.w("TAG","cursor ====》》》》 " + day);
                array.add(new ScoreBean(year + "." + month + "." + day, team_a + " vs " + team_b,
                        score_a + ":" + score_b));
            }
        }
        cursor.close();
        return array;
    }
}
