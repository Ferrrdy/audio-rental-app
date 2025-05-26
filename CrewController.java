package Controller;

import DataBase.DbConnection;
import Modell.Crew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CrewController {

    // Metode untuk menambahkan crew baru ke database
    public static boolean addCrew(Crew crew) {
        String sql = "INSERT INTO crew (nama_crew, posisi, gaji_bulanan, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection(); // Pastikan DbConnection.getConnection() mengembalikan Connection
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, crew.getNamaCrew());
            pstmt.setString(2, crew.getPosisi());
            pstmt.setDouble(3, crew.getGajiBulanan());

            // Set created_at dan updated_at saat ini di controller
            LocalDateTime now = LocalDateTime.now();
            pstmt.setTimestamp(4, Timestamp.valueOf(now));
            pstmt.setTimestamp(5, Timestamp.valueOf(now));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Mendapatkan ID yang dihasilkan jika ada
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        crew.setIdCrew(generatedKeys.getInt(1));
                    }
                }
                // Set createdAt dan updatedAt pada objek Crew setelah berhasil disimpan
                crew.setCreatedAt(now);
                crew.setUpdatedAt(now);
                System.out.println("Crew added successfully: " + crew.getNamaCrew());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding crew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Metode untuk mendapatkan semua kru dari database
    public static List<Crew> getAllCrew() {
        List<Crew> crews = new ArrayList<>();
        String sql = "SELECT id_crew, nama_crew, posisi, gaji_bulanan, created_at, updated_at FROM crew";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_crew");
                String nama = rs.getString("nama_crew");
                String posisi = rs.getString("posisi");
                double gaji = rs.getDouble("gaji_bulanan");

                Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");

                LocalDateTime createdAt = (createdAtTimestamp != null) ? createdAtTimestamp.toLocalDateTime() : null;
                LocalDateTime updatedAt = (updatedAtTimestamp != null) ? updatedAtTimestamp.toLocalDateTime() : null;

                // Menggunakan konstruktor baru dengan 6 argumen
                crews.add(new Crew(id, nama, posisi, gaji, createdAt, updatedAt));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all crews: " + e.getMessage());
            e.printStackTrace();
            return null; // Kembalikan null jika ada error
        }
        return crews;
    }

    // Metode untuk mendapatkan kru berdasarkan ID
    public static Crew getCrewById(int idCrew) {
        String sql = "SELECT id_crew, nama_crew, posisi, gaji_bulanan, created_at, updated_at FROM crew WHERE id_crew = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCrew);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_crew");
                    String nama = rs.getString("nama_crew");
                    String posisi = rs.getString("posisi");
                    double gaji = rs.getDouble("gaji_bulanan");

                    Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                    Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");

                    LocalDateTime createdAt = (createdAtTimestamp != null) ? createdAtTimestamp.toLocalDateTime() : null;
                    LocalDateTime updatedAt = (updatedAtTimestamp != null) ? updatedAtTimestamp.toLocalDateTime() : null;

                    // Menggunakan konstruktor baru dengan 6 argumen
                    return new Crew(id, nama, posisi, gaji, createdAt, updatedAt);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching crew by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Metode untuk memperbarui data kru di database
    public static boolean updateCrew(Crew crew) {
        String sql = "UPDATE crew SET nama_crew = ?, posisi = ?, gaji_bulanan = ?, updated_at = ? WHERE id_crew = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, crew.getNamaCrew());
            pstmt.setString(2, crew.getPosisi());
            pstmt.setDouble(3, crew.getGajiBulanan());
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now())); // Update updated_at
            pstmt.setInt(5, crew.getIdCrew());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crew updated successfully: " + crew.getNamaCrew());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating crew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Metode untuk menghapus kru dari database
    public static boolean deleteCrew(int idCrew) {
        String sql = "DELETE FROM crew WHERE id_crew = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCrew);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crew with ID " + idCrew + " deleted successfully.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting crew: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}