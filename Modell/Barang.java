package Modell;
import java.time.LocalDateTime;

public class Barang {
    private int idBarang;
    private String namaBarang;
    private int idKategori;
    private String kondisi;
    private int jumlahTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Barang() {}

    public Barang(int idBarang, String namaBarang, int idKategori, String kondisi, int jumlahTotal,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.idKategori = idKategori;
        this.kondisi = kondisi;
        this.jumlahTotal = jumlahTotal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; }

    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }

    public int getIdKategori() { return idKategori; }
    public void setIdKategori(int idKategori) { this.idKategori = idKategori; }

    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }

    public int getJumlahTotal() { return jumlahTotal; }
    public void setJumlahTotal(int jumlahTotal) { this.jumlahTotal = jumlahTotal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}