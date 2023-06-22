package com.example.duan1_ph27232.model;

public class SanPham {
    private int maSP;
    private String tenSP;
    private String ngaynhap;
    private int soluong;
    private int gianhap;
    private int giaban;
    private String mausac;
    private String tenDM;
    private byte[] anh;

    public SanPham(int maSP, String tenSP, String ngaynhap, int soluong, int gianhap, int giaban, String mausac, String tenDM, byte[] anh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.ngaynhap = ngaynhap;
        this.soluong = soluong;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.mausac = mausac;
        this.tenDM = tenDM;
        this.anh = anh;
    }

    public SanPham() {
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
}
