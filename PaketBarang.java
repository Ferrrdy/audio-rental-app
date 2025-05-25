public class PaketBarang {
    private int idPaketBarang;
    private int idPaket;
    private int idBarang;
    private int jumlah;

    public PaketBarang() {}

    public PaketBarang(int idPaketBarang, int idPaket, int idBarang, int jumlah) {
        this.idPaketBarang = idPaketBarang;
        this.idPaket = idPaket;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
    }

    public int getIdPaketBarang() {
        return idPaketBarang;
    }

    public void setIdPaketBarang(int idPaketBarang) {
        this.idPaketBarang = idPaketBarang;
    }

    public int getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
