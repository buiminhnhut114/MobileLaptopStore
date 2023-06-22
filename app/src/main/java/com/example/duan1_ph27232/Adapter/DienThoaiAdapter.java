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
import com.example.duan1_ph27232.model.SanPham;

import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.ViewHolder> {
    private List<SanPham> list;
    private Callback callback;

    public DienThoaiAdapter(List<SanPham> list, Callback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_dienthoai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtmasp.setText("Mã Sản phẩm: " +Integer.toString(list.get(position).getMaSP()) );
        holder.txtsoluong.setText("Số lượng: " + Integer.toString(list.get(position).getSoluong()) );
        holder.txtmausac.setText("Màu sắc: " + list.get(position).getMausac());
        holder.txtgia.setText("Gía: " + list.get(position).getGiaban()+" VNĐ");
        holder.txttensp.setText("Tên sản phẩm: "+list.get(position).getTenSP());
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
        TextView txtmasp, txtmausac, txtgia, txtsoluong,txttensp;
        LinearLayout lnkh;
        ImageView imgkh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensp=itemView.findViewById(R.id.txttensp);
            txtmasp = itemView.findViewById(R.id.txtmasanpham);
            txtmausac = itemView.findViewById(R.id.txtmausac);
            txtsoluong = itemView.findViewById(R.id.txtsoluong);
            txtgia = itemView.findViewById(R.id.txtgia);
            lnkh=itemView.findViewById(R.id.lnsp);
            imgkh=itemView.findViewById(R.id.imgsp);

        }
    }

    public interface Callback {
        void xoa(SanPham nguoiDung);
        void sua(SanPham nguoiDung);
    }

    public void filterList(List<SanPham> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}
