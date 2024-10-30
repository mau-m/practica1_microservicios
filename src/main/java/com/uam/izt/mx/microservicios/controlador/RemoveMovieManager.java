package com.uam.izt.mx.microservicios.controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uam.izt.mx.microservicios.microservicios.modelo.DatabaseManager;

public class RemoveMovieManager {
    private final DatabaseManager dbManager;

    public RemoveMovieManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // Métodos para operaciones de base de datos (agregar película, obtener películas, etc.)
    // Por ejemplo, agregar una película:
    public boolean removeMovie(int movieID) {
        String sql = "DELETE FROM movies WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = dbManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, movieID);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Eliminación exitosa");
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddMovieManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}