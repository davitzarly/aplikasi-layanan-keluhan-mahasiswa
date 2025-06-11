/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication11;

/**
 *
 * @author Davit Zarly
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MahasiswaClient {
    private static final String SERVER_HOST = "localhost"; // Ganti jika server beda IP
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("=== Kirim Keluhan Mahasiswa ===");

            System.out.print("Masukkan nama Anda: ");
            String nama = scanner.nextLine();

            System.out.print("Tulis keluhan Anda: ");
            String isi = scanner.nextLine();

            // Buat objek keluhan dan kirim ke server
            Keluhan keluhan = new Keluhan(nama, isi);
            out.writeObject(keluhan);
            out.flush();

            // Terima status dari server
            Keluhan respon = (Keluhan) in.readObject();
            System.out.println("\n=== Status Keluhan ===");
            System.out.println(respon);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Tidak dapat terhubung ke server.");
            e.printStackTrace();
        }
    }
}
