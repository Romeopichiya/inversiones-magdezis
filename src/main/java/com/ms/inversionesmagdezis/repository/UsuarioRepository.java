package main.java.com.ms.inversionesmagdezis.repository;

import main.java.com.ms.inversionesmagdezis.config.DatabaseConfig;
import main.java.com.ms.inversionesmagdezis.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Nombre en PascalCase
public class UsuarioRepository {

    // Constructor vacío
    public UsuarioRepository() {
    }

    // Método en camelCase para insertar un usuario en la BD
    public boolean registrarUsuario(Usuario usuario) {
        // Usamos signos de interrogación (?) para evitar Inyección SQL (Buena práctica de seguridad)
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena, rol_id) VALUES (?, ?, ?, ?, ?)";
        
        // Usamos una estructura llamada "try-with-resources" (los paréntesis después del try)
        // Esto asegura que la conexión a la base de datos se cierre automáticamente al terminar, evitando fugas de memoria.
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Reemplazamos los "?" con los datos reales del objeto Usuario
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasena()); // OJO: Aquí llegará la contraseña ya encriptada por BCrypt
            stmt.setInt(5, usuario.getRolId());
            
            // Ejecutamos la actualización (INSERT)
            int filasAfectadas = stmt.executeUpdate();
            
            // Si se afectó al menos 1 fila, significa que se guardó correctamente
            return filasAfectadas > 0; 
            
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario en la BD: " + e.getMessage());
            return false;
        }
    }

    // Método en camelCase para buscar si un correo existe (para validar el Login)
    public Usuario buscarUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        Usuario usuarioEncontrado = null;
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, correo);
            
            // Usamos executeQuery porque esperamos un "conjunto de resultados" (ResultSet)
            ResultSet rs = stmt.executeQuery();
            
            // Si rs.next() es true, significa que encontró una fila con ese correo
            if (rs.next()) {
                // Instanciamos el objeto y lo llenamos con lo que vino de la base de datos
                usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("id"));
                usuarioEncontrado.setNombre(rs.getString("nombre"));
                usuarioEncontrado.setApellido(rs.getString("apellido"));
                usuarioEncontrado.setCorreo(rs.getString("correo"));
                usuarioEncontrado.setContrasena(rs.getString("contrasena"));
                usuarioEncontrado.setRolId(rs.getInt("rol_id"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario en la BD: " + e.getMessage());
        }
        
        // Si no encontró nada, retorna null. Si encontró, retorna el objeto Usuario lleno.
        return usuarioEncontrado;
    }
}

