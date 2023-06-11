package MVC;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class VentanaCrear{
        abstract void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);
        abstract void abrirVentanaCrearAlarma() throws IOException;
        abstract void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);
        abstract void eliminarAlarmasSeleccionadas();
        abstract void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);
        abstract void habilitarBorrarAlarma();
        abstract void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler);
        abstract boolean esDiaCompleto();
        abstract void setFechaDiaCompleto();
        abstract void setFechaConHora();
        abstract void setMensajeErrorFecha(String mensaje);


}
