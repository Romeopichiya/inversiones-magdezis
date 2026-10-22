package main.java.com.ms.inversionesmagdezis.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.ms.inversionesmagdezis.config.DatabaseConfig;
import main.java.com.ms.inversionesmagdezis.model.Inversion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController {

    @FXML
    private TableView<Inversion> tablaInversiones;
    @FXML
    private TableColumn<Inversion, Integer> colId;
    @FXML
    private TableColumn<Inversion, String> colDescripcion;
    @FXML
    private TableColumn<Inversion, Double> colMonto;
    @FXML
    private TableColumn<Inversion, Date> colFecha;

    private ObservableList<Inversion> listaInversiones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. Conectar las columnas con los atributos de la clase Inversion
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // 2. Cargar registros desde MySQL
        cargarInversiones();
    }

    private void cargarInversiones() {
        listaInversiones.clear();
        String sql = "SELECT id, descripcion, monto, fecha, usuario_id FROM inversiones";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inversion inv = new Inversion(
                    rs.getInt("id"),
                    rs.getString("descripcion"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha"),
                    rs.getInt("usuario_id")
                );
                listaInversiones.add(inv);
            }

            // 3. Pasar la lista de registros a la tabla
            tablaInversiones.setItems(listaInversiones);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

