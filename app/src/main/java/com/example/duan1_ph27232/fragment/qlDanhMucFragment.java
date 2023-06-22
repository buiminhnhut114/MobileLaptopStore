package com.example.duan1_ph27232.fragment;

import static android.app.Activity.RESULT_OK;

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

import com.example.duan1_ph27232.Adapter.DanhMucAdapter;
import com.example.duan1_ph27232.Adapter.KhachHangAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.DanhMucDao;
import com.example.duan1_ph27232.dao.KhachHangDao;
import com.example.duan1_ph27232.model.DanhMuc;
import com.example.duan1_ph27232.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class qlDanhMucFragment extends AppCompatActivity implements DanhMucAdapter.Callback {
   DanhMucDao danhMucDao;
    RecyclerView recyclerView;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView idImageUser;
    EditText edtmadm,edttendm,tkdm;
    EditText edtmadm1,edttendm1;
    AppCompatButton btnthem,btnsua;
    TextView thoat,tongnv,thoat1;
    List<DanhMuc> list;
    DanhMucAdapter adapter;
    ImageView imgdm,back3;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_danhmuc);
        FloatingActionButton floatadd=findViewById(R.id.floatingadddm);
        recyclerView=findViewById(R.id.recycledanhmuc);
        tongnv=findViewById(R.id.danhmuc);
        tkdm=findViewById(R.id.tkdm);
        back3=findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlDanhMucFragment.this, MainActivity.class));
            }
        });
        tkdm.addTextChangedListener(new TextWatcher() {
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
        List<DanhMuc> filteredList = new ArrayList<>();
        for (DanhMuc nguoiDung: list){
                if (nguoiDung.getTenDM().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(nguoiDung);
                }
        }
        adapter.filterList(filteredList);
    }
    public void loaddata(){
        danhMucDao=new DanhMucDao(this);
        list = danhMucDao.getDSDanhMuc();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DanhMucAdapter(list,  this);
        recyclerView.setAdapter(adapter);
        tongnv.setText("Danh Mục: "+list.size());
    }
    public void showdialog(){
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_them_dm);
       edtmadm=dialog.findViewById(R.id.edt_madm);
        edttendm=dialog.findViewById(R.id.edt_tendm);
        btnthem=dialog.findViewById(R.id.btn_themdm);
        imgdm=dialog.findViewById(R.id.imgthemanhdm);
        imgdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick=new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser=Intent.createChooser(pick,"lua chon");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{pho});
                startActivityForResult(chosser,REQUEST_CODE_GALLERY);            }
        });
        edtmadm.setEnabled(false);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danhMucDao=new DanhMucDao(qlDanhMucFragment.this);
                DanhMuc khachHang=new DanhMuc();
                khachHang.setTenDM(edttendm.getText().toString());
                byte[] anh=imageViewToByte(imgdm);
khachHang.setAnh(anh);
               danhMucDao.them_danhmuc(khachHang);
                loaddata();
                Toast.makeText(qlDanhMucFragment.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        thoat=dialog.findViewById(R.id.tv_thoatdm);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void xoa(DanhMuc nguoiDung) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                danhMucDao.xoa_damuc(nguoiDung.getMaDM());
                Toast.makeText(qlDanhMucFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
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
    public void sua(DanhMuc nguoiDung) {
        final Dialog dialog = new Dialog(this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_dm);
        edtmadm1=dialog.findViewById(R.id.edt_madm1);
        edttendm1=dialog.findViewById(R.id.edt_tendm1);
        edtmadm1.setText(Integer.toString(nguoiDung.getMaDM()));
        edttendm1.setText(nguoiDung.getTenDM());
        edtmadm1.setEnabled(false);
        btnsua=dialog.findViewById(R.id.btn_themdm1);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                danhMucDao=new DanhMucDao(qlDanhMucFragment.this);
                DanhMuc khachHang=new DanhMuc();
                khachHang.setMaDM(Integer.parseInt(edtmadm1.getText().toString()));
                khachHang.setTenDM(edttendm1.getText().toString());
                danhMucDao.sua_danhmuc(khachHang);
                loaddata();
                Toast.makeText(qlDanhMucFragment.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat1=dialog.findViewById(R.id.tv_thoatdm1);
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
                Toast.makeText(qlDanhMucFragment.this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
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


}
