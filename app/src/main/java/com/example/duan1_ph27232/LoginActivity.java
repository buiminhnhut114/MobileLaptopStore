package com.example.duan1_ph27232;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_ph27232.dao.AdminDao;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.Admin;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton btnDangNhap;
    TextInputEditText txttk,txtmk;
public static int id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        txtmk=findViewById(R.id.txtmk);
txttk=findViewById(R.id.tentk);
        AdminDao daoNguoiDung = new AdminDao(this);
        List<Admin> nguoiDungList = daoNguoiDung.getALL_nguoidung();
        NhanVienDAO dao=new NhanVienDAO(this);
        List<NhanVien> nhanVienList=dao.getDSNhanVien();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Admin nguoiDung: nguoiDungList) {
                    if (txttk.getText().toString().equals(nguoiDung.getTenDN()) && txtmk.getText().toString().equals(nguoiDung.getMatkhau())) {
                        id=nguoiDung.getMaAM();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                  }
                for (NhanVien nguoiDung: nhanVienList) {
                    if (txttk.getText().toString().equals(nguoiDung.getUsername()) && txtmk.getText().toString().equals(nguoiDung.getPassword())) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

}