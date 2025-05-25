import java.time.LocalDateTime;

public class LogAktivitas {
    private int idLog;
    private String aktivitas;
    private Integer idUser;
    private LocalDateTime waktu;

    public LogAktivitas() {}

    public LogAktivitas(int idLog, String aktivitas, Integer idUser) {
        this.idLog = idLog;
        this.aktivitas = aktivitas;
        this.idUser = idUser;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }
}
