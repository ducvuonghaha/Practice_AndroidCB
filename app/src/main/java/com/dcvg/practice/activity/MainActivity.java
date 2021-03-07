package com.dcvg.practice.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcvg.practice.R;
import com.dcvg.practice.adapter.DataAdapter;
import com.dcvg.practice.dao.DataDAO;
import com.dcvg.practice.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int id = 0;
    public static String content_data = "";
    public static String status_data = "";
    private EditText edtContentData;
    private RadioButton rdJustGenerate;
    private RadioButton rdDoing;
    private RadioButton rdFinish;
    private Button btnAdd;
    private RecyclerView rcvDatas;
    private DataDAO dataDAO = new DataDAO(this);
    ;
    private DataAdapter dataAdapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    ;
    private RadioGroup rdStatus;
    private String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (dataDAO.getAllDatas().size() > 0) {
            dataAdapter = new DataAdapter(this, dataDAO.getAllDatas());
            rcvDatas.setAdapter(dataAdapter);
            rcvDatas.setLayoutManager(linearLayoutManager);
            dataAdapter.notifyDataSetChanged();
        }


        rdStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_just_generate:
                        status = rdJustGenerate.getText().toString().trim();
                        break;
                    case R.id.rd_doing:
                        status = rdDoing.getText().toString().trim();
                        break;
                    case R.id.rd_finish:
                        status = rdFinish.getText().toString().trim();
                        break;
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtContentData.getText().toString().trim().isEmpty() || rdStatus.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Vui lòng điền và chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (dataDAO.insertData(new Data(edtContentData.getText().toString().trim(), status)) > 0) {
                    dataAdapter = new DataAdapter(MainActivity.this, dataDAO.getAllDatas());
                    rcvDatas.setAdapter(dataAdapter);
                    rcvDatas.setLayoutManager(linearLayoutManager);
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        edtContentData = (EditText) findViewById(R.id.edtContentData);
        rdJustGenerate = (RadioButton) findViewById(R.id.rd_just_generate);
        rdDoing = (RadioButton) findViewById(R.id.rd_doing);
        rdFinish = (RadioButton) findViewById(R.id.rd_finish);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rcvDatas = (RecyclerView) findViewById(R.id.rcvDatas);
        rdStatus = (RadioGroup) findViewById(R.id.rdStatus);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        dataDAO = new DataDAO(this);
        switch (item.getItemId()) {
            case 121:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("content", content_data);
                intent.putExtra("status", status_data);
                startActivity(intent);

            case 122:
                if (edtContentData.getText().toString().trim().isEmpty() || rdStatus.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Vui lòng điền và chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (dataDAO.deleteData(id) > 0) {
                    dataAdapter = new DataAdapter(MainActivity.this, dataDAO.getAllDatas());
                    rcvDatas.setAdapter(dataAdapter);
                    rcvDatas.setLayoutManager(linearLayoutManager);
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }

            case 123:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Sửa thông tin");

                Context context = alertDialog.getContext();

                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                alertDialog.setView(layout);


                TextView tvContent_label = new TextView(this);
                tvContent_label.setText("Nội dung");
                tvContent_label.setTypeface(null, Typeface.BOLD);
                layout.addView(tvContent_label);

                EditText edtContent_data_update = new EditText(this);
                edtContent_data_update.setText(content_data);
                layout.addView(edtContent_data_update);


                List<String> stringList = new ArrayList<>();  // here is list
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        stringList.add(rdJustGenerate.getText().toString().trim());
                    } else if (i == 1) {
                        stringList.add(rdDoing.getText().toString().trim());
                    } else {
                        stringList.add(rdFinish.getText().toString().trim());
                    }

                }

                RadioGroup rgStatus_content = new RadioGroup(this);

                for (int i = 0; i < stringList.size(); i++) {
                    RadioButton rb = new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
                    rb.setText(stringList.get(i));
                    if (status_data == stringList.get(i)) {
                        rb.setChecked(true);
                    }
                    rgStatus_content.addView(rb);
                }

                TextView tvContent_status = new TextView(this);
                tvContent_status.setText("Trạng thái");
                tvContent_status.setTypeface(null, Typeface.BOLD);
                layout.addView(tvContent_status);

                layout.addView(rgStatus_content);


                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (edtContent_data_update.getText().toString().trim().isEmpty() || rdStatus.getCheckedRadioButtonId() == -1) {
                                    Toast.makeText(MainActivity.this, "Vui lòng điền và chọn đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                } else if (dataDAO.updateData(new Data(edtContentData.getText().toString().trim(), status), id) > 0) {
                                    dataAdapter = new DataAdapter(MainActivity.this, dataDAO.getAllDatas());
                                    rcvDatas.setAdapter(dataAdapter);
                                    rcvDatas.setLayoutManager(linearLayoutManager);
                                    dataAdapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("Hủy",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

                // closed

                // Showing Alert Message
                alertDialog.show();


        }

        return super.onContextItemSelected(item);
    }
}