package main.java.com.ms.inversionesmagdezis.model;

// Clase en PascalCase
public class Usuario {
    
    // Atributos en camelCase y privados
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private int rolId; // Referencia al ID de la tabla roles

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los parámetros
    public Usuario(int id, String nombre, String apellido, String correo, String contrasena, int rolId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rolId = rolId;
    }

    // Constructor sin ID (útil para cuando insertamos un nuevo usuario y la BD genera el ID automáticamente)
    public Usuario(String nombre, String apellido, String correo, String contrasena, int rolId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rolId = rolId;
    }

    // --- Getters y Setters en camelCase ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }
}

