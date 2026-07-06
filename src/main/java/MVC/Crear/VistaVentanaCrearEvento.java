package MVC.Crear;

import Calendario.Enums.Frecuencia;
import Calendario.Enums.LimiteRepeticion;
import Calendario.Enums.TipoRepeticion;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class VistaVentanaCrearEvento extends VentanaCrear {
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
    private ComboBox<String> diaHasta = crearComboBox(249, 71, "Dia", Constantes.DIAS_MES);
    @FXML
    private ComboBox<String> mesHasta = crearComboBox(324, 125, "Mes" ,Constantes.MESES);
    @FXML
    private TextField anioHasta = crearTextField(453, 55, "Año");
    @FXML
    private TextField ocurrencias = crearTextField(253, 37, "Intervalo");
    @FXML
    private Pane ventana;
    private EventoArgs argsEventoActual;
    private RepeticionArgs repeticionArgs;
    private Map<TipoRepeticion, String> tipoRepeticion = new HashMap<>();
    private Map<LimiteRepeticion, String> limiteRepeticion = new HashMap<>();

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

    /**
     * Registra el eventHandler al seleccionar la checkBox de Repetición
     */
    public void registrarEscuchaRepeticion(EventHandler<ActionEvent> eventHandler){
        repeticion.setOnAction(eventHandler);
    }

    /**
     * Habilita seleccionar la información sobre la repetición
     */
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

    /**
     * Abre la ventana para crear una Alarma
     */
    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarmaTarea = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma_(ventanaCrearAlarmaTarea);
        stageNuevo.showAndWait();
    }

    /**
     * Registra el eventHandler para crear una alarma
     */
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    /**
     * Guarda la información de la alarma a crear
     */
    public void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo) {
        agregarAlarmaALaLista_(listaAlarmas, aviso, intervalo, tiempoRelativo);
    }

    /**
     * Registra el eventHandler para eliminar una alarma
     */
    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    /**
     * Elimina la información guardada de las Alarmas seleccionadas
     */
    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma);
    }

    /**
     * Registra el eventHandler para seleccionar una alarma
     */
    public void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler){
        listaAlarmas.setOnMouseClicked(eventHandler);
    }

    /**
     * Habilita el botón para eliminar alarmas
     */
    public void habilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(false);
    }

    /**
     * Registra el eventHandler al seleccionar la checkBox de día completo
     */
    public void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler) {
        checkDiaCompleto.setOnAction(eventHandler);
    }

    /**
     * Devuelve true si la checkBox de día completo fue seleccionada
     */
    public boolean esDiaCompleto() {
        return checkDiaCompleto.isSelected();
    }

    /**
     * Deshabilita las ComboBox de hora y minuto
     */
    public void setFechaDiaCompleto() {
        horaInicio.setDisable(true);
        horaFin.setDisable(true);
        minutoInicio.setDisable(true);
        minutoFin.setDisable(true);
    }

    /**
     * Habilita las ComboBox de hora y minuto
     */
    public void setFechaConHora() {
        horaInicio.setDisable(false);
        horaFin.setDisable(false);
        minutoInicio.setDisable(false);
        minutoFin.setDisable(false);
    }

    /**
     * Escribe en la label errorFecha el error correspondiente
     */
    public void setMensajeErrorFecha(String mensaje) {
        errorFecha.setText(mensaje);
    }

    /**
     * Devuelve la información del evento a crear
     */
    public EventoArgs getInfoEvento(){
        return argsEventoActual;
    }

    /**
     * Cierra la ventana
     */
    public void cerrarVentana(){
        stage.close();
    }

    /**
     * Registra el eventHandler para guardar el evento
     */
    public void registrarEscuchaGuardarEvento(EventHandler<ActionEvent> eventHandler){
        botonGuardarEvento.setOnAction(eventHandler);
    }

    /**
     * Guarda la información del Evento. Si ocurre algún error, detiene el proceso y muestra el mensaje de
     * error correspondiente
     */
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
                setMensajeErrorFecha(Constantes.FECHA_INVALIDA);
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
                setMensajeErrorFecha(Constantes.FECHA_INVALIDA);
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve la información de la repetición
     */
    public RepeticionArgs getInfoRepeticion(){
        return repeticionArgs;
    }

    /**
     * Guarda la información de la repetición. Si ocurre algún error, detiene el proceso y muestra el mensaje de
     * error correspondiente
     */
    public boolean guardarRepeticionEvento() {
        if (!tieneRepeticion()) {
            return true;
        }
        try {
            repeticionArgs = getRepeticion();
        } catch (NumberFormatException | DateTimeException exception) {
            setMensajeErrorFecha(Constantes.REPETICION_INVALIDA);
            return false;
        }
        return true;
    }

    /**
     * Inicializa los controles
     */
    private void initialize(){
        var dias = Constantes.DIAS_MES;
        diaInicio.setItems(dias);
        diaInicio.setVisibleRowCount(Constantes.VISIBLE_ROWS);
        diaFin.setItems(dias);
        diaFin.setVisibleRowCount(Constantes.VISIBLE_ROWS);

        var meses = Constantes.MESES;
        mesInicio.setItems(meses);
        mesInicio.setVisibleRowCount(Constantes.VISIBLE_ROWS);
        mesFin.setItems(meses);
        mesFin.setVisibleRowCount(Constantes.VISIBLE_ROWS);

        var minutos = Constantes.MINUTOS;
        minutoInicio.setItems(minutos);
        minutoFin.setItems(minutos);
        minutoInicio.setValue(minutos.get(0));
        minutoFin.setValue(minutos.get(0));

        var horas = Constantes.HORAS;
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

        tipoRepeticion.put(TipoRepeticion.DIARIA, frecuencias.get(0));
        tipoRepeticion.put(TipoRepeticion.SEMANAL, frecuencias.get(1));
        tipoRepeticion.put(TipoRepeticion.MENSUAL, frecuencias.get(2));
        tipoRepeticion.put(TipoRepeticion.ANUAL, frecuencias.get(3));

        limiteRepeticion.put(LimiteRepeticion.OCURRENCIAS, hastaOpciones.get(0));
        limiteRepeticion.put(LimiteRepeticion.FECHA_LIMITE, hastaOpciones.get(1));
        limiteRepeticion.put(LimiteRepeticion.INFINITA, hastaOpciones.get(2));
    }

    /**
     * Registra el eventHandler al seleccionar como límite de repetición una fecha
     */
    private void registrarEscuchaHasta(EventHandler<ActionEvent> eventHandler){
        hastaRepe.setOnAction(eventHandler);
    }

    /**
     * Registra el eventHandler al seleccionar la frecuencia de la repetición
     */
    private void registrarEscuchaFrecuencia(EventHandler<ActionEvent> eventHandler){
        frecuencia.setOnAction(eventHandler);
    }

    /**
     * Si se selecciona el tipo de Repetición semanal, se habilitan las checkBox de los días de semana a elegir
     * En caso contrario, se deshabilitan
     */
    private void setFrecuencia(){
        boolean aux = true;
        if (repeticion.isSelected() && (frecuencia.getValue().equals(tipoRepeticion.get(TipoRepeticion.SEMANAL)))){
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

    /**
     * Habilita los controles correspondientes dependiendo del tipo de límite de repetición seleccionado
     */
    private void setHasta(){
        limpiarVentana();
        if (repeticion.isSelected() && hastaRepe.getValue().equals(limiteRepeticion.get(LimiteRepeticion.OCURRENCIAS))){
            ventana.getChildren().add(ocurrencias);
        }
        if (repeticion.isSelected() && hastaRepe.getValue().equals(limiteRepeticion.get(LimiteRepeticion.FECHA_LIMITE))){
            ventana.getChildren().add(diaHasta);
            ventana.getChildren().add(mesHasta);
            ventana.getChildren().add(anioHasta);
        }
    }

    /**
     * Elimina los controles del tipo de límite de repetición
     */
    private void limpiarVentana(){
        var aux = ventana.getChildren();
        aux.remove(ocurrencias);
        if (aux.contains(diaHasta)){
            aux.removeAll(diaHasta, mesHasta, anioHasta);
        }
    }

    /**
     * Crea un TextField con el promptText pasado por parámetro
     */
    private TextField crearTextField(int x, int width, String promptText){
        var textField = new TextField();
        textField.setLayoutY(425);
        textField.setLayoutX(x);
        textField.setCursor(Cursor.HAND);
        textField.setPrefWidth(width);
        textField.setPromptText(promptText);
        // Establecer el color de fondo
        textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        // Establecer el color del borde
        textField.setStyle("-fx-border-color: #bdbbbb;");
        return textField;
    }

    /**
     * Crea una ComboBox con las opciones pasadas por parámetro
     */
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

    /**
     * Maneja el evento de seleccionar la frecuencia
     */
    private void getEscuchaFrecuencia(){
        registrarEscuchaFrecuencia(actionEvent -> {
            setFrecuencia();
        });
    }

    /**
     * Maneja el evento de seleccionar el tipo de límite de repetición
     */
    private void getEscuchaHasta(){
        registrarEscuchaHasta(actionEvent -> {
            setHasta();
        });
    }

    /**
     * Devuelve el título
     */
    private  String getTitulo(){
        return titulo.getText();
    }

    /**
     * Devuelve la descripción
     */
    private String getDescripcion(){
        return descripcion.getText();
    }

    /**
     * Devuelve el día de inicio
     */
    private int getDiaInicio(){
        return Integer.parseInt(diaInicio.getValue());
    }

    /**
     * Devuelve el día de finalización
     */
    private int getDiaFin(){
        return Integer.parseInt(diaFin.getValue());
    }

    /**
     * Devuelve el mes de inicio
     */
    private int getMesInicio(){
        return 1 + mesInicio.getItems().indexOf(mesInicio.getValue());

    }

    /**
     * Devuelve el mes de finalización
     */
    private int getMesFin(){
        return 1 + mesFin.getItems().indexOf(mesFin.getValue());

    }


    /**
     * Devuelve el año de inicio
     */
    private int getAnioInicio(){
        return Integer.parseInt(anioInicio.getText());

    }

    /**
     * Devuelve el año de finalización
     */
    private int getAnioFin(){
        return Integer.parseInt(anioFin.getText());
    }

    /**
     * Devuelve la hora de inicio
     */
    private int getHoraInicio(){
        return Integer.parseInt(horaInicio.getValue());
    }

    /**
     * Devuelve la hora de finalización
     */
    private int getHoraFin(){
        return Integer.parseInt(horaFin.getValue());
    }

    /**
     * Devuelve el minuto de inicio
     */
    private int getMinutoInicio(){
        return Integer.parseInt(minutoInicio.getValue());
    }

    /**
     * Devuelve el minuto de finalización
     */
    private int getMinutoFin(){
        return Integer.parseInt(minutoFin.getValue());
    }

    /**
     * Devuelve true si se ha seleccionado la checkBox de repetición
     */
    private boolean tieneRepeticion(){
        return repeticion.isSelected();
    }

    /**
     * Obtiene la información de la repetición
     */
    private RepeticionArgs getRepeticion() throws NumberFormatException, DateTimeException{
        int intervalo = getIntervalo();
        if (esRepeticionSemanal()){
            return getHasta(intervalo, getDiasSemanas());
        }else{
            return getHasta(intervalo, getFrecuencia());
        }
    }

    /**
     * Devuelve los días de semana elegidos para la Repetición semanal
     */
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

    /**
     * Guarda los argumentos de la repetición semanal dependiendo del tipo de límite elegido
     */
    private RepeticionArgs getHasta(int intervalo, List<DayOfWeek> listaSemana){
        LimiteRepeticion limite = getTipoLimite();
        switch (limite){
            case  OCURRENCIAS -> {return new RepeticionArgs(intervalo, listaSemana, getOcurrencias());}
            case FECHA_LIMITE -> {return new RepeticionArgs(intervalo,listaSemana, getFechaHasta());}
            case INFINITA -> {return new RepeticionArgs(intervalo,listaSemana);}
            default -> {return null;}
        }
    }

    /**
     * Obtiene la clave del valor de hastaRepe en el mapa limiteRepeticion. Nunca devuelve null
     */
    private LimiteRepeticion getTipoLimite(){
        for (LimiteRepeticion limite: limiteRepeticion.keySet()){
            if (limiteRepeticion.get(limite).equals(hastaRepe.getValue())){
                return limite;
            }
        }
        return null;
    }

    /**
     * Guarda los argumentos de la repetición NO semanal dependiendo del tipo de límite elegido
     */
    private RepeticionArgs getHasta(int intervalo, Frecuencia frecuencia){
        LimiteRepeticion limite = getTipoLimite();
        switch (limite){
            case OCURRENCIAS -> {return new RepeticionArgs(intervalo, frecuencia, getOcurrencias());}
            case FECHA_LIMITE -> {return new RepeticionArgs(intervalo,frecuencia, getFechaHasta());}
            case INFINITA -> {return new RepeticionArgs(intervalo,frecuencia);}
            default -> {return null;}
        }
    }

    /**
     * Devuelve la fecha límite de la repetición
     */
    private LocalDate getFechaHasta(){
        return LocalDate.of(getAnioHasta(), getMesHasta(), getDiaHasta());
    }

    /**
     * Devuelve el año de finalización de la repetición
     */
    private int getAnioHasta(){
        return Integer.parseInt(anioHasta.getText());
    }

    /**
     * Devuelve el mes de finalización de la repetición
     */
    private int getMesHasta(){
        return 1 + mesHasta.getItems().indexOf(mesHasta.getValue());
    }

    /**
     * Devuelve el día de finalización de la repetición
     */
    private int getDiaHasta(){
        return Integer.parseInt(diaHasta.getValue());
    }

    /**
     * Devuelve la cantidad de ocurrencias de la repetición
     */
    private int getOcurrencias(){
        return Integer.parseInt(ocurrencias.getText());

    }

    /**
     * Devuelve el tipo de frecuencia elegido
     */
    private Frecuencia getFrecuencia(){
        TipoRepeticion tipo = getTipoRepeticion();
        switch (tipo){
            case  DIARIA -> {return Frecuencia.DIARIA;}
            case MENSUAL -> {return Frecuencia.MENSUAL;}
            case ANUAL -> {return  Frecuencia.ANUAL;}
            default -> {return null;}
        }
    }

    /**
     * Obtiene la clave del valor de frecuencia en el mapa tipoRepeticion. Nunca devuelve null
     */
    private TipoRepeticion getTipoRepeticion(){
        for (TipoRepeticion tipo : tipoRepeticion.keySet()){
            if(tipoRepeticion.get(tipo).equals(frecuencia.getValue())){
                return tipo;
            }
        }
        return null;
    }

    /**
     * Devuelve el intervalo de la repetición
     */
    private int getIntervalo(){
        return Integer.parseInt(intervaloRepe.getText());
    }

    /**
     * Devuelve true se ha seleccionado el tipo de repetición semanal
     */
    private boolean esRepeticionSemanal(){
        return frecuencia.getValue().equals(tipoRepeticion.get(TipoRepeticion.SEMANAL));
    }
}
