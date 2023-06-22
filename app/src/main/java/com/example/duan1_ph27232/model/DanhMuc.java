package com.example.duan1_ph27232.model;

public class DanhMuc {
    private int maDM;
    private String tenDM;
    private byte[] anh;

    public DanhMuc(int maDM, String tenDM, byte[] anh) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.anh = anh;
    }

    public DanhMuc() {
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
}
