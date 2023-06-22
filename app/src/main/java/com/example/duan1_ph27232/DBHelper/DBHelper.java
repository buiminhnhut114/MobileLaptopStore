package com.example.duan1_ph27232.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "DIENTHOAI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbKhachHang= "CREATE TABLE KHACHHANG(maKH integer primary key autoincrement, " +
                "tenKH TEXT,diachi text, email text,sodienthoai integer,anh BLOB) ";
        db.execSQL(dbKhachHang);
        String dbDanhGia = "CREATE TABLE DANHGIA(maDG integer primary key autoincrement, " +
                "ghichu TEXT,gio text,ngay text,maKH integer references KHACHHANG(maKH)) ";
        db.execSQL(dbDanhGia);

        String dbKhuyenMai = "CREATE TABLE KHUYENMAI(maKM integer primary key autoincrement, " +
                "chietkhau INTEGER,tungay text,denngay TEXT,anh BLOB) ";
        db.execSQL(dbKhuyenMai);

        String dbHoaDon = "CREATE TABLE HOADON(maHD integer primary key autoincrement,maSP integer references SANPHAM(maSP)," +
                " tenSP text references SANPHAM(tenSP),giaban integer references SANPHAM(giaban),mausac text references SANPHAM(mausac),soluong integer," +
                "tenKH Text references KHACHHANG(tenKH),sodienthoai integer references KHACHHANG(sodienthoai)," +
                "diachi text references KHACHHANG(diachi),chietkhau integer references KHUYENMAI(chietkhau)," +
                "ngaytao text,trangthai text,thanhtoan integer,maKH integer references KHACHHANG (maKH)) ";
        db.execSQL(dbHoaDon);
        String dbDanhMuc = "CREATE TABLE DANHMUC(maDM integer primary key autoincrement, tenDM TEXT,ANH BLOB) ";
        db.execSQL(dbDanhMuc);

        String dbSanPham = "CREATE TABLE SANPHAM(maSP integer primary key autoincrement, tenSP text," +
                "ngaynhap text,soluong integer,gianhap integer,giaban integer,mausac text, " +
                "tenDM TEXT references DANHMUC(tenDM),anh BLOB) ";
        db.execSQL(dbSanPham);


        String dbNhanVien = "CREATE TABLE NHANVIEN(maNV integer primary key autoincrement, " +
                "tenNV TEXT,ngaysinh text,sodienthoai integer, username text,password text,vaitro text,anh BLOB) ";
        db.execSQL(dbNhanVien);
                String dbadmin="CREATE TABLE ADMIN(maAM integer primary key autoincrement, " +
                        "tenAM TEXT NOT NULL, tenDN TEXT NOT NULL, matkhau NOT NULL) ";
                db.execSQL(dbadmin);
        db.execSQL("INSERT INTO ADMIN VALUES (1,'Nguyen Hoang Tra','tra123','123456')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS DANHGIA");
            db.execSQL("DROP TABLE IF EXISTS KHUYENMAI");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS HOADONCHITIET");
            db.execSQL("DROP TABLE IF EXISTS DANHMUC");
            db.execSQL("DROP TABLE IF EXISTS SANPHAM");
            db.execSQL("DROP TABLE IF EXISTS ADMIN");
            onCreate(db);
        }
    }
}
