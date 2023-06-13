package MVC;

import Calendario.Actividad.Actividad;
import Calendario.Alarmas.Alarma;
import Calendario.Enums.TipoRepeticion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.Repeticion;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaEvento extends VistaActividad{
    private InstanciaEvento evento;
    private Stage stage;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private Label fechaInicio;
    @FXML
    private Label fechaFin;
    @FXML
    private  TextField repeticion;
    @FXML
    private ListView<String> listaAlarmas;
    @FXML
    private Button editarTitulo;
    @FXML
    private Button editarDescripcion;
    @FXML
    private Button editarFecha;
    @FXML
    private Button editarRepeticion;
    @FXML
    private Button botonCrearAlarma;
    @FXML
    private Button botonEliminarAlarma;
    @FXML
    private Button eliminar;
    private VentanaCrearAlarma ventanaCrearAlarma;
    private List<List<String>> infoAlarmas = new ArrayList<>();
    public VistaEvento(InstanciaEvento evento) {
        this.evento = evento;
    }
    Map<String, List<String>> alarmas = new HashMap<>();
    
    public Evento getActividad(){return evento.getReferenciaEvento();}

    public void abrirVistaDetallada(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/verModificarEvento.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        Scene sceneNueva = new Scene(ventana);
        this.stage = stage;
        stage.setResizable(false);
        listaAlarmas.getItems().add("Sin alarmas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneNueva);
        initialize();
    }

    public void cerrarVistaDetallada(){
        stage.close();
    }

    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
    }

    public void abrirVentanaCrearAlarma() throws IOException {
        Stage stageNuevo = new Stage();
        ventanaCrearAlarma = new VentanaCrearAlarma(stageNuevo);
        getEscuchaGuardarAlarma(ventanaCrearAlarma, infoAlarmas, listaAlarmas, alarmas);
        stageNuevo.showAndWait();
    }

    public void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler){
        listaAlarmas.setOnMouseClicked(eventHandler);
    }

    public void habilitarBorrarAlarma(){
        botonEliminarAlarma.setDisable(false);
    }


    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma, infoAlarmas, alarmas);
    }

    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }

    public void mostrarEventoMes(Map<LocalDate, MenuButton> menuMes,
                                  Map<MenuItem,VistaActividad> vistas, LocalDate diaAct, LocalDate ultimoDia){
        String mensaje = getMensaje();
        while ((!diaAct.isAfter(ultimoDia)) && (!diaAct.isAfter(evento.getDiaFin()))) {
            if (menuMes.containsKey(diaAct)) {
                MenuItem item = new MenuItem(mensaje);
                item.setStyle("-fx-background-color: #ffd3a1;");
                MenuButton menu = menuMes.get(diaAct);
                if(menu.getItems().isEmpty()){
                    menu.setText("Ver más");
                }
                vistas.put(item, this);
                menu.getItems().add(item);
            }
            diaAct = diaAct.plusDays(1);
        }
    }

    private String getMensaje(){
        String mensaje = "Título: ";
        if (evento.getTitulo().length()!=0){
            mensaje+=evento.getTitulo();
        }
        mensaje+= "\nFecha Inicio: ";
        if (!evento.esDiaCompleto()){
            mensaje+=evento.getFechaInicio().toString()+"\nFecha fin: "+evento.getFechaFin().toString();
        }else{
            mensaje+=evento.getFechaInicio().toLocalDate().toString()+"\nFecha fin: "+ evento.getFechaFin().toLocalDate().toString();
        }
        return mensaje;
    }

    public void mostrarEventoSemana(Map<LocalDate, ListView<Label>> listasSemana,
                                     Map<Label,VistaActividad> vistas, LocalDate diaAct, LocalDate ultimoDia){
        String mensaje = getMensaje();
        while (!(diaAct.isAfter(ultimoDia)) && !(diaAct.isAfter(evento.getDiaFin()))){
            Label labelEvento = new Label(mensaje);
            labelEvento.setStyle("-fx-background-color: #ffd3a1;");
            if (listasSemana.containsKey(diaAct)){
                ListView<Label> lista = listasSemana.get(diaAct);
                lista.getItems().add(labelEvento);
                vistas.put(labelEvento, this);
            }
            diaAct = diaAct.plusDays(1);
        }

    }

    public void mostrarEventoDia(Map<Integer, MenuButton> menuDia,
                                  Map<MenuItem,VistaActividad> vistas){
        int hora = evento.getFechaInicio().toLocalTime().getHour();
        if (evento.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensaje());
        item.setStyle("-fx-background-color: #ffd3a1;");
        menu.getItems().add(item);
        vistas.put(item, this);
    }

    private void initialize(){
        titulo.setText(evento.getTitulo());
        descripcion.setText(evento.getDescripcion());
        if (evento.esDiaCompleto()){
            fechaInicio.setText(evento.getFechaInicio().toLocalDate().toString());
            fechaFin.setText(evento.getFechaFin().toLocalDate().toString());
        }else{
            fechaInicio.setText(evento.getFechaInicio().toString());
            fechaFin.setText(evento.getFechaFin().toString());
        }
        Repeticion repeticionE = evento.getReferenciaEvento().getRepeticion();
        if (repeticionE != null){
            repeticion.setText(getMensajeRepeticion(repeticionE));
        }else {
            repeticion.setText("Sin repetición");
        }
        inicializarListasAlarmas();
    }

    private String getMensajeRepeticion(Repeticion repeticionE){
        TipoRepeticion tipo = repeticionE.getTipoRepeticion();
        StringBuilder mensajeRepe = new StringBuilder(tipo.getTipo() + ". ");
        if (tipo.equals(TipoRepeticion.SEMANAL)){
            for (DayOfWeek dia: repeticionE.getDiasSemana()){
                switch (dia){
                    case SUNDAY -> mensajeRepe.append(" Domingos,");
                    case MONDAY -> mensajeRepe.append(" Lunes,");
                    case TUESDAY -> mensajeRepe.append(" Martes,");
                    case WEDNESDAY -> mensajeRepe.append(" Miércoles,");
                    case THURSDAY -> mensajeRepe.append(" Jueves,");
                    case FRIDAY -> mensajeRepe.append(" Viernes,");
                    case SATURDAY -> mensajeRepe.append(" Sábados,");
                }
            }
            mensajeRepe = new StringBuilder(mensajeRepe.substring(0, mensajeRepe.length() - 1));
        }
        mensajeRepe.append(". Intervalo: ").append(repeticionE.getIntervalo()).append(". Hasta: ");
        if (repeticionE.getFechaHasta() != null){
            mensajeRepe.append(repeticionE.getFechaHasta().toString());
        }else if (repeticionE.getOcurrencias() != 0){
            mensajeRepe.append(repeticionE.getOcurrencias()).append(" ocurrencias.");
        }else{
            mensajeRepe.append(" sin límite.");
        }
        return mensajeRepe.toString();
    }
    public void inicializarListasAlarmas(){
        inicializarListasAlarmas_(evento, listaAlarmas, infoAlarmas, alarmas);
    }
}
