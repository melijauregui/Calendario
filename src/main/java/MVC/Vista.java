package MVC;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadMutable;
import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.RepeticionArgs;
import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Main.Calendario;
import Calendario.Tareas.Tarea;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


public class Vista {
    private Calendario calendario;
    private Stage stage;
    private Scene scene;
    private LocalDate diaActual;
    private ChoiceBox<String> choiceFrecuencia = crearChoiceBox();
    private AnchorPane paneGeneral = new AnchorPane();
    private AnchorPane actualFondo;
    private Label dia = new Label();
    private String frecuencia;
    private Button siguiente = crearButtonAntSig(">", 780, 20);
    private Button anterior = crearButtonAntSig("<", 730, 20);
    private MenuButton menuCrearActividad = menuCrearActividad();
    private MenuItem itemCrearTarea;
    private MenuItem itemCrearEvento;
    private VistaVentanaCrearTarea vistaVentanaCrearTarea;
    private VistaVentanaCrearEvento vistaVentanaCrearEvento;
    private VentanaCrear vistaVentanaCrear;
    private RepeticionArgs argsRepeticionEvento;
    private TareaArgs argsTareaActual;
    private EventoArgs argsEventoActual;
    private List<List<String>> infoAlarmaActual;
    private Map<Label, VistaTarea> vistasTareaLabel;
    private Map<LocalDate,ListView<Label>> listasSemana;
    private Map<LocalDate,MenuButton> menuMes;
    private Map<Integer,MenuButton> menuDia;
    private Map<Label, VistaActividad> vistasLabel;
    private Map<MenuItem, VistaActividad> vistasMenu;
    private MenuItem itemSeleccionado;
    private AnchorPane mainLayout;
    //private List<ActividadMutable> actividades = new ArrayList<>();
    //private Tarea tareaActual;
    private VistaActividad vistaActual;
    private Actividad actividadActual;
    private boolean eliminarActividad;

    public Vista(Calendario calendario, Stage stage) throws IOException {
        this.calendario = calendario;
        this.stage = stage;
        initialize();
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setResizable(false);
        setearSemana();
        stage.show();
    }

    private void initialize() {
        paneGeneral.getChildren().addAll(choiceFrecuencia, siguiente, anterior, menuCrearActividad);
        diaActual = LocalDate.now();

    }

    private ChoiceBox<String> crearChoiceBox() {
        ChoiceBox<String> box = new ChoiceBox<>(FXCollections.observableArrayList("Dia", "Semana", "Mes"));
        box.setStyle("-fx-background-color: white; -fx-border-color: black;");
        box.setLayoutX(715.0);
        box.setLayoutY(50.0);
        box.setPrefWidth(100.0);
        box.setCursor(Cursor.HAND);
        return box;
    }

