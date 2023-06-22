package com.example.duan1_ph27232;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.duan1_ph27232.dao.AdminDao;
import com.example.duan1_ph27232.model.Admin;
import com.google.android.material.textfield.TextInputEditText;

public class DangKyActivity extends AppCompatActivity {
AppCompatButton btndangki;
TextInputEditText txthoten,txttentkdk,txtmkdk,txtnhaplai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        txthoten=findViewById(R.id.txthotendn);
        txtmkdk=findViewById(R.id.txtmkdk);
        txttentkdk=findViewById(R.id.txttentk);
        txtnhaplai=findViewById(R.id.txtnhaplai);
        btndangki=findViewById(R.id.btndangki1);
        String pass = txtmkdk.getText().toString();
        String repass =txtnhaplai.getText().toString();
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txthoten.length()==0) {
                    txthoten.setTextColor(Color.RED);
                    Toast.makeText(DangKyActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                if (txttentkdk.length()==0) {
                    txttentkdk.setTextColor(Color.RED);
                    Toast.makeText(DangKyActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                if (txtmkdk.length()==0) {
                    txtmkdk.setTextColor(Color.RED);
                    Toast.makeText(DangKyActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                if (txthoten.length()!=0 && txttentkdk.length()!=0 &&txtmkdk.length()!=0&&txtnhaplai.length()!=0) {
                    AdminDao daoNguoiDung = new AdminDao(DangKyActivity.this);
                    Admin nguoiDung = new Admin();
                    nguoiDung.setTenAM(txthoten.getText().toString());
                    nguoiDung.setTenDN(txttentkdk.getText().toString());
                    nguoiDung.setMatkhau(txtmkdk.getText().toString());
                    if (pass.equals(repass)) {
                        if (daoNguoiDung.them_NguoiDung(nguoiDung) == true) {
                       startActivity(new Intent(DangKyActivity.this,LoginActivity.class));
                            Toast.makeText(DangKyActivity.this, "Đăng kí thành công mời đăng nhập", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DangKyActivity.this, "Không để trống", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(DangKyActivity.this, "Password không giống, mời nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}