package MVC.MVC_GENERAL;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.RepeticionArgs;
import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Main.Calendario;
import Calendario.Tareas.Tarea;
import MVC.Crear.VentanaCrear;
import MVC.Crear.VistaVentanaCrearEvento;
import MVC.Crear.VistaVentanaCrearTarea;
import MVC.VistasActividades.VistaActividad;
import MVC.VistasActividades.VistaEvento;
import MVC.VistasActividades.VistaTarea;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


public class Vista {
    private Calendario calendario;
    private Stage stage;
    private Scene scene;
    private LocalDate diaActual = LocalDate.now();
    private ChoiceBox<String> choiceRango;
    private AnchorPane paneGeneral;
    private AnchorPane paneInicial = new AnchorPane();
    private Label dia = new Label();
    private TipoRango rango;
    private Map<TipoRango, String> valorRango = new HashMap<>();
    private Button siguiente;
    private Button anterior;
    private MenuButton menuCrearActividad;
    private MenuItem itemCrearTarea;
    private MenuItem itemCrearEvento;
    private VistaVentanaCrearTarea vistaVentanaCrearTarea;
    private VistaVentanaCrearEvento vistaVentanaCrearEvento;
    private RepeticionArgs argsRepeticionEvento;
    private TareaArgs argsTareaActual;
    private EventoArgs argsEventoActual;
    private List<List<String>> infoAlarmaActual;
    private Map<LocalDate,ListView<Label>> listasSemana;
    private Map<LocalDate,MenuButton> menuMes;
    private Map<Integer,MenuButton> menuDia;
    private Map<Label, VistaActividad> vistasLabel;
    private Map<MenuItem, VistaActividad> vistasMenu;
    private VistaActividad vistaActual;
    private Actividad actividadActual;
    private boolean eliminarActividad;

    public Vista(Calendario calendario, Stage stage) throws IOException {
        this.calendario = calendario;
        this.stage = stage;
        inicializar();
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setResizable(false);
        setearSemana();
        stage.show();
    }

    // Registrar

    /**
     * Registra el eventHandler del Botón 'siguiente'
     */
    public void registrarEscuchaSiguiente(EventHandler<ActionEvent> eventHandler) {
        siguiente.setOnAction(eventHandler);
    }

    /**
     * Registra el eventHandler del Botón 'anterior'
     */
    public void registrarEscuchaAnterior(EventHandler<ActionEvent> eventHandler) {
        anterior.setOnAction(eventHandler);
    }


    /**
     * Registra el eventHandler de la ChoiceBox Rango
     */
    public void registrarEscuchaRango(EventHandler<ActionEvent> eventHandler) {
        choiceRango.setOnAction(eventHandler);
    }

    /**
     * Registra el eventHandler para crear un Evento
     */
    public void registrarEscuchaCrearEvento(EventHandler<ActionEvent> eventHandler) {
        itemCrearEvento.setOnAction(eventHandler);
    }

    /**
     * Registra el eventHandler para crear una Tarea
     */
    public void registrarEscuchaCrearTarea(EventHandler<ActionEvent> eventHandler) {
        itemCrearTarea.setOnAction(eventHandler);
    }

    // Getters

    /**
     * Setea el siguiente día/semana/mes, según corresponda
     */
    public void getEscuchaSiguiente() {
        setDiaPlusActual();
        setTipoRango(getEscuchaRango());
    }
    /**
     * Setea el anterior día/semana/mes, según corresponda
     */
    public void getEscuchaAnterior() {
        setDiaMinusActual();
        setTipoRango(getEscuchaRango());
    }

    /**
     * Devuelve el valor del rango de tiempo actual
     */
    public String getEscuchaRango() {
        return choiceRango.getValue();
    }

    // Setters

    /**
     * Setea la vista asociada al tipo de Rango de tiempo actual
     */
    public void setTipoRango(String opcion) {
        TipoRango tipo = getTipoRango(opcion);
        switch (tipo) {
            case SEMANA -> setearSemana();
            case DIA -> setearDia();
            case MES -> setearMes();
        }
    }

    // Crear Actividades

