package com.dvs.sondo.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    Database database;
    TextView txtname, txtdate;
    Button btnyes, btnno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        txtname = (TextView) findViewById(R.id.txttencv);
        txtdate = (TextView) findViewById(R.id.txtngaythang);
        btnno = (Button) findViewById(R.id.btnno);
        btnyes = (Button) findViewById(R.id.btnyes);

        //get data in intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        final int Id = bundle.getInt("Id");
        String tencv = bundle.getString("Tencv");
        String ngaythang = bundle.getString("ngayThang");

        txtname.setText(tencv);
        txtdate.setText(ngaythang);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //khai bao database
                database = new Database(DeleteActivity.this, "ghichu.sqlite", null, 1);
                database.QueryData("DELETE FROM CongViec WHERE Id = '"+ Id +"'");
                Toast.makeText(DeleteActivity.this, "Xóa Thành Công !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteActivity.this, MainActivity.class));
            }
        });

        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteActivity.this, MainActivity.class));
            }
        });


    }
}
