package MVC.VistasActividades;

import Calendario.Enums.TipoRepeticion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.Repeticion;
import MVC.Crear.VentanaCrearAlarma;
import MVC.MVC_GENERAL.CantidadDias;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaEvento extends VistaActividad {
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
    private ListView<Label> listaAlarmas;
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
    private Map<Label, List<String>> alarmas = new HashMap<>();
    public VistaEvento(InstanciaEvento evento) {
        this.evento = evento;
    }

    
    public Evento getActividad(){return evento.getReferenciaEvento();}

    public void abrirVistaDetallada(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/verModificarEvento.fxml"));
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
     * Cierra la vista detallada
     */
    public void cerrarVistaDetallada(){
        stage.close();
    }

    /**
     * Registra el eventHandler para crear alarmas
     */
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler) {
        botonCrearAlarma.setOnAction(eventHandler);
    }

    /**
     * Registra el eventHandler para eliminar alarmas
     */
    public void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler) {
        botonEliminarAlarma.setOnAction(eventHandler);
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
     * Elimina la información asociada a la alarma seleccionada
     */
    public void eliminarAlarmasSeleccionadas(){
        eliminarAlarmasSeleccionadas_(listaAlarmas, botonEliminarAlarma, infoAlarmas, alarmas);
    }

    /**
     * Devuelve la información de las alarmas a crear
     */
    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }

    /**
     * Inicializa la lista de alarmas
     */
    public void inicializarListasAlarmas(){
        inicializarListasAlarmas_(evento, listaAlarmas, infoAlarmas, alarmas);
    }

    /**
     * Registra el eventHandler para eliminar la Actividad
     */
    public void registrarEscuchaEliminar(EventHandler<ActionEvent> eventHandler) {
        eliminar.setOnAction(eventHandler);
    }


    /**
     * Muestra la Instancia del Evento según el rango semanal
     */
    public void mostrarEventoSemana(Map<LocalDate, ListView<Label>> listasSemana,
                                    Map<Label,VistaActividad> vistas, LocalDate diaAct, LocalDate ultimoDia){
        String mensaje = getMensaje();
        while (!(diaAct.isAfter(ultimoDia)) && !(diaAct.isAfter(evento.getDiaFin()))){
            Label labelEvento = new Label(mensaje);
            labelEvento.setStyle(getBackgroundColor());
            if (listasSemana.containsKey(diaAct)){
                ListView<Label> lista = listasSemana.get(diaAct);
                lista.getItems().add(labelEvento);
                vistas.put(labelEvento, this);
            }
            diaAct = diaAct.plusDays(CantidadDias.UNO_LONG);
        }

    }

    /**
     * Muestra la Instancia del Evento según el rango mensual
     */
    public void mostrarEventoMes(Map<LocalDate, MenuButton> menuMes,
                                  Map<MenuItem,VistaActividad> vistas, LocalDate diaAct, LocalDate ultimoDia){
        while ((!diaAct.isAfter(ultimoDia)) && (!diaAct.isAfter(evento.getDiaFin()))) {
            if (menuMes.containsKey(diaAct)) {
                MenuButton menu = menuMes.get(diaAct);
                guardarItem(menu, vistas, getMensaje(), getBackgroundColor());
            }
            diaAct = diaAct.plusDays(CantidadDias.UNO_LONG);
        }
    }

    /**
     * Muestra la Instancia del Evento según el rango diario
     */
    public void mostrarEventoDia(Map<Integer, MenuButton> menuDia,
                                  Map<MenuItem,VistaActividad> vistas){
        int hora = evento.getFechaInicio().toLocalTime().getHour();
        if (evento.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        guardarItem(menu, vistas, getMensaje(), getBackgroundColor());
    }

    /**
     * Inicializa los controles de la Vista detallada
     */
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

    /**
     * Convierte el Tipo de Repetición a string
     */
    private String getTipoRepeticionToString(TipoRepeticion tipo){
        switch (tipo){
            case ANUAL -> {return "Anual";}
            case DIARIA -> {return "Diaria";}
            case MENSUAL -> {return "Mensual";}
            default -> {return "Semanal";}
        }
    }

    /**
     * Devuelve el mensaje con toda la información sobre la Repetición del evento
     */
    private String getMensajeRepeticion(Repeticion repeticionE){
        TipoRepeticion tipo = repeticionE.getTipoRepeticion();
        StringBuilder mensajeRepe = new StringBuilder(tipo + ". ");
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

    /**
     * Devuelve el mensaje con la información básica de la Instancia
     */
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

    private String getBackgroundColor(){
        String background = "-fx-background-color: #ffd3a1;";
        if (evento.esDiaCompleto()){
            background = "-fx-background-color: #fc9c26;";
        }
        return background;
    }

}
