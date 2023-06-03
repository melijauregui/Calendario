package MVC;

import Calendario.Main.Calendario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDate;


public class Vista {
    private Calendario calendario;
    private Stage stage;
    private Scene scene;
    private ChoiceBox<String> choiceBoxRangoTiempo;
    /*
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Label labelFechaRangoTiempo;
    @FXML
    private  ChoiceBox<String> choiceBoxCrear;
    */
    public Vista(Calendario calendario, Stage stage) throws IOException {
        this.calendario = calendario;
        this.stage = stage;
        initialize();
        stage.setScene(scene);
        stage.setWidth(1300);
        stage.setHeight(800);
        stage.setResizable(false);
        setearSemana();
        stage.show();
    }

    private void initialize(){
        choiceBoxRangoTiempo = new ChoiceBox<>(FXCollections.observableArrayList("Dia","Semana", "Mes"));
        choiceBoxRangoTiempo.setStyle("-fx-background-color: white; -fx-border-color: black;");
        choiceBoxRangoTiempo.setLayoutX(1100.0);
        choiceBoxRangoTiempo.setLayoutY(31.0);
        choiceBoxRangoTiempo.setPrefWidth(150.0);
        choiceBoxRangoTiempo.setCursor(Cursor.HAND);
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().add(choiceBoxRangoTiempo);
        scene = new Scene(mainLayout);
    }


    public void registrarEscuchaRango(EventHandler<ActionEvent> eventHandler) {
        choiceBoxRangoTiempo.setOnAction(eventHandler);
    }

    public void tipoRango() {
        String opcion = choiceBoxRangoTiempo.getValue();
        if (opcion.equals("Semana")) {
            setearSemana();
        } else if (opcion.equals("Dia")) {
            setearDia();
        } else if (opcion.equals("Mes")) {
            setearMes();
        }
    }

    public void setearRango(String ruta){
        AnchorPane rootPane = new AnchorPane();
        Image image = new Image(ruta);
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(rootPane.widthProperty());
        imageView.fitHeightProperty().bind(rootPane.heightProperty());
        rootPane.getChildren().add(imageView);
        rootPane.getChildren().addAll(choiceBoxRangoTiempo);
        this.scene = new Scene(rootPane);
        stage.setScene(scene);

    }
    public void setearDia(){
       setearRango("file:src/main/java/MVC/imagenes/dia.jpg");
    }
    public void setearMes(){
        setearRango("file:src/main/java/MVC/imagenes/mes.png");
    }
    public void setearSemana(){
        setearRango("file:src/main/java/MVC/imagenes/semana.jpg");
    }



}