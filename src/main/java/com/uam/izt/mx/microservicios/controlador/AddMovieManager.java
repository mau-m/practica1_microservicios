package com.uam.izt.mx.microservicios.controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uam.izt.mx.microservicios.microservicios.modelo.DatabaseManager;

public class AddMovieManager {
    private final DatabaseManager dbManager;

    public AddMovieManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // Métodos para operaciones de base de datos (agregar película, obtener películas, etc.)
    // Por ejemplo, agregar una película:
    public boolean addMovie(String title, String director, int releaseYear) {
        String sql = "INSERT INTO movies (title, director, releaseYear) VALUES (?, ?, ?)";

        PreparedStatement pstmt;
        try {
            pstmt = dbManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, director);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();
            System.out.println("Inserción exitosa");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}