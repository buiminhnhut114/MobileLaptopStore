package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.DanhMucAdapter;
import com.example.duan1_ph27232.Adapter.DienThoaiAdapter;
import com.example.duan1_ph27232.Adapter.KhachHangAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.DanhMucDao;
import com.example.duan1_ph27232.dao.DienThoaiDao;
import com.example.duan1_ph27232.dao.KhachHangDao;
import com.example.duan1_ph27232.model.DanhMuc;
import com.example.duan1_ph27232.model.KhachHang;
import com.example.duan1_ph27232.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class qlDienThoaiFragment extends AppCompatActivity implements DienThoaiAdapter.Callback {
    DienThoaiDao dienThoaiDao;
    RecyclerView recyclerView;
    final int REQUEST_CODE_GALLERY = 999;
    EditText edttensp,edtngaynhap,edtsoluong,edtgianhap,edtgiaban,edtmausac,tkdt;
    EditText edttensp1,edtngaynhap1,edtsoluong1,edtgianhap1,edtgiaban1,edtmausac1;
    AppCompatButton btnthem,btnsua;
    TextView thoat,tongnv,thoat1,tvsp;
    List<SanPham> list;
    DienThoaiAdapter adapter;
    ImageView imgdm,back5;
    Spinner spnthem,spnsua;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dienthoai);
        FloatingActionButton add=findViewById(R.id.fladdienthoai);
        tongnv=findViewById(R.id.tongdt);
        recyclerView=findViewById(R.id.id_recycler_dienthoai);
        loadata();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showdialog();
            }
        });
        back5=findViewById(R.id.back5);
        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlDienThoaiFragment.this, MainActivity.class));
            }
        });
        tkdt=findViewById(R.id.edittkdienthoai);
        tkdt.addTextChangedListener(new TextWatcher() {
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
}
    private void filter(String text) {
        List<SanPham> filteredList = new ArrayList<>();
        for (SanPham nguoiDung: list){
            if (nguoiDung.getTenSP().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(nguoiDung);
            }
        }
        adapter.filterList(filteredList);
    }
public void loadata(){
    dienThoaiDao=new DienThoaiDao(qlDienThoaiFragment.this);
    list = dienThoaiDao.getDSDienThoai();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
    adapter = new DienThoaiAdapter(list, this);
    recyclerView.setAdapter(adapter);
    tongnv.setText("Điện Thoại: "+list.size());
}

    @Override
    public void xoa(SanPham nguoiDung) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dienThoaiDao.xoa_sp(nguoiDung.getMaSP());
                Toast.makeText(qlDienThoaiFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                loadata();
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
    public void sua(SanPham nguoiDung) {
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_dienthoai);
        edttensp1=dialog.findViewById(R.id.edt_tensp1);
        edtgiaban1=dialog.findViewById(R.id.edt_giaban1);
        edtgianhap1=dialog.findViewById(R.id.edt_gianhap1);
        edtngaynhap1=dialog.findViewById(R.id.edt_ngaynhap1);
        edtsoluong1=dialog.findViewById(R.id.edt_soluong1);
        edtmausac1=dialog.findViewById(R.id.edt_mausac1);
        btnsua=dialog.findViewById(R.id.btn_suadt);
        spnsua=dialog.findViewById(R.id.edt_spp1);
        tvsp=dialog.findViewById(R.id.tvmasp1);
        getDatadanhmuc(spnsua);
        edttensp1.setText(nguoiDung.getTenSP());
        tvsp.setText(Integer.toString(nguoiDung.getMaSP()));
        edtgiaban1.setText(Integer.toString(nguoiDung.getGiaban()));
        edtgianhap1.setText(Integer.toString(nguoiDung.getGianhap()));
        edtngaynhap1.setText(nguoiDung.getNgaynhap());
        edtsoluong1.setText(Integer.toString(nguoiDung.getSoluong()));
        edtmausac1.setText(nguoiDung.getMausac());
        edtngaynhap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlDienThoaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtngaynhap1.setText(ngay + "/" + (thang + 1) + "/" + nam);
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
                dienThoaiDao=new DienThoaiDao(qlDienThoaiFragment.this);
                SanPham sp=new SanPham();
                sp.setMaSP(Integer.parseInt(tvsp.getText().toString()));
                sp.setSoluong(Integer.parseInt(edtsoluong1.getText().toString()));
                sp.setMausac(edtmausac1.getText().toString());
                sp.setGianhap(Integer.parseInt(edtgianhap1.getText().toString()));
                sp.setNgaynhap(edtngaynhap1.getText().toString());
                sp.setGiaban(Integer.parseInt(edtgiaban1.getText().toString()));
                sp.setTenSP(edttensp1.getText().toString());
                sp.setTenDM(spnsua.getSelectedItem().toString());
                dienThoaiDao.sua_sp(sp);
                loadata();
                Toast.makeText(qlDienThoaiFragment.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat1=dialog.findViewById(R.id.tv_thoatdt1);
        thoat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void showdialog(){
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_them_dienthoai);
        edttensp=dialog.findViewById(R.id.edt_tensp);
        edtgiaban=dialog.findViewById(R.id.edt_giaban);
        edtgianhap=dialog.findViewById(R.id.edt_gianhap);
        edtngaynhap=dialog.findViewById(R.id.edt_ngaynhap);
        edtsoluong=dialog.findViewById(R.id.edt_soluong);
        edtmausac=dialog.findViewById(R.id.edt_mausac);
        btnthem=dialog.findViewById(R.id.btn_themdt);
        imgdm=dialog.findViewById(R.id.imgthemanhdt);
        spnthem=dialog.findViewById(R.id.edt_spp);
       getDatadanhmuc(spnthem);
        imgdm.setOnClickListener(new View.OnClickListener() {
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
        edtngaynhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlDienThoaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtngaynhap.setText(ngay + "/" + (thang + 1) + "/" + nam);
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
dienThoaiDao=new DienThoaiDao(qlDienThoaiFragment.this);
SanPham sp=new SanPham();
sp.setSoluong(Integer.parseInt(edtsoluong.getText().toString()));
sp.setMausac(edtmausac.getText().toString());
sp.setGianhap(Integer.parseInt(edtgianhap.getText().toString()));
sp.setNgaynhap(edtngaynhap.getText().toString());
sp.setGiaban(Integer.parseInt(edtgiaban.getText().toString()));
sp.setTenSP(edttensp.getText().toString());
sp.setTenDM(spnthem.getSelectedItem().toString());
byte[] anh=imageViewToByte(imgdm);
sp.setAnh(anh);
dienThoaiDao.them_sp(sp);
loadata();
Toast.makeText(qlDienThoaiFragment.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
dialog.dismiss();
            }
        });
        thoat=dialog.findViewById(R.id.tv_thoatdt);
        thoat.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(qlDienThoaiFragment.this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
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
                imgdm.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getDatadanhmuc(Spinner spndanhmuc){
        DanhMucDao danhMucDao = new DanhMucDao(qlDienThoaiFragment.this);
        List<DanhMuc> list = danhMucDao.getDSDanhMuc();
        List<String> qdt = new ArrayList<>();
        for(int i=0;i< list.size();i++){
            qdt.add(list.get(i).getTenDM());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(qlDienThoaiFragment.this, android.R.layout.simple_list_item_1,qdt);
        spndanhmuc.setAdapter(arrayAdapter);
    }
}
