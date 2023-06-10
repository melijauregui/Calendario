package MVC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VistaVentanaCrearTarea implements VentanaCrear{
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
    @FXML
    private CheckBox checkDiaCompleto;
    private Stage stage;
    @FXML
    private Button botonGuardarTarea;
    @FXML
    private Button botonCrearAlarma;
    @FXML
    private Button botonEliminarAlarma;
    @FXML
    private ListView<String> listaAlarmas;
    private LocalDateTime fechaActual = LocalDateTime.now();
    private VentanaCrearAlarma ventanaCrearAlarmaTarea;

    private List<List<String>> infoAlarmas = new ArrayList<>();
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
        mes.setVisibleRowCount(5);
        var minutos = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31","32","33", "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
                "49","50","51","52","53","54","55","56","57","58","59");
        minuto.setItems(minutos);
        var horas = FXCollections.observableArrayList("00","01", "02", "03","04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        hora.setItems(horas);
        inicializarValorListaAlarmas();
        deshabilitarBorrarAlarma();
    }

    private void inicializarValorListaAlarmas(){
        listaAlarmas.getItems().add("Sin alarmas");
    }
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarmaTarea = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma();
        stageNuevo.showAndWait();
    }

/*    public void registrarEscuchaGuardarTarea(EventHandler<ActionEvent> eventHandler) {
        botonGuardarTarea.setOnAction(eventHandler);
    }*/
    private void getEscuchaGuardarAlarma(){
        ventanaCrearAlarmaTarea.registrarEscuchaCrearAlarma(actionEvent -> {
            String aviso = ventanaCrearAlarmaTarea.getAviso();
            String tiempoRelativo = ventanaCrearAlarmaTarea.getTiempoRelativo();
            String intervalo = ventanaCrearAlarmaTarea.getIntervalo();
            if (intervalo.length()>0){
                if(manejarErrorIntervalo(intervalo) || manejarErrorTiempoRelativo(tiempoRelativo)){
                   return;
                }
            }
            agregarAlarmaALaLista(aviso, intervalo, tiempoRelativo);
            ventanaCrearAlarmaTarea.cerrarVentana();

        });
    }

    private boolean manejarErrorTiempoRelativo(String tiempoRelativo){
        boolean hayError = false;
        if (tiempoRelativo.equals(" - ")){
            ventanaCrearAlarmaTarea.setMensajeError("Tiempo relativo inválido");
            hayError = true;
        }else {
            ventanaCrearAlarmaTarea.setMensajeError("");
        }
        return hayError;
    }

    private boolean manejarErrorIntervalo(String intervalo) {
        try{
            int intervaloNumero = Integer.parseInt(intervalo);
            if (intervaloNumero <=0){
                throw new NumberFormatException();
            }
            ventanaCrearAlarmaTarea.setMensajeError("");
            return false;
        } catch(NumberFormatException exception){
            ventanaCrearAlarmaTarea.setMensajeError("Intervalo inválido");
        }
        return true;
    }

    private void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo){
        String mensaje = "Alarma con " + aviso;
        List<String> infoAlarma = new ArrayList<>();
        infoAlarma.add(aviso);
        if (!(intervalo.length()==0) && !(intervalo.equals(" - "))){
            mensaje += ", " + intervalo + " " + tiempoRelativo.toLowerCase() + " antes.";
            infoAlarma.add(intervalo);
            infoAlarma.add(tiempoRelativo);
        }
        infoAlarmas.add(infoAlarma); //aviso - intervalo - tRelativo
        var alarmas = listaAlarmas.getItems();
        if (alarmas.get(0).equals("Sin alarmas")){
            alarmas.remove(0);
        }
        alarmas.add(mensaje);
    }

    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    public void eliminarAlarmasSeleccionadas(){
        var indiceSeleccionado = listaAlarmas.getSelectionModel().getSelectedIndex();
        listaAlarmas.getItems().remove(indiceSeleccionado);
        if (listaAlarmas.getItems().isEmpty()){
            inicializarValorListaAlarmas();
        }
        deshabilitarBorrarAlarma();
    }

    public void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler){
        listaAlarmas.setOnMouseClicked(eventHandler);
    }

    public void habilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(false);
    }

    public void deshabilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(true);
    }




}
