package com.iwebsapp.ejemploandroid.expandable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iwebsapp.ejemploandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListView extends AppCompatActivity {
    private android.widget.ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private List<String> listDataHeader;
    private List<String> listText;
    //private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        expandableListView = findViewById(R.id.expandableListView);
        //ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this);
        //expandableListView.setAdapter(adapter);
        initData();
        adapter = new ExpandableListViewAdapter(this, listDataHeader, listText);
        expandableListView.setAdapter(adapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        //listHashMap = new HashMap<>();
        listText = new ArrayList<>();

        listDataHeader.add("Android");
        listDataHeader.add("Xamarin");
        listDataHeader.add("IOS");

        listText.add("Android descripction");
        listText.add("Xamarin description");
        listText.add("IOS description");

        /*List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is Expandable ListView");

        List<String> android = new ArrayList<>();
        android.add("Google maps");
        android.add("Firebase");

        List<String> ios = new ArrayList<>();
        ios.add("ios uno");
        ios.add("ios dos");*/
    }
}
