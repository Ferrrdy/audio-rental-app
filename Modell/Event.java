package Modell;
import java.time.LocalDateTime;

public class Event {
    private int idEvent;
    private int durasi;
    private String namaEvent;
    private LocalDateTime tanggalMulai;
    private LocalDateTime tanggalSelesai;
    private String lokasi;
    private String keterangan;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Event() {}

    public Event(int idEvent, int durasi, String namaEvent, LocalDateTime tanggalMulai, LocalDateTime tanggalSelesai,
                 String lokasi, String keterangan, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idEvent = idEvent;
        this.durasi = durasi;
        this.namaEvent = namaEvent;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.lokasi = lokasi;
        this.keterangan = keterangan;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdEvent() { return idEvent; }
    public void setIdEvent(int idEvent) { this.idEvent = idEvent; }

    public int getDurasi() { return durasi; }
    public void setDurasi(int durasi) { this.durasi = durasi; }

    public String getNamaEvent() { return namaEvent; }
    public void setNamaEvent(String namaEvent) { this.namaEvent = namaEvent; }

    public LocalDateTime getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(LocalDateTime tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public LocalDateTime getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(LocalDateTime tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }

    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}