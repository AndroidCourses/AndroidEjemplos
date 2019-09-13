package com.iwebsapp.ejemploandroid.RCLItemView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.RCLItemView.adapter.MyAdapterRecyclerView;
import com.iwebsapp.ejemploandroid.RCLItemView.model.ItemRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RCLItemViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<ItemRecyclerView> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rclitem_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setData();
    }

    private void setData() {
        for (int i=0; i<20; i++){
            ItemRecyclerView itemRecyclerView = new ItemRecyclerView("This is question " + (i+1), "This is asnswer " + (i+1));
            items.add(itemRecyclerView);
        }

        MyAdapterRecyclerView adapter = new MyAdapterRecyclerView(items);
        recyclerView.setAdapter(adapter);

    }
}
