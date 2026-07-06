package MVC.Crear;

import Calendario.Main.Argumentos.TareaArgs;
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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VistaVentanaCrearTarea extends VentanaCrear {
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
    @FXML
    private Label errorFecha;
    private VentanaCrearAlarma ventanaCrearAlarmaTarea;
    private TareaArgs argsTareaActual;

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

    /**
     * Registra el eventHandler para crear una alarma
     */
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    /**
     * Abre la ventana para crear una Alarma y maneja los eventos asociados
     */
    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarmaTarea = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma_(ventanaCrearAlarmaTarea);
        stageNuevo.showAndWait();
    }

    /**
     * Guarda la información de la alarma a crear en listaAlarmas
     */
    public void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo){
        agregarAlarmaALaLista_(listaAlarmas, aviso, intervalo, tiempoRelativo);
    }

    /**
     * Registra el eventHandler para eliminar una alarma
     */
    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    /**
     * Elimina la información de la Alarma seleccionada y deshabilita el botón de Eliminar
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
     * Registra el eventHandler para guardar la información de la tarea a crear
     */
    public void registrarEscuchaGuardarTarea(EventHandler<ActionEvent> eventHandler){
        botonGuardarTarea.setOnAction(eventHandler);
    }

    /**
     * Devuelve la información de la tarea a crear
     */
    public TareaArgs getInfoTarea(){
        return argsTareaActual;
    }

    /**
     * Devuelve true si la checkBox de día completo fue seleccionada
     */    public boolean esDiaCompleto(){
        return checkDiaCompleto.isSelected();
    }

    /**
     * Registra el eventHandler al seleccionar la checkBox de día completo
     */
    public void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler) {
        checkDiaCompleto.setOnAction(eventHandler);
    }

    /**
     * Deshabilita las comboBox de hora y minuto
     */
    public void setFechaDiaCompleto(){
        hora.setDisable(true);
        minuto.setDisable(true);
    }

    /**
     * Habilita las comboBox de hora y minuto
     */
    public void setFechaConHora(){
        hora.setDisable(false);
        minuto.setDisable(false);

    }

    /**
     * Escribe en la label errorFecha el error correspondiente
     */
    public void setMensajeErrorFecha(String mensaje){
        errorFecha.setText(mensaje);
    }

    /**
     * Cierra la ventana
     */
    public void cerrarVentana(){
        stage.close();
    }

    /**
     * Guarda la información de la tarea a crear. Si hubo algún problema, detiene el proceso y muestra
     * el mensaje de error correspondiente
     */
    public boolean guardarDatosTarea() {
        if (esDiaCompleto()) {
            try {
                argsTareaActual = new TareaArgs(getTitulo(), getDescripcion(), LocalDate.of(getAnio(), getMes(), getDia()));
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha(Constantes.FECHA_INVALIDA);
                return false;
            }
        } else {
            try {
                argsTareaActual = new TareaArgs(getTitulo(), getDescripcion(), LocalDateTime.of(getAnio(), getMes(), getDia(), getHora(), getMinuto()));
            } catch (NumberFormatException | DateTimeException exception) {
                setMensajeErrorFecha(Constantes.FECHA_INVALIDA);
                return false;
            }
        }
        return true;
    }

    /**
     * Inicializa los controles
     */
    private void initialize(){
        var dias = Constantes.DIAS_MES;
        dia.setItems(dias);
        dia.setVisibleRowCount(5);
        var meses = Constantes.MESES;
        mes.setItems(meses);
        mes.setVisibleRowCount(5);
        var minutos = Constantes.MINUTOS;
        minuto.setItems(minutos);
        minuto.setValue(minutos.get(0));
        var horas = Constantes.HORAS;
        hora.setItems(horas);
        hora.setValue(horas.get(0));
        inicializarValorListaAlarmas_(listaAlarmas);
        deshabilitarBorrarAlarma_(botonEliminarAlarma);
    }


    /**
     * Devuelve el título de la tarea a crear
     */
    private String getTitulo(){
        return titulo.getText();
    }

    /**
     * Devuelve la descripción de la tarea a crear
     */
    private String getDescripcion(){
        return descripcion.getText();
    }


    /**
     * Devuelve el día de la tarea a crear
     */
    private int getDia(){
        return Integer.parseInt(dia.getValue());
    }

    /**
     * Devuelve el mes de la tarea a crear
     */
    private int getMes(){
        return 1 + mes.getItems().indexOf(mes.getValue());

    }

    /**
     * Devuelve el año de la tarea a crear
     */
    private int getAnio(){
        return Integer.parseInt(anio.getText());
    }

    /**
     * Devuelve la hora de la tarea a crear
     */
    private int getHora(){
        return Integer.parseInt(hora.getValue());
    }

    /**
     * Devuelve los minutos de la tarea a crear
     */
    private int getMinuto(){
        return Integer.parseInt(minuto.getValue());
    }

}