    /**
     * Abre la ventana para crear una Tarea y maneja los eventos asociados
     */
    public void abrirVentanaCrearTarea() throws IOException {
        Stage stageNuevo = new Stage();
        vistaVentanaCrearTarea = new VistaVentanaCrearTarea(stageNuevo);
        getEscuchaCrearAlarma(vistaVentanaCrearTarea);
        getEscuchaSeleccionarAlarmas(vistaVentanaCrearTarea);
        getEscuchaEliminarAlarmas(vistaVentanaCrearTarea);
        getEscuchaEsDiaCompleto(vistaVentanaCrearTarea);
        getEscuchaGuardarTarea();
        stageNuevo.showAndWait();
    }

    /**
     * Abre la ventana para crear un Evento y maneja los eventos asociados
     */
    public void abrirVentanaCrearEvento() throws IOException {
        Stage stageNuevo = new Stage();
        vistaVentanaCrearEvento = new VistaVentanaCrearEvento(stageNuevo);
        getEscuchaCrearAlarma(vistaVentanaCrearEvento);
        getEscuchaSeleccionarAlarmas(vistaVentanaCrearEvento);
        getEscuchaEliminarAlarmas(vistaVentanaCrearEvento);
        getEscuchaEsDiaCompleto(vistaVentanaCrearEvento);
        getEscuchaEventoConRepeticion();
        getEscuchaGuardarEvento();
        stageNuevo.showAndWait();
    }

    /**
     * Maneja el evento de eliminar una Actividad
     */
    public void getEscuchaEliminar(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaEliminar(actionEvent -> {
            eliminarActividad = true;
            vistaActividad.cerrarVistaDetallada();
        });
    }

    /**
     * Devuelve true si el usuario quiere eliminar una Actividad
     */
    public boolean eliminarActividad(){
        return eliminarActividad;
    }

    public void actualizarEliminar(){
        eliminarActividad = false;
    }


    // Crear y eliminar alarmas

