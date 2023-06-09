package MVC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class VistaVentanaCrearTarea {
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private ComboBox<String> dia;
    @FXML
    private ComboBox<String> mes;
    @FXML
    private TextField anio;
    @FXML
    private ComboBox<String> hora;
    @FXML
    private ComboBox<String> minuto;
    private Stage stage;
    @FXML
    private Button botonGuardarTarea;
    @FXML
    private Button botonCrearAlarma;
    private LocalDateTime fechaActual = LocalDateTime.now();
    private VentanaCrearAlarmaTarea ventanaCrearAlarmaTarea;
    public VistaVentanaCrearTarea(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearTarea.fxml"));
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
        var dias = FXCollections.observableArrayList("1", "2", "3","4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        dia.setItems(dias);
        dia.setVisibleRowCount(5);
        var meses = FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo",
                "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        mes.setItems(meses);
        dia.setVisibleRowCount(5);
        var minutos = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31","32","33", "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
                "49","50","51","52","53","54","55","56","57","58","59");
        minuto.setItems(minutos);
        var horas = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        hora.setItems(horas);

    }
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarmaTarea = new VentanaCrearAlarmaTarea(stageNuevo);
        stageNuevo.showAndWait();
    }

/*    public void registrarEscuchaGuardarTarea(EventHandler<ActionEvent> eventHandler) {
        botonGuardarTarea.setOnAction(eventHandler);
    }*/

}
