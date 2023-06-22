package com.example.duan1_ph27232.model;

public class KhuyenMai {
    private int maKM;
    private int chietkhau;
    private String tungay;
    private String denngay;
    private byte[] anh;

    public KhuyenMai(int maKM, int chietkhau, String tungay, String denngay, byte[] anh) {
        this.maKM = maKM;
        this.chietkhau = chietkhau;
        this.tungay = tungay;
        this.denngay = denngay;
        this.anh = anh;
    }

    public KhuyenMai() {
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(int chietkhau) {
        this.chietkhau = chietkhau;
    }

    public String getTungay() {
        return tungay;
    }

    public void setTungay(String tungay) {
        this.tungay = tungay;
    }

    public String getDenngay() {
        return denngay;
    }

    public void setDenngay(String denngay) {
        this.denngay = denngay;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
