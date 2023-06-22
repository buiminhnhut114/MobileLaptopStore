package com.example.duan1_ph27232.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    DBHelper dbHelper;
    SQLiteDatabase database;
    public NhanVienDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public List<NhanVien> getDSNhanVien(){
        List<NhanVien> nguoiDungList = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from NHANVIEN", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                NhanVien nguoiDung = new NhanVien();
                nguoiDung.setMaNV(cursor.getInt(0));
                nguoiDung.setTenNV(cursor.getString(1));
                nguoiDung.setNgaysinh(cursor.getString(2));
                nguoiDung.setSodienthoai(cursor.getInt(3));
                nguoiDung.setUsername(cursor.getString(4));
                nguoiDung.setPassword(cursor.getString(5));
                nguoiDung.setVaitro(cursor.getString(6));
                nguoiDung.setAnh(cursor.getBlob(7));
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nguoiDungList;
    }
    public boolean them_nhanvien(NhanVien nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNV", nguoiDung.getTenNV());
        values.put("ngaysinh", nguoiDung.getNgaysinh());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("username",nguoiDung.getUsername());
        values.put("password",nguoiDung.getPassword());
        values.put("vaitro",nguoiDung.getVaitro());
        values.put("anh",nguoiDung.getAnh());
        long row = database.insert("NHANVIEN", null, values);
        if (row<=0){
            return  false;
        }
        else {
            return true;
        }

    }
    public boolean sua_NguoiDung(NhanVien nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNV", nguoiDung.getTenNV());
        values.put("ngaysinh", nguoiDung.getNgaysinh());
        values.put("sodienthoai", nguoiDung.getSodienthoai());
        values.put("username",nguoiDung.getUsername());
        values.put("password",nguoiDung.getPassword());
        values.put("vaitro",nguoiDung.getVaitro());
        long row = database.update("NHANVIEN", values, "maNV = ?", new String[]{String.valueOf(nguoiDung.getMaNV())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }

    }
    public boolean xoa_NguoiDung(int id_nguoidung){
        database = dbHelper.getWritableDatabase();
        return database.delete("NHANVIEN", "maNV = ?", new String[]{String.valueOf(id_nguoidung)}) >0;
    }
}
