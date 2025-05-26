package Modell;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GajiCrew {
    private int idGaji;
    private int idCrew;
    private String namaCrew; // BARU: Menambahkan properti ini untuk menyimpan nama crew
    private LocalDate tanggalGaji;
    private double jumlahGaji;
    private String nomorRekening;
    private LocalDateTime tanggalPembayaran;
    private String keterangan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GajiCrew() {}

    // Konstruktor dasar (tanpa idGaji, createdAt, updatedAt, tanggalPembayaran)
    public GajiCrew(int idCrew, LocalDate tanggalGaji, double jumlahGaji, String nomorRekening) {
        this.idCrew = idCrew;
        this.tanggalGaji = tanggalGaji;
        this.jumlahGaji = jumlahGaji;
        this.nomorRekening = nomorRekening;
    }

    // Konstruktor dengan namaCrew (berguna saat mengambil data dari JOIN)
    public GajiCrew(int idCrew, String namaCrew, LocalDate tanggalGaji, double jumlahGaji, String nomorRekening) {
        this.idCrew = idCrew;
        this.namaCrew = namaCrew;
        this.tanggalGaji = tanggalGaji;
        this.jumlahGaji = jumlahGaji;
        this.nomorRekening = nomorRekening;
    }

    // KONSTRUKTOR LENGKAP (untuk memuat data lengkap dari database)
    public GajiCrew(int idGaji, int idCrew, String namaCrew, LocalDate tanggalGaji, double jumlahGaji, String nomorRekening,
                    LocalDateTime tanggalPembayaran, String keterangan, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idGaji = idGaji;
        this.idCrew = idCrew;
        this.namaCrew = namaCrew; // Memasukkan namaCrew di konstruktor lengkap
        this.tanggalGaji = tanggalGaji;
        this.jumlahGaji = jumlahGaji;
        this.nomorRekening = nomorRekening;
        this.tanggalPembayaran = tanggalPembayaran;
        this.keterangan = keterangan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getter dan Setter ---
    public int getIdGaji() {
        return idGaji;
    }

    public void setIdGaji(int idGaji) {
        this.idGaji = idGaji;
    }

    public int getIdCrew() {
        return idCrew;
    }

    public void setIdCrew(int idCrew) {
        this.idCrew = idCrew;
    }

    // BARU: Getter dan Setter untuk namaCrew
    public String getNamaCrew() {
        return namaCrew;
    }

    public void setNamaCrew(String namaCrew) {
        this.namaCrew = namaCrew;
    }

    public LocalDate getTanggalGaji() {
        return tanggalGaji;
    }

    public void setTanggalGaji(LocalDate tanggalGaji) {
        this.tanggalGaji = tanggalGaji;
    }

    public double getJumlahGaji() {
        return jumlahGaji;
    }

    public void setJumlahGaji(double jumlahGaji) {
        this.jumlahGaji = jumlahGaji;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDateTime tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}