package MVC;

import Calendario.Main.Calendario;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;



public class Vista {
    private Calendario calendario;
    private Stage stage;
    private Scene scene;
    private ChoiceBox<String> choiceDia = crearChoiceBox();
    private ChoiceBox<String> choiceSemana = crearChoiceBox();
    private ChoiceBox<String> choiceMes = crearChoiceBox();
    private AnchorPane actualFondo;

    private AnchorPane semanaFondo = new AnchorPane();
    private AnchorPane diaFondo = new AnchorPane();
    private AnchorPane mesFondo = new AnchorPane();


    public Vista(Calendario calendario, Stage stage) throws IOException {
        this.calendario = calendario;
        this.stage = stage;
        initialize();

        stage.setWidth(900);
        stage.setHeight(600);
        stage.setResizable(false);


        setearMes();
        stage.show();
    }

    private void initialize(){
        diaFondo.getChildren().add(choiceDia);
        semanaFondo.getChildren().add(choiceSemana);
        mesFondo.getChildren().add(choiceMes);
    }
    private ChoiceBox crearChoiceBox(){
        ChoiceBox box = new ChoiceBox<>(FXCollections.observableArrayList("Dia","Semana", "Mes"));
        box.setStyle("-fx-background-color: white; -fx-border-color: black;");
        box.setLayoutX(700.0);
        box.setLayoutY(31.0);
        box.setPrefWidth(150.0);
        box.setCursor(Cursor.HAND);
        return box;
    }


    public void registrarEscuchaMes(EventHandler<ActionEvent> eventHandler) {
        choiceMes.setOnAction(eventHandler);
    }
    public void registrarEscuchaDia(EventHandler<ActionEvent> eventHandler) {
        choiceDia.setOnAction(eventHandler);
    }
    public void registrarEscuchaSemana(EventHandler<ActionEvent> eventHandler) {
        choiceSemana.setOnAction(eventHandler);
    }


    public String getEscuchaMes() {
        return choiceMes.getValue();
    }
    public String getEscuchaDia() {
        return choiceDia.getValue();
    }
    public String getEscuchaSemana() {
        return choiceSemana.getValue();
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

    public void setearPane(Pane pane, String ruta){
        AnchorPane rootPane = new AnchorPane();
        setearFondo(rootPane, ruta);
        rootPane.getChildren().addAll(pane);
        actualFondo = rootPane;
        this.scene = new Scene(rootPane);
        stage.setScene(scene);
    }

    public void setearDia(){
        setearPane(diaFondo, "file:src/main/java/MVC/imagenes/diario.png");

    }
    public void setearMes(){
        setearPane(mesFondo, "file:src/main/java/MVC/imagenes/mensual.png");
    }
    public void setearSemana(){
        setearPane(semanaFondo, "file:src/main/java/MVC/imagenes/semanal.png");
    }


    private void setearFondo(AnchorPane pane, String ruta) {
        ImageView imageView = new ImageView(new Image(ruta));
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(imageView);
    }

}