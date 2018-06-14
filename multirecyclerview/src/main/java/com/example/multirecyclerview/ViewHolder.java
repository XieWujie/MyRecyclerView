package com.example.multirecyclerview;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private  SparseArray<View> sparseArray;
    public ViewHolder(View itemView) {
        super(itemView);
        sparseArray = new SparseArray<>();
    }

    public ViewHolder setText(int viewId,CharSequence content){
        TextView view = getView(viewId);
        view.setText(content.toString());
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resourceId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public <T extends View> T getView(int viewId){
        View view = sparseArray.get(viewId);
        if (view==null){
            view = itemView.findViewById(viewId);
                        sparseArray.put(viewId,view);
        }
        return (T)view;
    }

    public void setVisibility(int viewId,int visibility){
        getView(viewId).setVisibility(visibility);
    }
}
