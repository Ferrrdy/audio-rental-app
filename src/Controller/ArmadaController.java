package Controller; // Sesuaikan dengan nama package Controller Anda

import Modell.Armada; // Nama package Model Anda adalah "Modell"
import DataBase.DbConnection;    // Pastikan ini adalah kelas koneksi DB Anda

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArmadaController {

    /**
     * CREATE: Menambah armada baru ke database.
     * @param namaArmada Nama armada.
     * @param status Status ketersediaan armada.
     * @return true jika berhasil ditambahkan, false jika gagal.
     */
    public boolean tambahArmada(String namaArmada, String status) {
        // SQL disesuaikan: kolom jenis_armada dihilangkan
        String sql = "INSERT INTO armada (nama_armada, status, created_at, updated_at) VALUES (?, ?, NOW(), NOW())";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, namaArmada);
            pstmt.setString(2, status);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Armada berhasil ditambahkan dengan ID: " + generatedKeys.getLong(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat menambah armada: " + e.getMessage());
            e.printStackTrace(); // Tambahkan ini untuk detail error di konsol
        }
        return false;
    }

    /**
     * READ: Mendapatkan semua data armada dari database.
     * @return List berisi semua objek Armada.
     */
    public List<Armada> getAllArmada() {
        List<Armada> daftarArmada = new ArrayList<>();
        // SQL disesuaikan: kolom jenis_armada dihilangkan
        String sql = "SELECT id_armada, nama_armada, status, created_at, updated_at FROM armada";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Armada armada = new Armada();
                armada.setIdArmada(rs.getInt("id_armada"));
                armada.setNamaArmada(rs.getString("nama_armada"));
                armada.setStatus(rs.getString("status"));
                
                if (rs.getTimestamp("created_at") != null) {
                    armada.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }
                if (rs.getTimestamp("updated_at") != null) {
                    armada.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                }
                daftarArmada.add(armada);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua armada: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarArmada;
    }

    /**
     * READ: Mencari armada berdasarkan ID dari database.
     * @param idArmada ID armada yang ingin dicari.
     * @return Objek Armada jika ditemukan, null jika tidak.
     */
    public Armada getArmadaById(int idArmada) {
        // SQL disesuaikan
        String sql = "SELECT id_armada, nama_armada, status, created_at, updated_at FROM armada WHERE id_armada = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idArmada);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Armada armada = new Armada();
                    armada.setIdArmada(rs.getInt("id_armada"));
                    armada.setNamaArmada(rs.getString("nama_armada"));
                    armada.setStatus(rs.getString("status"));
                    if (rs.getTimestamp("created_at") != null) {
                        armada.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    }
                    if (rs.getTimestamp("updated_at") != null) {
                        armada.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    }
                    return armada;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari armada by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UPDATE: Memperbarui data armada di database.
     * @param idArmada ID armada yang akan diupdate.
     * @param namaBaru Nama baru untuk armada.
     * @param statusBaru Status ketersediaan baru.
     * @return true jika berhasil diupdate, false jika gagal atau armada tidak ditemukan.
     */
    public boolean updateArmada(int idArmada, String namaBaru, String statusBaru) {
        // SQL disesuaikan
        String sql = "UPDATE armada SET nama_armada = ?, status = ?, updated_at = NOW() WHERE id_armada = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, namaBaru);
            pstmt.setString(2, statusBaru);
            pstmt.setInt(3, idArmada);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Berhasil update armada dengan ID: " + idArmada);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat update armada: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Gagal update, armada dengan ID: " + idArmada + " mungkin tidak ditemukan.");
        return false;
    }

    /**
     * DELETE: Menghapus armada dari database.
     * @param idArmada ID armada yang akan dihapus.
     * @return true jika berhasil dihapus, false jika gagal atau armada tidak ditemukan.
     */
    public boolean hapusArmada(int idArmada) {
        String sql = "DELETE FROM armada WHERE id_armada = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idArmada);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Berhasil menghapus armada dengan ID: " + idArmada);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghapus armada: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Gagal hapus, armada dengan ID: " + idArmada + " mungkin tidak ditemukan.");
        return false;
    }
}