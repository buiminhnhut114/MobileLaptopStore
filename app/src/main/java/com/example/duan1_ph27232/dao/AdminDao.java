package com.example.duan1_ph27232.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private DBHelper helper;
    private SQLiteDatabase database;

    public AdminDao(Context context) {
        helper = new DBHelper(context);
    }

    public List<Admin> getALL_nguoidung(){
        List<Admin> nguoiDungList = new ArrayList<>();
        database = helper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from ADMIN", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                Admin nguoiDung = new Admin();
                nguoiDung.setMaAM(cursor.getInt(0));
                nguoiDung.setTenAM(cursor.getString(1));
                nguoiDung.setTenDN(cursor.getString(2));
                nguoiDung.setMatkhau(cursor.getString(3));
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nguoiDungList;
    }

    public boolean them_NguoiDung(Admin nguoiDung){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenAM", nguoiDung.getTenAM());
        values.put("tenDN", nguoiDung.getTenDN());
        values.put("matkhau", nguoiDung.getMatkhau());
        long row = database.insert("ADMIN", null, values);
        if (row<=0){
            return  false;
        }
        return true;
    }
    public boolean sua_NguoiDung(Admin nguoiDung){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenAM", nguoiDung.getTenAM());
        values.put("tenDN", nguoiDung.getTenDN());
        values.put("matkhau", nguoiDung.getMatkhau());
        long row = database.update("ADMIN", values, "maAM = ?", new String[]{String.valueOf(nguoiDung.getMaAM())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }
    }
}
