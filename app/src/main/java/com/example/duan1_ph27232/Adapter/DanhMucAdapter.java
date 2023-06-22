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
import com.example.duan1_ph27232.model.DanhMuc;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.ViewHolder>{
    private List<DanhMuc> list;
    private Callback callback;
    public DanhMucAdapter(List<DanhMuc> list, Callback callback) {
        this.list = list;
        this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_danhmuc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtmadm.setText("Mã Danh Mục: "+list.get(position).getMaDM());
        holder.txttendm.setText("Tên Danh Mục: "+list.get(position).getTenDM());
        byte[] anh=list.get(position).getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0,anh.length);
        holder.imgdm.setImageBitmap(bitmap);
        holder.lndm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.sua(list.get(position));
            }
        });
        holder.lndm.setOnLongClickListener(new View.OnLongClickListener() {
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
        TextView txtmadm,txttendm;
        LinearLayout lndm;
        ImageView imgdm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmadm = itemView.findViewById(R.id.txtmadm);
            txttendm = itemView.findViewById(R.id.txttendm);
            lndm=itemView.findViewById(R.id.lndm);
            imgdm=itemView.findViewById(R.id.imgdanhmuc);
        }
    }

    public  interface Callback{
        void xoa(DanhMuc nguoiDung);
        void sua(DanhMuc nguoiDung);
    }
    public void filterList(List<DanhMuc> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}
