package com.example.basketballscoretools.ui.home;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basketballscoretools.R;
import com.example.basketballscoretools.database.DataBaseUtils;

public class Home_StartScoretool extends AppCompatActivity implements View.OnClickListener {

    private static final int VERSON = 1;
    EditText editText;
    EditText editText2;
    Button buttonsave;

    private final int scoreArray[] = {-1, 1, 2, 3};
    private Button btn_a_1, btn_a_2, btn_a_3, btn_b_1, btn_b_2, btn_b_3;
    private Button ResetScore, SaveScore;//重置跟保存战绩
    private TextView textView1, textView2;//数字显示
    private int Start_Tv_1, Start_Tv_2;
    private int temp1, temp2;//用于存放数
    private Button btn_A_returnOne, btn_B_returnOne;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveScore();
    }

    private void saveScore() {
        if (TextUtils.isEmpty(editText.getText().toString()) || TextUtils.isEmpty(editText2.getText().toString())) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put("time","");
        values.put("team_a",editText.getText().toString());
        values.put("team_b",editText2.getText().toString());
        values.put("score_a",textView1.getText().toString());
        values.put("score_b",textView1.getText().toString());
        //database.insert("score",null, values) ;
        int result = DataBaseUtils.save("score",values);
        Toast.makeText(this,result == -1? "保存失败":"保存成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_startscoretool);

        //控件绑定
        Scoreinint();

        editText = findViewById(R.id.Start_left_Et1);
        editText2 = findViewById(R.id.Start_left_Et2);
        buttonsave = findViewById(R.id.Start_left_Save_5);

        final AppCompatActivity activity = this;

        //截图功能
        findViewById(R.id.Start_left_Save_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home_PictureTools.saveScreenshotFromActivity(activity);
                Toast.makeText(Home_StartScoretool.this,"战绩已保存至相册~", Toast.LENGTH_LONG).show();
            }
        });

        /*保存战绩调用自定义view加载效果*/
        findViewById(R.id.Start_Right_SaveScore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* new Progress(Home_StartScoretool.this).show();*/
                Progress progress = new Progress();
                progress.show(getFragmentManager(),"show");
                Toast.makeText(Home_StartScoretool.this, "战绩保存成功~", Toast.LENGTH_SHORT).show();
            }
        });

        //点开始就强制横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


       /* //如果分数变为3位数了，字体就变小
                textView1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() == 3) {
                            textView1.setTextSize(170);
                        }else{
                            textView1.setTextSize(190);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
        textView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 3) {
                    textView2.setTextSize(170);
                }else{
                    textView2.setTextSize(190);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/


        //输入队名自动变大队名字体
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                } else {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editText2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
                } else {
                    editText2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private void Scoreinint() {
        btn_A_returnOne = findViewById(R.id.Start_left_Bt_4);
        btn_a_1 = findViewById(R.id.Start_left_Bt_1);
        btn_a_2 = findViewById(R.id.Start_left_Bt_2);
        btn_a_3 = findViewById(R.id.Start_left_Bt_3);

        btn_B_returnOne = findViewById(R.id.Start_right_Bt_4);
        btn_b_1 = findViewById(R.id.Start_right_Bt_1);
        btn_b_2 = findViewById(R.id.Start_right_Bt_2);
        btn_b_3 = findViewById(R.id.Start_right_Bt_3);

        //输入队名跟显示分数
        textView1 = findViewById(R.id.Start_Tv_1);
        textView2 = findViewById(R.id.Start_Tv_2);

        //重置分数跟保存战绩
        ResetScore = findViewById(R.id.center_Reset_score);
        ResetScore.setOnClickListener(this);
        SaveScore = findViewById(R.id.Start_Right_SaveScore);

        btn_a_1.setOnClickListener(this);
        btn_a_2.setOnClickListener(this);
        btn_a_3.setOnClickListener(this);
        btn_A_returnOne.setOnClickListener(this);

        btn_b_1.setOnClickListener(this);
        btn_b_2.setOnClickListener(this);
        btn_b_3.setOnClickListener(this);
        btn_B_returnOne.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_Reset_score:
                reset();
                break;
            case R.id.Start_left_Bt_1:
                scoreAdd(0, scoreArray[1]);
                break;
            case R.id.Start_left_Bt_2:
                scoreAdd(0, scoreArray[2]);
                break;
            case R.id.Start_left_Bt_3:
                scoreAdd(0, scoreArray[3]);
                break;
            case R.id.Start_right_Bt_1:
                scoreAdd(1, scoreArray[1]);
                break;
            case R.id.Start_right_Bt_2:
                scoreAdd(1, scoreArray[2]);
                break;
            case R.id.Start_right_Bt_3:
                scoreAdd(1, scoreArray[3]);
                break;
            case R.id.Start_left_Bt_4:
                scoreAdd(2, scoreArray[1]);
                break;
            case R.id.Start_right_Bt_4:
                scoreAdd(3, scoreArray[1]);
                break;
            default:
                break;

        }
    }

    //重置功能,弹出提示框
    private void reset() {
        //弹出提示框，提示用户你是否要重置
        AlertDialog.Builder builder = new AlertDialog.Builder(Home_StartScoretool.this);
        builder.setTitle("提示")
                .setIcon(R.drawable.prompt)
                .setMessage("确定要重置吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Start_Tv_1 = 00;
                        Start_Tv_2 = 00;
                        ShowText();
                        dialog.dismiss();
                        ;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //加分
    private void scoreAdd(int Tage, int score) {
        // 0:a  1:b
        if (Tage == 0 || Tage == 1 || Tage == 2 || Tage == 3) {
            if (Tage == 0 && Start_Tv_1 < 105) {
                temp2 = 0;
                temp1 = score;
                Start_Tv_1 += temp1;
            } else if (Tage == 1 && Start_Tv_2 < 105) {
                temp1 = 0;
                temp2 = score;
                Start_Tv_2 += temp2;
            } else if (Tage == 2) {
                if (Start_Tv_1 != 0) {
                    temp1 = 0;
                    temp2 = score;
                    Start_Tv_1 -= temp2;
                }
            } else if (Tage == 3) {
                if (Start_Tv_2 != 0) {
                    temp2 = 0;
                    temp1 = score;
                    Start_Tv_2 -= temp1;
                }
            }
            ShowText();
        }
    }
    //分数居中
    private void ShowText() {
        textView1.setText(String.format("%2s", Integer.toString(Start_Tv_1)));
        textView2.setText(String.format("%2s", Integer.toString(Start_Tv_2)));
    }
}

