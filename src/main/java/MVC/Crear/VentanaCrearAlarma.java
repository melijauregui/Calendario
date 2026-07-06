package MVC.Crear;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class VentanaCrearAlarma {
    @FXML
    private ChoiceBox<String> tipoTiempoRelativo;
    @FXML
    private ChoiceBox<String> tipoAviso;
    @FXML
    private TextField intervalo;
    private Stage stage;
    @FXML
    private Button botonGuardarAlarma;
    @FXML
    private Label mensajeError;
    public VentanaCrearAlarma(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crearAlarma.fxml"));
        loader.setController(this);
        Pane ventana = loader.load();
        Scene sceneNueva = new Scene(ventana);
        this.stage = stage;
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(sceneNueva);
        initialize();

    }

    /**
     * Registra el eventHandler para crear una alarma
     */
    public void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler){
        botonGuardarAlarma.setOnAction(eventHandler);
    }

    /**
     * Muestra un mensaje de error
     */
    public void setMensajeError(String mensaje){
        mensajeError.setText(mensaje);
    }

    /**
     * Devuelve el tipo de aviso elegido
     */
    public String getAviso(){
        return tipoAviso.getValue();
    }

    /**
     * Devuelve el intervalo relativo seleccionado
     */
    public String getIntervalo(){
        return intervalo.getText();
    }

    /**
     * Devuelve el tipo de tiempo relativo seleccionado
     */
    public String getTiempoRelativo(){
        return tipoTiempoRelativo.getValue();
    }

    /**
     * Cierra la ventana
     */
    public void cerrarVentana(){
        stage.close();
    }

    /**
     * Si se selecciona un intervalo, pero no un tiempo relativo, muestra el mensaje de error correspondiente
     */
    public boolean manejarErrorTiempoRelativo() {
        boolean hayError = false;
        if (getTiempoRelativo().equals(" - ")) {
            setMensajeError(Constantes.TIEMPO_RELATIVO_INVALIDO);
            hayError = true;
        } else {
            setMensajeError("");
        }
        return hayError;
    }

    /**
     * Si se selecciona un intervalo incorrecto muestra el mensaje de error correspondiente
     */
    public boolean manejarErrorIntervalo() {
        try {
            int intervaloNumero = Integer.parseInt(getIntervalo());
            if (intervaloNumero <= 0) {
                throw new NumberFormatException();
            }
            setMensajeError("");
            return false;
        } catch (NumberFormatException exception) {
            setMensajeError(Constantes.INTERVALO_INVALIDO);
        }
        return true;
    }

    /**
     * Inicializa los controles
     */
    private void initialize(){
        var tipos = FXCollections.observableArrayList(" - ","Minutos", "Horas","Días", "Semanas");
        tipoTiempoRelativo.setItems(tipos);
        tipoTiempoRelativo.setValue(tipos.get(0));
        var avisos = FXCollections.observableArrayList("Notificación", "Mail", "Sonido");
        tipoAviso.setItems(avisos);
        tipoAviso.setValue(avisos.get(0));
    }
}
