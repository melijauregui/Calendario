package MVC;

import Calendario.Main.Calendario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;
import java.util.ResourceBundle;

public class Vista {
     private Calendario calendario;
     private Stage stage;
     private Scene scene;
     @FXML
     private AnchorPane mainLayout;
     @FXML
     private ChoiceBox<String> choiceBoxRangoTiempo;
     @FXML
     private Button botonAnterior;
     @FXML
     private Button botonSiguiente;
     @FXML
     private Label labelFechaRangoTiempo;
     @FXML
     private  ChoiceBox<String> choiceBoxCrear;
     public Vista(Calendario calendario, Stage stage) throws IOException {
         this.calendario = calendario;
         this.stage = stage;
         load();
         stage.setScene(scene);
         stage.show();
     }
     @FXML
     private void load() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistaPrincipal.fxml"));
         loader.setController(this);
         mainLayout = loader.load();
         this.scene = new Scene(mainLayout);

     }

    @FXML
    private void initialize() {
        ObservableList<String> tiposRangoTiempo = FXCollections.observableArrayList("Día","Semana", "Mes");
        choiceBoxRangoTiempo.setValue("Semana");
        choiceBoxRangoTiempo.setItems(tiposRangoTiempo);
        botonAnterior.setText("Anterior");
        botonSiguiente.setText("Siguiente");
        labelFechaRangoTiempo.setText(LocalDate.now().toString()); //cambiar formato y q se muestre distinto si es por semana, día o mes
        choiceBoxCrear.setValue("Crear");
        ObservableList<String> tiposCrear = FXCollections.observableArrayList("Evento", "Tarea");
        choiceBoxCrear.setItems(tiposCrear);
    }

     // cnvertir ActMutable en abstracta

}
