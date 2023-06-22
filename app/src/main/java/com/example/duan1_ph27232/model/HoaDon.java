package com.example.duan1_ph27232.model;

public class HoaDon {
    private int maHD;
    private int maSP;
    private String tenSP;
    private int giaban;
    private String mausac;
    private String tenKH;
    private int sodienthoai;
    private String diachi;
    private int chietkhau;
private int soluong;
    private int thanhtoan;
    private String ngaytao;
    private String trangthai;
    private int maKH;

    public HoaDon(int maHD, int maSP, String tenSP, int giaban, String mausac, String tenKH, int sodienthoai, String diachi, int chietkhau,  int thanhtoan, String ngaytao, String trangthai) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaban = giaban;
        this.mausac = mausac;
        this.tenKH = tenKH;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.chietkhau = chietkhau;
        this.thanhtoan = thanhtoan;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
    }

    public HoaDon(int maHD, int maSP, String tenSP, int giaban, String mausac, String tenKH, int sodienthoai, String diachi, int chietkhau, int soluong, int thanhtoan, String ngaytao, String trangthai, int maKH) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaban = giaban;
        this.mausac = mausac;
        this.tenKH = tenKH;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.chietkhau = chietkhau;
        this.soluong = soluong;
        this.thanhtoan = thanhtoan;
        this.ngaytao = ngaytao;
        this.trangthai = trangthai;
        this.maKH = maKH;
    }

    public HoaDon() {

    }

    public HoaDon(int maHD, int maSP, int chietkhau,  int thanhtoan, String ngaytao) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.chietkhau = chietkhau;
        this.thanhtoan = thanhtoan;
        this.ngaytao = ngaytao;
    }
//
//    public HoaDon() {
//    }


    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
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

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(int chietkhau) {
        this.chietkhau = chietkhau;
    }
    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}