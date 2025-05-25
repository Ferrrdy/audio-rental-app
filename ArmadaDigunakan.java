import java.time.LocalDateTime;

public class ArmadaDigunakan {
    private int idArmadaDigunakan;
    private int idEvent;
    private int idArmada;
    private LocalDateTime tanggalKeluar;
    private LocalDateTime tanggalMasuk;
    private String kondisiSetelahMasuk;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArmadaDigunakan() {}

    public ArmadaDigunakan(int idArmadaDigunakan, int idEvent, int idArmada, LocalDateTime tanggalKeluar,
                           LocalDateTime tanggalMasuk, String kondisiSetelahMasuk, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.idArmadaDigunakan = idArmadaDigunakan;
        this.idEvent = idEvent;
        this.idArmada = idArmada;
        this.tanggalKeluar = tanggalKeluar;
        this.tanggalMasuk = tanggalMasuk;
        this.kondisiSetelahMasuk = kondisiSetelahMasuk;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getIdArmadaDigunakan() { return idArmadaDigunakan; }
    public void setIdArmadaDigunakan(int idArmadaDigunakan) { this.idArmadaDigunakan = idArmadaDigunakan; }

    public int getIdEvent() { return idEvent; }
    public void setIdEvent(int idEvent) { this.idEvent = idEvent; }

    public int getIdArmada() { return idArmada; }
    public void setIdArmada(int idArmada) { this.idArmada = idArmada; }

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