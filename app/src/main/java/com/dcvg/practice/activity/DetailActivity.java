package com.dcvg.practice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dcvg.practice.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvContentData;
    private TextView tvStatusData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        Intent intent = getIntent();
        tvContentData.setText(intent.getStringExtra("content"));
        tvStatusData.setText(intent.getStringExtra("status"));
    }

    private void initView() {
        tvContentData = (TextView) findViewById(R.id.tvContent_data);
        tvStatusData = (TextView) findViewById(R.id.tvStatus_data);
    }
}