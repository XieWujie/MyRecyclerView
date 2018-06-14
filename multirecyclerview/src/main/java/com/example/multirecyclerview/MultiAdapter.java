package com.example.multirecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class MultiAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected List<T> mList;
    protected int layoutId;
    protected Context mContext;
    protected LayoutInflater inflater;
    protected OnItemClickListener onItemClickListener;
    protected OnLongClickListener onLongClickListener;
    public static final int HEADER_TYPE = 0;
    public static final int FOOTER_TYPE = 1;
    public static final int NOMAL_TYPE = 2;
    protected  boolean isHaveHeader = false;
    protected boolean isHaveFooter = false;
    protected int headerLayoutId;
    protected int footerLayoutId;
    public MultiAdapter(List<T> mList, int layoutId, Context mContext) {
        this.mList = mList;
        this.layoutId = layoutId;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int realLayoutId = layoutId;
        if (viewType==HEADER_TYPE){
            realLayoutId = headerLayoutId;
        }else if (viewType==FOOTER_TYPE){
            realLayoutId = footerLayoutId;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(realLayoutId,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
         final int realPosition = getRealPosition(holder);
        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,realPosition);
                }
            });
        }
        if (onLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onLongClickListener.onLongClick(v,realPosition);
                }
            });
        }
        if (realPosition<0||(isHaveFooter&&position==getItemCount()-1))return;
        bindView(holder,mList.get(realPosition));
    }

    @Override
    public int getItemViewType(int position) {
        if (isHaveHeader){
            if (position==0){
                return HEADER_TYPE;
            }
        }
        if (isHaveFooter){
            if (position==getItemCount()-1){
                return FOOTER_TYPE;
            }
        }
        return NOMAL_TYPE;
    }

    public void addHeaderView(int layoutId){
        headerLayoutId = layoutId;
        isHaveHeader = true;
    }

    public void addFooterView(int layoutId){
        footerLayoutId = layoutId;
        isHaveFooter = true;
    }

    @Override
    public int getItemCount() {
        int m = 0;
        if (isHaveHeader)m++;
        if (isHaveFooter)m++;
        return mList.size()+m;
    }

    public int getRealPosition(ViewHolder holder){
        int position = holder.getLayoutPosition();
        return isHaveHeader?position-1:position;
    }

    public abstract void bindView(ViewHolder holder,T item);

    public void setOnClickListener(OnItemClickListener onClickListener){
        onItemClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnLongClickListener{
        boolean onLongClick(View view,int position);
    }
}
