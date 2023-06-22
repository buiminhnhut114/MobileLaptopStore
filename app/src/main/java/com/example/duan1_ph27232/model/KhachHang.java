package com.example.duan1_ph27232.model;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String diachi;
    private String email;
    private int sodienthoai;
    private byte[] anh;

    public KhachHang(int maKH, String tenKH, String diachi, String email, int sodienthoai, byte[] anh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diachi = diachi;
        this.email = email;
        this.sodienthoai = sodienthoai;
        this.anh = anh;
    }

    public KhachHang() {
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
