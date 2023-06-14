package MVC;

import Calendario.Main.Calendario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class AppCalendario extends Application {

    static String dataDirectory = System.getProperty("user.dir");
    static String filePath = dataDirectory + "/calendario.bin";
    @Override
    public void start(Stage stage) throws Exception {
        Calendario calendario = new Calendario();
        File archivo = new File(filePath);

        if (!archivo.exists()){
            try {
                archivo.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException();
            }
        } else{
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(archivo));
            calendario = Calendario.deserializar(buffer);
        }

        Calendario finalCalendario = calendario;
        stage.setOnCloseRequest(windowEvent -> {
            try {
                finalCalendario.serializar(new BufferedOutputStream(new FileOutputStream(AppCalendario.filePath)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Vista vista = new Vista(calendario, stage);
        Controlador controlador = new Controlador(calendario, vista);
        controlador.start();

        }


    public static void main(String[] args) throws FileNotFoundException {
        launch();

    }

}