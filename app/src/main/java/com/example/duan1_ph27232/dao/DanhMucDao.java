package com.example.duan1_ph27232.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.DanhMuc;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DanhMucDao {
    DBHelper dbHelper;
    SQLiteDatabase database;
    public DanhMucDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public List<DanhMuc> getDSDanhMuc(){
        List<DanhMuc> danhmucList = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from DANHMUC", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                DanhMuc nguoiDung = new DanhMuc();
                nguoiDung.setMaDM(cursor.getInt(0));
                nguoiDung.setTenDM(cursor.getString(1));
                nguoiDung.setAnh(cursor.getBlob(2));
                danhmucList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return danhmucList;
    }
    public boolean them_danhmuc(DanhMuc nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDM", nguoiDung.getTenDM());
        values.put("ANH",nguoiDung.getAnh());
        long row = database.insert("DANHMUC", null, values);
        if (row<=0){
            return  false;
        }
        return true;
    }
    public boolean sua_danhmuc(DanhMuc nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDM", nguoiDung.getTenDM());
        long row = database.update("DANHMUC", values, "maDM = ?", new String[]{String.valueOf(nguoiDung.getMaDM())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }

    }
    public boolean xoa_damuc(int id_nguoidung){
        database = dbHelper.getWritableDatabase();
        return database.delete("DANHMUC", "maDM = ?", new String[]{String.valueOf(id_nguoidung)}) >0;
    }
}
