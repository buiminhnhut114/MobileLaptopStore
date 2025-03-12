package com.example.duan1_ph27232.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.model.Top10;

import java.util.List;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {

    private List<Top10> list;

    public Top10Adapter(List<Top10> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Top10Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10Adapter.ViewHolder holder, int position) {
        Top10 product = list.get(position);
        holder.tvTenSP.setText("Tên sản phẩm: " + product.getTenSP());
        holder.tvLuotBan.setText("Số lượng bán: " + product.getSoluongban());

        // Hiển thị ảnh từ dữ liệu BLOB
        byte[] imageData = product.getAnh();
        if (imageData != null && imageData.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            holder.ivIcon.setImageBitmap(bitmap);
        } else {
            // Nếu không có ảnh thì hiển thị ảnh mặc định
            holder.ivIcon.setImageResource(R.drawable.laptop1);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP, tvLuotBan;
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvLuotBan = itemView.findViewById(R.id.tvLuotBan);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
