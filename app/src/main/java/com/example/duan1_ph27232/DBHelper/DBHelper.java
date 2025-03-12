package com.example.duan1_ph27232.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3; // Cập nhật version khi cần
    private static final String DATABASE_NAME = "DIENTHOAI";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng KHÁCHHÀNG
        String dbKhachHang = "CREATE TABLE KHACHHANG(" +
                "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenKH TEXT, " +
                "diachi TEXT, " +
                "email TEXT, " +
                "sodienthoai INTEGER, " +
                "anh BLOB)";
        db.execSQL(dbKhachHang);

        // Tạo bảng ĐÁNHGIÁ
        String dbDanhGia = "CREATE TABLE DANHGIA(" +
                "maDG INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ghichu TEXT, " +
                "gio TEXT, " +
                "ngay TEXT, " +
                "maKH INTEGER REFERENCES KHACHHANG(maKH))";
        db.execSQL(dbDanhGia);

        // Tạo bảng KHUYẾNMÃI
        String dbKhuyenMai = "CREATE TABLE KHUYENMAI(" +
                "maKM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "chietkhau INTEGER, " +
                "tungay TEXT, " +
                "denngay TEXT, " +
                "anh BLOB)";
        db.execSQL(dbKhuyenMai);

        // Tạo bảng DANHMỤC
        String dbDanhMuc = "CREATE TABLE DANHMUC(" +
                "maDM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenDM TEXT, " +
                "anh BLOB)";
        db.execSQL(dbDanhMuc);

        // Tạo bảng SẢNPHẨM (tham chiếu cột tenDM của DANHMUC)
        String dbSanPham = "CREATE TABLE SANPHAM(" +
                "maSP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSP TEXT, " +
                "ngaynhap TEXT, " +
                "soluong INTEGER, " +
                "gianhap INTEGER, " +
                "giaban INTEGER, " +
                "mausac TEXT, " +
                "tenDM TEXT REFERENCES DANHMUC(tenDM), " +
                "anh BLOB)";
        db.execSQL(dbSanPham);

        // Tạo bảng HOÁĐƠN
        // Lưu ý: Cột ngaytao được lưu theo định dạng "yyyy-MM-dd" (ví dụ: "2025-03-15")
        String dbHoaDon = "CREATE TABLE HOADON(" +
                "maHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSP INTEGER REFERENCES SANPHAM(maSP), " +
                "tenSP TEXT REFERENCES SANPHAM(tenSP), " +
                "giaban INTEGER REFERENCES SANPHAM(giaban), " +
                "mausac TEXT REFERENCES SANPHAM(mausac), " +
                "soluong INTEGER, " +
                "tenKH TEXT REFERENCES KHACHHANG(tenKH), " +
                "sodienthoai INTEGER REFERENCES KHACHHANG(sodienthoai), " +
                "diachi TEXT REFERENCES KHACHHANG(diachi), " +
                "chietkhau INTEGER REFERENCES KHUYENMAI(chietkhau), " +
                "ngaytao TEXT, " +    // Lưu ngày theo định dạng "yyyy-MM-dd"
                "trangthai TEXT, " +
                "thanhtoan INTEGER, " +
                "maKH INTEGER REFERENCES KHACHHANG(maKH))";
        db.execSQL(dbHoaDon);

        // Tạo bảng NHÂNVIÊN
        String dbNhanVien = "CREATE TABLE NHANVIEN(" +
                "maNV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenNV TEXT, " +
                "ngaysinh TEXT, " +
                "sodienthoai INTEGER, " +
                "username TEXT, " +
                "password TEXT, " +
                "vaitro TEXT, " +
                "anh BLOB)";
        db.execSQL(dbNhanVien);

        // Tạo bảng ADMIN
        String dbAdmin = "CREATE TABLE ADMIN(" +
                "maAM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenAM TEXT NOT NULL, " +
                "tenDN TEXT NOT NULL, " +
                "matkhau NOT NULL)";
        db.execSQL(dbAdmin);

        // Thêm 1 admin mặc định
        db.execSQL("INSERT INTO ADMIN VALUES (1, 'Bui Minh Nhut', 'buiminhnhut', '123456')");

        // Thêm dữ liệu mẫu cho bảng DANHMUC (chỉ 2 item: PC và Laptop)
        db.execSQL("INSERT INTO DANHMUC(tenDM) VALUES('PC')");
        db.execSQL("INSERT INTO DANHMUC(tenDM) VALUES('Laptop')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS KHACHHÀNG");
            db.execSQL("DROP TABLE IF EXISTS NHANVIÊN");
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
