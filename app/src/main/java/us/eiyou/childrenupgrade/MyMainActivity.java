package us.eiyou.childrenupgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import us.eiyou.childrenupgrade.utils.Contants;
import us.eiyou.childrenupgrade.utils.SP;

public class MyMainActivity extends AppCompatActivity {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.lv)
    TextView lv;
    @Bind(R.id.fraction)
    TextView fraction;
    @Bind(R.id.lave)
    TextView lave;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.promise)
    TextView promise;
    @Bind(R.id.promise_edit)
    EditText promiseEdit;
    @Bind(R.id.praise)
    TextView praise;
    @Bind(R.id.praise_edit)
    EditText praiseEdit;
    @Bind(R.id.layout)
    PercentRelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        ButterKnife.bind(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setInfo();

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowMoreActivity.class).putExtra("who", getIntent().getStringExtra("who")));
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                清除焦点
                promiseEdit.setText("");
                promiseEdit.clearFocus();

                praiseEdit.setText("");
                praiseEdit.clearFocus();

//                关闭软键盘
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    private void setInfo() {
        if (getIntent().getStringExtra("who").equals("user1")) {
            avatar.setImageResource(R.drawable.avatar1);
            name.setText(SP.getString(getApplicationContext(), "user1Name"));
            int fractionInt=SP.getInt(getApplicationContext(), "user1Fraction");
            fraction.setText("总分：" + fractionInt);
            int lvInt=fractionInt/Contants.upgradeFraction;
            lv.setText("lv" + lvInt);
            int progressInt=fractionInt-lvInt*Contants.upgradeFraction;
            progress.setProgress(progressInt);
            double laveDouble=(1-(double)progressInt/Contants.upgradeFraction)*100;
            lave.setText("还差" +Math.rint(laveDouble)+ "%就要升级了，加油！");
            promise.setText(SP.getString(getApplicationContext(), "user1Promise"));
            praise.setText(SP.getString(getApplicationContext(), "user1Praise"));
            promiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (promiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user1Promise", SP.getString(getApplicationContext(), "user1Promise") + "\n" + promiseEdit.getText().toString());
                            promise.setText(SP.getString(getApplicationContext(), "user1Promise"));
                            promiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入承诺呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
            praiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (praiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user1Praise", SP.getString(getApplicationContext(), "user1Praise") + "\n" + praiseEdit.getText().toString());
                            praise.setText(SP.getString(getApplicationContext(), "user1Praise"));
                            praiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入奖励呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        } else if (getIntent().getStringExtra("who").equals("user2")) {
            avatar.setImageResource(R.drawable.avatar1);
            name.setText(SP.getString(getApplicationContext(), "user2Name"));
            int fractionInt=SP.getInt(getApplicationContext(), "user2Fraction");
            fraction.setText("总分：" + fractionInt);
            int lvInt=fractionInt/Contants.upgradeFraction;
            lv.setText("lv" + lvInt);
            int progressInt=fractionInt-lvInt*Contants.upgradeFraction;
            progress.setProgress(progressInt);
            double laveDouble=(1-(double)progressInt/Contants.upgradeFraction)*100;
            lave.setText("还差" + Math.rint(laveDouble) + "%就要升级了，加油！");
            promise.setText(SP.getString(getApplicationContext(), "user2Promise"));
            praise.setText(SP.getString(getApplicationContext(), "user2Praise"));
            promiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (promiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user2Promise", SP.getString(getApplicationContext(), "user2Promise") + "\n" + promiseEdit.getText().toString());
                            promise.setText(SP.getString(getApplicationContext(), "user2Promise"));
                            promiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入承诺呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
            praiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (praiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user2Praise", SP.getString(getApplicationContext(), "user2Praise") + "\n" + praiseEdit.getText().toString());
                            praise.setText(SP.getString(getApplicationContext(), "user2Praise"));
                            praiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入奖励呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        } else if (getIntent().getStringExtra("who").equals("user3")) {
            avatar.setImageResource(R.drawable.avatar1);
            name.setText(SP.getString(getApplicationContext(), "user3Name"));
            int fractionInt=SP.getInt(getApplicationContext(), "user3Fraction");
            fraction.setText("总分：" + fractionInt);
            int lvInt=fractionInt/Contants.upgradeFraction;
            lv.setText("lv" + lvInt);
            int progressInt=fractionInt-lvInt*Contants.upgradeFraction;
            progress.setProgress(progressInt);
            double laveDouble=(1-(double)progressInt/Contants.upgradeFraction)*100;
            lave.setText("还差" + Math.rint(laveDouble) + "%就要升级了，加油！");
            promise.setText(SP.getString(getApplicationContext(), "user3Promise"));
            praise.setText(SP.getString(getApplicationContext(), "user3Praise"));
            promiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (promiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user3Promise", SP.getString(getApplicationContext(), "user3Promise") + "\n" + promiseEdit.getText().toString());
                            promise.setText(SP.getString(getApplicationContext(), "user3Promise"));
                            promiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入承诺呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
            praiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (praiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user3Praise", SP.getString(getApplicationContext(), "user3Praise") + "\n" + praiseEdit.getText().toString());
                            praise.setText(SP.getString(getApplicationContext(), "user3Praise"));
                            praiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入奖励呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        } else if (getIntent().getStringExtra("who").equals("user4")) {
            avatar.setImageResource(R.drawable.avatar1);
            name.setText(SP.getString(getApplicationContext(), "user4Name"));
            int fractionInt=SP.getInt(getApplicationContext(), "user4Fraction");
            fraction.setText("总分：" + fractionInt);
            int lvInt=fractionInt/Contants.upgradeFraction;
            lv.setText("lv" + lvInt);
            int progressInt=fractionInt-lvInt*Contants.upgradeFraction;
            progress.setProgress(progressInt);
            double laveDouble=(1-(double)progressInt/Contants.upgradeFraction)*100;
            lave.setText("还差" + Math.rint(laveDouble) + "%就要升级了，加油！");
            promise.setText(SP.getString(getApplicationContext(), "user4Promise"));
            praise.setText(SP.getString(getApplicationContext(), "user4Praise"));
            promiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (promiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user4Promise", SP.getString(getApplicationContext(), "user4Promise") + "\n" + promiseEdit.getText().toString());
                            promise.setText(SP.getString(getApplicationContext(), "user4Promise"));
                            promiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入承诺呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
            praiseEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (praiseEdit.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), "user4Praise", SP.getString(getApplicationContext(), "user4Praise") + "\n" + praiseEdit.getText().toString());
                            praise.setText(SP.getString(getApplicationContext(), "user4Praise"));
                            praiseEdit.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入奖励呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        }

    }

    @Override
    protected void onResume() {
        setInfo();
        super.onResume();
    }
}
