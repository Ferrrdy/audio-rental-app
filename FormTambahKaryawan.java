package UI;

import Modell.Crew;
import Controller.CrewController;

import javax.swing.*;
import java.awt.*;

public class FormTambahKaryawan extends JFrame {
    private JTextField tfNama, tfPosisi, tfGaji;
    private JButton btnSimpan, btnBatal;

    public FormTambahKaryawan() {
        super("Form Tambah Crew");
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        add(new JLabel("Nama Crew"));
        tfNama = new JTextField();
        add(tfNama);

        add(new JLabel("Posisi"));
        tfPosisi = new JTextField();
        add(tfPosisi);

        add(new JLabel("Gaji Bulanan"));
        tfGaji = new JTextField();
        add(tfGaji);

        btnBatal = new JButton("Batal");
        btnSimpan = new JButton("Simpan");
        add(btnBatal);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanCrew());
        btnBatal.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null);
    }

    private void simpanCrew() {
        String nama = tfNama.getText().trim();
        String posisi = tfPosisi.getText().trim();
        String gajiText = tfGaji.getText().trim();

        if (nama.isEmpty() || posisi.isEmpty() || gajiText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Semua field harus diisi.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double gaji;
        try {
            gaji = Double.parseDouble(gajiText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Gaji Bulanan harus berupa angka.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Crew crew = new Crew();
        crew.setNamaCrew(nama);
        crew.setPosisi(posisi);
        crew.setGajiBulanan(gaji);

        boolean berhasil = CrewController.insertCrew(crew);
        if (berhasil) {
            JOptionPane.showMessageDialog(this,
                "Data crew berhasil disimpan.",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Gagal menyimpan data.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormTambahKaryawan().setVisible(true);
        });
    }
}
