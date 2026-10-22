package main.java.com.ms.inversionesmagdezis.model;

// El nombre de la clase usa PascalCase
public class Rol {
    
    // Atributos en camelCase y privados (Encapsulamiento)
    private int id;
    private String nombre;

    // Constructor vacío (mismo nombre que la clase, sin tipo de retorno)
    public Rol() {
    }

    // Constructor con parámetros para facilitar la creación del objeto
    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos Getters y Setters en camelCase
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
}
