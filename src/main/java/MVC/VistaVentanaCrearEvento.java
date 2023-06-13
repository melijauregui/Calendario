package MVC;

import Calendario.Main.Argumentos.*;
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
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
    private Label errorFecha;
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
    private ComboBox<String> diaHasta = crearComboBox(249, 71, "Dia", FXCollections.observableArrayList("1", "2", "3","4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"));
    @FXML
    private ComboBox<String> mesHasta = crearComboBox(324, 125, "Mes" ,FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo",
            "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"));
    @FXML
    private TextField anioHasta = crearTextField(453, 55, "Año");
    @FXML
    private TextField ocurrencias = crearTextField(253, 37, "Intervalo");
    @FXML
    private Pane ventana;
    private EventoArgs argsEventoActual;
    private RepeticionArgs repeticionArgs;


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
        inicializarValorListaAlarmas_(listaAlarmas);
        deshabilitarBorrarAlarma_(botonEliminarAlarma);
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
        getEscuchaFrecuencia();
        getEscuchaHasta();
        setHasta();

    }

    private void registrarEscuchaHasta(EventHandler<ActionEvent> eventHandler){
        hastaRepe.setOnAction(eventHandler);
    }
    private void registrarEscuchaFrecuencia(EventHandler<ActionEvent> eventHandler){
        frecuencia.setOnAction(eventHandler);
    }
    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarmaTarea = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma_(ventanaCrearAlarmaTarea);
        stageNuevo.showAndWait();
    }

    private void setFrecuencia(){
        boolean aux = true;
        if (repeticion.isSelected() && (frecuencia.getValue().equals("Semanal"))){
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
        if (repeticion.isSelected() && hastaRepe.getValue().equals("Ocurrencias")){
            ventana.getChildren().add(ocurrencias);
        }
        if (repeticion.isSelected() && hastaRepe.getValue().equals("Fecha")){
            ventana.getChildren().add(diaHasta);
            ventana.getChildren().add(mesHasta);
            ventana.getChildren().add(anioHasta);
        }
    }

    private void limpiarVentana(){
        var aux = ventana.getChildren();
        aux.remove(ocurrencias);
        if (aux.contains(diaHasta)){
            aux.removeAll(diaHasta, mesHasta, anioHasta);
        }
    }
    private TextField crearTextField(int x, int width, String promText){
        var textField = new TextField();
        textField.setLayoutY(425);
        textField.setLayoutX(x);
        textField.setCursor(Cursor.HAND);
        textField.setPrefWidth(width);
        textField.setPromptText(promText);
        // Establecer el color de fondo
        textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        // Establecer el color del borde
        textField.setStyle("-fx-border-color: #bdbbbb;");
        return textField;
    }
    private ComboBox<String> crearComboBox(int x, int width, String promText, javafx.collections.ObservableList<String> opciones){
        var comboBox = new ComboBox<String>();
        comboBox.setLayoutY(425);
        comboBox.setLayoutX(x);
        comboBox.setCursor(Cursor.HAND);
        comboBox.setPrefWidth(width);
        comboBox.setItems(opciones);
        comboBox.setValue(promText);
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

    public void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo) {
        agregarAlarmaALaLista_(listaAlarmas, aviso, intervalo, tiempoRelativo);
    }

    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma);
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


    private  String getTitulo(){
        return titulo.getText();
    }

    private String getDescripcion(){
        return descripcion.getText();
    }

    public List<List<String>> getInfoAlarmas(){
        return getInfoAlarmas_();
    }
    public EventoArgs getInfoEvento(){
        return argsEventoActual;
    }

    private int getDiaInicio(){
        return Integer.parseInt(diaInicio.getValue());
    }

    private int getDiaFin(){
        return Integer.parseInt(diaFin.getValue());
    }

    private int getMesInicio(){
        return 1 + mesInicio.getItems().indexOf(mesInicio.getValue());

    }

    private int getMesFin(){
        return 1 + mesFin.getItems().indexOf(mesFin.getValue());

    }


    private int getAnioInicio(){
       return Integer.parseInt(anioInicio.getText());

    }

    private int getAnioFin(){
        return Integer.parseInt(anioFin.getText());
    }
    private int getHoraInicio(){
        return Integer.parseInt(horaInicio.getValue());
    }

    private int getHoraFin(){
        return Integer.parseInt(horaFin.getValue());
    }

    private int getMinutoInicio(){
        return Integer.parseInt(minutoInicio.getValue());
    }
    private int getMinutoFin(){
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
                LocalDate fechaInicio = LocalDate.of(getAnioInicio(), getMesInicio(), getDiaInicio());
                LocalDate fechaFin = LocalDate.of(getAnioFin(), getMesFin(), getDiaFin());
                if (fechaInicio.isAfter(fechaFin)){
                    throw new NumberFormatException();
                }
                DuracionArgs duracionArgs = new DuracionArgs(fechaInicio, fechaFin);
                argsEventoActual = new EventoArgs(getTitulo(), getDescripcion(), duracionArgs);
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha("Fecha inválida");
                return false;
            }
        } else {
            try {
                LocalDate fechaInicio = LocalDate.of(getAnioInicio(), getMesInicio(), getDiaInicio());
                LocalDate fechaFin = LocalDate.of(getAnioFin(), getMesFin(), getDiaFin());
                LocalTime horaInicio = LocalTime.of(getHoraInicio(), getMinutoInicio());
                LocalTime horaFin = LocalTime.of(getHoraFin(), getMinutoFin());
                if (LocalDateTime.of(fechaInicio, horaInicio).isAfter(LocalDateTime.of(fechaFin, horaFin))){
                    throw new NumberFormatException();
                }
                DuracionArgs duracionArgs = new DuracionArgs(fechaInicio, fechaFin, horaInicio ,horaFin);
                argsEventoActual = new EventoArgs(getTitulo(), getDescripcion(), duracionArgs);
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha("Fecha inválida");
                return false;
            }
        }
        return true;
    }

    public RepeticionArgs getInfoRepeticion(){
        return repeticionArgs;
    }

    public boolean guardarRepeticionEvento() {
        if (!tieneRepeticion()) {
            return true;
        }
        try {
            repeticionArgs = getRepeticion();
        } catch (NumberFormatException | DateTimeException exception) {
            setMensajeErrorFecha("Repetición inválida");
            return false;
        }
        return true;
    }

    private boolean tieneRepeticion(){
        return repeticion.isSelected();
    }

    public RepeticionArgs getRepeticion() throws NumberFormatException, DateTimeException{
        int intervalo = getIntervalo();
        if (esRepeticionSemanal()){
            return getHasta(intervalo, getDiasSemanas());
        }else{
            return getHasta(intervalo, getFrecuencia());
        }
    }
    private List<DayOfWeek> getDiasSemanas(){
        List<DayOfWeek> lista = new ArrayList<>();
        if (lun.isSelected()){
            lista.add(DayOfWeek.MONDAY);
        }
        if (mar.isSelected()){
            lista.add(DayOfWeek.TUESDAY);
        }
        if (mie.isSelected()){
            lista.add(DayOfWeek.WEDNESDAY);
        }
        if (jue.isSelected()){
            lista.add(DayOfWeek.THURSDAY);
        }
        if (vie.isSelected()){
            lista.add(DayOfWeek.FRIDAY);
        }
        if (sab.isSelected()){
            lista.add(DayOfWeek.SATURDAY);
        }
        if (dom.isSelected()){
            lista.add(DayOfWeek.SUNDAY);
        }
        return lista;
    }

    private RepeticionArgs getHasta(int intervalo, List<DayOfWeek> listaSemana){
        switch (hastaRepe.getValue()){
            case "Ocurrencias" -> {return new RepeticionArgs(intervalo, listaSemana, getOcurrencias());}
            case "Fecha" -> {return new RepeticionArgs(intervalo,listaSemana, getFechaHasta());}
            case "Sin límite" -> {return new RepeticionArgs(intervalo,listaSemana);}
            default -> {return null;}
        }
    }
    private RepeticionArgs getHasta(int intervalo, Frecuencia frecuencia){
        switch (hastaRepe.getValue()){
            case "Ocurrencias" -> {return new RepeticionArgs(intervalo, frecuencia, getOcurrencias());}
            case "Fecha" -> {return new RepeticionArgs(intervalo,frecuencia, getFechaHasta());}
            case "Sin límite" -> {return new RepeticionArgs(intervalo,frecuencia);}
            default -> {return null;}
        }
    }

    private LocalDate getFechaHasta(){
        return LocalDate.of(getAnioHasta(), getMesHasta(), getDiaHasta());
    }

    private int getAnioHasta(){
        return Integer.parseInt(anioHasta.getText());
    }
    private int getMesHasta(){
        return 1 + mesHasta.getItems().indexOf(mesHasta.getValue());
    }
    private int getDiaHasta(){
        return Integer.parseInt(diaHasta.getValue());
    }


    private int getOcurrencias(){
        return Integer.parseInt(ocurrencias.getText());

    }

    private Frecuencia getFrecuencia(){
        switch (frecuencia.getValue()){
            case "Diaria" -> {return Frecuencia.DIARIA;}
            case "Mensual" -> {return Frecuencia.MENSUAL;}
            case "Anual" -> {return  Frecuencia.ANUAL;}
            default -> {return null;}
        }
    }
    private int getIntervalo(){
        return Integer.parseInt(intervaloRepe.getText());
    }

    private boolean esRepeticionSemanal(){
        return frecuencia.getValue().equals("Semanal");
    }
}
