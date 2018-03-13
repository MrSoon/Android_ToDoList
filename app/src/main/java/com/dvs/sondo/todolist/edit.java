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

public class edit extends AppCompatActivity {

    Database database;
    EditText edtEditname, edtEditdate;
    Button btnEditsave, btnEdithuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        anhsa();




        //nhan data tu intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");

        final int Id = bundle.getInt("Id");
         String tencv = bundle.getString("Tencv");
         String ngaythang = bundle.getString("ngayThang");

        edtEditname.setText(tencv);
        edtEditdate.setText(ngaythang);

        edtEditdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonngay();
            }
        });
        //SAVE click
        btnEditsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tencvmoi = edtEditname.getText().toString().trim();
                String ngaymoi = edtEditdate.getText().toString().trim();

                //khai bao database
                database = new Database(edit.this, "ghichu.sqlite", null, 1);
                database.QueryData("UPDATE CongViec SET TenCV = '"+ tencvmoi +"', NgayLam = '"+ ngaymoi +"' WHERE Id = '"+ Id +"'");
                Toast.makeText(edit.this, "Cập Nhật Thành Công !", Toast.LENGTH_SHORT).show();


                backActivityMain();
            }
        });

        //HUY click
        btnEdithuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backActivityMain();
            }
        });
    }

    private void backActivityMain(){
        Intent intent = new Intent(edit.this, MainActivity.class);
        startActivity(intent);
    }
    private void anhsa(){
        edtEditdate = (EditText) findViewById(R.id.edtEditdate);
        edtEditname = (EditText) findViewById(R.id.edtEditname);
        btnEditsave = (Button) findViewById(R.id.btnEditsave);
        btnEdithuy = (Button) findViewById(R.id.btnEdithuy);
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
                edtEditdate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }


}
