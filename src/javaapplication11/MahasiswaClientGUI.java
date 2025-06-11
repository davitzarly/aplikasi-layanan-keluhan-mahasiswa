/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication11;

/**
 *
 * @author Davit Zarly
 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MahasiswaClientGUI extends JFrame {
    private JTextField namaField;
    private JTextArea keluhanArea;
    private JButton kirimButton;
    private JLabel statusLabel;
    private JTextArea balasanArea;

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public MahasiswaClientGUI() {
        setTitle("Layanan Keluhan Mahasiswa");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Input form
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form Keluhan"));

        inputPanel.add(new JLabel("Nama Mahasiswa:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Isi Keluhan:"));
        keluhanArea = new JTextArea(5, 20);
        keluhanArea.setLineWrap(true);
        keluhanArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(keluhanArea);
        inputPanel.add(scrollPane);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Tombol kirim
        kirimButton = new JButton("Kirim Keluhan");
        panel.add(kirimButton, BorderLayout.SOUTH);

        // Bagian atas untuk status dan balasan admin
        JPanel atasPanel = new JPanel(new GridLayout(3, 1));
        statusLabel = new JLabel("Status: -");
        balasanArea = new JTextArea("Balasan Admin: -");
        balasanArea.setEditable(false);
        balasanArea.setBackground(new Color(240, 240, 240));
        balasanArea.setLineWrap(true);
        balasanArea.setWrapStyleWord(true);
        atasPanel.add(statusLabel);
        atasPanel.add(new JLabel("Balasan Admin:"));
        atasPanel.add(new JScrollPane(balasanArea));

        panel.add(atasPanel, BorderLayout.NORTH);

        add(panel);

        // Action
        kirimButton.addActionListener(e -> kirimKeluhan());
    }

    private void kirimKeluhan() {
        String nama = namaField.getText().trim();
        String isi = keluhanArea.getText().trim();

        if (nama.isEmpty() || isi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon isi nama dan keluhan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Keluhan keluhan = new Keluhan(nama, isi);
            out.writeObject(keluhan);
            out.flush();

            Keluhan respon = (Keluhan) in.readObject();

            statusLabel.setText("Status: " + respon.getStatus());
            balasanArea.setText(respon.getBalasanAdmin());

            JOptionPane.showMessageDialog(this, "Keluhan dikirim & dibalas oleh admin!");

            // Reset input
            namaField.setText("");
            keluhanArea.setText("");

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal terhubung ke server.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaClientGUI().setVisible(true));
    }
}
