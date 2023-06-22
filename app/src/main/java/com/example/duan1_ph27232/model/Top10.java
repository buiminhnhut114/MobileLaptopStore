package com.example.duan1_ph27232.model;

public class Top10 {
    private String tenSP;
    private int soluongban;

    public Top10() {
    }

    public Top10(String tenSP, int soluongban) {
        this.tenSP = tenSP;
        this.soluongban = soluongban;
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
}
