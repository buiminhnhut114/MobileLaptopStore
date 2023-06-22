package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.HoaDonAdapter;
import com.example.duan1_ph27232.Adapter.NhanVienAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;

import com.example.duan1_ph27232.dao.DienThoaiDao;
import com.example.duan1_ph27232.dao.HoaDonDAO;
import com.example.duan1_ph27232.dao.KhachHangDao;
import com.example.duan1_ph27232.dao.KhuyenMaiDao;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.HoaDon;
import com.example.duan1_ph27232.model.KhachHang;
import com.example.duan1_ph27232.model.KhuyenMai;
import com.example.duan1_ph27232.model.NhanVien;
import com.example.duan1_ph27232.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class qlHoaDonFragment extends AppCompatActivity implements HoaDonAdapter.Callback {
    HoaDonDAO hoaDonDAO;
    RecyclerView recyclerView;
    List<SanPham> list = new ArrayList<>();
    RelativeLayout itemHD;
    TextView tvTongHD,tvsp2;
    ArrayList<HoaDon> lst;
    ImageView comback1;
    List<KhachHang>   list1=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ql_hoa_don);

        FloatingActionButton floaAdd = findViewById(R.id.flhoadon);
        hoaDonDAO = new HoaDonDAO(qlHoaDonFragment.this);
        recyclerView = findViewById(R.id.id_recycler_hoadon);
        itemHD = findViewById(R.id.itemHD1);
        comback1=findViewById(R.id.comeback1);
