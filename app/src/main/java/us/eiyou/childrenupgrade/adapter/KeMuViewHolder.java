package us.eiyou.childrenupgrade.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import us.eiyou.childrenupgrade.R;

/**
 * Created by Au on 2016/3/21.
 */
class KeMuViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.b)
    Button b;

    KeMuViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}
