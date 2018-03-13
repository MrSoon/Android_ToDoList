package com.dvs.sondo.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Database database;
    Button btnsave, btnhuy;
    EditText edtname,edtdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnsave = (Button) findViewById(R.id.btnsave);
        btnhuy = (Button) findViewById(R.id.btnhuy);
        edtname = (EditText) findViewById(R.id.edtname);
        edtdate = (EditText) findViewById(R.id.edtdate);

        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               chonngay();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tencv = edtname.getText().toString().trim();
                String ngay = edtdate.getText().toString().trim();
                database = new Database(AddActivity.this, "ghichu.sqlite", null, 1);
                database.QueryData("INSERT INTO CongViec VALUES( null, '"+ tencv +"', '"+ ngay +"')");
                clearIp();
                Toast.makeText(AddActivity.this, "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearIp();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

    }
    public void chonngay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(calendar.DATE);
        int thang = calendar.get(calendar.MONTH);
        int nam = calendar.get(calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtdate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    //clear all text

    public void clearIp(){
        edtdate.getText().clear();
        edtname.getText().clear();
    }
}
