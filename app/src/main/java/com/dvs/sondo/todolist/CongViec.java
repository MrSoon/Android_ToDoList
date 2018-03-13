package com.dvs.sondo.todolist;

/**
 * Created by SonDo on 3/12/2018.
 */

public class CongViec {
    private int IdCV;
    private String TenCV;
    private String Ngaythang;

    public CongViec(int idCV, String tenCV, String ngaythang) {
        IdCV = idCV;
        TenCV = tenCV;
        Ngaythang = ngaythang;
    }

    public int getIdCV() {
        return IdCV;
    }

    public void setIdCV(int idCV) {
        IdCV = idCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }

    public String getNgaythang() {
        return Ngaythang;
    }

    public void setNgaythang(String ngaythang) {
        Ngaythang = ngaythang;
    }
}
