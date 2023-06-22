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
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.KhuyenMaiAdapter;
import com.example.duan1_ph27232.Adapter.NhanVienAdapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.KhuyenMaiDao;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.KhuyenMai;
import com.example.duan1_ph27232.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

public class qlKhuyenMaiFragment extends AppCompatActivity implements KhuyenMaiAdapter.Callback{
    KhuyenMaiDao khuyenMaiDao;
    RecyclerView recyclerView;
    final int REQUEST_CODE_GALLERY = 999;
    EditText edtchietkhau,edttungay,edtdenngay;
    EditText edtchietkhau1,edttungay1,edtdenngay1;
    AppCompatButton btnthem,btnsua;
    TextView thoat,tongkm,thoat1,makm;
    List<KhuyenMai> list;
   KhuyenMaiAdapter adapter;
    ImageView imgthemkm,back4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_khuyenmai);
        FloatingActionButton add=findViewById(R.id.fladdkm);
        recyclerView=findViewById(R.id.id_recycler_km);
        tongkm=findViewById(R.id.txtsokm);
        back4=findViewById(R.id.back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlKhuyenMaiFragment.this, MainActivity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
showdialog();
            }
        });
        loadData();
    }

    public void loadData() {
        khuyenMaiDao = new KhuyenMaiDao(qlKhuyenMaiFragment.this);
        list = khuyenMaiDao.getDSKhuyenmai();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new KhuyenMaiAdapter(list, this);
        recyclerView.setAdapter(adapter);
        tongkm.setText("Khuyến Mãi: " + list.size());
    }
    public void showdialog(){
        final Dialog dialog = new Dialog(qlKhuyenMaiFragment.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_them_km);
        edtchietkhau=dialog.findViewById(R.id.edt_chietkhau);
        edttungay=dialog.findViewById(R.id.edt_tungay);
        edtdenngay=dialog.findViewById(R.id.edt_denngay);
        btnthem=dialog.findViewById(R.id.btn_Themkm);
        imgthemkm=dialog.findViewById(R.id.imgthemanhkm);
        imgthemkm.setOnClickListener(new View.OnClickListener() {
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
        edttungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlKhuyenMaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edttungay.setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog1.show();
            }
        });
        edtdenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlKhuyenMaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtdenngay.setText(ngay + "/" + (thang + 1) + "/" + nam);
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
                khuyenMaiDao = new KhuyenMaiDao(qlKhuyenMaiFragment.this);
                KhuyenMai nv=new KhuyenMai();
                nv.setChietkhau(Integer.parseInt(edtchietkhau.getText().toString()));
                nv.setTungay(edttungay.getText().toString());
                nv.setDenngay(edtdenngay.getText().toString());
                byte[] anh=imageViewToByte(imgthemkm);
                nv.setAnh(anh);
                khuyenMaiDao.them_khuyenmai(nv);
                loadData();
                Toast.makeText(qlKhuyenMaiFragment.this, "thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat=dialog.findViewById(R.id.tv_Thoatkm);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void xoa(KhuyenMai nguoiDung) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                khuyenMaiDao.xoa_NguoiDung(nguoiDung.getMaKM());
                Toast.makeText(qlKhuyenMaiFragment.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
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
    public void sua(KhuyenMai nguoiDung) {
        final Dialog dialog = new Dialog(qlKhuyenMaiFragment.this, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_km);
        edtchietkhau1=dialog.findViewById(R.id.edt_chietkhau1);
        edttungay1=dialog.findViewById(R.id.edt_tungay1);
        edtdenngay1=dialog.findViewById(R.id.edt_denngay1);
        btnsua=dialog.findViewById(R.id.btn_suakm);
        edtchietkhau1.setText(Integer.toString(nguoiDung.getChietkhau()));
        edtdenngay1.setText(nguoiDung.getDenngay());
        edttungay1.setText(nguoiDung.getTungay());
        makm=dialog.findViewById(R.id.tvmakm1);
        makm.setText(Integer.toString(nguoiDung.getMaKM()));
        edttungay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlKhuyenMaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edttungay1.setText(ngay + "/" + (thang + 1) + "/" + nam);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                dialog1.show();
            }
        });
        edtdenngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePickerDialog dialog1 = new DatePickerDialog(qlKhuyenMaiFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1;
                        int ngay = i2;

                        edtdenngay1.setText(ngay + "/" + (thang + 1) + "/" + nam);
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
                khuyenMaiDao = new KhuyenMaiDao(qlKhuyenMaiFragment.this);
                KhuyenMai nv=new KhuyenMai();
                nv.setChietkhau(Integer.parseInt(edtchietkhau1.getText().toString()));
                nv.setTungay(edttungay1.getText().toString());
                nv.setDenngay(edtdenngay1.getText().toString());
                nv.setMaKM(Integer.parseInt(makm.getText().toString()));
                khuyenMaiDao.sua_NguoiDung(nv);
                loadData();
                Toast.makeText(qlKhuyenMaiFragment.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        thoat1=dialog.findViewById(R.id.tv_Thoatkm1);
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
                Toast.makeText(qlKhuyenMaiFragment.this, "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
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
                imgthemkm.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
