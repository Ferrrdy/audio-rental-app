package Controller; // Sesuaikan dengan package Controller Anda

import Modell.Event;            // Package Model Anda
import DataBase.DbConnection;   // Kelas koneksi DB Anda

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventController {

    /**
     * CREATE: Menambah event baru ke database.
     * ASUMSI: Nama tabel adalah 'event' dan ada kolom 'durasi' (INT).
     */
    public boolean tambahEvent(String namaEvent, int durasi, LocalDateTime tanggalMulai, LocalDateTime tanggalSelesai,
                               String lokasi, String keterangan, String status) {
        String sql = "INSERT INTO event (nama_event, durasi, tanggal_mulai, tanggal_selesai, lokasi, keterangan, status, created_at, updated_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())"; // Sesuaikan nama tabel jika berbeda
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, namaEvent);
            pstmt.setInt(2, durasi); // Untuk field durasi
            pstmt.setTimestamp(3, Timestamp.valueOf(tanggalMulai));
            pstmt.setTimestamp(4, Timestamp.valueOf(tanggalSelesai));
            pstmt.setString(5, lokasi);
            pstmt.setString(6, keterangan); // Menggunakan 'keterangan'
            pstmt.setString(7, status);     // Menggunakan 'status'

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Event berhasil ditambahkan dengan ID: " + generatedKeys.getLong(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat menambah event: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * READ: Mendapatkan semua event dari database.
     * ASUMSI: Nama tabel adalah 'event' dan ada kolom 'durasi'.
     */
    public List<Event> getAllEvent() {
        List<Event> daftarEvent = new ArrayList<>();
        // Sesuaikan nama tabel dan kolom jika berbeda
        String sql = "SELECT id_event, nama_event, durasi, tanggal_mulai, tanggal_selesai, lokasi, keterangan, status, created_at, updated_at FROM event"; 

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event event = new Event();
                event.setIdEvent(rs.getInt("id_event"));
                event.setNamaEvent(rs.getString("nama_event"));
                event.setDurasi(rs.getInt("durasi")); // Untuk field durasi
                if (rs.getTimestamp("tanggal_mulai") != null) {
                    event.setTanggalMulai(rs.getTimestamp("tanggal_mulai").toLocalDateTime());
                }
                if (rs.getTimestamp("tanggal_selesai") != null) {
                    event.setTanggalSelesai(rs.getTimestamp("tanggal_selesai").toLocalDateTime());
                }
                event.setLokasi(rs.getString("lokasi"));
                event.setKeterangan(rs.getString("keterangan")); // Menggunakan 'keterangan'
                event.setStatus(rs.getString("status"));         // Menggunakan 'status'
                if (rs.getTimestamp("created_at") != null) {
                    event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }
                if (rs.getTimestamp("updated_at") != null) {
                    event.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                }
                daftarEvent.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil semua event: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarEvent;
    }

    /**
     * READ: Mencari event berdasarkan ID dari database.
     * ASUMSI: Nama tabel adalah 'event' dan ada kolom 'durasi'.
     */
    public Event getEventById(int idEvent) {
        // Sesuaikan nama tabel dan kolom jika berbeda
        String sql = "SELECT id_event, nama_event, durasi, tanggal_mulai, tanggal_selesai, lokasi, keterangan, status, created_at, updated_at FROM event WHERE id_event = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEvent);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Event event = new Event();
                    event.setIdEvent(rs.getInt("id_event"));
                    event.setNamaEvent(rs.getString("nama_event"));
                    event.setDurasi(rs.getInt("durasi")); // Untuk field durasi
                    if (rs.getTimestamp("tanggal_mulai") != null) {
                        event.setTanggalMulai(rs.getTimestamp("tanggal_mulai").toLocalDateTime());
                    }
                    if (rs.getTimestamp("tanggal_selesai") != null) {
                        event.setTanggalSelesai(rs.getTimestamp("tanggal_selesai").toLocalDateTime());
                    }
                    event.setLokasi(rs.getString("lokasi"));
                    event.setKeterangan(rs.getString("keterangan")); // Menggunakan 'keterangan'
                    event.setStatus(rs.getString("status"));         // Menggunakan 'status'
                     if (rs.getTimestamp("created_at") != null) {
                        event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    }
                    if (rs.getTimestamp("updated_at") != null) {
                        event.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    }
                    return event;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mencari event by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UPDATE: Memperbarui data event di database.
     * ASUMSI: Nama tabel adalah 'event' dan ada kolom 'durasi'.
     */
    public boolean updateEvent(int idEvent, String namaEvent, int durasi, LocalDateTime tanggalMulai, LocalDateTime tanggalSelesai,
                               String lokasi, String keterangan, String status) {
        // Sesuaikan nama tabel dan kolom jika berbeda
        String sql = "UPDATE event SET nama_event = ?, durasi = ?, tanggal_mulai = ?, tanggal_selesai = ?, lokasi = ?, " +
                     "keterangan = ?, status = ?, updated_at = NOW() WHERE id_event = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, namaEvent);
            pstmt.setInt(2, durasi); // Untuk field durasi
            pstmt.setTimestamp(3, Timestamp.valueOf(tanggalMulai));
            pstmt.setTimestamp(4, Timestamp.valueOf(tanggalSelesai));
            pstmt.setString(5, lokasi);
            pstmt.setString(6, keterangan); // Menggunakan 'keterangan'
            pstmt.setString(7, status);     // Menggunakan 'status'
            pstmt.setInt(8, idEvent);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Berhasil update event dengan ID: " + idEvent);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat update event: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Gagal update, event dengan ID: " + idEvent + " mungkin tidak ditemukan.");
        return false;
    }

    /**
     * DELETE: Menghapus event dari database.
     * ASUMSI: Nama tabel adalah 'event'.
     */
    public boolean hapusEvent(int idEvent) {
        String sql = "DELETE FROM event WHERE id_event = ?"; // Sesuaikan nama tabel jika berbeda
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEvent);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Berhasil menghapus event dengan ID: " + idEvent);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error saat menghapus event: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Gagal hapus, event dengan ID: " + idEvent + " mungkin tidak ditemukan.");
        return false;
    }
}