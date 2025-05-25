import java.time.LocalDate;
import java.time.LocalDateTime;

public class GajiCrew {
    private int idGaji;
    private int idCrew;
    private LocalDate tanggalGaji;
    private double jumlahGaji;
    private LocalDateTime tanggalPembayaran;
    private String keterangan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GajiCrew() {}

    public GajiCrew(int idGaji, int idCrew, LocalDate tanggalGaji, double jumlahGaji) {
        this.idGaji = idGaji;
        this.idCrew = idCrew;
        this.tanggalGaji = tanggalGaji;
        this.jumlahGaji = jumlahGaji;
    }

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
