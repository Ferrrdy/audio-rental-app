package Controller;

import DataBase.DbConnection; // Pastikan path ini benar
import Modell.GajiCrew;
import Modell.Crew; // Import model Crew yang baru/diperbarui

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GajiCrewController {

    public static boolean addGajiCrew(GajiCrew gajiCrew) {
        String sql = "INSERT INTO gaji_crew (id_crew, tanggal_gaji, jumlah_gaji, nomor_rekening, tanggal_pembayaran, keterangan, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, gajiCrew.getIdCrew());
            pstmt.setDate(2, Date.valueOf(gajiCrew.getTanggalGaji()));
            pstmt.setDouble(3, gajiCrew.getJumlahGaji());
            pstmt.setString(4, gajiCrew.getNomorRekening());

            LocalDateTime now = LocalDateTime.now();
            // Jika tanggalPembayaran belum diset, gunakan waktu sekarang
            pstmt.setTimestamp(5, Timestamp.valueOf(gajiCrew.getTanggalPembayaran() != null ? gajiCrew.getTanggalPembayaran() : now));
            pstmt.setString(6, gajiCrew.getKeterangan());
            pstmt.setTimestamp(7, Timestamp.valueOf(now));
            pstmt.setTimestamp(8, Timestamp.valueOf(now));

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        gajiCrew.setIdGaji(generatedKeys.getInt(1));
                    }
                }
                gajiCrew.setCreatedAt(now);
                gajiCrew.setUpdatedAt(now);
                System.out.println("GajiCrew added successfully for idCrew: " + gajiCrew.getIdCrew());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding GajiCrew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mengambil semua data gaji crew beserta nama crew.
     * Menggunakan JOIN untuk mendapatkan nama crew dari tabel 'crew'.
     * @return List of GajiCrew objects, or null if an error occurs.
     */
    public static List<GajiCrew> getAllGajiCrew() {
        List<GajiCrew> gajiCrews = new ArrayList<>();
        // Query dengan JOIN ke tabel 'crew' untuk mendapatkan nama_crew
        String sql = "SELECT gc.id_gaji, gc.id_crew, c.nama_crew, gc.tanggal_gaji, gc.jumlah_gaji, gc.nomor_rekening, gc.tanggal_pembayaran, gc.keterangan, gc.created_at, gc.updated_at " +
                     "FROM gaji_crew gc JOIN crew c ON gc.id_crew = c.id_crew";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GajiCrew gc = new GajiCrew();
                gc.setIdGaji(rs.getInt("id_gaji"));
                gc.setIdCrew(rs.getInt("id_crew"));
                gc.setNamaCrew(rs.getString("nama_crew")); // BARU: Set nama crew dari hasil JOIN

                Date tanggalGajiSql = rs.getDate("tanggal_gaji");
                if (tanggalGajiSql != null) {
                    gc.setTanggalGaji(tanggalGajiSql.toLocalDate());
                }
                gc.setJumlahGaji(rs.getDouble("jumlah_gaji"));
                gc.setNomorRekening(rs.getString("nomor_rekening"));

                Timestamp tanggalPembayaranTimestamp = rs.getTimestamp("tanggal_pembayaran");
                if (tanggalPembayaranTimestamp != null) {
                    gc.setTanggalPembayaran(tanggalPembayaranTimestamp.toLocalDateTime());
                }
                gc.setKeterangan(rs.getString("keterangan"));
                Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                if (createdAtTimestamp != null) {
                    gc.setCreatedAt(createdAtTimestamp.toLocalDateTime());
                }
                Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
                if (updatedAtTimestamp != null) {
                    gc.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
                }
                gajiCrews.add(gc);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all GajiCrews: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return gajiCrews;
    }

    /**
     * Mengambil daftar semua crew dari database.
     * Ini adalah metode yang hilang ("getCrewList()") yang menyebabkan error.
     * @return List of Crew objects, or null if an error occurs.
     */
    public static List<Crew> getCrewList() {
        List<Crew> crews = new ArrayList<>();
        // Asumsi nama tabel crew adalah 'crew' dan ada kolom 'id_crew' dan 'nama_crew'
        String sql = "SELECT id_crew, nama_crew FROM crew";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Crew crew = new Crew();
                crew.setIdCrew(rs.getInt("id_crew"));
                crew.setNamaCrew(rs.getString("nama_crew"));
                // Jika ada kolom lain yang relevan di model Crew, tambahkan di sini
                crews.add(crew);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching crew list: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return crews;
    }

    /**
     * Mengambil nama crew berdasarkan ID.
     * Metode ini sudah ada dan tetap berguna, namun jika Anda memuat namaCrew di model GajiCrew,
     * mungkin tidak selalu perlu dipanggil secara terpisah.
     * @param idCrew ID dari crew yang ingin dicari namanya.
     * @return Nama crew, atau null jika tidak ditemukan.
     */
    public static String getNamaCrewById(int idCrew) {
        String namaCrew = null;
        String sql = "SELECT nama_crew FROM crew WHERE id_crew = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCrew);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    namaCrew = rs.getString("nama_crew");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching crew name by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return namaCrew;
    }


    public static GajiCrew getGajiCrewById(int idGaji) {
        // Query dengan JOIN untuk mendapatkan nama crew
        String sql = "SELECT gc.id_gaji, gc.id_crew, c.nama_crew, gc.tanggal_gaji, gc.jumlah_gaji, gc.nomor_rekening, gc.tanggal_pembayaran, gc.keterangan, gc.created_at, gc.updated_at FROM gaji_crew gc JOIN crew c ON gc.id_crew = c.id_crew WHERE id_gaji = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idGaji);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    GajiCrew gc = new GajiCrew();
                    gc.setIdGaji(rs.getInt("id_gaji"));
                    gc.setIdCrew(rs.getInt("id_crew"));
                    gc.setNamaCrew(rs.getString("nama_crew")); // BARU: Set nama crew

                    Date tanggalGajiSql = rs.getDate("tanggal_gaji");
                    if (tanggalGajiSql != null) {
                        gc.setTanggalGaji(tanggalGajiSql.toLocalDate());
                    }
                    gc.setJumlahGaji(rs.getDouble("jumlah_gaji"));
                    gc.setNomorRekening(rs.getString("nomor_rekening"));
                    Timestamp tanggalPembayaranTimestamp = rs.getTimestamp("tanggal_pembayaran");
                    if (tanggalPembayaranTimestamp != null) {
                        gc.setTanggalPembayaran(tanggalPembayaranTimestamp.toLocalDateTime());
                    }
                    gc.setKeterangan(rs.getString("keterangan"));
                    Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                    if (createdAtTimestamp != null) {
                        gc.setCreatedAt(createdAtTimestamp.toLocalDateTime());
                    }
                    Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
                    if (updatedAtTimestamp != null) {
                        gc.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
                    }
                    return gc;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching GajiCrew by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateGajiCrew(GajiCrew gajiCrew) {
        String sql = "UPDATE gaji_crew SET id_crew = ?, tanggal_gaji = ?, jumlah_gaji = ?, nomor_rekening = ?, tanggal_pembayaran = ?, keterangan = ?, updated_at = ? WHERE id_gaji = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gajiCrew.getIdCrew());
            pstmt.setDate(2, Date.valueOf(gajiCrew.getTanggalGaji()));
            pstmt.setDouble(3, gajiCrew.getJumlahGaji());
            pstmt.setString(4, gajiCrew.getNomorRekening());
            pstmt.setTimestamp(5, Timestamp.valueOf(gajiCrew.getTanggalPembayaran() != null ? gajiCrew.getTanggalPembayaran() : LocalDateTime.now()));
            pstmt.setString(6, gajiCrew.getKeterangan());
            pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // Update updated_at
            pstmt.setInt(8, gajiCrew.getIdGaji());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("GajiCrew updated successfully for idGaji: " + gajiCrew.getIdGaji());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating GajiCrew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteGajiCrew(int idGaji) {
        String sql = "DELETE FROM gaji_crew WHERE id_gaji = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idGaji);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("GajiCrew with ID " + idGaji + " deleted successfully.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting GajiCrew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static List<GajiCrew> getGajiCrewByCrewId(int idCrew) {
        List<GajiCrew> gajiCrews = new ArrayList<>();
        // Query dengan JOIN untuk mendapatkan nama crew
        String sql = "SELECT gc.id_gaji, gc.id_crew, c.nama_crew, gc.tanggal_gaji, gc.jumlah_gaji, gc.nomor_rekening, gc.tanggal_pembayaran, gc.keterangan, gc.created_at, gc.updated_at FROM gaji_crew gc JOIN crew c ON gc.id_crew = c.id_crew WHERE gc.id_crew = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCrew);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    GajiCrew gc = new GajiCrew();
                    gc.setIdGaji(rs.getInt("id_gaji"));
                    gc.setIdCrew(rs.getInt("id_crew"));
                    gc.setNamaCrew(rs.getString("nama_crew")); // BARU: Set nama crew

                    Date tanggalGajiSql = rs.getDate("tanggal_gaji");
                    if (tanggalGajiSql != null) {
                        gc.setTanggalGaji(tanggalGajiSql.toLocalDate());
                    }
                    gc.setJumlahGaji(rs.getDouble("jumlah_gaji"));
                    gc.setNomorRekening(rs.getString("nomor_rekening"));
                    Timestamp tanggalPembayaranTimestamp = rs.getTimestamp("tanggal_pembayaran");
                    if (tanggalPembayaranTimestamp != null) {
                        gc.setTanggalPembayaran(tanggalPembayaranTimestamp.toLocalDateTime());
                    }
                    gc.setKeterangan(rs.getString("keterangan"));
                    Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                    if (createdAtTimestamp != null) {
                        gc.setCreatedAt(createdAtTimestamp.toLocalDateTime());
                    }
                    Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
                    if (updatedAtTimestamp != null) {
                        gc.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
                    }
                    gajiCrews.add(gc);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching GajiCrew by Crew ID: " + e.getMessage());
            e.printStackTrace();
        }
        return gajiCrews;
    }


    public static void main(String[] args) {
        // --- TEST addGajiCrew ---
        // Asumsi ada Crew dengan id_crew = 1 di database Anda untuk testing (INT)
        GajiCrew newGaji = new GajiCrew();
        newGaji.setIdCrew(1); // Set ID Crew yang valid
        newGaji.setTanggalGaji(LocalDate.of(2025, 5, 25));
        newGaji.setJumlahGaji(5000000.0);
        newGaji.setNomorRekening("1234567890");
        newGaji.setTanggalPembayaran(LocalDateTime.now());
        newGaji.setKeterangan("Gaji bulanan Mei 2025");

        if (GajiCrewController.addGajiCrew(newGaji)) {
            System.out.println("Gaji baru berhasil ditambahkan dengan ID: " + newGaji.getIdGaji());
        } else {
            System.out.println("Gagal menambahkan gaji baru.");
        }

        // --- TEST getAllGajiCrew ---
        System.out.println("\nAll GajiCrews:");
        List<GajiCrew> allGajiCrews = GajiCrewController.getAllGajiCrew();
        if (allGajiCrews != null) {
            for (GajiCrew gc : allGajiCrews) {
                // Sekarang namaCrew sudah ada di objek GajiCrew karena di-JOIN
                System.out.println("ID Gaji: " + gc.getIdGaji() + ", ID Crew: " + gc.getIdCrew() +
                                   ", Nama Crew: " + gc.getNamaCrew() + // Langsung ambil dari objek GajiCrew
                                   ", Tanggal Gaji: " + gc.getTanggalGaji() + ", Jumlah: " + gc.getJumlahGaji() +
                                   ", Rekening: " + gc.getNomorRekening() +
                                   ", Pembayaran: " + gc.getTanggalPembayaran() + ", Keterangan: " + gc.getKeterangan() +
                                   ", Created: " + gc.getCreatedAt() + ", Updated: " + gc.getUpdatedAt());
            }
        }

        // --- TEST getGajiCrewById ---
        if (newGaji.getIdGaji() != 0) {
            System.out.println("\nGajiCrew by ID " + newGaji.getIdGaji() + ":");
            GajiCrew foundGaji = GajiCrewController.getGajiCrewById(newGaji.getIdGaji());
            if (foundGaji != null) {
                System.out.println("ID Gaji: " + foundGaji.getIdGaji() + ", ID Crew: " + foundGaji.getIdCrew() +
                                   ", Nama Crew: " + foundGaji.getNamaCrew() + // Langsung ambil dari objek GajiCrew
                                   ", Jumlah: " + foundGaji.getJumlahGaji() + ", Rekening: " + foundGaji.getNomorRekening());
            } else {
                System.out.println("GajiCrew not found.");
            }

            // --- TEST updateGajiCrew ---
            if (foundGaji != null) {
                foundGaji.setJumlahGaji(5500000.0);
                foundGaji.setNomorRekening("0987654321");
                foundGaji.setKeterangan("Gaji bulanan Mei 2025 (Revisi)");
                if (GajiCrewController.updateGajiCrew(foundGaji)) {
                    System.out.println("GajiCrew updated successfully.");
                } else {
                    System.out.println("Gagal memperbarui GajiCrew.");
                }
            }
        }

        // --- TEST getCrewList() ---
        System.out.println("\nList of all Crews:");
        List<Crew> allCrews = GajiCrewController.getCrewList();
        if (allCrews != null) {
            for (Crew crew : allCrews) {
                System.out.println("Crew ID: " + crew.getIdCrew() + ", Nama: " + crew.getNamaCrew());
            }
        } else {
            System.out.println("Failed to retrieve crew list.");
        }


        // --- TEST deleteGajiCrew ---
        // Uncomment baris di bawah ini untuk menguji penghapusan
        // if (newGaji.getIdGaji() != 0) {
        //     System.out.println("\nAttempting to delete GajiCrew with ID: " + newGaji.getIdGaji());
        //     if (GajiCrewController.deleteGajiCrew(newGaji.getIdGaji())) {
        //         System.out.println("GajiCrew deleted successfully.");
        //     } else {
        //         System.out.println("Failed to delete GajiCrew.");
        //     }
        // }
    }
}