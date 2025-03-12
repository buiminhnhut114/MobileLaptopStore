package com.example.duan1_ph27232.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.model.SanPham;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sp = list.get(position);

        holder.txtmasp.setText("Mã Sản phẩm: " + sp.getMaSP());
        holder.txtsoluong.setText("Số lượng: " + sp.getSoluong());
        holder.txtmausac.setText("Màu sắc: " + sp.getMausac());
        holder.txttensp.setText("Tên sản phẩm: " + sp.getTenSP());

        // Định dạng giá bán
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.'); // Dùng dấu chấm làm dấu phân cách
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        String formattedPrice = decimalFormat.format(sp.getGiaban());

        // Set text và màu đỏ
        holder.txtgia.setText("Giá: " + formattedPrice + " VNĐ");
        holder.txtgia.setTextColor(Color.RED);

        // Hiển thị ảnh sản phẩm
        byte[] anh = sp.getAnh();
        if(anh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
            holder.imgkh.setImageBitmap(bitmap);
        }

        // Xử lý sự kiện click và long click
        holder.lnkh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callback.xoa(sp);
                return false;
            }
        });
        holder.lnkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.sua(sp);
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
