package MVC;

import Calendario.Main.Calendario;
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
import java.time.DayOfWeek;
import java.time.LocalDate;


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
            fecha.setLayoutY(y+57*fila);
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
        stageNuevo.showAndWait();


    }

    private void abrirVentanaCrearEvento(){

    }


    //public String getEscuchaGuardarTarea() {
    //    return botonGuardarTarea
    //}

    /*public void guardarTarea(){
        tipoRango(frecuencia);
        choiceCrearActividad.setDisable(false);
    }
*/


}