    /**
     * Maneja el evento para crear una Alarma al crear una Actividad
     */
    public void getEscuchaCrearAlarma(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaCrearAlarma(actionEvent -> {
            try {
                ventanaCrear.abrirVentanaCrearAlarma();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Maneja el evento para eliminar una Alarma al crear una Actividad
     */
    public void getEscuchaEliminarAlarmas(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaEliminarAlarma(actionEvent -> ventanaCrear.eliminarAlarmasSeleccionadas());
    }

    /**
     * Maneja el evento para seleccionar una Alarma al crear una Actividad
     */
    public void getEscuchaSeleccionarAlarmas(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaSeleccionarAlarma(mouseEvent -> {
            ventanaCrear.habilitarBorrarAlarma();
        });
    }

    /**
     * Maneja el evento para determinar si una Actividad es de día completo
     */
    private void getEscuchaEsDiaCompleto(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaSeleccionarDiaCompleto(actionEvent -> {
            if (ventanaCrear.esDiaCompleto()) {
                ventanaCrear.setFechaDiaCompleto();
            } else {
                ventanaCrear.setFechaConHora();
            }
        });
    }


    /**
     * Maneja el evento para obtener la información de la Repetición del Evento
     */
    private void getEscuchaEventoConRepeticion() {
        vistaVentanaCrearEvento.registrarEscuchaRepeticion(actionEvent -> {
            vistaVentanaCrearEvento.setRepeticion();
        });
    }

    /**
     * Maneja el evento para guardar la información de la Tarea creada
     */
    public void getEscuchaGuardarTarea() {
        vistaVentanaCrearTarea.registrarEscuchaGuardarTarea(actionEvent -> {
            if (!vistaVentanaCrearTarea.guardarDatosTarea()) {
                return;
            }
            argsTareaActual = vistaVentanaCrearTarea.getInfoTarea();
            infoAlarmaActual = vistaVentanaCrearTarea.getInfoAlarmas();
            vistaVentanaCrearTarea.cerrarVentana();
        });
    }

    /**
     * Maneja el evento para guardar la información del Evento creado
     */
    public void getEscuchaGuardarEvento() {
        vistaVentanaCrearEvento.registrarEscuchaGuardarEvento(actionEvent -> {
            if (!vistaVentanaCrearEvento.guardarDatosEvento()) {
                return;
            }
            if (!vistaVentanaCrearEvento.guardarRepeticionEvento()){
                return;
            }
            argsRepeticionEvento = vistaVentanaCrearEvento.getInfoRepeticion();
            argsEventoActual = vistaVentanaCrearEvento.getInfoEvento();
            infoAlarmaActual = vistaVentanaCrearEvento.getInfoAlarmas();
            vistaVentanaCrearEvento.cerrarVentana();
        });
    }

    /**
     * Devuelve la información de la Tarea a crear
     */
    public TareaArgs getInfoTarea() {
        return argsTareaActual;
    }

    /**
     * Devuelve la información del Evento a crear
     */
    public EventoArgs getInfoEvento() {
        return argsEventoActual;
    }

    /**
     * Devuelve la información de la repetición del Evento a crear
     */
    public RepeticionArgs getInfoRepeticionEvento() {
        return argsRepeticionEvento;
    }

    /**
     * Devuelve la información de la Alarma a crear
     */
    public List<List<String>> getInfoAlarmaCreada() {
        return infoAlarmaActual;
    }

    /**
     * Elimina la información de la Tarea luego de ser creada por el Controlador
     */
    public void eliminarTareaActual(){
        this.argsTareaActual = null;
        eliminarAlarmaActual();
    }

    /**
     * Elimina la información del Evento luego de ser creado por el Controlador
     */
    public void eliminarEventoActual(){
        this.argsEventoActual = null;
        eliminarAlarmaActual();
        this.argsRepeticionEvento = null;
    }

    // Actualizar Vista

    /**
     * Actualiza la vista de las actividades del calendario
     */
    public void actualizarVistaActividades(){
        switch (rango){
            case SEMANA -> actualizarListas();
            case MES -> actualizarMenuMes();
            case DIA -> actualizarMenuDia();
        }
    }

    /**
     * Actualiza las listas del rango semanal actual, mostrando las actividades del calendario
     */
    public void actualizarListas(){
        reiniciarListSemana();
        vistasLabel = new HashMap<>();
        var primerDia = getPrimerDiaSemana(diaActual);
        var ultimoDia = getUltimoDiaSemana(primerDia);
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(primerDia, LocalTime.of(0,0)), LocalDateTime.of(ultimoDia, LocalTime.of(23,59)));
        for (Actividad act : acts){

            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                }

                @Override
                public void visitarTarea(Tarea tarea) {
                    VistaTarea vistaTarea = new VistaTarea(tarea);
                    vistaTarea.mostrarTareaSemana(listasSemana, vistasLabel);
                }

                @Override
                public void visitarInstancia(InstanciaEvento instancia) {
                    var ultimoDia = getUltimoDiaSemana(getPrimerDiaSemana(diaActual));
                    LocalDate diaAct = (instancia.getDiaInicio().isBefore(getPrimerDiaSemana(diaActual))) ? getPrimerDiaSemana(diaActual): instancia.getDiaInicio();
                    VistaEvento vistaEvento = new VistaEvento(instancia);
                    vistaEvento.mostrarEventoSemana(listasSemana, vistasLabel, diaAct, ultimoDia);
                }
            });
        }
    }

    /**
     * Actualiza las menu del rango mensual actual, mostrando las actividades del calendario
     */
    public void actualizarMenuMes(){
        reiniciarMenuMes();
        var primerDia = getPrimerDiaSemana(getPrimerDiaMes(diaActual));
        vistasMenu = new HashMap<>();
        var ultimoDia = getUltimoDiaMes(primerDia);
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(primerDia, LocalTime.of(0,0)), LocalDateTime.of(ultimoDia, LocalTime.of(23,59)));
        for (Actividad act : acts){
            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                }

                @Override
                public void visitarTarea(Tarea tarea) {
                    VistaTarea vistaTarea = new VistaTarea(tarea);
                    vistaTarea.mostrarTareaMes(menuMes, vistasMenu);
                }

