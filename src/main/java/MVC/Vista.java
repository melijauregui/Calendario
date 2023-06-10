package MVC;

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
import java.util.List;


public class Vista {
    private Calendario calendario;
    private Stage stage;
    private Scene scene;
    private LocalDate diaActual;
    private ChoiceBox<String> choiceFrecuencia = crearChoiceBox();
    private AnchorPane paneGeneral = new AnchorPane();
    private AnchorPane actualFondo;
    private Label dia;
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
        dia = new Label(diaActual.toString());
        dia.setLayoutX(500);
        dia.setLayoutY(50);
        dia.setStyle("-fx-font-size: 16px;");
        rootPane.getChildren().add(dia);
        actualFondo = rootPane;
        this.scene = new Scene(rootPane);
        stage.setScene(scene);
    }

    private void setearDia(){
        setearPane("file:src/main/java/MVC/imagenes/diario.png");
        frecuencia = "Dia";
        setearFechas();
        choiceFrecuencia.setValue(frecuencia);

    }
    private void setearMes(){
        setearPane("file:src/main/java/MVC/imagenes/mensual.png");
        frecuencia = "Mes";
        setearFechas();
        choiceFrecuencia.setValue(frecuencia);
    }
    private void setearSemana(){
        setearPane("file:src/main/java/MVC/imagenes/semanal.png");
        frecuencia = "Semana";
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

    private void setearFechasSemana(){
        var diaActualSemana = diaActual.getDayOfWeek();
        LocalDate primerDia = diaActual;
        while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY){
            primerDia = primerDia.minusDays(1);
        }
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
    private void setearFechasMes(){
        var mesActual = diaActual.getMonth();
        LocalDate primerDia = LocalDate.of(diaActual.getYear(), mesActual, 1);
        while (primerDia.getDayOfWeek() != DayOfWeek.MONDAY){
            primerDia = primerDia.minusDays(1);
        }
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

    public void getEscuchaEsDiaCompleto(VentanaCrear ventanaCrear){
        ventanaCrear.registrarEscuchaSeleccionarDiaCompleto(actionEvent -> {
            if (ventanaCrear.esDiaCompleto()){
                ventanaCrear.setFechaDiaCompleto();
            }else{
                ventanaCrear.setFechaConHora();
            }
        });
    }

    public void getEscuchaGuardarTarea(){
        vistaVentanaCrearTarea.registrarEscuchaGuardarTarea(actionEvent -> {
            String titulo = vistaVentanaCrearTarea.getTitulo();
            String descripcion = vistaVentanaCrearTarea.getDescripcion();
            String dia = vistaVentanaCrearTarea.getDia();
            int mes = vistaVentanaCrearTarea.getMes();
            String anio = vistaVentanaCrearTarea.getAnio();
            String hora = vistaVentanaCrearTarea.getHora();
            String minuto = vistaVentanaCrearTarea.getMinuto();
            boolean esDiaCompleto = vistaVentanaCrearTarea.esDiaCompleto();
            List<List<String>> infoAlarmas = vistaVentanaCrearTarea.getInfoAlarmas();
            if (esDiaCompleto){
                if(hayErrorFechaDiaCompleto(dia, mes, anio, vistaVentanaCrearTarea)){
                    return;
                }
                argsTareaActual = new TareaArgs(titulo, descripcion,
                        LocalDate.of(Integer.parseInt(anio), mes, Integer.parseInt(dia)));
            }else{
                if(hayErrorFecha(dia, mes, anio, hora, minuto, vistaVentanaCrearTarea)){
                    return;
                }
                argsTareaActual = new TareaArgs(titulo, descripcion,
                        LocalDateTime.of(Integer.parseInt(anio), mes, Integer.parseInt(dia),
                        Integer.parseInt(hora), Integer.parseInt(minuto)));
            }
            infoAlarmaActual = infoAlarmas;
            vistaVentanaCrearTarea.cerrarVentana();
        });
    }

    public TareaArgs getInfoTarea(){
        return argsTareaActual;
    }

    public List<List<String>> getInfoAlarmaCreada(){
        return infoAlarmaActual;
    }

    private boolean hayErrorFecha(String dia, int mes, String anio, String hora, String minuto, VentanaCrear ventanaCrear){
        try{
            int diaNumero = Integer.parseInt(dia);
            int anioNumero = Integer.parseInt(anio);
            int horaNumero = Integer.parseInt(hora);
            int minutoNumero = Integer.parseInt(minuto);
            LocalDateTime.of(anioNumero, mes, diaNumero, horaNumero, minutoNumero);
            ventanaCrear.setMensajeErrorFecha("");
            return false;
        }catch(NumberFormatException | DateTimeException exception){
            ventanaCrear.setMensajeErrorFecha("Fecha inválida");
        }
        return true;
    }

    private boolean hayErrorFechaDiaCompleto(String dia, int mes, String anio, VentanaCrear ventanaCrear){
        try{
            int diaNumero = Integer.parseInt(dia);
            int anioNumero = Integer.parseInt(anio);
            LocalDate.of(anioNumero, mes, diaNumero);
            ventanaCrear.setMensajeErrorFecha("");
            return false;
        }catch(NumberFormatException | DateTimeException exception){
            ventanaCrear.setMensajeErrorFecha("Fecha inválida");
        }
        return true;
    }

    public void guardarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    private void actualizarVistaActividades(){

    }
}