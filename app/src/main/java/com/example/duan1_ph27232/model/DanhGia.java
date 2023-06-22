package com.example.duan1_ph27232.model;

public class DanhGia {
    private int maDG;
    private String ghichu;
    private int maKH;

    public DanhGia(int maDG, String ghichu, int maKH) {
        this.maDG = maDG;
        this.ghichu = ghichu;
        this.maKH = maKH;
    }

    public DanhGia() {
    }

    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }
}
