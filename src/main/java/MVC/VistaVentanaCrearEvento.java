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

public class VistaVentanaCrearEvento {
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private ComboBox<String> diaInicio;
    @FXML
    private ComboBox<String> mesInicio;
    @FXML
    private TextField anioInicio;
    @FXML
    private ComboBox<String> diaFin;
    @FXML
    private ComboBox<String> mesFin;
    @FXML
    private TextField anioFin;
    @FXML
    private ComboBox<String> horaInicio;
    @FXML
    private ComboBox<String> minutoInicio;
    @FXML
    private ComboBox<String> horaFin;
    @FXML
    private ComboBox<String> minutoFin;
    private Stage stage;
    @FXML
    private Button botonGuardarTarea;
    @FXML
    private Button botonCrearAlarma;
    private LocalDateTime fechaActual = LocalDateTime.now();
    @FXML
    private Label infoRepeticion;
    @FXML
    private ChoiceBox<String> choiceCrearRepeticion;
    //private VentanaCrearAlarmaTarea ventanaCrearAlarmaTarea;
    public VistaVentanaCrearEvento(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearEvento.fxml"));
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
        diaInicio.setItems(dias);
        diaInicio.setVisibleRowCount(5);
        diaFin.setItems(dias);
        diaFin.setVisibleRowCount(5);
        var meses = FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo",
                "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        mesInicio.setItems(meses);
        mesInicio.setVisibleRowCount(5);
        mesFin.setItems(meses);
        mesFin.setVisibleRowCount(5);
        var minutos = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31","32","33", "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
                "49","50","51","52","53","54","55","56","57","58","59");
        minutoInicio.setItems(minutos);
        minutoFin.setItems(minutos);
        var horas = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        horaInicio.setItems(horas);
        horaFin.setItems(horas);

    }
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        //ventanaCrearAlarmaTarea = new VentanaCrearAlarmaTarea(stageNuevo);
        stageNuevo.showAndWait();
    }
}
