package com.example.duan1_ph27232.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_ph27232.DBHelper.DBHelper;
import com.example.duan1_ph27232.model.HoaDon;
import com.example.duan1_ph27232.model.SanPham;
import com.example.duan1_ph27232.model.Top10;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    public HoaDonDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<HoaDon> getDSHoaDon(){
        ArrayList<HoaDon> list = new ArrayList<>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADON ",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                HoaDon hoaDon=new HoaDon();
                hoaDon.setMaHD(cursor.getInt(0));
                hoaDon.setMaSP(cursor.getInt(1));
                hoaDon.setTenSP(cursor.getString(2));
                hoaDon.setGiaban(cursor.getInt(3));
                hoaDon.setMausac(cursor.getString(4));
                hoaDon.setSoluong(cursor.getInt(5));
                hoaDon.setTenKH(cursor.getString(6));
                hoaDon.setSodienthoai(cursor.getInt(7));
                hoaDon.setDiachi(cursor.getString(8));
                hoaDon.setChietkhau(cursor.getInt(9));
                hoaDon.setNgaytao(cursor.getString(10));
                hoaDon.setTrangthai(cursor.getString(11));
                hoaDon.setThanhtoan(cursor.getInt(12));
                hoaDon.setMaKH(cursor.getInt(13));
                list.add(hoaDon);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean thayDoiTrangThai(int maHD){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        HoaDon hoaDon=new HoaDon();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai",hoaDon.getTrangthai());
        long check = sqLiteDatabase.update("HOADON",contentValues,"maHD=?",new String[]{String.valueOf(maHD)});
        if(check==-1){
            return false;
        }else{
            return true;
        }

    }
    public boolean themHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("mapm",phieuMuon.getMapm());
//        contentValues.put("maHD",hoaDon.getMaHD());
        contentValues.put("maSP",hoaDon.getMaSP());
        contentValues.put("tenSP",hoaDon.getTenSP());
        contentValues.put("giaban",hoaDon.getGiaban());
        contentValues.put("mausac",hoaDon.getMausac());
        contentValues.put("tenKH",hoaDon.getTenKH());
        contentValues.put("sodienthoai",hoaDon.getSodienthoai());
        contentValues.put("diachi",hoaDon.getDiachi());
        contentValues.put("chietkhau",hoaDon.getChietkhau());
        contentValues.put("ngaytao",hoaDon.getNgaytao());
        contentValues.put("soluong",hoaDon.getSoluong());
        contentValues.put("trangthai",hoaDon.getTrangthai());
        contentValues.put("maKH",hoaDon.getMaKH());
        long check = sqLiteDatabase.insert("HOADON",null,contentValues);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean Xoa_HD(int maHD){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("HOADON", "maHD = ?", new String[]{String.valueOf(maHD)}) >0;
    }
    public boolean sua_hd(HoaDon nguoiDung){
        sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangthai",nguoiDung.getTrangthai());
        values.put("tenSP",nguoiDung.getTenSP());
        values.put("mausac",nguoiDung.getMausac());
        values.put("giaban",nguoiDung.getGiaban());
        values.put("tenKH",nguoiDung.getTenKH());
        values.put("sodienthoai",nguoiDung.getSodienthoai());
        values.put("diachi",nguoiDung.getDiachi());
        values.put("chietkhau",nguoiDung.getChietkhau());
        values.put("ngaytao",nguoiDung.getNgaytao());
        long row = sqLiteDatabase.update("HOADON", values, "maHD = ?", new String[]{String.valueOf(nguoiDung.getMaHD())});
        if (row<=0){
            return  false;
        }else {
            return true;
        }
    }

    @SuppressLint("Range")
    public List<HoaDon> getData(String sql, String...selectionArgs){
        List<HoaDon> list=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            HoaDon hoaDon=new HoaDon();
            hoaDon.setMaHD(cursor.getInt(cursor.getColumnIndex("maHD")));
            hoaDon.setMaSP(cursor.getInt(cursor.getColumnIndex("maSP")));
            hoaDon.setTenSP(cursor.getString(cursor.getColumnIndex("tenSP")));
            hoaDon.setGiaban(cursor.getInt(cursor.getColumnIndex("giaban")));
            hoaDon.setMausac(cursor.getString(cursor.getColumnIndex("mausac")));
            hoaDon.setTenKH(cursor.getString(cursor.getColumnIndex("tenKH")));
            hoaDon.setSodienthoai(cursor.getInt(cursor.getColumnIndex("sodienthoai")));
            hoaDon.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
            hoaDon.setChietkhau(cursor.getInt(cursor.getColumnIndex("chietkhau")));
            hoaDon.setNgaytao(cursor.getString(cursor.getColumnIndex("ngaytao")));
            hoaDon.setTrangthai(cursor.getString(cursor.getColumnIndex("trangthai")));
            hoaDon.setThanhtoan(cursor.getInt(cursor.getColumnIndex("thanhtoan")));
            list.add(hoaDon);
        }
        return list;
    }
    @SuppressLint("Range")
    public List<Top10> getTop10(){
        String sqlTop10 = "SELECT tenSP, count(tenSP) as soluong FROM HOADON GROUP BY tenSP ORDER BY soluong DESC LIMIT 10";
        List<Top10> list = new ArrayList<Top10>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlTop10, null);
        while (cursor.moveToNext()){
            Top10 top10 = new Top10();
            top10.setTenSP(cursor.getString(cursor.getColumnIndex("tenSP")));
            top10.setSoluongban(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(top10);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhthu (String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(giaban*soluong -((giaban*soluong*chietkhau)/100)) as doanhThu FROM HOADON WHERE ngaytao BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));

            } catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
