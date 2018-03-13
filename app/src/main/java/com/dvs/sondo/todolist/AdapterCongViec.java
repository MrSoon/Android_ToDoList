package com.dvs.sondo.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SonDo on 3/12/2018.
 */

public class AdapterCongViec extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CongViec> congViecList;

    public AdapterCongViec(Context context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtdateshow;
        ImageView imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTen = (TextView) view.findViewById(R.id.txtTen);
            holder.txtdateshow = (TextView) view.findViewById(R.id.txtdateshow);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        final CongViec congViec = congViecList.get(i);
        holder.txtTen.setText(congViec.getTenCV());
        holder.txtdateshow.setText(congViec.getNgaythang());

        //click to edit or remove note
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, edit.class );
                Bundle bundle = new Bundle();
                bundle.putInt("Id", congViec.getIdCV());
                bundle.putString("Tencv", congViec.getTenCV());
                bundle.putString("ngayThang", congViec.getNgaythang());
                intent.putExtra("data", bundle);

                view.getContext().startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeleteActivity.class );
                Bundle bundle = new Bundle();
                bundle.putInt("Id", congViec.getIdCV());
                bundle.putString("Tencv", congViec.getTenCV());
                bundle.putString("ngayThang", congViec.getNgaythang());
                intent.putExtra("data", bundle);

                view.getContext().startActivity(intent);
            }
        });


        return view;
    }


}
