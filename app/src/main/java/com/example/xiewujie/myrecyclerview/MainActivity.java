package com.example.xiewujie.myrecyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multirecyclerview.ItemDecoration;
import com.example.multirecyclerview.MultiAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private RecyclerView recyclerView;
   private List<String> mList = new ArrayList<>();
   private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(mList,R.layout.recycler_view_item,this);
        recyclerView.setAdapter(adapter);
        initData();
        adapter.setOnClickListener(new MultiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               TextView textView = (TextView)view.findViewById(R.id.view_text);
               if (textView!=null)
               textView.append("已点击"+position);
            }
        });

        adapter.setOnLongClickListener(new MultiAdapter.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"已长按"+position,Toast.LENGTH_LONG).show();
                return true;            //说明已消费这个长按事件
            }
        });
    }

    private void initData(){
        for (int i = 0;i<50;i++){
            mList.add(i+"");
        }
        adapter.notifyDataSetChanged();
        ItemDecoration decoration = new ItemDecoration();
        decoration.setDividingLine(Color.BLUE,10,true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p_1);
        decoration.setBackground(bitmap,100,true);
        recyclerView.addItemDecoration(decoration);
        adapter.addHeaderView(R.layout.header_item);
        adapter.addFooterView(R.layout.header_item);
    }
}
