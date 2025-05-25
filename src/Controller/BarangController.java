package Controller;

import Modell.Barang;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BarangController {

    // Untuk sementara, kita simpan data di dalam List.
    // Nantinya, ini akan diganti dengan interaksi ke database.
    private List<Barang> daftarBarang;

    public BarangController() {
        daftarBarang = new ArrayList<>();
        // Contoh data awal (opsional)
        tambahBarang(1, "Gitar Akustik Yamaha F310", 1, "Baru", 10);
        tambahBarang(2, "Keyboard Roland XPS-10", 2, "Bekas", 5);
    }

    /**
     * CREATE: Method untuk menambah barang baru ke dalam daftar.
     */
    public void tambahBarang(int idBarang, String namaBarang, int idKategori, String kondisi, int jumlahTotal) {
        Barang barangBaru = new Barang(
            idBarang,
            namaBarang,
            idKategori,
            kondisi,
            jumlahTotal,
            LocalDateTime.now(), // Waktu dibuat
            LocalDateTime.now()  // Waktu diupdate
        );
        daftarBarang.add(barangBaru);
        System.out.println("Berhasil menambah barang: " + namaBarang);
    }

    /**
     * READ: Method untuk mendapatkan semua data barang.
     * @return List berisi semua objek Barang.
     */
    public List<Barang> getAllBarang() {
        return this.daftarBarang;
    }

    /**
     * READ: Method untuk mencari barang berdasarkan ID.
     * @param idBarang ID barang yang ingin dicari.
     * @return Objek Barang jika ditemukan, null jika tidak.
     */
    public Barang getBarangById(int idBarang) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang() == idBarang) {
                return barang;
            }
        }
        return null; // Kembalikan null jika tidak ditemukan
    }

    /**
     * UPDATE: Method untuk memperbarui data barang.
     * @param idBarang ID barang yang akan diupdate.
     * @param namaBaru Nama baru untuk barang.
     * @param kondisiBaru Kondisi baru untuk barang.
     * @param jumlahBaru Jumlah total baru.
     * @return true jika berhasil, false jika barang tidak ditemukan.
     */
    public boolean updateBarang(int idBarang, String namaBaru, String kondisiBaru, int jumlahBaru) {
        Barang barangUntukUpdate = getBarangById(idBarang);

        if (barangUntukUpdate != null) {
            barangUntukUpdate.setNamaBarang(namaBaru);
            barangUntukUpdate.setKondisi(kondisiBaru);
            barangUntukUpdate.setJumlahTotal(jumlahBaru);
            barangUntukUpdate.setUpdatedAt(LocalDateTime.now()); // Perbarui waktu update
            System.out.println("Berhasil update barang dengan ID: " + idBarang);
            return true;
        }
        System.out.println("Gagal update, barang dengan ID: " + idBarang + " tidak ditemukan.");
        return false;
    }

    /**
     * DELETE: Method untuk menghapus barang dari daftar.
     * @param idBarang ID barang yang akan dihapus.
     * @return true jika berhasil, false jika barang tidak ditemukan.
     */
    public boolean hapusBarang(int idBarang) {
        Barang barangUntukHapus = getBarangById(idBarang);

        if (barangUntukHapus != null) {
            daftarBarang.remove(barangUntukHapus);
            System.out.println("Berhasil menghapus barang: " + barangUntukHapus.getNamaBarang());
            return true;
        }
        System.out.println("Gagal hapus, barang dengan ID: " + idBarang + " tidak ditemukan.");
        return false;
    }
}