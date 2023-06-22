package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.AdminDao;
import com.example.duan1_ph27232.model.Admin;

import java.util.List;


public class DoiPassFragment extends AppCompatActivity {

    private EditText edt_Matkhaucu;
    private EditText edt_Matkhaumoi;
    private EditText edt_NhaplaiMatkhau;
    private Button btn_Capnhap,btncancel;

        private AdminDao daoNguoiDung;
    private List<Admin> nguoiDungList;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_doi_pass);
        edt_Matkhaucu = findViewById(R.id.edPassOld);
        edt_Matkhaumoi =findViewById(R.id.edPasswordNew);
        edt_NhaplaiMatkhau = findViewById(R.id.edPasswordConfirm);
        btn_Capnhap = findViewById(R.id.btnset);
        btn_Capnhap.setOnClickListener(v ->{
            daoNguoiDung = new AdminDao(DoiPassFragment.this);
            nguoiDungList = daoNguoiDung.getALL_nguoidung();
            int i =0;
            for (Admin nguoiDung: nguoiDungList) {
                if (nguoiDung.getMatkhau().equalsIgnoreCase(edt_Matkhaucu.getText().toString())) {
                    if (edt_NhaplaiMatkhau.getText().toString().equalsIgnoreCase(edt_Matkhaumoi.getText().toString())) {
                        nguoiDung.setMatkhau(edt_Matkhaumoi.getText().toString());
                        daoNguoiDung.sua_NguoiDung(nguoiDung);
                        Toast.makeText(DoiPassFragment.this, "Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DoiPassFragment.this, "Nhập lại mật khẩu không trùng mật khẩu mới", Toast.LENGTH_SHORT).show();
                    }
                    i=1;
                    break;
                }
            }
            if (i == 0) {
                Toast.makeText(DoiPassFragment.this,"Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
            }

        });
        btncancel=findViewById(R.id.btnCancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(DoiPassFragment.this, MainActivity.class));
            }
        });
    }

}