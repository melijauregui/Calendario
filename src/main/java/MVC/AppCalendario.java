package MVC;

import Calendario.Main.Calendario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class AppCalendario extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Calendario calendario = new Calendario();
        Vista vista = new Vista(calendario, stage);
        Controlador controlador = new Controlador(calendario, vista);
        //controlador.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
