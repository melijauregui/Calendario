package MVC;

import Calendario.Main.Argumentos.DuracionArgs;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.TareaArgs;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
public class VistaVentanaCrearEvento extends VentanaCrear{
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
    private Button botonGuardarEvento;
    @FXML
    private Button botonEliminarAlarma;
    @FXML
    private ListView<String> listaAlarmas;
    @FXML
    private Label errorFecha;;
    @FXML
    private Button botonCrearAlarma;
    private LocalDateTime fechaActual = LocalDateTime.now();
    @FXML
    private Label infoRepeticion;
    @FXML
    private ChoiceBox<String> frecuencia;
    private VentanaCrearAlarma ventanaCrearAlarmaTarea;
    @FXML
    private CheckBox repeticion;
    @FXML
    private CheckBox lun;
    @FXML
    private CheckBox mar;
    @FXML
    private CheckBox mie;
    @FXML
    private CheckBox jue;
    @FXML
    private CheckBox vie;
    @FXML
    private CheckBox sab;
    @FXML
    private CheckBox dom;
    @FXML
    private ChoiceBox<String> hastaRepe;
    @FXML
    private TextField intervaloRepe;
    @FXML
    private CheckBox checkDiaCompleto;
    @FXML
    private ComboBox<String> diaHasta = crearTextField(253, 67, FXCollections.observableArrayList("1", "2", "3","4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
    @FXML
    private ComboBox<String> mesHasta = crearTextField(324, 125 ,FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo",
            "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
    @FXML
    private TextField anioHasta = crearTextField(453, 55);
    @FXML
    private TextField ocurrencias = crearTextField(253, 37);
    @FXML
    private Pane ventana;
    private List<List<String>> infoAlarmas = new ArrayList<>();
    private EventoArgs argsEventoActual;


    //private VentanaCrearAlarmaTarea ventanaCrearAlarmaTarea;
    public VistaVentanaCrearEvento(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearEvento.fxml"));
        loader.setController(this);
        ventana = loader.load();
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
        var minutos = FXCollections.observableArrayList("0","1", "2", "3","4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31","32","33", "34","35","36","37","38","39","40","41","42","43","44","45","46","47","48",
                "49","50","51","52","53","54","55","56","57","58","59");
        minutoInicio.setItems(minutos);
        minutoFin.setItems(minutos);
        minutoInicio.setValue(minutos.get(0));
        minutoFin.setValue(minutos.get(0));
        var horas = FXCollections.observableArrayList("0","1", "2", "3","4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        horaInicio.setItems(horas);
        horaFin.setItems(horas);
        horaInicio.setValue(horas.get(0));
        horaFin.setValue(horas.get(0));
        var frecuencias = FXCollections.observableArrayList("Diaria", "Semanal", "Mensual", "Anual");
        frecuencia.setItems(frecuencias);
        var hastaOpciones = FXCollections.observableArrayList("Ocurrencias", "Fecha", "Sin límite");
        hastaRepe.setItems(hastaOpciones);
        hastaRepe.setValue("Sin límite");
        inicializarValorListaAlarmas();
        deshabilitarBorrarAlarma();
    }
    public void registrarEscuchaRepeticion(EventHandler<ActionEvent> eventHandler){
        repeticion.setOnAction(eventHandler);
    }
    public void setRepeticion(){
        boolean aux = true;
         if (repeticion.isSelected()){
            aux = !aux;
        }
        frecuencia.setDisable(aux);
        intervaloRepe.setDisable(aux);
        hastaRepe.setDisable(aux);
        getEscuchaFrecuencia();
        setFrecuencia();
        getEscuchaHasta();
        setHasta();

    }

    private void inicializarValorListaAlarmas(){
        listaAlarmas.getItems().add("Sin alarmas");
    }

    private void registrarEscuchaHasta(EventHandler<ActionEvent> eventHandler){
        hastaRepe.setOnAction(eventHandler);
    }
    private void registrarEscuchaFrecuencia(EventHandler<ActionEvent> eventHandler){
        frecuencia.setOnAction(eventHandler);
    }
    private void setFrecuencia(){
        boolean aux = true;
        if (repeticion.isSelected() && (frecuencia.getValue() == "Semanal")){
            aux = !aux;
        }
        lun.setDisable(aux);
        mar.setDisable(aux);
        mie.setDisable(aux);
        jue.setDisable(aux);
        vie.setDisable(aux);
        sab.setDisable(aux);
        dom.setDisable(aux);
    }
    private void setHasta(){
        limpiarVentana();
        if (repeticion.isSelected() && hastaRepe.getValue() == "Ocurrencias"){
            ventana.getChildren().add(ocurrencias);
        }
        if (repeticion.isSelected() && hastaRepe.getValue() == "Fecha"){
            ventana.getChildren().add(diaHasta);
            ventana.getChildren().add(mesHasta);
            ventana.getChildren().add(anioHasta);
        }
    }

    private void limpiarVentana(){
        var aux = ventana.getChildren();
        if (aux.contains(ocurrencias)) {
            aux.remove(ocurrencias);
        }
        if (aux.contains(diaHasta)){
            aux.removeAll(diaHasta, mesHasta, anioHasta);
        }
    }
    private TextField crearTextField(int x, int width){
        var textField = new TextField();
        textField.setLayoutY(425);
        textField.setLayoutX(x);
        textField.setCursor(Cursor.HAND);
        textField.setPrefWidth(width);
        // Establecer el color de fondo
        textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        // Establecer el color del borde
        textField.setStyle("-fx-border-color: #bdbbbb;");
        return textField;
    }
    private ComboBox<String> crearTextField(int x, int width, javafx.collections.ObservableList<String> opciones){
        var comboBox = new ComboBox<String>();
        comboBox.setLayoutY(425);
        comboBox.setLayoutX(x);
        comboBox.setCursor(Cursor.HAND);
        comboBox.setPrefWidth(width);
        comboBox.setItems(opciones);
        // Establecer el color de fondo
        comboBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        // Establecer el color del borde
        comboBox.setStyle("-fx-border-color: #bdbbbb;");
        return comboBox;
    }

    private void getEscuchaFrecuencia(){
        registrarEscuchaFrecuencia(actionEvent -> {
            setFrecuencia();
        });
    }
    private void getEscuchaHasta(){
        registrarEscuchaHasta(actionEvent -> {
            setHasta();
        });
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


    public void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler) {
        checkDiaCompleto.setOnAction(eventHandler);
    }

    public boolean esDiaCompleto() {
        return checkDiaCompleto.isSelected();
    }

    public void setFechaDiaCompleto() {
        horaInicio.setDisable(true);
        horaFin.setDisable(true);
        minutoInicio.setDisable(true);
        minutoFin.setDisable(true);
    }


    public void setFechaConHora() {
        horaInicio.setDisable(false);
        horaFin.setDisable(false);
        minutoInicio.setDisable(false);
        minutoFin.setDisable(false);
    }
    public void setMensajeErrorFecha(String mensaje) {
        errorFecha.setText(mensaje);
    }

    public void deshabilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(true);
    }

    public String getTitulo(){
        return titulo.getText();
    }

    public String getDescripcion(){
        return descripcion.getText();
    }

    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }
    public EventoArgs getInfoEvento(){
        return argsEventoActual;
    }

    public int getDiaInicio(){
        Integer i = Integer.parseInt(diaInicio.getValue());
        return i.intValue();
    }

    public int getDiaFin(){
        Integer i = Integer.parseInt(diaFin.getValue());
        return i.intValue();
    }

    public int getMesInicio(){
        return 1 + mesInicio.getItems().indexOf(mesInicio.getValue());

    }

    public int getMesFin(){
        return 1 + mesFin.getItems().indexOf(mesFin.getValue());

    }


    public int getAnioInicio(){
       return Integer.parseInt(anioInicio.getText());

    }

    public int getAnioFin(){
        return Integer.parseInt(anioFin.getText());
    }
    public int getHoraInicio(){
        return Integer.parseInt(horaInicio.getValue());
    }

    public int getHoraFin(){
        return Integer.parseInt(horaFin.getValue());
    }

    public int getMinutoInicio(){
        return Integer.parseInt(minutoInicio.getValue());
    }
    public int getMinutoFin(){
        return Integer.parseInt(minutoFin.getValue());
    }
    public void cerrarVentana(){
        stage.close();
    }

    public void registrarEscuchaGuardarEvento(EventHandler<ActionEvent> eventHandler){
        botonGuardarEvento.setOnAction(eventHandler);
    }

    public boolean guardarDatosEvento() {
        if (esDiaCompleto()) {
            try {
                DuracionArgs duracionArgs = new DuracionArgs(LocalDate.of(getAnioInicio(), getMesInicio(), getDiaInicio()), LocalDate.of(getAnioFin(), getMesFin(), getDiaFin()));
                argsEventoActual = new EventoArgs(getTitulo(), getDescripcion(), duracionArgs);
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha("Fecha inválida");
                return false;
            }
        } else {
            try {
                DuracionArgs duracionArgs = new DuracionArgs(LocalDate.of(getAnioInicio(), getMesInicio(), getDiaInicio()), LocalDate.of(getAnioFin(), getMesFin(), getDiaFin()), LocalTime.of(getHoraInicio(), getMinutoInicio()),LocalTime.of(getHoraFin(), getMinutoFin()));
                argsEventoActual = new EventoArgs(getTitulo(), getDescripcion(), duracionArgs);
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha("Fecha inválida");
                return false;
            }
        }
        return true;
    }


}
