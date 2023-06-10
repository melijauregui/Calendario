package MVC;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface VentanaCrear{
        void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);
        void abrirVentanaCrearAlarma() throws IOException;
        void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);
        void eliminarAlarmasSeleccionadas();
        void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);
        void habilitarBorrarAlarma();
}
