package com.example.duan1_ph27232;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.duan1_ph27232.dao.AdminDao;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.Admin;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton btnDangNhap, btnDangKy;
    TextInputEditText txttk, txtmk;
    public static int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ các view
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy   = findViewById(R.id.btnDangKy);
        txttk       = findViewById(R.id.tentk);
        txtmk       = findViewById(R.id.txtmk);

        // Lấy dữ liệu từ database
        AdminDao daoNguoiDung = new AdminDao(this);
        List<Admin> nguoiDungList = daoNguoiDung.getALL_nguoidung();
        NhanVienDAO dao = new NhanVienDAO(this);
        List<NhanVien> nhanVienList = dao.getDSNhanVien();

        // Xử lý sự kiện cho nút ĐĂNG NHẬP
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = false;
                // Kiểm tra đăng nhập cho Admin
                for (Admin nguoiDung : nguoiDungList) {
                    if (txttk.getText().toString().equals(nguoiDung.getTenDN()) &&
                            txtmk.getText().toString().equals(nguoiDung.getMatkhau())) {
                        id = nguoiDung.getMaAM();
                        success = true;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                // Nếu không phải Admin, kiểm tra đăng nhập cho Nhân viên
                if (!success) {
                    for (NhanVien nv : nhanVienList) {
                        if (txttk.getText().toString().equals(nv.getUsername()) &&
                                txtmk.getText().toString().equals(nv.getPassword())) {
                            success = true;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                // Nếu không khớp, thông báo lỗi
                if (!success) {
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện cho nút ĐĂNG KÝ chuyển sang DangKyActivity
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}
