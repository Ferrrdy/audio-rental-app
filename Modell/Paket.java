package Modell;
import java.time.LocalDateTime;

public class Paket {
    private int idPaket;
    private String namaPaket;
    private String keterangan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Paket() {}

    public Paket(int idPaket, String namaPaket, String keterangan) {
        this.idPaket = idPaket;
        this.namaPaket = namaPaket;
        this.keterangan = keterangan;
    }

    public int getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
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