    private Button crearButtonAntSig(String text, float x, float y) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: white; -fx-border-color: black;");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setShape(new javafx.scene.shape.Circle(50));
        button.setCursor(Cursor.HAND);
        return button;
    }

    public void registrarEscuchaSiguiente(EventHandler<ActionEvent> eventHandler) {
        siguiente.setOnAction(eventHandler);
    }

    public void getEscuchaSiguiente() {
        setDiaPlusActual();
        tipoRango(getEscuchaFrecuencia());
    }

    public void registrarEscuchaAnterior(EventHandler<ActionEvent> eventHandler) {
        anterior.setOnAction(eventHandler);
    }

    public void getEscuchaAnterior() {
        setDiaMinusActual();
        tipoRango(getEscuchaFrecuencia());
    }


    private void setDiaPlusActual() {
        switch (frecuencia) {
            case "Dia" -> diaActual = diaActual.plusDays(1);
            case "Semana" -> diaActual = diaActual.plusDays(7);
            case "Mes" -> diaActual = diaActual.plusMonths(1);
        }
    }

    private void setDiaMinusActual() {
        switch (frecuencia) {
            case "Dia" -> diaActual = diaActual.minusDays(1);
            case "Semana" -> diaActual = diaActual.minusDays(7);
            case "Mes" -> diaActual = diaActual.minusMonths(1);
        }
    }

    public MenuButton menuCrearActividad() {
        MenuButton crearBox = new MenuButton("Crear");
        itemCrearTarea = new MenuItem("Tarea");
        itemCrearEvento = new MenuItem("Evento");
        crearBox.getItems().addAll(itemCrearTarea, itemCrearEvento);
        crearBox.setStyle("-fx-background-color: white; -fx-border-color: black;");
        crearBox.setLayoutX(620);
        crearBox.setLayoutY(50);
        crearBox.setPrefWidth(80);
        crearBox.setCursor(Cursor.HAND);
        return crearBox;
    }

    public void registrarEscuchaFrecuencia(EventHandler<ActionEvent> eventHandler) {
        choiceFrecuencia.setOnAction(eventHandler);
    }

    public String getEscuchaFrecuencia() {
        return choiceFrecuencia.getValue();
    }

    public void tipoRango(String opcion) {
        switch (opcion) {
            case "Semana" -> setearSemana();
            case "Dia" -> setearDia();
            case "Mes" -> setearMes();
        }
    }

    private void setearPane(String ruta) {
        AnchorPane rootPane = new AnchorPane();
        setearFondo(rootPane, ruta);
        rootPane.getChildren().addAll(paneGeneral);
        //dia = new Label(diaActual.toString());
        dia.setLayoutX(400);
        dia.setLayoutY(50);
        dia.setStyle("-fx-font-size: 16px;");
        rootPane.getChildren().add(dia);
        actualFondo = rootPane;
        this.scene = new Scene(rootPane);
        stage.setScene(scene);
        mainLayout = rootPane;
    }

    private void setearDia() {
        setearPane("file:src/main/java/MVC/imagenes/diario.png");
        frecuencia = "Dia";
        dia.setText(diaActual.toString());
        setMenuDia();
        actualizarMenuDia();
        choiceFrecuencia.setValue(frecuencia);

    }

    private void setearMes() {
        setearPane("file:src/main/java/MVC/imagenes/mensual.png");
        frecuencia = "Mes";
        dia.setText(diaActual.getMonth().toString());
        setMenuMes();
        setearFechas();
        actualizarMenuMes();
        choiceFrecuencia.setValue(frecuencia);
    }

    private void setearSemana() {
        setearPane("file:src/main/java/MVC/imagenes/semanal.png");
        frecuencia = "Semana";
        setListViewSemana();
        setearFechas();
        actualizarListas();
        choiceFrecuencia.setValue(frecuencia);
    }

    private void setearFondo(AnchorPane pane, String ruta) {
        ImageView imageView = new ImageView(new Image(ruta));
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(imageView);
    }

    private void setearFechas() {
        switch (frecuencia) {
            case "Semana" -> setearFechasSemana();
            case "Mes" -> setearFechasMes();
        }
    }

    private void crearListView(double x, double y, double width, double height, LocalDate clave) {
        ListView<Label> list = new ListView<>();
        list.setLayoutX(x);
        list.setLayoutY(y);
        list.setPrefWidth(width);
        list.setPrefHeight(height);
        list.setStyle("-fx-border-color: white");
        list.setCursor(Cursor.HAND);
        list.setId("listaSemana");
        listasSemana.put(clave, list);
        actualFondo.getChildren().add(list);
    }

    private void setListViewSemana() {
        listasSemana = new HashMap<>();
        double x = 65;
        double y = 160;
        double width = 102;
        double height = 255;
        LocalDate primerDia = getPrimerDiaSemana(diaActual);
        crearListView(x, y, width + 6, height, primerDia);
        crearListView(182, y, width, height, primerDia.plusDays(1));
        crearListView(289, y, width, height, primerDia.plusDays(2));
        crearListView(400, y, width, height, primerDia.plusDays(3));
        crearListView(508, y, width, height, primerDia.plusDays(4));
        crearListView(616, y, width, height, primerDia.plusDays(5));
        crearListView(727, y, width + 10, height, primerDia.plusDays(6));
    }

    private void setearFechasSemana() {
        LocalDate primerDia = getPrimerDiaSemana(diaActual);
        LocalDate ultimoDia = getUltimoDiaSemana(primerDia);
        dia.setText(primerDia + " al " + ultimoDia);
        int columna = 0;
        double x = 109;
        double y = 140;
        do {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x + 111 * columna);
            actualFondo.getChildren().add(fecha);
            fecha.setLayoutY(y);
            primerDia = primerDia.plusDays(1);
            columna++;
        } while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY);
    }

    private LocalDate getPrimerDiaSemana(LocalDate primerDia) {
        while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY) {
            primerDia = primerDia.minusDays(1);
        }
        return primerDia;
    }
    private LocalDate getPrimerDiaMes(LocalDate primerDia) {
        return LocalDate.of(primerDia.getYear(), primerDia.getMonth(), 1);
    }
    private LocalDate getUltimoDiaSemana(LocalDate primerDia) {
        return primerDia.plusDays(6);
    }

    private LocalDate getUltimoDiaMes(LocalDate primerDia) {
        while (primerDia.getDayOfMonth()!= 1){
            primerDia = primerDia.plusDays(1);
        }
        return primerDia.with(TemporalAdjusters.lastDayOfMonth());
    }

    private void setMenuMes(){
        menuMes = new HashMap<>();
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = getPrimerDiaSemana(getPrimerDiaMes(diaActual));
        int columna = 0;
        int fila = 0;
        double x = 105;
        double y = 155;
        double width = 95;
        double height = 15;
        while (primerDia.getMonth() == mesActual || primerDia.getMonth() == mesActual.minus(1)) {
            guardarMenuConClave(crearMenu(x+104*columna, y+47*fila,width, height), primerDia);
            primerDia = primerDia.plusDays(1);
            if (columna == 6){
                fila++;
                columna = 0;
            }else{
                columna++;
            }
        }
    }

    private MenuButton crearMenu(double x, double y, double width, double height){
        MenuButton menu = new MenuButton();
        menu.setLayoutX(x);
        menu.setLayoutY(y);
        menu.setPrefWidth(width);
        menu.setPrefHeight(height);
        menu.setStyle("-fx-border-color: #bdbbbb; -fx-background-color: white;");
        menu.setCursor(Cursor.HAND);
        actualFondo.getChildren().add(menu);
        return menu;
    }

    private void guardarMenuConClave(MenuButton menu,LocalDate claveFecha){
        menuMes.put(claveFecha, menu);
    }

    private void guardarMenuConClave(MenuButton menu,int claveHora){
        menuDia.put(claveHora, menu);
    }

    private void setMenuDia() {
        menuDia = new HashMap<>();
        int hora = 0;
        int columna = 0;
        int fila = 0;
        double x = 108;
        double y = 105;
        double width = 118;
        double height = 25;
        while (true) {
            guardarMenuConClave(crearMenu(x+186*columna, y+47*fila,width, height), hora);
            hora ++;
            if (hora == 24){
                break;
            }
            if (fila == 5){
                columna++;
                fila = 0;
            }else{
                fila++;
            }
        }
    }

    private void setearFechasMes() {
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = getPrimerDiaSemana(LocalDate.of(diaActual.getYear(), mesActual, 1));
        int columna = 0;
        int fila = 0;
        double x = 110;
        double y = 140;
        while (primerDia.getMonth() == mesActual || primerDia.getMonth() == mesActual.minus(1)) {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x + 105.5 * columna);
            fecha.setLayoutY(y + 47 * fila);
            actualFondo.getChildren().add(fecha);
            primerDia = primerDia.plusDays(1);
            if (columna == 6) {
                fila++;
                columna = 0;
            } else {
                columna++;
            }
        }
    }

    public void registrarEscuchaCrearTarea(EventHandler<ActionEvent> eventHandler) {
        itemCrearTarea.setOnAction(eventHandler);
    }

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

    public void registrarEscuchaCrearEvento(EventHandler<ActionEvent> eventHandler) {
        itemCrearEvento.setOnAction(eventHandler);
    }

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


    public void getEscuchaCrearAlarma(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaCrearAlarma(actionEvent -> {
            try {
                ventanaCrear.abrirVentanaCrearAlarma();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getEscuchaCrearAlarma(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaCrearAlarma(actionEvent -> {
            try {
                vistaActividad.abrirVentanaCrearAlarma();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getEscuchaEliminarAlarmas(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaEliminarAlarma(actionEvent -> ventanaCrear.eliminarAlarmasSeleccionadas());
    }
    public void getEscuchaEliminarAlarmas(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaEliminarAlarma(actionEvent -> vistaActividad.eliminarAlarmasSeleccionadas());
    }
    public void getEscuchaEliminar(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaEliminar(actionEvent -> {
            //vistaActividad.eliminar();
            eliminarActividad = true;
            vistaActividad.cerrarVistaDetallada();
                });
    }
    public boolean eliminarActividad(){
        return eliminarActividad;
    }
    public void actualizarEliminar(){
        eliminarActividad = false;
    }


    public void getEscuchaSeleccionarAlarmas(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaSeleccionarAlarma(mouseEvent -> {
            ventanaCrear.habilitarBorrarAlarma();
        });
    }

    public void getEscuchaSeleccionarAlarmas(VistaActividad vistaActividad) {
        vistaActividad.registrarEscuchaSeleccionarAlarma(mouseEvent -> {
            vistaActividad.habilitarBorrarAlarma();
        });
    }

    private void getEscuchaEsDiaCompleto(VentanaCrear ventanaCrear) {
        ventanaCrear.registrarEscuchaSeleccionarDiaCompleto(actionEvent -> {
            if (ventanaCrear.esDiaCompleto()) {
                ventanaCrear.setFechaDiaCompleto();
            } else {
                ventanaCrear.setFechaConHora();
            }
        });
    }

    private void getEscuchaEventoConRepeticion() {
        vistaVentanaCrearEvento.registrarEscuchaRepeticion(actionEvent -> {
            vistaVentanaCrearEvento.setRepeticion();
        });
    }

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

    public TareaArgs getInfoTarea() {
        return argsTareaActual;
    }

    public EventoArgs getInfoEvento() {
        return argsEventoActual;
    }
    public RepeticionArgs getInfoRepeticionEvento() {
        return argsRepeticionEvento;
    }

    public List<List<String>> getInfoAlarmaCreada() {
        return infoAlarmaActual;
    }

    public void eliminarTareaActual(){
        this.argsTareaActual = null;
        this.infoAlarmaActual = null;
    }
    public void eliminarEventoActual(){
        this.argsEventoActual = null;
        this.infoAlarmaActual = null;
        this.argsRepeticionEvento = null;
    }


    public void actualizarListas(){
        reiniciarListSemana();
        var primerDia = getPrimerDiaSemana(diaActual);
        vistasLabel = new HashMap<>();
        //var primerDia = getPrimerDia(diaActual);
        var ultimoDia = getUltimoDiaSemana(primerDia);
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(primerDia, LocalTime.of(0,0)), LocalDateTime.of(ultimoDia, LocalTime.of(23,59)));
        for (Actividad act : acts){

            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                    //
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


    private void reiniciarMenuMes(){
        for (MenuButton menu: menuMes.values()){
            menu.getItems().clear();
        }
    }

    private void reiniciarMenuDia(){
        for (MenuButton menu: menuDia.values()){
            menu.getItems().clear();
        }
    }

    private void reiniciarListSemana(){
        for (ListView<Label> list: listasSemana.values()){
            list.getItems().clear();
        }
    }

    public void actualizarMenuMes(){
        reiniciarMenuMes();
        var primerDia = getPrimerDiaSemana(getPrimerDiaMes(diaActual));
        vistasMenu = new HashMap<>();
        //var primerDia = getPrimerDia(diaActual);
        var ultimoDia = getUltimoDiaMes(primerDia);
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(primerDia, LocalTime.of(0,0)), LocalDateTime.of(ultimoDia, LocalTime.of(23,59)));
        for (Actividad act : acts){
            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                    //
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


    public void actualizarMenuDia(){
        reiniciarMenuDia();
        vistasMenu = new HashMap<>();
        List<Actividad> acts = calendario.getActividadesEnElIntervalo(LocalDateTime.of(diaActual, LocalTime.of(0,0)), LocalDateTime.of(diaActual, LocalTime.of(23,59)));
        for (Actividad act : acts){
            act.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                    //
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

    public void actualizarVistaActividades(){
        switch (frecuencia){
            case "Semana" -> actualizarListas();
            case "Mes" -> actualizarMenuMes();
            case "Dia" -> actualizarMenuDia();
        }
    }

    public void registrarEscuchaVerActividadLabel(EventHandler<MouseEvent> eventHandler){
        for (Label label: vistasLabel.keySet()){
            label.setOnMouseClicked(eventHandler);
        }
    }

    public void verActividadMenu(){
        for (MenuItem item: vistasMenu.keySet()){
            item.setOnAction(actionEvent -> {
                try {
                    abrirVistaDetalladaMenu(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public Map<MenuItem, VistaActividad> getVistasMenu(){
        return vistasMenu;
    }

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
                guardarActividad();
                nuevoStage.showAndWait();
                break;
            }
        }
    }

    public void guardarActividad() {
        infoAlarmaActual = vistaActual.getInfoAlarmas();
        actividadActual = vistaActual.getActividad();
    }


    public Actividad getActividadActual(){
        return actividadActual;
    }


    public void abrirVistaDetalladaMenu(MenuItem item) throws IOException {
        vistaActual = vistasMenu.get(item);
        Stage nuevoStage = new Stage();
        vistaActual.abrirVistaDetallada(nuevoStage);
        getEscuchaCrearAlarma(vistaActual);
        getEscuchaSeleccionarAlarmas(vistaActual);
        getEscuchaEliminarAlarmas(vistaActual);
        getEscuchaEliminar(vistaActual);
        guardarActividad();
        nuevoStage.showAndWait();

    }

    public void mostrarNotificacionAlarma(Alarma alarma){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notificación de Alarmas");
        alert.setHeaderText("Información Alarma");
        String mensaje = "Título: " + alarma.getTituloAlarma() + "\nDescripción: " + alarma.getDescripcionAlarma() + "\nFecha: " + alarma.getFechaAlarma();
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

}