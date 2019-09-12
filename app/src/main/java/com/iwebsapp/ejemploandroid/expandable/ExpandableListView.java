package com.iwebsapp.ejemploandroid.expandable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iwebsapp.ejemploandroid.R;

public class ExpandableListView extends AppCompatActivity {
    android.widget.ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        expandableListView = findViewById(R.id.expandableListView);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this);
        expandableListView.setAdapter(adapter);
    }
}
