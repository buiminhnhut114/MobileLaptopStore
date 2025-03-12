package com.example.duan1_ph27232.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.HoaDonDAO;
import com.example.duan1_ph27232.fragment.qlHoaDonFragment;
import com.example.duan1_ph27232.model.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HoaDon hoaDon = list.get(position);

        // Hiển thị thông tin hoá đơn
        holder.tvMaHoaDon.setText("Mã HD: " + hoaDon.getMaHD());
        holder.txtMaSP.setText("Mã SP: " + hoaDon.getMaSP());
        holder.txtTenSP.setText("Tên sản phẩm: " + hoaDon.getTenSP());
        holder.txtMausac.setText("Màu sắc: " + hoaDon.getMausac());
        holder.txtGia.setText("Giá: " + hoaDon.getGiaban());
        holder.txtMaKM.setText("Chiết khấu: " + hoaDon.getChietkhau() + "%");

        // Tính tổng tiền = giá * số lượng - chiết khấu
        int tongTien = hoaDon.getGiaban() * hoaDon.getSoluong()
                - (hoaDon.getGiaban() * hoaDon.getSoluong() * hoaDon.getChietkhau()) / 100;
        holder.txtTongTien.setText("Tổng Tiền: " + tongTien);

        // Thông tin khách hàng
        holder.txtTenKH.setText("Tên khách hàng: " + hoaDon.getTenKH());
        holder.txtSdtKH.setText("Số diện thoại: " + hoaDon.getSodienthoai());
        holder.txtDchiKH.setText("Địa chỉ: " + hoaDon.getDiachi());
        holder.txtNgayTao.setText("Ngày tạo: " + hoaDon.getNgaytao());

        // Trạng thái
        holder.txtTrangThai.setText(hoaDon.getTrangthai());
        if (holder.txtTrangThai.getText().toString().equals("Đã thanh toán")) {
            holder.btnThanhToan.setVisibility(View.GONE);
        }

        // Gán ảnh theo mã sản phẩm
        switch (hoaDon.getMaSP()) {
            case 3:
                holder.ivIcon.setImageResource(R.drawable.laptop2);
                break;
            case 4:
                holder.ivIcon.setImageResource(R.drawable.laptop3);
                break;
            case 5:
                holder.ivIcon.setImageResource(R.drawable.laptop4);
                break;
            case 6:
                holder.ivIcon.setImageResource(R.drawable.laptop5);
                break;
            case 7:
                holder.ivIcon.setImageResource(R.drawable.laptop6);
                break;
            default:
                // Mặc định hiển thị laptop1 nếu không thuộc các mã trên
                holder.ivIcon.setImageResource(R.drawable.laptop1);
                break;
        }

        // Xử lý sự kiện Thanh Toán
        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                holder.txtTrangThai.setText("Đã thanh toán");

                // Lấy các thông tin mới để cập nhật
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

                // Tạo đối tượng HoaDon mới để cập nhật
                HoaDon hoaDonq = new HoaDon(
                        hoaDon.getMaHD(),
                        hoaDon.getMaSP(),
                        tenSP,
                        hoaDon.getGiaban(),
                        mau,
                        hoaDon.getTenKH(),
                        hoaDon.getSodienthoai(),
                        hoaDon.getDiachi(),
                        hoaDon.getChietkhau(),
                        hoaDon.getThanhtoan(),
                        hoaDon.getNgaytao(),
                        trangthai
                );

                // Gọi hàm sửa_hd() để update trạng thái trong DB
                if (hoaDonDAO.sua_hd(hoaDonq)) {
                    Toast.makeText(context, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                    holder.btnThanhToan.setVisibility(View.GONE);
                } else {
                    Toast.makeText(context, "Xác nhận thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện click item để xóa
        holder.itemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.xoa(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder lưu trữ các View của item
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaHoaDon, txtTenSP, txtMaSP, txtMausac, txtGia, txtTenKH, txtSdtKH, txtDchiKH, tvTong;
        TextView txtTongTien, txtNgayTao, txtTrangThai, txtMaKM;
        Button btnThanhToan;
        RecyclerView recyclerView;
        RelativeLayout itemHD;

        // Thêm ImageView để hiển thị ảnh
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon  = itemView.findViewById(R.id.tvMaHoaDon);
            txtMaSP     = itemView.findViewById(R.id.tvMaSanPham);
            txtTenSP    = itemView.findViewById(R.id.tvTenSP);
            txtMausac   = itemView.findViewById(R.id.tvMauSac);
            txtGia      = itemView.findViewById(R.id.tvGia);
            txtTenKH    = itemView.findViewById(R.id.tvTenKH);
            txtSdtKH    = itemView.findViewById(R.id.tvSdtKH);
            txtDchiKH   = itemView.findViewById(R.id.tvDchiKH);
            txtMaKM     = itemView.findViewById(R.id.tvMaKM);
            txtTongTien = itemView.findViewById(R.id.tvTongTien);
            txtNgayTao  = itemView.findViewById(R.id.tvNgayTao);
            txtTrangThai= itemView.findViewById(R.id.tvTrangThai);
            btnThanhToan= itemView.findViewById(R.id.btnThanhToan);
            recyclerView= itemView.findViewById(R.id.id_recycler_hoadon);
            itemHD      = itemView.findViewById(R.id.itemHD1);
            tvTong      = itemView.findViewById(R.id.TongHD);

            // Ánh xạ ImageView
            ivIcon      = itemView.findViewById(R.id.ivIcon);
        }
    }

    // Giao diện callback để xử lý xóa/sửa
    public interface Callback {
        void xoa(HoaDon nguoiDung);
        void sua(HoaDon nguoiDung);
    }
}
