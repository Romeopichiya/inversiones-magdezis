package main.java.com.ms.inversionesmagdezis.controller;

import main.java.com.ms.inversionesmagdezis.dto.RegistroDTO;
import main.java.com.ms.inversionesmagdezis.model.Usuario;
import main.java.com.ms.inversionesmagdezis.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private ComboBox<String> cmbRol;

    private UsuarioService usuarioService;

    public RegistroController() {
        this.usuarioService = new UsuarioService();
    }

    @FXML
    public void registrarUsuario(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        String rolSeleccionado = cmbRol.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || rolSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor complete todos los campos.");
            return;
        }

        int rolId = rolSeleccionado.equals("ADMIN") ? 1 : 2;
        RegistroDTO registroData = new RegistroDTO(nombre, apellido, correo, contrasena, rolId);
        
        Usuario nuevoUsuario = new Usuario(
            registroData.getNombre(),
            registroData.getApellido(),
            registroData.getCorreo(),
            registroData.getContrasena(),
            registroData.getRolId()
        );

        boolean exito = usuarioService.registrarUsuario(nuevoUsuario);

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro Exitoso", "El usuario ha sido registrado correctamente.");
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar. Es posible que el correo ya exista.");
        }
    }
    
    @FXML
    public void volverAlLogin(ActionEvent event) {
        try {
            // Carga la vista del login usando la ruta correcta
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/login-view.fxml"));
            
            // Obtiene la ventana actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Crea la nueva escena y la muestra
            Scene scene = new Scene(root);
            stage.setTitle("Inversiones Magdezis - Inicio");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtContrasena.clear();
        cmbRol.getSelectionModel().selectFirst();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
