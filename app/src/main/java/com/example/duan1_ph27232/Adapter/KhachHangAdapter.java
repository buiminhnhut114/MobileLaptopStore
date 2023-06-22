package com.example.duan1_ph27232.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.model.KhachHang;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {
    private List<KhachHang> list;
    private Callback callback;

    public KhachHangAdapter(List<KhachHang> list, Callback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtHoTen.setText("Họ và Tên: " + list.get(position).getTenKH());
        holder.txtsodienthoai.setText("Số điện thoại: " + list.get(position).getSodienthoai());
        holder.txtdiachi.setText("Địa chỉ: " + list.get(position).getDiachi());
        holder.txtemail.setText("Email: " + list.get(position).getEmail());
        byte[] anh=list.get(position).getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0,anh.length);
        holder.imgkh.setImageBitmap(bitmap);
        holder.lnkh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callback.xoa(list.get(position));
                return false;
            }
        });
        holder.lnkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.sua(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHoTen, txtemail, txtdiachi, txtsodienthoai;
LinearLayout lnkh;
ImageView imgkh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdiachi = itemView.findViewById(R.id.txtdiachi);
            txtHoTen = itemView.findViewById(R.id.txtHoten);
            txtsodienthoai = itemView.findViewById(R.id.txtsdt);
            txtemail = itemView.findViewById(R.id.txtemail);
            lnkh=itemView.findViewById(R.id.lnkh);
            imgkh=itemView.findViewById(R.id.imgkh);

        }
    }

    public interface Callback {
        void xoa(KhachHang nguoiDung);
        void sua(KhachHang nguoiDung);
    }

    public void filterList(List<KhachHang> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}
