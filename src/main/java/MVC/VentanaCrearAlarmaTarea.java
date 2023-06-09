package MVC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class VentanaCrearAlarmaTarea {
    @FXML
    private ComboBox<String> aviso;
    @FXML
    private ComboBox<String> tipo;
    @FXML
    private TextField intervalo;
    private Stage stage;
    @FXML
    private CheckBox tiempoRelativo;
    @FXML
    private Button botonGuardarAlarma;
    private LocalDateTime fechaActual = LocalDateTime.now();
    public VentanaCrearAlarmaTarea(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearAlarma.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        Scene sceneNueva = new Scene(ventana);
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneNueva);
        initialize();

    }
    private void initialize(){
        var tipos = FXCollections.observableArrayList("Dia", "Semana","Mes");
        tipo.setItems(tipos);
        tipo.setVisibleRowCount(5);
        var avisos = FXCollections.observableArrayList("Notificación", "Mail", "Sonido");
        aviso.setItems(avisos);
        aviso.setVisibleRowCount(5);
    }
    public void registrarEscuchaTiempoRelativo(EventHandler<ActionEvent> eventHandler) {
        tiempoRelativo.setOnAction(eventHandler);
    }

    public void getEscuchaTiempoRelativo() {
        registrarEscuchaTiempoRelativo(actionEvent -> {
            if (tiempoRelativo.isSelected()) {
                tipo.setDisable(false);
                intervalo.setDisable(false);
            } else {
                tipo.setDisable(true);
                intervalo.setDisable(true);
            }});
    }

}

