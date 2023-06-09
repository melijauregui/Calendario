package MVC;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class VistaVentanaCrearTarea {
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descripcion;
    @FXML
    private ComboBox<Integer> dia;
    @FXML
    private ComboBox<String> mes;
    @FXML
    private ComboBox<Integer> anio;
    @FXML
    private ComboBox<Integer> hora;
    @FXML
    private ComboBox<Integer> minuto;
    private Stage stage;
    @FXML
    private Button botonGuardarTarea;
    private LocalDateTime fechaActual = LocalDateTime.now();
    public VistaVentanaCrearTarea(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearTarea.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        Scene sceneNueva = new Scene(ventana);
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneNueva);
        initialize();

    }
    private void initialize(){
        var dias = FXCollections.observableArrayList(1, 2, 3,4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        dia.setItems(dias);
        dia.setVisibleRowCount(5);
        //dia.setValue(fechaActual.getDayOfMonth());


    }


/*    public void registrarEscuchaGuardarTarea(EventHandler<ActionEvent> eventHandler) {
        botonGuardarTarea.setOnAction(eventHandler);
    }*/

}
