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
import com.example.duan1_ph27232.model.NhanVien;

import java.util.List;

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
        NhanVien nv = list.get(position);
        holder.txtHoTen.setText("Họ và Tên: " + nv.getTenNV());
        holder.txtngaysinh.setText("Ngày Sinh: " + nv.getNgaysinh());
        holder.txtsodienthoai.setText("Số Điện Thoại: " + Integer.toString(nv.getSodienthoai()));
        holder.txtUser.setText("Username: " + nv.getUsername());
        holder.txtPass.setText("Password: " + nv.getPassword());
        holder.txtChucVu.setText("Chức vụ: " + nv.getVaitro());

        // Xử lý hình ảnh, tránh crash nếu dữ liệu ảnh bị null
        byte[] anh = nv.getAnh();
        if (anh != null && anh.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
            holder.imgnv.setImageBitmap(bitmap);
        } else {
            holder.imgnv.setImageResource(R.drawable.default_image);
        }

        // Xử lý sự kiện click để sửa nhân viên
        holder.lnnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.sua(nv);
                }
            }
        });

        // Xử lý sự kiện long click để xóa nhân viên
        holder.lnnv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (callback != null) {
                    callback.xoa(nv);
                }
                return true;
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