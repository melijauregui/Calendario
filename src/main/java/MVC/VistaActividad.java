package MVC;

import javafx.stage.Stage;

import java.io.IOException;

public interface VistaActividad {
    void abrirVistaDetallada(Stage stage) throws IOException;
    void cerrarVistaDetallada();
}
