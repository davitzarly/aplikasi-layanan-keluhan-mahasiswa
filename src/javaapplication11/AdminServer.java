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
import java.util.*;

public class AdminServer {
    private static final int PORT = 12345;
    private static List<Keluhan> daftarKeluhan = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Server Keluhan Mahasiswa ===");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Menunggu keluhan dari client...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client terhubung.");

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                Keluhan keluhan = (Keluhan) in.readObject();
                System.out.println("\n--- Keluhan Masuk ---");
                System.out.println("Nama: " + keluhan.getNamaMahasiswa());
                System.out.println("Keluhan: " + keluhan.getIsiKeluhan());

                Scanner scanner = new Scanner(System.in);
                System.out.print("Balas keluhan ini: ");
                String balasan = scanner.nextLine();

                keluhan.setBalasanAdmin(balasan);
                daftarKeluhan.add(keluhan);

                out.writeObject(keluhan);
                out.flush();

                clientSocket.close();
                System.out.println("✅ Balasan dikirim ke mahasiswa.\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
