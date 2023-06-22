package com.example.duan1_ph27232.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import  com.example.duan1_ph27232.fragment.qlHoaDonFragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.HoaDonDAO;
import com.example.duan1_ph27232.model.HoaDon;
import com.example.duan1_ph27232.model.KhachHang;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HoaDon> list;
    private HoaDonDAO hoaDonDAO;
    private Callback callback;
    qlHoaDonFragment qlHoaDonFragment;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list, HoaDonDAO hoaDonDAO, Callback callback) {
        this.context = context;
        this.list = list;
        this.hoaDonDAO = hoaDonDAO;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HoaDon hoaDon = list.get(position);
        holder.tvMaHoaDon.setText("Mã HD: "+list.get(position).getMaHD());
        holder.txtMaSP.setText("Mã SP: "+list.get(position).getMaSP());
        holder.txtTenSP.setText("Tên sản phẩm: "+list.get(position).getTenSP());
        holder.txtMausac.setText("Màu sắc: "+list.get(position).getMausac());
        holder.txtGia.setText("Giá: "+list.get(position).getGiaban());
        holder.txtMaKM.setText("Chiết khấu: "+list.get(position).getChietkhau()+"%");
    holder.txtTongTien.setText("Tổng Tiền: "+(list.get(position).getGiaban()*list.get(position).getSoluong()-((list.get(position).getGiaban()*list.get(position).getSoluong()*list.get(position).getChietkhau())/100)));

        holder.txtTenKH.setText("Tên khách hàng: "+list.get(position).getTenKH());
        holder.txtSdtKH.setText("Số diện thoại: "+list.get(position).getSodienthoai());
        holder.txtDchiKH.setText("Địa chỉ: "+list.get(position).getDiachi());
        holder.txtNgayTao.setText("Ngày tạo: "+list.get(position).getNgaytao());
        holder.txtTrangThai.setText(list.get(position).getTrangthai());
        if(holder.txtTrangThai.getText().toString().equals("Đã thanh toán")){
            holder.btnThanhToan.setVisibility(View.GONE);
        }


        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                //boolean kiemtra = hoaDonDAO.thayDoiTrangThai(list.get(positi on).getMaHD());
                holder.txtTrangThai.setText("Đã thanh toán");

                String trangthai = holder.txtTrangThai.getText().toString();
                String tenKH = holder.txtTenKH.getText().toString();
                String tenSP = holder.txtTenSP.getText().toString();
                String mau = holder.txtMausac.getText().toString();
                String gia = holder.txtGia.getText().toString();
                String maKM = holder.txtMaKM.getText().toString();
                String tongTien = holder.txtTongTien.getText().toString();
                String sdt = holder.txtSdtKH.getText().toString();
                String diaChi = holder.txtDchiKH.getText().toString();
                String ngayTao = holder.txtNgayTao.getText().toString();
                String maSP = holder.txtMaSP.getText().toString();
                HoaDon hoaDonq = new HoaDon(hoaDon.getMaHD(),hoaDon.getMaSP(),tenSP,hoaDon.getGiaban(),mau,tenKH,hoaDon.getSodienthoai(),diaChi,hoaDon.getChietkhau(),hoaDon.getThanhtoan(),ngayTao,trangthai);
                if(hoaDonDAO.sua_hd(hoaDonq) == true){
                    Toast.makeText(context, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                    holder.btnThanhToan.setVisibility(View.GONE);
                }else{
                    Toast.makeText(context, "Xác nhận thất bại", Toast.LENGTH_SHORT).show();

                }

            }
        });
        holder.itemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.xoa(list.get(position));
            }
        });
//        holder.tvTong.setText("Hóa đơn: "+list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaHoaDon,txtTenSP,txtMaSP,txtMausac,txtGia,txtTenKH,txtSdtKH,txtDchiKH,tvTong;
        TextView txtTongTien,txtNgayTao,txtTrangThai,txtMaKM;
        Button btnThanhToan;
        RecyclerView recyclerView;
        RelativeLayout itemHD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            txtMaSP = itemView.findViewById(R.id.tvMaSanPham);
            txtTenSP = itemView.findViewById(R.id.tvTenSP);
            txtMausac = itemView.findViewById(R.id.tvMauSac);
            txtGia = itemView.findViewById(R.id.tvGia);
            txtTenKH = itemView.findViewById(R.id.tvTenKH);
            txtSdtKH = itemView.findViewById(R.id.tvSdtKH);
            txtDchiKH = itemView.findViewById(R.id.tvDchiKH);
            txtMaKM = itemView.findViewById(R.id.tvMaKM);
            txtTongTien = itemView.findViewById(R.id.tvTongTien);
            txtNgayTao = itemView.findViewById(R.id.tvNgayTao);
            txtTrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnThanhToan = itemView.findViewById(R.id.btnThanhToan);
            recyclerView = itemView.findViewById(R.id.id_recycler_hoadon);
            itemHD = itemView.findViewById(R.id.itemHD1);
            tvTong = itemView.findViewById(R.id.TongHD);

        }
    }
    public interface Callback {
            void xoa(HoaDon nguoiDung);
        void sua(HoaDon nguoiDung);
    }
}