                @Override
                public void visitarInstancia(InstanciaEvento instancia) {
                    var ultimoDia = getUltimoDiaMes(getPrimerDiaMes(diaActual));
                    LocalDate diaAct = instancia.getDiaInicio().isBefore(getPrimerDiaSemana(getPrimerDiaMes(diaActual))) ? getPrimerDiaSemana(getPrimerDiaMes(diaActual)): instancia.getDiaInicio();
                    VistaEvento vistaEvento = new VistaEvento(instancia);
                    vistaEvento.mostrarEventoMes(menuMes, vistasMenu, diaAct, ultimoDia);
                }
            });
        }
    }


    /**
     * Actualiza los menu del dia actual mostrado, mostrando las actividades del calendario
     */
    public void actualizarMenuDia(){
        reiniciarMenuDia();
        vistasMenu = new HashMap<>();
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(diaActual, LocalTime.of(0,0)), LocalDateTime.of(diaActual, LocalTime.of(23,59)));
        for (Actividad act : acts){
            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                }

                @Override
                public void visitarTarea(Tarea tarea){
                    VistaTarea vistaTarea = new VistaTarea(tarea);
                    vistaTarea.mostrarTareaDia(menuDia, vistasMenu);
                }

                @Override
                public void visitarInstancia(InstanciaEvento instancia) {
                    VistaEvento vistaEvento = new VistaEvento(instancia);
                    vistaEvento.mostrarEventoDia(menuDia, vistasMenu);
                }
            });
        }
    }

    // Vista detallada de actividades

    /**
     * Registra el eventHandler para ver la vista detallada de las actividades en el rango 'semana'
     */
    public void registrarEscuchaVerActividadLabel(EventHandler<MouseEvent> eventHandler){
        for (Label label: vistasLabel.keySet()){
            label.setOnMouseClicked(eventHandler);
        }
    }

    /**
     * Maneja el evento para ver la vista detallada de las actividades en el rango 'mes' o 'día'
     */
    public Set<MenuItem> verActividadMenu(){
        return vistasMenu.keySet();
    }

    /**
     * Abre la vista detallada de la Actividad seleccionada en el rango 'semana'
     * y maneja los eventos asociados
     */
    public void abrirVistaDetalladaSemana() throws IOException {
        for (ListView<Label> lista: listasSemana.values()){
            Label labelSeleccionada = lista.getSelectionModel().getSelectedItem();
            if (labelSeleccionada != null){
                vistaActual = vistasLabel.get(labelSeleccionada);
                Stage nuevoStage = new Stage();
                vistaActual.abrirVistaDetallada(nuevoStage);
                getEscuchaCrearAlarma(vistaActual);
                getEscuchaSeleccionarAlarmas(vistaActual);
                getEscuchaEliminarAlarmas(vistaActual);
                getEscuchaEliminar(vistaActual);
                nuevoStage.showAndWait();
                guardarActividad();
                break;
            }
        }
    }

    /**
     * Abre la vista detallada de la Actividad seleccionada en el rango 'mes' o 'dia'
     * y maneja los eventos asociados
     */
    public void abrirVistaDetalladaMenu(MenuItem item) throws IOException {
        vistaActual = vistasMenu.get(item);
        Stage nuevoStage = new Stage();
        vistaActual.abrirVistaDetallada(nuevoStage);
        getEscuchaCrearAlarma(vistaActual);
        getEscuchaSeleccionarAlarmas(vistaActual);
        getEscuchaEliminarAlarmas(vistaActual);
        getEscuchaEliminar(vistaActual);
        nuevoStage.showAndWait();
        guardarActividad();

    }

    /**
     * Maneja el evento para crear una Alarma al ver una Actividad
     */
    public void getEscuchaCrearAlarma(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaCrearAlarma(actionEvent -> {
            try {
                vistaActividad.abrirVentanaCrearAlarma();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Maneja el evento para eliminar una Alarma al ver una Actividad
     */
    public void getEscuchaEliminarAlarmas(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaEliminarAlarma(actionEvent -> vistaActividad.eliminarAlarmasSeleccionadas());
    }

    /**
     * Maneja el evento para seleccionar una Alarma al ver una Actividad
     */
    public void getEscuchaSeleccionarAlarmas(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaSeleccionarAlarma(mouseEvent -> {
            vistaActividad.habilitarBorrarAlarma();
        });
    }

    /**
     * Devuelve la Actividad seleccionada
     */
    public Actividad getActividadActual(){
        return actividadActual;
    }

    /**
     * Muestra la notificación de la Alarma
     */
    public void mostrarNotificacionAlarma(Alarma alarma){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación de Alarmas");
        alert.setHeaderText("Información Alarma");
        String mensaje = "Título: " + alarma.getTituloAlarma() + "\nDescripción: " + alarma.getDescripcionAlarma() + "\nFecha: " + alarma.getFechaAlarma();
        alert.setContentText(mensaje);
        alert.showAndWait();

    }


    // Crear

    /**
     * Inicializa los controles
     */
    private void inicializar(){
        valorRango.put(TipoRango.DIA, "Día");
        valorRango.put(TipoRango.SEMANA,"Semana");
        valorRango.put(TipoRango.MES, "Mes");
        choiceRango = crearChoiceBoxRango();
        siguiente = crearButtonAntSig(">");
        anterior = crearButtonAntSig("<");
        menuCrearActividad = menuCrearActividad();
        paneInicial.getChildren().addAll(choiceRango, siguiente, anterior, menuCrearActividad);
    }

    /**
     * Crea la ChoiceBox que contiene los 3 tipos de rango de tiempo: Día, Semana y Mes
     */
    private ChoiceBox<String> crearChoiceBoxRango() {
        ChoiceBox<String> box = new ChoiceBox<>(FXCollections.observableArrayList(valorRango.get(TipoRango.DIA),
                valorRango.get(TipoRango.SEMANA), valorRango.get(TipoRango.MES)));
        box.setStyle("-fx-background-color: white; -fx-border-color: black;");
        box.setLayoutX(Tamanio.RANGO_X);
        box.setLayoutY(Tamanio.RANGO_Y);
        box.setPrefWidth(Tamanio.RANGO_WIDTH);
        box.setCursor(Cursor.HAND);
        return box;
    }

    /**
     * Crea el botón para ver el siguiente o anterior día (o semana o mes)
     */
    private Button crearButtonAntSig(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: white; -fx-border-color: black;");
        button.setShape(new javafx.scene.shape.Circle(Tamanio.CIRCLE_RADIUS));
        button.setCursor(Cursor.HAND);
        return button;
    }

    /**
     * Crea el MenuButton usado para crear Actividades
     */
    private MenuButton menuCrearActividad() {
        MenuButton menu = crearMenu(Tamanio.MENU_CREAR_ACTIVIDAD_X, Tamanio.MENU_CREAR_ACTIVIDAD_Y,
                Tamanio.MENU_CREAR_ACTIVIDAD_WIDTH, Tamanio.MENU_CREAR_ACTIVIDAD_HEIGHT);
        menu.setText("Crear");
        itemCrearTarea = new MenuItem("Tarea");
        itemCrearEvento = new MenuItem("Evento");
        menu.getItems().addAll(itemCrearTarea, itemCrearEvento);
        menu.setStyle("-fx-background-color: white; -fx-border-color: black;");
        return menu;
    }

    /**
     * Crea un MenuButton
     */
    private MenuButton crearMenu(double x, double y, double width, double height){
        MenuButton menu = new MenuButton();
        menu.setLayoutX(x);
        menu.setLayoutY(y);
        menu.setPrefWidth(width);
        menu.setPrefHeight(height);
        menu.setCursor(Cursor.HAND);
        return menu;
    }

    /**
     * Crea un MenuButton usado para ver Actividades
     */
    private MenuButton crearMenuVerActividad(double x, double y, double width, double height){
        MenuButton menu = crearMenu(x, y, width, height);
        menu.setStyle("-fx-border-color: #bdbbbb; -fx-background-color: white;");
        paneGeneral.getChildren().add(menu);
        return menu;
    }

    /**
     * Crea una ListView y la guarda en listasSemana según la clave pasada
     */
    private void crearListView(double x, double y, double width, double height, LocalDate clave) {
        ListView<Label> list = new ListView<>();
        paneGeneral.getChildren().add(list);
        list.setLayoutX(x);
        list.setLayoutY(y);
        list.setPrefHeight(height);
        list.setPrefWidth(width);
        list.setStyle("-fx-border-color: white");
        list.setCursor(Cursor.HAND);
        list.setId("listaSemana");
        listasSemana.put(clave, list);
    }

    //Getters

    /**
     * Obtiene el tipo de Rango actual según su valor
     */
    private TipoRango getTipoRango(String opcion){
        for (TipoRango tipo : valorRango.keySet()){
            if (valorRango.get(tipo).equals(opcion)){
                return tipo;
            }
        }
        return null;
    }

    /**
     * Devuelve el primer dia de semana dada
     */
    private LocalDate getPrimerDiaSemana(LocalDate primerDia) {
        while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY) {
            primerDia = primerDia.minusDays(CantidadDias.UNO_LONG);
        }
        return primerDia;
    }

    /**
     * Devuelve el primer dia del mes dado
     */
    private LocalDate getPrimerDiaMes(LocalDate primerDia) {
        return LocalDate.of(primerDia.getYear(), primerDia.getMonth(), CantidadDias.UNO_INT);
    }

    /**
     * Devuelve el último dia de la semana dada
     */
    private LocalDate getUltimoDiaSemana(LocalDate primerDia) {
        return primerDia.plusDays(CantidadDias.SEIS_LONG);
    }

    /**
     * Devuelve el último día del mes dado
     */
    private LocalDate getUltimoDiaMes(LocalDate primerDia) {
        while (primerDia.getDayOfMonth()!= CantidadDias.UNO_INT){
            primerDia = primerDia.plusDays(CantidadDias.UNO_LONG);
        }
        return primerDia.with(TemporalAdjusters.lastDayOfMonth());
    }

    // Setters

    /**
     * Setea la nueva fecha actual al usar el botón 'siguiente'
     */
    private void setDiaPlusActual() {
        switch (rango) {
            case DIA -> diaActual = diaActual.plusDays(CantidadDias.UNO_LONG);
            case SEMANA -> diaActual = diaActual.plusDays(CantidadDias.SIETE_LONG);
            case MES -> diaActual = diaActual.plusMonths(CantidadDias.UNO_LONG);
        }
    }

    /**
     * Setea la nueva fecha actual al usar el botón 'anterior'
     */
    private void setDiaMinusActual() {
        switch (rango) {
            case DIA -> diaActual = diaActual.minusDays(CantidadDias.UNO_LONG);
            case SEMANA -> diaActual = diaActual.minusDays(CantidadDias.SIETE_LONG);
            case MES -> diaActual = diaActual.minusMonths(CantidadDias.UNO_LONG);
        }
    }

    /**
     * Setea el Pane actual según el rango de tiempo
     */
    private void setearPane(String ruta, Label label, int x, int y, int x2, int y2) {
        paneGeneral = new AnchorPane();
        setearFondo(ruta);
        siguiente.setLayoutX(x2+Tamanio.BTN_MAS_SIGUIENTE_X);
        anterior.setLayoutX(x2);
        siguiente.setLayoutY(y2);
        anterior.setLayoutY(y2);
        dia.setStyle("-fx-font-size: 16px;");
        dia.setLayoutX(x);
        dia.setLayoutY(y);
        dia.setText(LocalDate.now().toString());
        paneGeneral.getChildren().addAll(paneInicial,label, dia);
        this.scene = new Scene(paneGeneral);
        stage.setScene(scene);
    }

    /**
     * Setea la vista del tipo de rango Día
     */
    private void setearDia() {
        Label label = new Label();
        label.setLayoutY(Tamanio.LABEL_FECHA_DIA_Y);
        label.setLayoutX(Tamanio.LABEL_FECHA_DIA_X);
        label.setStyle("-fx-font-size: 16px;");
        label.setText(LocalDate.now().toString());
        setearPane("file:src/main/java/MVC/imagenes/diario.png", label, Tamanio.LABEL_DIA_X_RANGO_DIA,
                Tamanio.LABEL_DIA_Y_RANGO_DIA, Tamanio.BTN_ANT_SIG_X_RANGO_DIA, Tamanio.BTN_ANT_SIG_Y_RANGO_DIA);
        rango = TipoRango.DIA;
        setMenuDia();
        actualizarMenuDia();
        choiceRango.setValue(valorRango.get(rango));

    }

    /**
     * Setea la vista del tipo de rango Mes
     */
    private void setearMes() {
        Label label = new Label();
        label.setLayoutY(Tamanio.LABEL_FECHA_MES_Y);
        label.setLayoutX(Tamanio.LABEL_FECHA_MES_X);
        label.setStyle("-fx-font-size: 16px;");
        label.setText(diaActual.getMonth().toString() +" "+ diaActual.getYear() );
        setearPane("file:src/main/java/MVC/imagenes/mensual.png", label, Tamanio.LABEL_DIA_X_RANGO_MES,
                Tamanio.LABEL_DIA_Y_RANGO_MES, Tamanio.BTN_ANT_SIG_X_RANGO_MES, Tamanio.BTN_ANT_SIG_Y_RANGO_MES);
        rango = TipoRango.MES;
        setMenuMes();
        setearFechas();
        actualizarMenuMes();
        choiceRango.setValue(valorRango.get(rango));
    }

    /**
     * Setea la vista del tipo de rango Semana
     */
    private void setearSemana() {
        Label label = new Label();
        label.setLayoutY(Tamanio.LABEL_FECHA_SEMANA_Y);
        label.setLayoutX(Tamanio.LABEL_FECHA_SEMANA_X);
        label.setStyle("-fx-font-size: 16px;");
        LocalDate primerDia = getPrimerDiaSemana(diaActual);
        LocalDate ultimoDia = getUltimoDiaSemana(primerDia);
        label.setText(primerDia + "\n \n"+ ultimoDia);
        setearPane("file:src/main/java/MVC/imagenes/semanal.png", label, Tamanio.LABEL_DIA_X_RANGO_SEMANA,
                Tamanio.LABEL_DIA_Y_RANGO_SEMANA, Tamanio.BTN_ANT_SIG_X_RANGO_SEMANA, Tamanio.BTN_ANT_SIG_Y_RANGO_SEMANA);
        rango = TipoRango.SEMANA;
        setListViewSemana();
        setearFechas();
        actualizarListas();
        choiceRango.setValue(valorRango.get(rango));
    }

    /**
     * Setea la imagen de fondo
     */
    private void setearFondo(String ruta) {
        ImageView imageView = new ImageView(new Image(ruta));
        imageView.fitWidthProperty().bind(paneGeneral.widthProperty());
        imageView.fitHeightProperty().bind(paneGeneral.heightProperty());
        paneGeneral.getChildren().add(imageView);
    }

    /**
     * Setea las fechas de cada día comprendido en el rango actual
     */
    private void setearFechas() {
        switch (rango) {
            case SEMANA -> setearFechasSemana();
            case MES -> setearFechasMes();
        }
    }

    /**
     * Setea las fechas de cada día comprendido en el rango semanal actual
     */
    private void setearFechasSemana() {
        LocalDate primerDia = getPrimerDiaSemana(diaActual);
        int columna = 0;
        double x = ValoresSemana.POSX_FECHAS;
        double y = ValoresSemana.POSY_FECHAS;
        do {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x + ValoresSemana.INCREMENTO_FECHAS * columna);
            paneGeneral.getChildren().add(fecha);
            fecha.setLayoutY(y);
            primerDia = primerDia.plusDays(CantidadDias.UNO_LONG);
            columna++;
        } while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY);
    }

    /**
     * setea las ListView del tipo de rango Semana
     */
    private void setListViewSemana() {
        listasSemana = new HashMap<>();
        double y = ValoresSemana.LIST_Y;
        double width = ValoresSemana.LIST_WIDTH;
        double height = ValoresSemana.LIST_HEIGHT;
        LocalDate primerDia = getPrimerDiaSemana(diaActual);
        crearListView(ValoresSemana.LIST_X1, y, ValoresSemana.LIST_WIDTH2, height, primerDia);
        crearListView(ValoresSemana.LIST_X2, y, width, height, primerDia.plusDays(CantidadDias.UNO_LONG));
        crearListView(ValoresSemana.LIST_X3, y, width, height, primerDia.plusDays(CantidadDias.DOS_LONG));
        crearListView(ValoresSemana.LIST_X4, y, width, height, primerDia.plusDays(CantidadDias.TRES_LONG));
        crearListView(ValoresSemana.LIST_X5, y, width, height, primerDia.plusDays(CantidadDias.CUATRO_LONG));
        crearListView(ValoresSemana.LIST_X6, y, width, height, primerDia.plusDays(CantidadDias.CINCO_LONG));
        crearListView(ValoresSemana.LIST_X7, y, ValoresSemana.LIST_WIDTH3, height, primerDia.plusDays(CantidadDias.SEIS_LONG));
    }

    /**
     * Setea las fechas de cada día comprendido en el rango mensual actual
     */
    private void setearFechasMes() {
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = getPrimerDiaSemana(LocalDate.of(diaActual.getYear(), mesActual, CantidadDias.UNO_INT));
        int columna = 0;
        int fila = 0;
        double x = ValoresMes.POSX_FECHAS;
        double y = ValoresMes.POSY_FECHAS;
        while (primerDia.getMonth() == mesActual || primerDia.getMonth() == mesActual.minus(CantidadDias.UNO_LONG)) {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x + ValoresMes.INCREMENTOX_FECHAS * columna);
            fecha.setLayoutY(y + ValoresMes.INCREMENTOY_FECHAS * fila);
            paneGeneral.getChildren().add(fecha);
            primerDia = primerDia.plusDays(CantidadDias.UNO_LONG);
            if (columna == ValoresMes.ULTIMA_COLUMNA_FECHAS) {
                fila++;
                columna = 0;
            } else {
                columna++;
            }
        }
    }

    /**
     * setea los MenuButton del tipo de rango Mes
     */
    private void setMenuMes(){
        menuMes = new HashMap<>();
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = getPrimerDiaSemana(getPrimerDiaMes(diaActual));
        int columna = 0;
        int fila = 0;
        double x = ValoresMes.MENU_X;
        double y = ValoresMes.MENU_Y;
        double width = ValoresMes.MENU_WIDTH;
        double height = ValoresMes.MENU_HEIGHT;
        while (primerDia.getMonth() == mesActual || primerDia.getMonth() == mesActual.minus(CantidadDias.UNO_LONG)) {
            guardarMenuConClave(crearMenuVerActividad(x+ValoresMes.INCREMENTO_MENU_X*columna, y+ValoresMes.INCREMENTO_MENU_Y*fila,width, height), primerDia);
            primerDia = primerDia.plusDays(CantidadDias.UNO_LONG);
            if (columna == ValoresMes.ULTIMA_COLUMNA_FECHAS){
                fila++;
                columna = 0;
            }else{
                columna++;
            }
        }
    }

    /**
     * setea los MenuButton del tipo de rango Día
     */
    private void setMenuDia() {
        menuDia = new HashMap<>();
        int hora = 0;
        int columna = 0;
        int fila = 0;
        double x = ValoresDia.MENU_X;
        double y = ValoresDia.MENU_Y;
        double width = ValoresDia.MENU_WIDTH;
        double height = ValoresDia.MENU_HEIGHT;
        while (true) {
            guardarMenuConClave(crearMenuVerActividad(x+ValoresDia.INCREMENTO_MENU_X*columna, y+ValoresDia.INCREMENTO_MENU_Y*fila,width, height), hora);
            hora ++;
            if (hora == 24){
                break;
            }
            if (fila == ValoresDia.ULTIMA_FILA){
                columna++;
                fila = 0;
            }else{
                fila++;
            }
        }
    }

    // Otros

    /**
     * Guarda en menuMes el MenuButton con su clave
     */
    private void guardarMenuConClave(MenuButton menu,LocalDate claveFecha){
        menuMes.put(claveFecha, menu);
    }

    /**
     * Guarda en menuDia el MenuButton con su clave
     */
    private void guardarMenuConClave(MenuButton menu,int claveHora){
        menuDia.put(claveHora, menu);
    }

    /**
     * Elimina la información de la Alarma luego de ser creada por el Controlador
     */
    private void eliminarAlarmaActual(){
        this.infoAlarmaActual = null;
    }

    /**
     * Elimina los MenuItem de cada MenuButton del rango 'mes'
     */
    private void reiniciarMenuMes(){
        for (MenuButton menu: menuMes.values()){
            menu.getItems().clear();
        }
    }

    /**
     * Elimina los MenuItem de cada MenuButton del rango 'dia'
     */
    private void reiniciarMenuDia(){
        for (MenuButton menu: menuDia.values()){
            menu.getItems().clear();
        }
    }

    /**
     * Elimina las Label de cada ListView del rango 'semana'
     */
    private void reiniciarListSemana(){
        for (ListView<Label> list: listasSemana.values()){
            list.getItems().clear();
        }
    }

    /**
     * Guarda la información de la Actividad seleccionada
     */
    private void guardarActividad() {
        infoAlarmaActual = vistaActual.getInfoAlarmas();
        actividadActual = vistaActual.getActividad();
    }

}