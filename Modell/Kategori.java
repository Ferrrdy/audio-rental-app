package Modell;
import java.time.LocalDateTime;

public class Kategori {
    private int idKategori;
    private String namaKategori;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Kategori() {}

    public Kategori(int idKategori, String namaKategori, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdKategori() { return idKategori; }
    public void setIdKategori(int idKategori) { this.idKategori = idKategori; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}