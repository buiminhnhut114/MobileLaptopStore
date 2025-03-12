package com.example.duan1_ph27232.model;

public class Top10 {
    private String tenSP;      // Tên sản phẩm
    private int soluongban;    // Tổng số lượng bán được
    private byte[] anh;        // Ảnh sản phẩm (BLOB)

    public Top10() {
    }

    public Top10(String tenSP, int soluongban, byte[] anh) {
        this.tenSP = tenSP;
        this.soluongban = soluongban;
        this.anh = anh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(int soluongban) {
        this.soluongban = soluongban;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
