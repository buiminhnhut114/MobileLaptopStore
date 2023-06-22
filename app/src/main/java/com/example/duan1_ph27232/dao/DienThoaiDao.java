package com.example.duan1_ph27232.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.NhanVien;
import com.example.duan1_ph27232.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DienThoaiDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public DienThoaiDao(Context context){
        helper = new DBHelper(context);
    }
    public List<SanPham> getDSDienThoai(){
        List<SanPham> spList = new ArrayList<>();
        database = helper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from SANPHAM", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
               SanPham nguoiDung = new SanPham();
                nguoiDung.setMaSP(cursor.getInt(0));
                nguoiDung.setTenSP(cursor.getString(1));
                nguoiDung.setNgaynhap(cursor.getString(2));
                nguoiDung.setSoluong(cursor.getInt(3));
                nguoiDung.setGianhap(cursor.getInt(4));
                nguoiDung.setGiaban(cursor.getInt(5));
                nguoiDung.setMausac(cursor.getString(6));
                nguoiDung.setTenDM(cursor.getString(7));
                nguoiDung.setAnh(cursor.getBlob(8));
                spList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return spList;
    }
    public boolean them_sp(SanPham nguoiDung){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSP", nguoiDung.getTenSP());
        values.put("ngaynhap", nguoiDung.getNgaynhap());
        values.put("soluong", nguoiDung.getSoluong());
        values.put("gianhap",nguoiDung.getGianhap());
        values.put("giaban",nguoiDung.getGiaban());
        values.put("mausac",nguoiDung.getMausac());
        values.put("tenDM",nguoiDung.getTenDM());
        values.put("anh",nguoiDung.getAnh());
        long row = database.insert("SANPHAM", null, values);
        if (row<=0){
            return  false;
        }
            return true;

    }
    public boolean sua_sp(SanPham nguoiDung){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSP", nguoiDung.getTenSP());
        values.put("ngaynhap", nguoiDung.getNgaynhap());
        values.put("soluong", nguoiDung.getSoluong());
        values.put("gianhap",nguoiDung.getGianhap());
        values.put("giaban",nguoiDung.getGiaban());
        values.put("mausac",nguoiDung.getMausac());
        values.put("tenDM",nguoiDung.getTenDM());
        long row = database.update("SANPHAM", values, "maSP = ?", new String[]{String.valueOf(nguoiDung.getMaSP())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }
    }
    public boolean xoa_sp(int id_nguoidung){
        database = helper.getWritableDatabase();
        return database.delete("SANPHAM", "maSP = ?", new String[]{String.valueOf(id_nguoidung)}) >0;
    }
    @SuppressLint("Range")
    public List<SanPham> getData(String sql, String...selectionArgs){
        List<SanPham> list=new ArrayList<>();
        Cursor cursor=database.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            SanPham nguoiDung=new SanPham();
            nguoiDung.setMaSP(cursor.getInt(cursor.getColumnIndex("maSP")));
            nguoiDung.setTenSP(cursor.getString(cursor.getColumnIndex("tenSP")));
            nguoiDung.setNgaynhap(cursor.getString(cursor.getColumnIndex("ngaynhap")));
            nguoiDung.setSoluong(cursor.getInt(cursor.getColumnIndex("soluong")));
            nguoiDung.setGianhap(cursor.getInt(cursor.getColumnIndex("gianhap")));
            nguoiDung.setGiaban(cursor.getInt(cursor.getColumnIndex("giaban")));
            nguoiDung.setMausac(cursor.getString(cursor.getColumnIndex("mausac")));
            nguoiDung.setTenDM(cursor.getString(cursor.getColumnIndex("tenDM")));
            nguoiDung.setAnh(cursor.getBlob(cursor.getColumnIndex("anh")));
            list.add(nguoiDung);

        }
        return list;
    }
    public SanPham getID(String id){
        String sql="SELECT*FROM SANPHAM WHERE maSP=?";
        List<SanPham> list=getData(sql,id);
        return list.get(0);
    }
}

