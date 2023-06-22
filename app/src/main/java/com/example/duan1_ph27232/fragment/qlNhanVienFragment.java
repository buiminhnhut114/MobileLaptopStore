package com.example.duan1_ph27232.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.NhanVienAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.Admin;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class qlNhanVienFragment extends AppCompatActivity implements NhanVienAdapter.Callback {
    NhanVienDAO nhanVienDAO;
    RecyclerView recyclerView;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView idImageUser;
    EditText edthoten,edtngaysinh,edtsodt,edttendangnhap,edtpassword,edtvaitro,edttimkiem;
    EditText edthoten1,edtngaysinh1,edtsodt1,edttendangnhap1,edtpassword1,edtvaitro1,edttimkiem1;
    AppCompatButton btnthem,btnsua;
    TextView thoat,tongnv,thoat1,tvsp;
    List<NhanVien> list;
    NhanVienAdapter adapter;
    ImageView imgthemnv,back1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qlnhanvien);
         FloatingActionButton floaAdd = findViewById(R.id.floatingbutton);
        recyclerView =findViewById(R.id.recyclerNhanVien);
        nhanVienDAO = new NhanVienDAO(qlNhanVienFragment.this);
        tongnv=findViewById(R.id.tongnv);
        back1=findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlNhanVienFragment.this, MainActivity.class));
            }
        });
        edttimkiem=findViewById(R.id.editTexttk);
        edttimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
filter(s.toString());
            }
        });
        loadData();
        floaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showdialog();
            }
        });

    }

    public void loadData(){
        nhanVienDAO=new NhanVienDAO(qlNhanVienFragment.this);
        list = nhanVienDAO.getDSNhanVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NhanVienAdapter(list,this);
        recyclerView.setAdapter(adapter);
tongnv.setText("Nhân Viên: "+list.size());
    }
    public void showdialog(){
        final Dialog dialog = new Dialog(qlNhanVienFragment.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.activity_dialog_them_nv);
        edthoten=dialog.findViewById(R.id.edt_Hoten);
        edtngaysinh=dialog.findViewById(R.id.edt_ngaysinh);
        edtsodt=dialog.findViewById(R.id.edt_sodienthoai);
        edttendangnhap=dialog.findViewById(R.id.edt_Tendangnhap);
        edtpassword=dialog.findViewById(R.id.edt_Matkhau);
        edtvaitro=dialog.findViewById(R.id.edt_chucvu);
        btnthem=dialog.findViewById(R.id.btn_Them);
        imgthemnv=dialog.findViewById(R.id.imgthemanhnv);
        imgthemnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick=new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser=Intent.createChooser(pick,"lua chon");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{pho});
                startActivityForResult(chosser,REQUEST_CODE_GALLERY);
            }
        });
        edtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlNhanVienFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtngaysinh.setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog1.show();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienDAO = new NhanVienDAO(qlNhanVienFragment.this);
                NhanVien nv=new NhanVien();
                nv.setTenNV(edthoten.getText().toString());
                nv.setNgaysinh(edtngaysinh.getText().toString());
                nv.setSodienthoai(Integer.parseInt(edtsodt.getText().toString()));
                nv.setVaitro(edtvaitro.getText().toString());
                nv.setUsername(edttendangnhap.getText().toString());
                nv.setPassword(edtpassword.getText().toString());
                byte[] anh=imageViewToByte(imgthemnv);
                nv.setAnh(anh);
                nhanVienDAO.them_nhanvien(nv);
                loadData();
                Toast.makeText(qlNhanVienFragment.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat=dialog.findViewById(R.id.tv_Thoatnv);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void filter(String text) {
           List<NhanVien> filteredList = new ArrayList<>();
        for (NhanVien nguoiDung: list){
            if (nguoiDung.getTenNV().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(nguoiDung);
            }
        }
        adapter.filterList(filteredList);
    }
    @Override
    public void xoa(NhanVien nguoiDung) {
androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              nhanVienDAO.xoa_NguoiDung(nguoiDung.getMaNV());
                Toast.makeText(qlNhanVienFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
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
    public void sua(NhanVien nguoiDung) {
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_nhanvien);
        edthoten1=dialog.findViewById(R.id.edt_Hoten1);
        edtngaysinh1=dialog.findViewById(R.id.edt_ngaysinh1);
        edtsodt1=dialog.findViewById(R.id.edt_sodienthoai1);
        edttendangnhap1=dialog.findViewById(R.id.edt_Tendangnhap1);
        edtpassword1=dialog.findViewById(R.id.edt_Matkhau1);
        edtvaitro1=dialog.findViewById(R.id.edt_chucvu1);
        btnsua=dialog.findViewById(R.id.btn_Them1);
        tvsp=dialog.findViewById(R.id.tvmanv1);
        edthoten1.setText(nguoiDung.getTenNV());
        edtngaysinh1.setText(nguoiDung.getNgaysinh());
        edtsodt1.setText(Integer.toString(nguoiDung.getSodienthoai()));
        edtpassword1.setText(nguoiDung.getPassword());
        edttendangnhap1.setText(nguoiDung.getUsername());
        edtvaitro1.setText(nguoiDung.getVaitro());
        tvsp.setText(Integer.toString(nguoiDung.getMaNV()));
        edtngaysinh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlNhanVienFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtngaysinh1.setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog1.show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienDAO = new NhanVienDAO(qlNhanVienFragment.this);
                NhanVien nv1=new NhanVien();
                nv1.setTenNV(edthoten1.getText().toString());
                nv1.setNgaysinh(edtngaysinh1.getText().toString());
                nv1.setSodienthoai(Integer.parseInt(edtsodt1.getText().toString()));
                nv1.setVaitro(edtvaitro1.getText().toString());
                nv1.setUsername(edttendangnhap1.getText().toString());
                nv1.setPassword(edtpassword1.getText().toString());
                nv1.setMaNV(Integer.parseInt(tvsp.getText().toString()));
                nhanVienDAO.sua_NguoiDung(nv1);
                loadData();
                Toast.makeText(qlNhanVienFragment.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat1=dialog.findViewById(R.id.tv_Thoatnv1);
        thoat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8), true);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(qlNhanVienFragment.this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgthemnv.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
