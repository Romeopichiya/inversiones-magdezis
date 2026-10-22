package main.java.com.ms.inversionesmagdezis.controller;

import main.java.com.ms.inversionesmagdezis.dto.LoginDTO;
import main.java.com.ms.inversionesmagdezis.model.Usuario;
import main.java.com.ms.inversionesmagdezis.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController {

    // La etiqueta @FXML le dice a Java: "Esta variable se conecta con un elemento de Scene Builder"
    // Los nombres de estas variables deben coincidir EXACTAMENTE con la propiedad "fx:id" en Scene Builder
    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    private UsuarioService usuarioService;

    // El constructor inicializa el servicio para poder usar la lógica de negocio
    public LoginController() {
        this.usuarioService = new UsuarioService();
    }

    // Este método en camelCase se ejecutará cuando el usuario haga clic en el botón de "Iniciar Sesión"
    // En Scene Builder, debes poner el nombre "iniciarSesion" en la propiedad "On Action" del botón
    @FXML
    public void iniciarSesion(ActionEvent event) {
        
        // 1. Capturamos el texto que el usuario escribió en la pantalla
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        // 2. Validamos que no envíen campos vacíos
        if (correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de Validación", "Por favor, llene todos los campos.");
            return;
        }

        // 3. Empaquetamos los datos en nuestro DTO
        LoginDTO loginData = new LoginDTO(correo, contrasena);

        // 4. Se lo enviamos al Service para que haga la validación con la BD y BCrypt
        Usuario usuarioLogueado = usuarioService.iniciarSesion(loginData.getCorreo(), loginData.getContrasena());

        // 5. Evaluamos la respuesta
        if (usuarioLogueado != null) {
            // Si el login es exitoso, verificamos el rol (1 para ADMIN, 2 para USER según nuestra base de datos)
            String nombreRol = (usuarioLogueado.getRolId() == 1) ? "ADMIN" : "USER";
            
            // Mostramos la alerta con el rol indicado en las instrucciones
            mostrarAlerta(Alert.AlertType.INFORMATION, "Login Exitoso", "Bienvenido " + usuarioLogueado.getNombre() + ".\nHas iniciado sesión como: " + nombreRol);
            
            // Reemplaza esto dentro del bloque donde apruebas el inicio de sesión exitoso
try {
    // 1. Cargar la nueva vista del dashboard
    Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/dashboard-view.fxml"));
    
    // 2. Obtener la ventana actual a partir del campo de texto de correo (o cualquier otro elemento de la vista)
    Stage stage = (Stage) txtCorreo.getScene().getWindow();
    
    // 3. Crear y aplicar la nueva escena
    Scene scene = new Scene(root);
    stage.setTitle("Inversiones Magdezis - Panel Principal");
    stage.setScene(scene);
    stage.centerOnScreen(); // Para centrar la ventana en la pantalla
    stage.show();
    
} catch (Exception e) {
    e.printStackTrace();
    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo cargar el panel principal.");
}
            
            // Aquí más adelante agregarías el código para cerrar esta ventana y abrir la pantalla principal
        } else {
            // Si retorna null, el correo no existe o la contraseña es incorrecta
            mostrarAlerta(Alert.AlertType.ERROR, "Login Fallido", "Correo o contraseña incorrectos.");
        }
    }
    
    @FXML
    public void irARegistro(ActionEvent event) {
        try {
            // Buscamos el archivo FXML de registro usando la misma ruta que usamos en Main
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/registro-view.fxml"));
            
            // Obtenemos la ventana (Stage) actual a partir del botón que se presionó
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Creamos una nueva escena con la vista de registro
            Scene scene = new Scene(root);
            
            // Cambiamos la escena actual por la nueva y le cambiamos el título
            stage.setTitle("Inversiones Magdezis - Registro");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo cargar la vista de registro.");
        }
    }
    
    @FXML
    public void volverAlLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Inversiones Magdezis - Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método de apoyo para crear alertas de JavaFX fácilmente y no repetir código
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

