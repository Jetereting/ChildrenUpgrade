package us.eiyou.childrenupgrade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import us.eiyou.childrenupgrade.R;
import us.eiyou.childrenupgrade.utils.SP;

/**
 * Created by Au on 2016/3/21.
 */
public class KeMuAdapter extends RecyclerView.Adapter<KeMuViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    ArrayList<String> strings;
    ArrayList<Integer> integers;
    String tag;

    public KeMuAdapter(Context context, ArrayList<String> strings, ArrayList<Integer> integers,String tag) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.strings = strings;
        this.integers = integers;
        this.tag=tag;
        integers.add(0);
    }

    @Override
    public KeMuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KeMuViewHolder(mLayoutInflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final KeMuViewHolder holder, final int position) {
        if (position != strings.size()) {
            holder.et.setText(strings.get(position));
            holder.et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        strings.set(position,holder.et.getText().toString());
                        String setStrings="";
                        for (int i = 0; i < strings.size(); i++) {
                            setStrings+=strings.get(i)+"#";
                        }
                        SP.put(mContext, (tag + "String"),setStrings);
                        Toast.makeText(mContext, "修改成功！", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

            holder.tv.setText(integers.get(position) + "");
        } else {
            holder.et.setText("");
            holder.et.setEnabled(true);
            holder.tv.setText(0 + "");
            integers.set(position, 0);

            holder.et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == 0) {
                        if (holder.et.getText().toString().length() != 0 && !holder.tv.getText().toString().equals("0")) {
                            for (int i = 0; i < integers.size(); i++) {
                                if (integers.get(i) == 0) {
                                    integers.remove(i);
                                }
                            }
                            strings.add(holder.et.getText().toString());
                            integers.add(Integer.parseInt(holder.tv.getText().toString()));

                            String setStrings = "";
                            String setIntegers="";
                            int fraction=0;
                            for (int i = 0; i < strings.size(); i++) {
                                setStrings+=strings.get(i)+"#";
                                setIntegers+=integers.get(i)+"#";
                                fraction+=integers.get(i);
                            }
                            SP.put(mContext,(tag+"String"),setStrings);
                            SP.put(mContext,(tag+"Int"),setIntegers);
                            SP.put(mContext,tag,fraction);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "还没有输入原因或者增加分数呢！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        }
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integers.set(position, (integers.get(position) + 1));
                holder.tv.setText("" + integers.get(position));
                Log.d("KeMuAdapter", "integers:" + integers);
                String setIntegers="";
                int fraction=0;
                for (int i = 0; i < strings.size(); i++) {
                    setIntegers+=integers.get(i)+"#";
                    fraction+=integers.get(i);
                }
                SP.put(mContext, (tag + "Int"),setIntegers);
                SP.put(mContext,tag,fraction);
            }
        });
    }


    @Override
    public int getItemCount() {
        return strings.size() + 1;
    }
}
