package com.dvs.sondo.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCV;
    AdapterCongViec adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvCongViec = (ListView) findViewById(R.id.lvCongviec);
        arrayCV = new ArrayList<>();

        adapter = new AdapterCongViec(this, R.layout.dongcv, arrayCV);
        lvCongViec.setAdapter(adapter);


        CreateSQL();

        //get data
        Cursor dataCV = database.GetData("SELECT * FROM CongViec");

        while(dataCV.moveToNext()){
            int id = dataCV.getInt(0);
            String namecv = dataCV.getString(1);
            String ngay = dataCV.getString(2);

            arrayCV.add(new CongViec(id, namecv, ngay));

        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreateSQL(){
        database = new Database(this, "ghichu.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec ( Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR, NgayLam DATE )");
        //insert data
        //database.QueryData("INSERT INTO CongViec VALUES( null, 'Hoc Android', '2018/3/12')");
    }

    //Create DiaLogXoaCV


}
