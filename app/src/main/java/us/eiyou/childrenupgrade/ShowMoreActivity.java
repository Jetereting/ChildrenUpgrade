package us.eiyou.childrenupgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import us.eiyou.childrenupgrade.utils.SP;

public class ShowMoreActivity extends AppCompatActivity {


    @Bind(R.id.kc1)
    EditText kc1;
    @Bind(R.id.kc1_fraction)
    TextView kc1Fraction;
    @Bind(R.id.kc2)
    EditText kc2;
    @Bind(R.id.kc2_fraction)
    TextView kc2Fraction;
    @Bind(R.id.kc3)
    EditText kc3;
    @Bind(R.id.kc3_fraction)
    TextView kc3Fraction;
    @Bind(R.id.kc4)
    EditText kc4;
    @Bind(R.id.kc4_fraction)
    TextView kc4Fraction;
    @Bind(R.id.kc5)
    EditText kc5;
    @Bind(R.id.kc5_fraction)
    TextView kc5Fraction;
    @Bind(R.id.kc6)
    EditText kc6;
    @Bind(R.id.kc6_fraction)
    TextView kc6Fraction;
    @Bind(R.id.kc7)
    EditText kc7;
    @Bind(R.id.kc7_fraction)
    TextView kc7Fraction;
    @Bind(R.id.kc8)
    EditText kc8;
    @Bind(R.id.kc8_fraction)
    TextView kc8Fraction;
    @Bind(R.id.kc9)
    EditText kc9;
    @Bind(R.id.kc9_fraction)
    TextView kc9Fraction;
    @Bind(R.id.kc10)
    EditText kc10;
    @Bind(R.id.kc10_fraction)
    TextView kc10Fraction;

    ;
    EditText[] editTexts;
    TextView[] textViews;
    @Bind(R.id.layout)
    PercentRelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more);
        ButterKnife.bind(this);
        editTexts = new EditText[]{kc1, kc2, kc3, kc4, kc5, kc6, kc7, kc8, kc9, kc10};
        textViews = new TextView[]{kc1Fraction, kc2Fraction, kc3Fraction, kc4Fraction, kc5Fraction, kc6Fraction, kc7Fraction, kc8Fraction, kc9Fraction, kc10Fraction};
        kcSetTag();
        if (getIntent().getStringExtra("who").equals("user1")) {
            kcName("user1", editTexts);
            kcFraction("user1", textViews);
            setKcName("user1", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user2")) {
            kcName("user2", editTexts);
            kcFraction("user2", textViews);
            setKcName("user2", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user3")) {
            kcName("user3", editTexts);
            kcFraction("user3", textViews);
            setKcName("user3", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user4")) {
            kcName("user4", editTexts);
            kcFraction("user4", textViews);
            setKcName("user4", editTexts);
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                清除焦点
                for (EditText editText : editTexts) {
                    editText.clearFocus();
                }
                for (TextView textView : textViews) {
                    textView.clearFocus();
                }
//                关闭软键盘
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    private void setKcName(final String user, EditText[] editTexts) {
        for (final EditText editText : editTexts) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (editText.getText().toString().length() != 0) {
                            SP.put(getApplicationContext(), user + editText.getTag().toString(), editText.getText().toString());
                            editText.setText(editText.getText().toString());
                            Toast.makeText(ShowMoreActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                            editText.clearFocus();
                        } else {
                            Toast.makeText(getApplicationContext(), "还没有输入课程名呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    private void kcSetTag() {
        kc1.setTag("kc1");
        kc2.setTag("kc2");
        kc3.setTag("kc3");
        kc4.setTag("kc4");
        kc5.setTag("kc5");
        kc6.setTag("kc6");
        kc7.setTag("kc7");
        kc8.setTag("kc8");
        kc9.setTag("kc9");
        kc10.setTag("kc10");

        kc1Fraction.setTag("kc1Fraction");
        kc2Fraction.setTag("kc2Fraction");
        kc3Fraction.setTag("kc3Fraction");
        kc4Fraction.setTag("kc4Fraction");
        kc5Fraction.setTag("kc5Fraction");
        kc6Fraction.setTag("kc6Fraction");
        kc7Fraction.setTag("kc7Fraction");
        kc8Fraction.setTag("kc8Fraction");
        kc9Fraction.setTag("kc9Fraction");
        kc10Fraction.setTag("kc10Fraction");
    }

    private void kcName(String user, EditText[] editTexts) {
        for (EditText editText : editTexts) {
            if (!SP.getString(getApplicationContext(), user + editText.getTag().toString()).equals("")) {
                editText.setText(SP.getString(getApplicationContext(), user + editText.getTag().toString()));
            }
        }
    }

    private void kcFraction(String user, TextView[] textViews) {
        int userFraction=0;
        for (TextView textView : textViews) {
            if (SP.getInt(getApplicationContext(), user + textView.getTag().toString())!=0) {
                textView.setText(SP.getInt(getApplicationContext(), user + textView.getTag().toString()) + "");
                userFraction+=Integer.parseInt(textView.getText().toString());
            }
        }
        SP.put(getApplicationContext(),(user+"Fraction"),userFraction);
        Log.d("ShowMoreActivity", SP.getInt(getApplicationContext(),(user+"Fraction"))+"");
    }

    @OnClick({R.id.kc1_fraction, R.id.kc2_fraction, R.id.kc3_fraction, R.id.kc4_fraction, R.id.kc5_fraction, R.id.kc6_fraction, R.id.kc7_fraction, R.id.kc8_fraction, R.id.kc9_fraction, R.id.kc10_fraction})
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),MoreDetailActivity.class).putExtra("which",getIntent().getStringExtra("who")+view.getTag().toString()));
    }

    @Override
    protected void onResume() {
        if (getIntent().getStringExtra("who").equals("user1")) {
            kcName("user1", editTexts);
            kcFraction("user1", textViews);
            setKcName("user1", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user2")) {
            kcName("user2", editTexts);
            kcFraction("user2", textViews);
            setKcName("user2", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user3")) {
            kcName("user3", editTexts);
            kcFraction("user3", textViews);
            setKcName("user3", editTexts);
        } else if (getIntent().getStringExtra("who").equals("user4")) {
            kcName("user4", editTexts);
            kcFraction("user4", textViews);
            setKcName("user4", editTexts);
        }
        super.onResume();
    }
}
