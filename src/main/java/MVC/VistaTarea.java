package MVC;

import Calendario.Actividad.ActividadMutable;
import Calendario.Alarmas.Alarma;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class VistaTarea {

    private Tarea tarea;
    private Stage stage;
    //private Scene scene;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private Label fecha;
    @FXML
    private  Label estado;
    @FXML
    private ListView<Label> infoAlarmas;
    @FXML
    private Button editarTitulo;
    @FXML
    private Button editarDescripcion;
    @FXML
    private Button editarFecha;
    @FXML
    private Button completar;
    @FXML
    private Button agregarAlarma;
    @FXML
    private Button eliminarAlarma;
    public VistaTarea(Tarea tarea) {
        this.tarea = tarea;
    }


    public void mostrarTareaSemana(Tarea tarea, Map<LocalDate, ListView<Label>> listasSemana,
                                   Map<Label,VistaTarea> vistaTareas){
        ListView<Label> lista = listasSemana.get(tarea.getFecha().toLocalDate());
        Label labelTarea =new Label(getMensajeTarea());
        labelTarea.setStyle("-fx-background-color: #adffc4;");
        lista.getItems().add(labelTarea);
        vistaTareas.put(labelTarea, this);
    }

    private String getMensajeTarea() {
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

    public  void mostrarTareaMes(Tarea tarea, Map<LocalDate, MenuButton> menuMes,
                                 Map<MenuItem,VistaTarea> vistaTareas){
        if (!menuMes.containsKey(tarea.getFecha().toLocalDate())){
            return;
        }
        MenuButton menu = menuMes.get(tarea.getFecha().toLocalDate());
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensajeTarea());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistaTareas.put(item, this);
    }

    public void mostrarTareaDia(Tarea tarea, Map<Integer, MenuButton> menuDia,
                                Map<MenuItem,VistaTarea> vistaTareas){
        int hora = tarea.getFecha().toLocalTime().getHour();
        if (tarea.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensajeTarea());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistaTareas.put(item, this);
    }

    public Tarea getTarea(){return this.tarea;}

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
        for (Alarma alarma: tarea.getAlarmas()){
            String mensaje = alarma.getFechaAlarma().toString();
            Label infoAlarma = new Label(mensaje);
            infoAlarmas.getItems().add(infoAlarma);
        }
    }

}
