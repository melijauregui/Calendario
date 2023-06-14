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

    @Override
    public void start(Stage stage) throws Exception {
        /*
        Calendario calendario = new Calendario();
        Vista vista = new Vista(calendario, stage);
        Controlador controlador = new Controlador(calendario, vista);
        controlador.start();*/
        Calendario calendario = new Calendario();

        /*String filePath = "persistencia.bin";

        File archivo = new File(filePath);

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException();
            }

        }else {


            // Cargar los bytes desde el archivo
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                ByteArrayOutputStream loadedBytes = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    loadedBytes.write(buffer, 0, bytesRead);
                }
                // Deserializar los bytes cargados
                calendario = Calendario.deserializar(new ByteArrayInputStream(loadedBytes.toByteArray()));
            } catch (IOException | ClassNotFoundException e) {
            }

        }*/

            Vista vista = new Vista(calendario, stage);
            Controlador controlador = new Controlador(calendario, vista);
            controlador.start();

/*            // Serializar
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            calendario.serializar(bytes);
            // Guardar los bytes en un archivo
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                bytes.writeTo(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }


    public static void main(String[] args) {
        launch();
    }
}