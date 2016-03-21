package us.eiyou.childrenupgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import us.eiyou.childrenupgrade.utils.SP;

public class SelectUserActivity extends AppCompatActivity {

    @Bind(R.id.user1)
    ImageView user1;
    @Bind(R.id.user2)
    ImageView user2;
    @Bind(R.id.user3)
    ImageView user3;
    @Bind(R.id.user4)
    ImageView user4;
    @Bind(R.id.user1_name)
    EditText user1Name;
    @Bind(R.id.user2_name)
    EditText user2Name;
    @Bind(R.id.user3_name)
    EditText user3Name;
    @Bind(R.id.user4_name)
    EditText user4Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        ButterKnife.bind(this);
        haveName();
        setName();
    }

    private void haveName() {
        if (!SP.getString(getApplicationContext(), "user1Name").equals("")) {
            user1Name.setText(SP.getString(getApplicationContext(), "user1Name"));
        }
        if (!SP.getString(getApplicationContext(), "user2Name").equals("")) {
            user2Name.setText(SP.getString(getApplicationContext(), "user2Name"));
        }
        if (!SP.getString(getApplicationContext(), "user3Name").equals("")) {
            user3Name.setText(SP.getString(getApplicationContext(), "user3Name"));
        }
        if (!SP.getString(getApplicationContext(), "user4Name").equals("")) {
            user4Name.setText(SP.getString(getApplicationContext(), "user4Name"));
        }
    }

    private void setName() {
        user1Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SP.put(getApplicationContext(), "user1Name", user1Name.getText().toString());
                }
            }
        });
        user2Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SP.put(getApplicationContext(), "user2Name", user2Name.getText().toString());
                }
            }
        });
        user3Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SP.put(getApplicationContext(), "user3Name", user3Name.getText().toString());
                }
            }
        });
        user4Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SP.put(getApplicationContext(), "user4Name", user4Name.getText().toString());
                }
            }
        });
    }

    @OnClick({R.id.user1, R.id.user2, R.id.user3, R.id.user4,R.id.layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user1:
                startActivity(new Intent(getApplicationContext(), MyMainActivity.class).putExtra("who", "user1"));
                break;
            case R.id.user2:
                startActivity(new Intent(getApplicationContext(), MyMainActivity.class).putExtra("who", "user2"));
                break;
            case R.id.user3:
                startActivity(new Intent(getApplicationContext(), MyMainActivity.class).putExtra("who", "user3"));
                break;
            case R.id.user4:
                startActivity(new Intent(getApplicationContext(), MyMainActivity.class).putExtra("who", "user4"));
                break;
            case R.id.layout:
//                清除焦点
                user1Name.clearFocus();
                user2Name.clearFocus();
                user3Name.clearFocus();
                user4Name.clearFocus();
//                关闭软键盘
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }
}
