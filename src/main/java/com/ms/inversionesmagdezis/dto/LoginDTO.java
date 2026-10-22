package main.java.com.ms.inversionesmagdezis.dto;

// Clase en PascalCase
public class LoginDTO {
    
    // Atributos en camelCase
    private String correo;
    private String contrasena;

    public LoginDTO() {
    }

    public LoginDTO(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

