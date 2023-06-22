package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageView;
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

import com.example.duan1_ph27232.Adapter.KhachHangAdapter;
import com.example.duan1_ph27232.Adapter.NhanVienAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.KhachHangDao;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.KhachHang;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class qlKhachHangFragment extends AppCompatActivity implements KhachHangAdapter.Callback {
    KhachHangDao khachHangDao;
    RecyclerView recyclerView;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView idImageUser;
    EditText edthoten,edtsdt,edtemail,edtdiachi,tkkh;
    EditText edthoten1,edtsdt1,edtemail1,edtdiachi1;
    AppCompatButton btnthem,btnsua;
    TextView thoat,tongnv,thoat1,tvkh;
    List<KhachHang> list;
    KhachHangAdapter adapter;
    ImageView imgthemkh,back2;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_khachhang);
        FloatingActionButton floatadd=findViewById(R.id.floatadd1);
        recyclerView=findViewById(R.id.id_recycler_kh);
tongnv=findViewById(R.id.khachhang);
tkkh=findViewById(R.id.tkkh);
back2=findViewById(R.id.back2);
back2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(qlKhachHangFragment.this, MainActivity.class));
    }
});
tkkh.addTextChangedListener(new TextWatcher() {
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
loaddata();
        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showdialog();
            }
        });


    }
    private void filter(String text) {
        List<KhachHang> filteredList = new ArrayList<>();
        for (KhachHang nguoiDung: list){
            if (nguoiDung.getTenKH().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(nguoiDung);
            }
        }
        adapter.filterList(filteredList);
    }
    public void loaddata(){
       khachHangDao=new KhachHangDao(qlKhachHangFragment.this);
        list = khachHangDao.getDSKhachHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(qlKhachHangFragment.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new KhachHangAdapter(list, this);
        recyclerView.setAdapter(adapter);
        tongnv.setText("Khách Hàng: "+list.size());
    }
    public void showdialog(){
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_them_khachhang);
        edthoten=dialog.findViewById(R.id.edthoten);
        edtsdt=dialog.findViewById(R.id.edtsodienthoai);
        edtdiachi=dialog.findViewById(R.id.edtdiachi);
        edtemail=dialog.findViewById(R.id.edtemail);
        btnthem=dialog.findViewById(R.id.btn_Themkh);
        imgthemkh=dialog.findViewById(R.id.imgthemanhkh);
        imgthemkh.setOnClickListener(new View.OnClickListener() {
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
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachHangDao=new KhachHangDao(qlKhachHangFragment.this);
                KhachHang khachHang=new KhachHang();
                khachHang.setTenKH(edthoten.getText().toString());
                khachHang.setEmail(edtemail.getText().toString());
                khachHang.setDiachi(edtdiachi.getText().toString());
                khachHang.setSodienthoai(Integer.parseInt(edtsdt.getText().toString()));
                byte[] anh=imageViewToByte(imgthemkh);
                khachHang.setAnh(anh);
                khachHangDao.them_kh(khachHang);
                loaddata();
                Toast.makeText(qlKhachHangFragment.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        thoat=dialog.findViewById(R.id.tv_Thoatkh);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void xoa(KhachHang nguoiDung) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(qlKhachHangFragment.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                khachHangDao.xoa_kh(nguoiDung.getMaKH());
                Toast.makeText(qlKhachHangFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
               loaddata();
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
    public void sua(KhachHang nguoiDung) {
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_kh);
        edtdiachi1=dialog.findViewById(R.id.edtdiachi1);
        edtemail1=dialog.findViewById(R.id.edtemail1);
        edthoten1=dialog.findViewById(R.id.edthoten1);
        edtsdt1=dialog.findViewById(R.id.edtsodienthoai1);
        tvkh=dialog.findViewById(R.id.tvmakh1);
        tvkh.setText(Integer.toString(nguoiDung.getMaKH()));
        edtsdt1.setText(Integer.toString(nguoiDung.getSodienthoai()));
        edthoten1.setText(nguoiDung.getTenKH());
        edtemail1.setText(nguoiDung.getEmail());
        edtdiachi1.setText(nguoiDung.getDiachi());
        btnsua=dialog.findViewById(R.id.btn_sua);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachHangDao=new KhachHangDao(qlKhachHangFragment.this);
                KhachHang khachHang=new KhachHang();
                khachHang.setMaKH(Integer.parseInt(tvkh.getText().toString()));
                khachHang.setTenKH(edthoten1.getText().toString());
                khachHang.setEmail(edtemail1.getText().toString());
                khachHang.setDiachi(edtdiachi1.getText().toString());
                khachHang.setSodienthoai(Integer.parseInt(edtsdt1.getText().toString()));
                khachHangDao.sua_kh(khachHang);
                loaddata();
                Toast.makeText(qlKhachHangFragment.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat1=dialog.findViewById(R.id.tv_Thoatkh1);
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
                Toast.makeText(qlKhachHangFragment.this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
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
                imgthemkh.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
