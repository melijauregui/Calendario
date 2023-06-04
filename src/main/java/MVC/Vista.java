package MVC;

import Calendario.Main.Calendario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private String frecuencia;
    private Button siguiente = crearButton("Siguiente", 550 , 50);
    private Button anterior = crearButton("Anterior",340 , 50);

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
        paneGeneral.getChildren().add(choiceFrecuencia);
        paneGeneral.getChildren().add(siguiente);
        paneGeneral.getChildren().add(anterior);
        diaActual = LocalDate.now();

    }
    private ChoiceBox crearChoiceBox(){
        ChoiceBox box = new ChoiceBox<>(FXCollections.observableArrayList("Dia","Semana", "Mes"));
        box.setStyle("-fx-background-color: white; -fx-border-color: black;");
        box.setLayoutX(700.0);
        box.setLayoutY(50.0);
        box.setPrefWidth(150.0);
        box.setCursor(Cursor.HAND);
        return box;
    }
    private Button crearButton(String text, float x, float y){
        Button button = new Button(text);
        button.setStyle("-fx-background-color: white; -fx-border-color: black;");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(95.0);
        button.setCursor(Cursor.HAND);
        return button;
    }

    public void registrarEscuchaFrecuencia(EventHandler<ActionEvent> eventHandler) {
        choiceFrecuencia.setOnAction(eventHandler);
    }
    public String getEscuchaFrecuencia() {
        return choiceFrecuencia.getValue();
    }

    public void tipoRango(String opcion) {
        if (opcion.equals("Semana")) {
            setearSemana();
        } else if (opcion.equals("Dia")) {
            setearDia();
        } else if (opcion.equals("Mes")) {
            setearMes();
        }
    }

    private void setearPane(String ruta){
        AnchorPane rootPane = new AnchorPane();
        setearFondo(rootPane, ruta);
        rootPane.getChildren().addAll(paneGeneral);
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
        if (frecuencia == "Semana"){
            setearFechasSemana();
        }else if (frecuencia == "Mes"){
            setearFechasMes();
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



}