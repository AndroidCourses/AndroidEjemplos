package com.iwebsapp.ejemploandroid.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.RCLItemView.RCLItemViewActivity;
import com.iwebsapp.ejemploandroid.RCLView.RCLViewActivity;
import com.iwebsapp.ejemploandroid.ViewPagerBtnNext.ViewPagerBtnNext;
import com.iwebsapp.ejemploandroid.expandable.ExpandableActivity;
import com.iwebsapp.ejemploandroid.expandable.ExpandableListView;
import com.iwebsapp.ejemploandroid.navidationview.MainNavigationActivity;
import com.iwebsapp.ejemploandroid.uploadphoto.UploadPhotoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {
        Button btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        Button btnExpandableActivity = findViewById(R.id.btnExpandableActivity);
        Button btnExpandableListView = findViewById(R.id.btnExpandableListView);
        Button btnMainNavigation = findViewById(R.id.btnMainNavigation);
        Button btnRCLItem = findViewById(R.id.btnRCLItem);
        Button btnRCLView = findViewById(R.id.btnRCLView);
        Button btnViewPager = findViewById(R.id.btnViewPager);

        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUploadPhotoActivity();
            }
        });
        btnExpandableActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExpandableActivity();
            }
        });
        btnExpandableListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExpandableListView();
            }
        });
        btnMainNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainNavigation();
            }
        });
        btnRCLItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRCLItem();
            }
        });
        btnRCLView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRCLView();
            }
        });
        btnViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewPager();
            }
        });

    }

    private void showUploadPhotoActivity(){
        Intent intent = new Intent(this, UploadPhotoActivity.class);
        startActivity(intent);
    }
    private void showExpandableActivity(){
        Intent intent = new Intent(this, ExpandableActivity.class);
        startActivity(intent);
    }
    private void showExpandableListView(){
        Intent intent = new Intent(this, ExpandableListView.class);
        startActivity(intent);
    }
    private void showMainNavigation(){
        Intent intent = new Intent(this, MainNavigationActivity.class);
        startActivity(intent);
    }
    private void showRCLItem(){
        Intent intent = new Intent(this, RCLItemViewActivity.class);
        startActivity(intent);
    }
    private void showRCLView(){
        Intent intent = new Intent(this, RCLViewActivity.class);
        startActivity(intent);
    }
    private void showViewPager(){
        Intent intent = new Intent(this, ViewPagerBtnNext.class);
        startActivity(intent);
    }
}
