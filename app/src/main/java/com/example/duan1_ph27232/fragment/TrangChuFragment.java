package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1_ph27232.Adapter.PhotoAdapter;
import com.example.duan1_ph27232.LoginActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.model.Photo;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;


public class TrangChuFragment extends Fragment {
Activity context;
LinearLayout khohang,hoadon,khachang,doanhthu,nhanvien,khuyenmai;
TextView tvten,tvwelcome,tvnhanvien;
CircleImageView imgavtar;
ImageView iconnhanvien;
    ViewPager2 viewPager2;
    CircleIndicator3 circleIndicator3;
    PhotoAdapter adapter;
    int id= LoginActivity.id;
    private final int GALLERY_REQ_CODE = 1000;
    List<Photo> list;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager2.getCurrentItem();
            if (currentPosition == list.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View view=inflater.inflate(R.layout.fragment_trang_chu, container, false);
        tvten=view.findViewById(R.id.tvten);
        tvwelcome=view.findViewById(R.id.tvwelcome);
        imgavtar=view.findViewById(R.id.iv_avatar);
        viewPager2 = view.findViewById(R.id.id_viewpager2);
        iconnhanvien=view.findViewById(R.id.iconnhanvien);
        tvnhanvien=view.findViewById(R.id.tvnhanvien);
        circleIndicator3 = view.findViewById(R.id.id_circleindicator3);
        if (id!=1){
            imgavtar.setImageResource(R.drawable.avatar);
            tvwelcome.setText("WELCOME USER");
            tvten.setText("phone shop employee");
        }
        else {
            imgavtar.setImageResource(R.drawable.tra);
            tvwelcome.setText("WELCOME ADMIN");
            tvten.setText("phone shop manager");
        }
        list = new ArrayList<>();
        adapter = new PhotoAdapter(getContext(), list);
        list.add(new Photo(R.drawable.iphone));
        list.add(new Photo(R.drawable.samsung));
        list.add(new Photo(R.drawable.vivo));
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 5000);
            }
        });

        return view ;
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);

    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }
    @Override
    public void onStart() {
        super.onStart();
        khohang=context.findViewById(R.id.id_kho_hang);
        khohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,qlDienThoaiFragment.class);
                startActivity(intent);
            }
        });
        hoadon=context.findViewById(R.id.id_hoa_don);
        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,qlHoaDonFragment.class));
            }
        });
        khachang=context.findViewById(R.id.id_khach_hang);
        khachang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,qlKhachHangFragment.class));
            }
        });
        doanhthu=context.findViewById(R.id.id_doanh_thu);
        doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,qlDoanhThuFragment.class));
            }
        });

        nhanvien=context.findViewById(R.id.id_nhan_vien);
        if (id!=1){
            iconnhanvien.setImageResource(R.drawable.top101);
            tvnhanvien.setText("Top 10");
            nhanvien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context,qlTop10Fragment.class));
                }
            });

        }
        else {
            iconnhanvien.setImageResource(R.drawable.accountant);
            tvnhanvien.setText("Nhân viên");
            nhanvien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context,qlNhanVienFragment.class));
                }
            });
        }


        khuyenmai=context.findViewById(R.id.id_khuyen_mai);
        khuyenmai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,qlKhuyenMaiFragment.class));
            }
        });
    }
}