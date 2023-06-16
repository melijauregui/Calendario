package MVC.VistasActividades;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadVisitor;
import Calendario.Tareas.Tarea;
import MVC.Crear.Constantes;
import MVC.Crear.VentanaCrearAlarma;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;

public class VistaTarea extends VistaActividad {

    private Tarea tarea;
    private Stage stage;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private Label fecha;
    @FXML
    private  Label estado;
    @FXML
    private ListView<Label> listaAlarmas;
    private List<List<String>> infoAlarmas = new ArrayList<>();
    @FXML
    private Button editarTitulo;
    @FXML
    private Button editarDescripcion;
    @FXML
    private Button editarFecha;
    @FXML
    private CheckBox completar;
    @FXML
    private Button botonCrearAlarma;;
    @FXML
    private Button botonEliminarAlarma;
    private VentanaCrearAlarma ventanaCrearAlarma;
    @FXML
    private Button eliminar;
    private Map<Label, List<String>> alarmas = new HashMap<>();

    public VistaTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    /**
     * Abre la vista detallada
     */
    public void abrirVistaDetallada(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/verModificarTarea.fxml"));
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
     * Muestra la Tarea según el rango semanal
     */
    public void mostrarTareaSemana(Map<LocalDate, ListView<Label>> listasSemana,
                                   Map<Label,VistaActividad> vistas){
        ListView<Label> lista = listasSemana.get(tarea.getFecha().toLocalDate());
        Label labelTarea =new Label(getMensaje());
        labelTarea.setStyle(getBackgroundColor());
        lista.getItems().add(labelTarea);
        vistas.put(labelTarea, this);
    }

    /**
     * Muestra la Tarea según el rango mensual
     */
    public  void mostrarTareaMes(Map<LocalDate, MenuButton> menuMes,
                                 Map<MenuItem,VistaActividad> vistas){
        if (!menuMes.containsKey(tarea.getFecha().toLocalDate())){
            return;
        }
        MenuButton menu = menuMes.get(tarea.getFecha().toLocalDate());
        guardarItem(menu, vistas, getMensaje(), getBackgroundColor());
    }

    /**
     * Muestra la Tarea según el rango diario
     */
    public void mostrarTareaDia(Map<Integer, MenuButton> menuDia,
                                Map<MenuItem,VistaActividad> vistas){
        int hora = tarea.getFecha().toLocalTime().getHour();
        if (tarea.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        guardarItem(menu, vistas, getMensaje(), getBackgroundColor());
    }

    /**
     * Cierra la vista detallada
     */
    public void cerrarVistaDetallada(){
        stage.close();
    }

    /**
     * Inicializa la lista de alarmas
     */
    public void inicializarListasAlarmas(){
        inicializarListasAlarmas_(tarea, listaAlarmas, infoAlarmas, alarmas);
    }

    /**
     * Registra el eventHandler para crear alarmas
     */
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void registrarEscuchaCompletar(EventHandler<ActionEvent> eventHandler) {
        completar.setOnAction(eventHandler);
    }

    public void completarTarea(){
        if (completar.isSelected()){
            estado.setText("Completada");
        }else {
            estado.setText("No completada");
        }
    }


    /**
     * Abre la ventana para crear alarmas
     */
    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarma = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma(ventanaCrearAlarma, infoAlarmas, listaAlarmas, alarmas);
        stageNuevo.showAndWait();
    }

    /**
     * Registra el eventHandler para eliminar alarmas
     */
    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    /**
     * Elimina la información asociada a la alarma seleccionada
     */
    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma, infoAlarmas, alarmas);
    }

    /**
     * Registra el eventHandler para seleccionar alarmas
     */
    public void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler){
        listaAlarmas.setOnMouseClicked(eventHandler);
    }

    /**
     * Habilita el botón para borrar alarmas
     */
    public void habilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(false);
    }

    /**
     * Devuelve la información de las alarmas a crear
     */
    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }

    /**
     * Devuelve la actividad asociada a la vista detallada
     */
    public Actividad getActividad(){
        return tarea;
    }

    /**
     * Registra el eventHandler para eliminar la Actividad
     */
    public void registrarEscuchaEliminar(EventHandler<ActionEvent> eventHandler) {
        eliminar.setOnAction(eventHandler);
    }

    /**
     * Devuelve el mensaje con la información básica de la Tarea
     */
    private String getMensaje() {
        String mensaje = "Título: ";
        if (tarea.getTitulo().length() != 0) {
            mensaje += tarea.getTitulo();
        }
        mensaje += "\nFecha: ";
        if (!tarea.esDiaCompleto()) {
            mensaje += tarea.getFecha().toString();
        } else {
            mensaje += tarea.getFecha().toLocalDate().toString();
        }
        mensaje += "\nEstado: ";
        if (tarea.estaCompleta()){
            mensaje += "completada";
        }else{
            mensaje += "no completada";
        }
        return mensaje;
    }

    /**
     * Inicializa los controles de la Vista detallada
     */
    private void initialize(){
        titulo.setText(tarea.getTitulo());
        descripcion.setText(tarea.getDescripcion());
        if (tarea.esDiaCompleto()){
            fecha.setText(tarea.getFecha().toLocalDate().toString());
        }else{
            fecha.setText(tarea.getFecha().toString());
        }
        if (tarea.estaCompleta()){
            estado.setText("Completada");
            completar.setSelected(true);
        }else {
            estado.setText("No completada");
            completar.setSelected(false);
        }
        inicializarListasAlarmas();
    }

    private String getBackgroundColor(){
        String background = "-fx-background-color: #adffc4;";
        if (tarea.esDiaCompleto()){
            background = "-fx-background-color: #1adb6b;";
        }
        return background;
    }
    public boolean getCompletar(){
        return completar.isSelected();
    }
    public void aceptarVisitor(VistaActividadVisitor vistaActividadVisitor){
        vistaActividadVisitor.visitarVistaTarea(this);
    }
}
