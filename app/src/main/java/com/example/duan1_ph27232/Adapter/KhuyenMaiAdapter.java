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
import com.example.duan1_ph27232.model.KhuyenMai;
import com.example.duan1_ph27232.model.NhanVien;

import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder>{
    private List<KhuyenMai> list;
    private Callback callback;
    public KhuyenMaiAdapter(List<KhuyenMai> list, Callback callback) {
        this.list = list;
        this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_khuyenmai,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtmakm.setText("Mã Khuyến Mãi: "+Integer.toString(list.get(position).getMaKM()));
        holder.txttungay.setText(list.get(position).getTungay());
        holder.txtchietkhau.setText("Chiết khấu: "+Integer.toString(list.get(position).getChietkhau())+"%");
        holder.txtdenngay.setText(list.get(position).getDenngay());
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
        TextView txtmakm,txttungay,txtdenngay,txtchietkhau;
        LinearLayout lnnv;
        ImageView imgnv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmakm = itemView.findViewById(R.id.txtmakm);
            txttungay = itemView.findViewById(R.id.txttungay);
            txtdenngay= itemView.findViewById(R.id.txtdenngay);
            txtchietkhau = itemView.findViewById(R.id.txtchietkhau);
            imgnv=itemView.findViewById(R.id.imgkhuyenmai);
            lnnv=itemView.findViewById(R.id.lnkm);
        }
    }

    public  interface Callback{
        void xoa(KhuyenMai nguoiDung);
        void sua(KhuyenMai nguoiDung);
    }
}
