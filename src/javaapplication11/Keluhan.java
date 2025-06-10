/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication11;

/**
 *
 * @author Davit Zarly
 */






import java.io.Serializable;

public class Keluhan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String namaMahasiswa;
    private String isiKeluhan;
    private String status;
    private String balasanAdmin;

    public Keluhan(String namaMahasiswa, String isiKeluhan) {
        this.namaMahasiswa = namaMahasiswa;
        this.isiKeluhan = isiKeluhan;
        this.status = "Belum Ditanggapi";
        this.balasanAdmin = "-";
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getIsiKeluhan() {
        return isiKeluhan;
    }

    public String getStatus() {
        return status;
    }

    public String getBalasanAdmin() {
        return balasanAdmin;
    }

    public void setBalasanAdmin(String pesan) {
        this.balasanAdmin = pesan;
        this.status = "Sudah Ditanggapi";
    }

    @Override
    public String toString() {
        return "Nama: " + namaMahasiswa +
               "\nKeluhan: " + isiKeluhan +
               "\nStatus: " + status +
               "\nBalasan Admin: " + balasanAdmin;
    }
}
