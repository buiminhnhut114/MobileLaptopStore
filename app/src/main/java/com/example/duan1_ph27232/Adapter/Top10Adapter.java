package com.example.duan1_ph27232.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.NhanVienDAO;
import com.example.duan1_ph27232.model.NhanVien;
import com.example.duan1_ph27232.model.Top10;

import java.util.ArrayList;
import java.util.List;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{
    private Context context;
    private List<Top10> list;


    public Top10Adapter(List<Top10> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public Top10Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10Adapter.ViewHolder holder, int position) {
        Top10 userTop = list.get(position);
        holder.tvTenSP.setText("Tên sản phẩm: "+userTop.getTenSP());
        holder.tvLuotBan.setText("Số lượng bán: "+Integer.toString(userTop.getSoluongban()));
    }

    @Override
    public int getItemCount() {
//        return list.size();
        return list==null?0: list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSP,tvLuotBan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvLuotBan = itemView.findViewById(R.id.tvLuotBan);

        }
    }

}