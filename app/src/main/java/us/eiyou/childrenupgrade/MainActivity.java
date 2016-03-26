package us.eiyou.childrenupgrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import us.eiyou.childrenupgrade.model.Probability;
import us.eiyou.childrenupgrade.utils.SP;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        isPay();
    }


    public void isPay() {
        Bmob.initialize(this, "52c499abafd075320161de647bdf5dfa");
        BmobQuery<Probability> bmobQuery1 = new BmobQuery<>();
        bmobQuery1.getObject(getApplicationContext(), "WtEC666G", new GetListener<Probability>() {
            @Override
            public void onSuccess(Probability probability) {
                Double d_probability = probability.getProbability();
                Log.e("d_probability", d_probability + "");
                if (d_probability == 1.0) {
                    showDialogWrong();
                }
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }

    public void showDialogWrong() {

    }

    //  点击返回按钮后回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
        return true;
    }

    @Override
    protected void onResume() {
        isPay();
        super.onResume();
    }

    @OnClick(R.id.b_login)
    public void onClick() {
        if(etName.getText().toString().equals("qust")&&etPassword.getText().toString().equals("qust")){
            SP.put(getApplicationContext(),"userName",etName.getText().toString());
            SP.put(getApplicationContext(),"userPassword",etPassword.getText().toString());
            startActivity(new Intent(getApplicationContext(), SelectUserActivity.class));
            finish();
        }else {
            Toast.makeText(this, "亲，用户名或密码错误~", Toast.LENGTH_SHORT).show();
        }
    }
}
