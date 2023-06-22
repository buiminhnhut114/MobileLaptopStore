package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.HoaDonDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class qlDoanhThuFragment extends AppCompatActivity {
    private ImageView imgDateStart;
    private EditText edtDateStart;
    private ImageView imgDateEnd,comedoanhthu;
    private EditText edtDateEnd;
    private TextView tvDoanhthu;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int mYear, mMonth, mDay;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ql_doanh_thu);
        imgDateStart = (ImageView) findViewById(R.id.img_dateStart);
        edtDateStart = (EditText) findViewById(R.id.edt_dateStart);
        imgDateEnd = (ImageView) findViewById(R.id.img_dateEnd);
        edtDateEnd = (EditText) findViewById(R.id.edt_dateEnd);
        tvDoanhthu = (TextView) findViewById(R.id.tv_doanhThu);
comedoanhthu=findViewById(R.id.comedoanhthu);
comedoanhthu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(qlDoanhThuFragment.this, MainActivity.class));
    }
});

        imgDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(qlDoanhThuFragment.this,0,dateEnd,mYear,mMonth,mDay);
                d.show();
            }
        });

        imgDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(qlDoanhThuFragment.this,0,dateStart,mYear,mMonth,mDay);
                d.show();
            }
        });



        findViewById(R.id.btn_thongkeDoanhThu).setOnClickListener(v ->{
            String tuNgay = edtDateStart.getText().toString();
            String denNgay = edtDateEnd.getText().toString();
            HoaDonDAO daoHoaDon = new HoaDonDAO(qlDoanhThuFragment.this);
            tvDoanhthu.setText("  "+daoHoaDon.getDoanhthu(tuNgay, denNgay) +"VNĐ");
        });
    }
    DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtDateStart.setText(simpleDateFormat.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtDateEnd.setText(simpleDateFormat.format(c.getTime()));
        }
    };

    }



