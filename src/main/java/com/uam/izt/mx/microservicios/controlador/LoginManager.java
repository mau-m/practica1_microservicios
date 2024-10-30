package com.uam.izt.mx.microservicios.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uam.izt.mx.microservicios.microservicios.modelo.DatabaseManager;

public class LoginManager {
    private final DatabaseManager dbManager;

    public LoginManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean login(String username, String password) {
        try {
            // Consulta SQL para contar los usuarios con el nombre de usuario y contraseña dados
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";

            // Preparar la consulta y establecer los parámetros
            PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // En un caso real, esto debería ser un hash de la contraseña

            // Ejecutar la consulta y obtener el resultado
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retorna verdadero si se encontró al menos un usuario
                }
            }
        } catch (SQLException ex) {
            // Manejar la excepción y registrar un mensaje de error
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Si ocurre algún error o no se encuentra al usuario, retorna falso
    }
}