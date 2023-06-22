package com.example.duan1_ph27232.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.KhachHang;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    DBHelper dbHelper;
    SQLiteDatabase database;
    public KhachHangDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public List<KhachHang> getDSKhachHang(){
        List<KhachHang> khachhangList = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from KHACHHANG", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                KhachHang nguoiDung = new KhachHang();
                nguoiDung.setMaKH(cursor.getInt(0));
                nguoiDung.setTenKH(cursor.getString(1));
                nguoiDung.setDiachi(cursor.getString(2));
                nguoiDung.setEmail(cursor.getString(3));
                nguoiDung.setSodienthoai(cursor.getInt(4));
                nguoiDung.setAnh(cursor.getBlob(5));
                khachhangList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return khachhangList;
    }
    public boolean them_kh(KhachHang nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKH", nguoiDung.getTenKH());
        values.put("diachi", nguoiDung.getDiachi());
        values.put("email",nguoiDung.getEmail());
        values.put("sodienthoai",nguoiDung.getSodienthoai());
        values.put("anh",nguoiDung.getAnh());
        long row = database.insert("KHACHHANG", null, values);
        if (row<=0){
            return  false;
        }else {
            return true;
        }

    }
    public boolean sua_kh(KhachHang nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", nguoiDung.getMaKH());
        values.put("tenKH", nguoiDung.getTenKH());
        values.put("diachi", nguoiDung.getDiachi());
        values.put("email",nguoiDung.getEmail());
        values.put("sodienthoai",nguoiDung.getSodienthoai());
        long row = database.update("KHACHHANG", values, "maKH = ?", new String[]{String.valueOf(nguoiDung.getMaKH())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }
    }
    public boolean xoa_kh(int id_nguoidung){
        database = dbHelper.getWritableDatabase();
        return database.delete("KHACHHANG", "maKH = ?", new String[]{String.valueOf(id_nguoidung)}) >0;
    }
}
