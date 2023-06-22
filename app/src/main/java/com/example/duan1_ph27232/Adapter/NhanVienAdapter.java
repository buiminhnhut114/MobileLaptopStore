package com.example.duan1_ph27232.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.Admin;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder>{
    private List<NhanVien> list;
    private Callback callback;
    public NhanVienAdapter(List<NhanVien> list,Callback callback) {
        this.list = list;
       this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nhanvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtHoTen.setText("Họ và Tên: "+list.get(position).getTenNV());
        holder.txtngaysinh.setText("Ngày Sinh: "+list.get(position).getNgaysinh());
        holder.txtsodienthoai.setText("Số Điện Thoại: "+Integer.toString(list.get(position).getSodienthoai()));
        holder.txtUser.setText("Username: "+list.get(position).getUsername());
        holder.txtPass.setText("Password: "+list.get(position).getPassword());
        holder.txtChucVu.setText("Chức vụ: "+list.get(position).getVaitro());
        byte[] anh=list.get(position).getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0,anh.length);
        holder.imgnv.setImageBitmap(bitmap);
holder.lnnv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        callback.sua(list.get(position));
    }
});
holder.lnnv.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        callback.xoa(list.get(position));
        return false;
    }
});
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtHoTen,txtChucVu,txtUser,txtPass,txtngaysinh,txtsodienthoai;
        LinearLayout lnnv;
        ImageView imgnv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txtUsername);
            txtHoTen = itemView.findViewById(R.id.txtHovaTen);
            txtChucVu = itemView.findViewById(R.id.txtChucVu);
            txtPass = itemView.findViewById(R.id.txtPassword);
            txtngaysinh=itemView.findViewById(R.id.txtngaysinh);
            txtsodienthoai=itemView.findViewById(R.id.txtsodienthoai);
            imgnv=itemView.findViewById(R.id.imgnv);
lnnv=itemView.findViewById(R.id.lnnhanvien);
        }
    }

    public  interface Callback{
        void xoa(NhanVien nguoiDung);
        void sua(NhanVien nguoiDung);
    }
    public void filterList(List<NhanVien> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}