package com.example.xiewujie.myrecyclerview;

import android.content.Context;


import com.example.multirecyclerview.MultiAdapter;
import com.example.multirecyclerview.ViewHolder;

import java.util.List;

public class Adapter extends MultiAdapter<String> {

    public Adapter(List<String> mList, int layoutId, Context mContext) {
        super(mList, layoutId, mContext);
    }

    @Override
    public void bindView(ViewHolder holder, String item) {
        holder.setText(R.id.view_text,item);
    }
}
