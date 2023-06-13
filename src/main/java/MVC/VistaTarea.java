package MVC;

import Calendario.Actividad.Actividad;
import Calendario.Alarmas.Alarma;
import Calendario.Tareas.Tarea;
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
    private ListView<String> listaAlarmas;
    private List<List<String>> infoAlarmas = new ArrayList<>();
    @FXML
    private Button editarTitulo;
    @FXML
    private Button editarDescripcion;
    @FXML
    private Button editarFecha;
    @FXML
    private Button completar;
    @FXML
    private Button botonCrearAlarma;;
    @FXML
    private Button botonEliminarAlarma;
    private VentanaCrearAlarma ventanaCrearAlarma;
    @FXML
    private Button eliminar;
    Map<String, List<String>> alarmas = new HashMap<>();

    public VistaTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public void abrirVistaDetallada(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/verModificarTarea.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        Scene sceneNueva = new Scene(ventana);
        this.stage = stage;
        listaAlarmas.getItems().add("Sin alarmas");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneNueva);
        initialize();
    }

    public void mostrarTareaSemana(Map<LocalDate, ListView<Label>> listasSemana,
                                   Map<Label,VistaActividad> vistas){
        ListView<Label> lista = listasSemana.get(tarea.getFecha().toLocalDate());
        Label labelTarea =new Label(getMensaje());
        labelTarea.setStyle("-fx-background-color: #adffc4;");
        lista.getItems().add(labelTarea);
        vistas.put(labelTarea, this);
    }

    private String getMensaje() {
        String mensaje = "Título: ";
        if (tarea.getTitulo().length() != 0) {
            mensaje += tarea.getTitulo();
        }
        mensaje += "\nFecha ";
        if (!tarea.esDiaCompleto()) {
            mensaje += tarea.getFecha().toString();
        } else {
            mensaje += tarea.getFecha().toLocalDate().toString();
        }
        return mensaje;
    }

    public  void mostrarTareaMes(Map<LocalDate, MenuButton> menuMes,
                                 Map<MenuItem,VistaActividad> vistas){
        if (!menuMes.containsKey(tarea.getFecha().toLocalDate())){
            return;
        }
        MenuButton menu = menuMes.get(tarea.getFecha().toLocalDate());
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensaje());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistas.put(item, this);
    }

    public void mostrarTareaDia(Map<Integer, MenuButton> menuDia,
                                Map<MenuItem,VistaActividad> vistas){
        int hora = tarea.getFecha().toLocalTime().getHour();
        if (tarea.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensaje());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistas.put(item, this);
    }


    public void cerrarVistaDetallada(){
        stage.close();
    }

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
        }else {
            estado.setText("No completada");
        }
        inicializarListasAlarmas();
    }

    public void inicializarListasAlarmas(){
        inicializarListasAlarmas_(tarea, listaAlarmas, infoAlarmas, alarmas);
    }
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarma = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma(ventanaCrearAlarma, infoAlarmas, listaAlarmas, alarmas);
        stageNuevo.showAndWait();
    }
    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }
    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma, infoAlarmas, alarmas);
    }

    public void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler){
        listaAlarmas.setOnMouseClicked(eventHandler);
    }

    public void habilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(false);
    }


    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }

    public Actividad getActividad(){
        return tarea;
    }


}
