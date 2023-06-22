package com.example.duan1_ph27232.model;

public class Admin {
    private int maAM;
    private String tenAM,tenDN,matkhau;

    public Admin(int maAM, String tenAM, String tenDN, String matkhau) {
        this.maAM = maAM;
        this.tenAM = tenAM;
        this.tenDN = tenDN;
        this.matkhau = matkhau;
    }

    public Admin() {
    }

    public int getMaAM() {
        return maAM;
    }

    public void setMaAM(int maAM) {
        this.maAM = maAM;
    }

    public String getTenAM() {
        return tenAM;
    }

    public void setTenAM(String tenAM) {
        this.tenAM = tenAM;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
