package us.eiyou.childrenupgrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import us.eiyou.childrenupgrade.adapter.KeMuAdapter;
import us.eiyou.childrenupgrade.utils.SP;

public class MoreDetailActivity extends AppCompatActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.layout)
    PercentRelativeLayout layout;

    KeMuAdapter keMuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detail);
        ButterKnife.bind(this);

        final ArrayList<String> strings = new ArrayList<>();
        final ArrayList<Integer> integers = new ArrayList<>();

        String getStrings=SP.getString(getApplicationContext(),getIntent().getStringExtra("which")+"String").replace("null","");
        String getIntegers=SP.getString(getApplicationContext(),getIntent().getStringExtra("which")+"Int").replace("null","");

        for (int i = 0; i < getStrings.split("#").length; i++) {
            if(!"".equals(getStrings.split("#")[i])) {
                strings.add(getStrings.split("#")[i]);
                integers.add(Integer.parseInt(getIntegers.split("#")[i]));
            }
        }

        keMuAdapter=new KeMuAdapter(getApplicationContext(), strings, integers,getIntent().getStringExtra("which"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(keMuAdapter);

    }

}
