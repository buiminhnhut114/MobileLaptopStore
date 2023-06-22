package com.example.duan1_ph27232.model;

public class NhanVien {
    private int maNV;
    private String tenNV;
    private String ngaysinh;
    private int sodienthoai;
    private String username;
    private String password;
    private String vaitro;
    private byte[] anh;

    public NhanVien(int maNV, String tenNV, String ngaysinh, int sodienthoai, String username, String password, String vaitro, byte[] anh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaysinh = ngaysinh;
        this.sodienthoai = sodienthoai;
        this.username = username;
        this.password = password;
        this.vaitro = vaitro;
        this.anh = anh;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public NhanVien() {
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }


}
