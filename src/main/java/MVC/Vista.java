package MVC;

import Calendario.Actividad.Actividad;
import Calendario.Main.Argumentos.EventoArgs;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private Button siguiente = crearButtonAntSig(">", 780 , 20);
    private Button anterior = crearButtonAntSig("<",730, 20);
    private MenuButton menuCrearActividad = menuCrearActividad();
    private MenuItem itemCrearTarea;
    private MenuItem itemCrearEvento;
    private VistaVentanaCrearTarea vistaVentanaCrearTarea;
    private VistaVentanaCrearEvento vistaVentanaCrearEvento;

    private TareaArgs argsTareaActual;
    private EventoArgs argsEventoActual;
    private List<List<String>> infoAlarmaActual;

    private List<Tarea> tareas = new ArrayList<>();
    private Map<LocalDate,ListView<String>> listas = new HashMap();

    private AnchorPane mainLayout;
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

    private void initialize(){
        paneGeneral.getChildren().addAll(choiceFrecuencia, siguiente, anterior, menuCrearActividad);
        diaActual = LocalDate.now();

    }
    private ChoiceBox crearChoiceBox(){
        ChoiceBox box = new ChoiceBox<>(FXCollections.observableArrayList("Dia","Semana", "Mes"));
        box.setStyle("-fx-background-color: white; -fx-border-color: black;");
        box.setLayoutX(715.0);
        box.setLayoutY(50.0);
        box.setPrefWidth(100.0);
        box.setCursor(Cursor.HAND);
        return box;
    }
    private Button crearButtonAntSig(String text, float x, float y){
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
        tipoRango(frecuencia);
    }
    public void registrarEscuchaAnterior(EventHandler<ActionEvent> eventHandler) {
        anterior.setOnAction(eventHandler);
    }
    public void getEscuchaAnterior() {
        setDiaMinusActual();
        tipoRango(frecuencia);
    }


    private void setDiaPlusActual(){
        switch(frecuencia){
            case "Dia" -> diaActual = diaActual.plusDays(1);
            case "Semana" -> diaActual = diaActual.plusDays(7);
            case "Mes" -> diaActual = diaActual.plusMonths(1);
        }
    }
    private void setDiaMinusActual(){
        switch(frecuencia){
            case "Dia" -> diaActual = diaActual.minusDays(1);
            case "Semana" -> diaActual = diaActual.minusDays(7);
            case "Mes" -> diaActual = diaActual.minusMonths(1);
        }
    }
    public MenuButton menuCrearActividad(){
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
        switch (opcion){
            case "Semana" -> setearSemana();
            case "Dia" -> setearDia();
            case "Mes" -> setearMes();
        }
    }

    private void setearPane(String ruta){
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

    private void setListViewDia(){

    }

    private void setearDia(){
        setearPane("file:src/main/java/MVC/imagenes/diario.png");
        frecuencia = "Dia";
        dia.setText(diaActual.toString());
        setListViewDia();
        choiceFrecuencia.setValue(frecuencia);

    }
    private void setearMes(){
        setearPane("file:src/main/java/MVC/imagenes/mensual.png");
        frecuencia = "Mes";
        dia.setText(diaActual.getMonth().toString());
        setearFechas();
        choiceFrecuencia.setValue(frecuencia);
    }

    private void setearSemana(){
        setearPane("file:src/main/java/MVC/imagenes/semanal.png");
        frecuencia = "Semana";
        setListViewSemana();
        setearFechas();
        choiceFrecuencia.setValue(frecuencia);
    }

    private void setearFondo(AnchorPane pane, String ruta) {
        ImageView imageView = new ImageView(new Image(ruta));
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(imageView);
    }

    private void setearFechas(){
        switch (frecuencia){
            case "Semana" -> setearFechasSemana();
            case "Mes" -> setearFechasMes();
        }
    }

    private void crearListView(double x, double y, double width, double height, LocalDate clave){
        if (!listas.containsKey(clave)) {
            ListView<String> list = new ListView<>();
            list.setLayoutX(x);
            list.setLayoutY(y);
            list.setPrefWidth(width);
            list.setPrefHeight(height);
            list.setStyle("-fx-border-color: white;");
            listas.put(clave, list);
        }
        ListView<String> list = listas.get(clave);
        actualFondo.getChildren().add(list);
    }

    private void setListViewSemana(){
        double x = 65;
        double y = 160;
        double width = 102;
        double height = 255;
        LocalDate primerDia = getPrimerDia(diaActual);
        crearListView(x, y, width+6, height, primerDia);
        crearListView(182, y, width, height, primerDia.plusDays(1));
        crearListView(289, y, width, height, primerDia.plusDays(2));
        crearListView(400, y, width, height, primerDia.plusDays(3));
        crearListView(508, y, width, height, primerDia.plusDays(4));
        crearListView(616, y, width, height, primerDia.plusDays(5));
        crearListView(727, y, width+10, height,primerDia.plusDays(6));
    }

    private void setearFechasSemana(){
        LocalDate primerDia = getPrimerDia(diaActual);
        dia.setText(primerDia.toString() + " al " + primerDia.plusDays(7).toString());
        int columna = 0;
        double x = 109;
        double y = 140;
        do {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x+111*columna);
            actualFondo.getChildren().add(fecha);
            fecha.setLayoutY(y);
            primerDia = primerDia.plusDays(1);
            columna++;
        } while(primerDia.getDayOfWeek() != DayOfWeek.MONDAY);

    }

    private LocalDate getPrimerDia(LocalDate primerDia){
        while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY){
            primerDia = primerDia.minusDays(1);
        }
        return primerDia;
    }
    private void setearFechasMes(){
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = getPrimerDia(LocalDate.of(diaActual.getYear(), mesActual, 1));
        int columna = 0;
        int fila = 0;
        double x = 110;
        double y = 140;
        while (primerDia.getMonth() == mesActual || primerDia.getMonth() == mesActual.minus(1)) {
            Label fecha = new Label(Integer.toString(primerDia.getDayOfMonth()));
            fecha.setLayoutX(x+105.5*columna);
            fecha.setLayoutY(y+47*fila);
            actualFondo.getChildren().add(fecha);
            primerDia = primerDia.plusDays(1);
            if (columna == 6){
                fila++;
                columna = 0;
            }else{
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
        stageNuevo.showAndWait();

    }
    public void getEscuchaCrearAlarma(VentanaCrear ventanaCrear){
        ventanaCrear.registrarEscuchaCrearAlarma(actionEvent -> {
            try {
                ventanaCrear.abrirVentanaCrearAlarma();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getEscuchaEliminarAlarmas(VentanaCrear ventanaCrear){
        ventanaCrear.registrarEscuchaEliminarAlarma(actionEvent -> ventanaCrear.eliminarAlarmasSeleccionadas());
    }

    public void getEscuchaSeleccionarAlarmas(VentanaCrear ventanaCrear){
        ventanaCrear.registrarEscuchaSeleccionarAlarma( mouseEvent -> {
            ventanaCrear.habilitarBorrarAlarma();
        });
    }

    private void getEscuchaEsDiaCompleto(VentanaCrear ventanaCrear){
        ventanaCrear.registrarEscuchaSeleccionarDiaCompleto(actionEvent -> {
            if (ventanaCrear.esDiaCompleto()){
                ventanaCrear.setFechaDiaCompleto();
            }else{
                ventanaCrear.setFechaConHora();
            }
        });
    }

    private void getEscuchaEventoConRepeticion(){
        vistaVentanaCrearEvento.registrarEscuchaRepeticion(actionEvent -> {
            vistaVentanaCrearEvento.setRepeticion();
        });
    }

    public void getEscuchaGuardarTarea(){
        vistaVentanaCrearTarea.registrarEscuchaGuardarTarea(actionEvent -> {
            if (vistaVentanaCrearTarea.guardarDatosTarea() == false) {
                return;
            }
            argsTareaActual = vistaVentanaCrearTarea.getInfoTarea();
            infoAlarmaActual = vistaVentanaCrearTarea.getInfoAlarmas();
            vistaVentanaCrearTarea.cerrarVentana();
        });
    }

    public TareaArgs getInfoTarea(){
        return argsTareaActual;
    }

    public List<List<String>> getInfoAlarmaCreada(){
        return infoAlarmaActual;
    }



    public void guardarTarea(Tarea tarea){
        this.tareas.add(tarea);
        mostrarTarea(tarea);
    }

    private void mostrarTarea(Tarea tarea){
        ListView<String> lista = listas.getOrDefault(tarea.getFecha().toLocalDate(), new ListView<String>());
        lista.getItems().add(tarea.getTitulo() + "- Fecha: " + tarea.getFecha().toString());
    }

}