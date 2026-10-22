package main.java.com.ms.inversionesmagdezis.dto;

public class RegistroDTO {
    
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private int rolId;

    public RegistroDTO(String nombre, String apellido, String correo, String contrasena, int rolId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getRolId() {
        return rolId;
    }
}