comback1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(qlHoaDonFragment.this, MainActivity.class));
    }
});
        tvTongHD = findViewById(R.id.TongHD);




        loadData();

        floaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }
    private void loadData(){
        lst = hoaDonDAO.getDSHoaDon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(qlHoaDonFragment.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HoaDonAdapter adapter = new HoaDonAdapter(qlHoaDonFragment.this,lst,hoaDonDAO,this);
        recyclerView.setAdapter(adapter);
        tvTongHD.setText("Hóa Đơn: "+lst.size());
    }
    @SuppressLint("MissingInflatedId")
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(qlHoaDonFragment.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themhoadon,null);
        Spinner spnSanPham = view.findViewById(R.id.spnSanPham);
        Spinner spnMauSac = view.findViewById(R.id.spnMauSac);
        Spinner spnMaKM = view.findViewById(R.id.spnMaKM);
        EditText edGia = view.findViewById(R.id.edGiaSP);
        Spinner spnmaKH=view.findViewById(R.id.spnmaKH);
//        EditText edMaHD = view.findViewById(R.id.edMaHD);
//        EditText edMaSP = view.findViewById(R.id.edMaSP);
        EditText soluong=view.findViewById(R.id.edsoluongSP);
        EditText edTenKH = view.findViewById(R.id.edTenKH);
        EditText edDchi = view.findViewById(R.id.edDiaChi);
        EditText edSdt = view.findViewById(R.id.edSdt);
        EditText edNgayTao = view.findViewById(R.id.edNgayTao);
        tvsp2=view.findViewById(R.id.tvmasp2);
        Calendar calendar = Calendar.getInstance();
        edNgayTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        qlHoaDonFragment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                                String ngay="";
                                String thang="";
                                if(i2<10){
                                    ngay="0"+i2;
                                }else{
                                    ngay = String.valueOf(i2);
                                }
                                if((i1+1)<10){
                                    thang="0"+(i1+1);
                                }else{
                                    thang = String.valueOf((i1+1));
                                }
                                edNgayTao.setText(ngay+"/"+thang+"/"+i);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        getDataSanPham(spnSanPham);
        getDataMauSac(spnMauSac);
        getDataMaKM(spnMaKM);
        getDataKhachHang(spnmaKH);
        spnmaKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<list1.size();i++){
                    if(list1.get(position).getMaKH()==Integer.parseInt(spnmaKH.getSelectedItem().toString())){
                        edTenKH.setText(list1.get(position).getTenKH());
                        edDchi.setText(list1.get(position).getDiachi());
                        edSdt.setText(Integer.toString(list1.get(position).getSodienthoai()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getTenSP().equals(spnSanPham.getSelectedItem())){
                        edGia.setText(""+list.get(i).getGiaban());
                        tvsp2.setText(Integer.toString(list.get(position).getMaSP()));
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hoaDonDAO =new HoaDonDAO(qlHoaDonFragment.this);
                HoaDon hoaDon=new HoaDon();
//                hoaDon.setMaHD(Integer.parseInt(edMaHD.getText().toString()));
                hoaDon.setSoluong(Integer.parseInt(soluong.getText().toString()));
                hoaDon.setMaSP(Integer.parseInt(tvsp2.getText().toString()));
                hoaDon.setTenSP(spnSanPham.getSelectedItem().toString());
                hoaDon.setGiaban(Integer.parseInt(edGia.getText().toString()));
                hoaDon.setMausac(spnMauSac.getSelectedItem().toString());
                hoaDon.setTenKH(edTenKH.getText().toString());
                hoaDon.setSodienthoai(Integer.parseInt(edSdt.getText().toString()));
                hoaDon.setDiachi(edDchi.getText().toString());
                hoaDon.setChietkhau(Integer.parseInt(spnMaKM.getSelectedItem().toString()));
                hoaDon.setNgaytao(edNgayTao.getText().toString());
                hoaDon.setTrangthai("Đang xử lí");
                if(hoaDonDAO.themHoaDon(hoaDon)==true){
                    Toast.makeText(qlHoaDonFragment.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(qlHoaDonFragment.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
                loadData();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getDataSanPham(Spinner spnSanPham){
        DienThoaiDao dienThoaiDAO = new DienThoaiDao(qlHoaDonFragment.this);
        List<SanPham> list = dienThoaiDAO.getDSDienThoai();
        List<String> qdt = new ArrayList<>();
        for(int i=0;i< list.size();i++){
            qdt.add(list.get(i).getTenSP());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(qlHoaDonFragment.this, android.R.layout.simple_list_item_1,qdt);
        spnSanPham.setAdapter(arrayAdapter);
    }
    private void getDataMaKM(Spinner spnMaKM){
        KhuyenMaiDao khuyenMaiDAO = new KhuyenMaiDao(qlHoaDonFragment.this);
        List<KhuyenMai> list = khuyenMaiDAO.getDSKhuyenmai();
        List<Object> qdt = new ArrayList<>();
        for(int i=0;i< list.size();i++){
            qdt.add(list.get(i).getChietkhau());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(qlHoaDonFragment.this, android.R.layout.simple_list_item_1,qdt);
        spnMaKM.setAdapter(arrayAdapter);
    }
    private void getDataMauSac(Spinner spnMauSac){
        DienThoaiDao dienThoaiDAO = new DienThoaiDao(qlHoaDonFragment.this);
        list = dienThoaiDAO.getDSDienThoai();
        List<String> qdt = new ArrayList<>();
        for(int i=0;i< list.size();i++){
            qdt.add(list.get(i).getMausac());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(qlHoaDonFragment.this, android.R.layout.simple_list_item_1,qdt);
        spnMauSac.setAdapter(arrayAdapter);
    }
    private void getDataKhachHang(Spinner spnmaKH){
        KhachHangDao dienThoaiDAO = new KhachHangDao(qlHoaDonFragment.this);
       list1 = dienThoaiDAO.getDSKhachHang();
        List<Object> qdt = new ArrayList<>();
        for(int i=0;i< list1.size();i++){
            qdt.add(list1.get(i).getMaKH());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(qlHoaDonFragment.this, android.R.layout.simple_list_item_1,qdt);
        spnmaKH.setAdapter(arrayAdapter);
    }

    @Override
    public void xoa(HoaDon nguoiDung) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(qlHoaDonFragment.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hoaDonDAO.Xoa_HD(nguoiDung.getMaHD());
                Toast.makeText(qlHoaDonFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                loadData();
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void sua(HoaDon nguoiDung) {

    }
}
