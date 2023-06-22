package com.example.duan1_ph27232.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.KhuyenMai;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDao {
    DBHelper dbHelper;
    SQLiteDatabase database;
    public KhuyenMaiDao(Context context){
        dbHelper = new DBHelper(context);
    }
    public List<KhuyenMai> getDSKhuyenmai(){
        List<KhuyenMai> nguoiDungList = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        Cursor cursor =database.rawQuery("select * from KHUYENMAI", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                KhuyenMai nguoiDung = new KhuyenMai();
                nguoiDung.setMaKM(cursor.getInt(0));
                nguoiDung.setChietkhau(cursor.getInt(1));
                nguoiDung.setTungay(cursor.getString(2));
                nguoiDung.setDenngay(cursor.getString(3));
                nguoiDung.setAnh(cursor.getBlob(4));
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nguoiDungList;
    }
    public boolean them_khuyenmai(KhuyenMai nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("chietkhau", nguoiDung.getChietkhau());
        values.put("tungay", nguoiDung.getTungay());
        values.put("denngay", nguoiDung.getDenngay());
        values.put("anh",nguoiDung.getAnh());
        long row = database.insert("KHUYENMAI", null, values);
        if (row<=0){
            return  false;
        }
        else {
            return true;
        }

    }
    public boolean sua_NguoiDung(KhuyenMai nguoiDung){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("chietkhau", nguoiDung.getChietkhau());
        values.put("tungay", nguoiDung.getTungay());
        values.put("denngay", nguoiDung.getDenngay());
        long row = database.update("KHUYENMAI", values, "maKM = ?", new String[]{String.valueOf(nguoiDung.getMaKM())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }
    }
    public boolean xoa_NguoiDung(int id_nguoidung){
        database = dbHelper.getWritableDatabase();
        return database.delete("KHUYENMAI", "maKM = ?", new String[]{String.valueOf(id_nguoidung)}) >0;
    }
}
