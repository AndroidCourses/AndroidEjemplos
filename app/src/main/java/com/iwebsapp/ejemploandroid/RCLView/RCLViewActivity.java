package com.iwebsapp.ejemploandroid.RCLView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.RCLView.adapter.MyAdapter;
import com.iwebsapp.ejemploandroid.RCLView.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RCLViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rclview);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setData();
    }

    private void setData() {
        for (int i=0; i<20; i++){
            if (i%2==0){
                Item item = new Item("This is item " + (i+1), "This is child item " + (i+1), true);
                items.add(item);
            }else {
                Item item = new Item("This is item " + (i+1), " ", false);
                items.add(item);
            }

            MyAdapter adapter = new MyAdapter(items);
            recyclerView.setAdapter(adapter);
        }
    }

}
