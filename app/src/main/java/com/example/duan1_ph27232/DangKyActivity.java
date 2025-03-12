package com.example.duan1_ph27232;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.duan1_ph27232.dao.AdminDao;
import com.example.duan1_ph27232.model.Admin;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class DangKyActivity extends AppCompatActivity {
    AppCompatButton btndangki;
    TextInputEditText txthoten, txttentkdk, txtmkdk, txtnhaplai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        // Ánh xạ View
        txthoten    = findViewById(R.id.txthotendn);
        txttentkdk  = findViewById(R.id.txttentk);
        txtmkdk     = findViewById(R.id.txtmkdk);
        txtnhaplai  = findViewById(R.id.txtnhaplai);
        btndangki   = findViewById(R.id.btndangki1);

        btndangki.setOnClickListener(v -> {
            String hoten   = Objects.requireNonNull(txthoten.getText()).toString().trim();
            String user    = Objects.requireNonNull(txttentkdk.getText()).toString().trim();
            String pass    = Objects.requireNonNull(txtmkdk.getText()).toString().trim();
            String repass  = Objects.requireNonNull(txtnhaplai.getText()).toString().trim();

            if (hoten.isEmpty() || user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(DangKyActivity.this, "Không để trống các trường!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass.equals(repass)) {
                Toast.makeText(DangKyActivity.this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            AdminDao daoNguoiDung = new AdminDao(DangKyActivity.this);
            Admin nguoiDung = new Admin();
            nguoiDung.setTenAM(hoten);
            nguoiDung.setTenDN(user);
            nguoiDung.setMatkhau(pass);

            boolean isInserted = daoNguoiDung.them_NguoiDung(nguoiDung);
            if (isInserted) {
                Toast.makeText(DangKyActivity.this, "Đăng ký thành công! Mời đăng nhập.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DangKyActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(DangKyActivity.this, "Đăng ký thất bại hoặc tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
