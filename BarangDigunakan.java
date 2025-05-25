import java.time.LocalDateTime;

public class BarangDigunakan {
    private int idBarangDigunakan;
    private int idEvent;
    private int idBarang;
    private int jumlahKeluar;
    private LocalDateTime tanggalKeluar;
    private LocalDateTime tanggalMasuk;
    private String kondisiSetelahMasuk;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BarangDigunakan() {}

    public BarangDigunakan(int idBarangDigunakan, int idEvent, int idBarang, int jumlahKeluar,
                           LocalDateTime tanggalKeluar, LocalDateTime tanggalMasuk, String kondisiSetelahMasuk,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idBarangDigunakan = idBarangDigunakan;
        this.idEvent = idEvent;
        this.idBarang = idBarang;
        this.jumlahKeluar = jumlahKeluar;
        this.tanggalKeluar = tanggalKeluar;
        this.tanggalMasuk = tanggalMasuk;
        this.kondisiSetelahMasuk = kondisiSetelahMasuk;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdBarangDigunakan() { return idBarangDigunakan; }
    public void setIdBarangDigunakan(int idBarangDigunakan) { this.idBarangDigunakan = idBarangDigunakan; }

    public int getIdEvent() { return idEvent; }
    public void setIdEvent(int idEvent) { this.idEvent = idEvent; }

    public int getIdBarang() { return idBarang; }
    public void setIdBarang(int idBarang) { this.idBarang = idBarang; }

    public int getJumlahKeluar() { return jumlahKeluar; }
    public void setJumlahKeluar(int jumlahKeluar) { this.jumlahKeluar = jumlahKeluar; }

    public LocalDateTime getTanggalKeluar() { return tanggalKeluar; }
    public void setTanggalKeluar(LocalDateTime tanggalKeluar) { this.tanggalKeluar = tanggalKeluar; }

    public LocalDateTime getTanggalMasuk() { return tanggalMasuk; }
    public void setTanggalMasuk(LocalDateTime tanggalMasuk) { this.tanggalMasuk = tanggalMasuk; }

    public String getKondisiSetelahMasuk() { return kondisiSetelahMasuk; }
    public void setKondisiSetelahMasuk(String kondisiSetelahMasuk) { this.kondisiSetelahMasuk = kondisiSetelahMasuk; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}