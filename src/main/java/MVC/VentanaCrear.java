package MVC;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class VentanaCrear {
    private VentanaCrearAlarma ventanaCrearAlarmaTarea;

    abstract void abrirVentanaCrearAlarma() throws IOException;

    abstract void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);

    abstract void eliminarAlarmasSeleccionadas();

    abstract void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);

    abstract void habilitarBorrarAlarma();

    abstract void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler);

    abstract boolean esDiaCompleto();

    abstract void setFechaDiaCompleto();

    abstract void setFechaConHora();
    private List<List<String>> infoAlarmas = new ArrayList<>();

    abstract void setMensajeErrorFecha(String mensaje);

    public abstract void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);


    protected void escuchaGuardarAlarma(VentanaCrearAlarma ventanaCrearAlarmaTarea) {
        ventanaCrearAlarmaTarea.registrarEscuchaCrearAlarma(actionEvent -> {
            String aviso = ventanaCrearAlarmaTarea.getAviso();
            String tiempoRelativo = ventanaCrearAlarmaTarea.getTiempoRelativo();
            String intervalo = ventanaCrearAlarmaTarea.getIntervalo();
            if (intervalo.length() > 0) {
                if (manejarErrorIntervalo(intervalo, ventanaCrearAlarmaTarea) || manejarErrorTiempoRelativo(tiempoRelativo, ventanaCrearAlarmaTarea)) {
                    return;
                }
            }
            agregarAlarmaALaLista(aviso, intervalo, tiempoRelativo);
            ventanaCrearAlarmaTarea.cerrarVentana();
        });
    }

    private boolean manejarErrorTiempoRelativo(String tiempoRelativo, VentanaCrearAlarma ventanaCrearAlarma) {
        boolean hayError = false;
        if (tiempoRelativo.equals(" - ")) {
            ventanaCrearAlarma.setMensajeError("Tiempo relativo inválido");
            hayError = true;
        } else {
            ventanaCrearAlarma.setMensajeError("");
        }
        return hayError;
    }

    private boolean manejarErrorIntervalo(String intervalo, VentanaCrearAlarma ventanaCrearAlarma) {
        try {
            int intervaloNumero = Integer.parseInt(intervalo);
            if (intervaloNumero <= 0) {
                throw new NumberFormatException();
            }
            ventanaCrearAlarma.setMensajeError("");
            return false;
        } catch (NumberFormatException exception) {
            ventanaCrearAlarma.setMensajeError("Intervalo inválido");
        }
        return true;
    }

    protected void eliminarAlarmasSeleccionadas_(ListView<String> listaAlarmas, Button botonEliminarAlarma){
        var indiceSeleccionado = listaAlarmas.getSelectionModel().getSelectedIndex();
        listaAlarmas.getItems().remove(indiceSeleccionado);
        if (listaAlarmas.getItems().isEmpty()){
            inicializarValorListaAlarmas_(listaAlarmas);
        }
        deshabilitarBorrarAlarma_(botonEliminarAlarma);
    }

    public void deshabilitarBorrarAlarma_(Button botonEliminarAlarma){

        botonEliminarAlarma.setDisable(true);
    }

    protected void inicializarValorListaAlarmas_(ListView<String> listaAlarmas){
        listaAlarmas.getItems().add("Sin alarmas");
    }


    protected void getEscuchaGuardarAlarma_(VentanaCrearAlarma ventanaCrearAlarmaTarea){
        escuchaGuardarAlarma(ventanaCrearAlarmaTarea);
    }


    protected void agregarAlarmaALaLista_(ListView<String> listaAlarmas, String aviso, String intervalo, String tiempoRelativo){
        String mensaje = "Alarma con " + aviso;
        List<String> infoAlarma = new ArrayList<>();
        infoAlarma.add(aviso);
        if (!(intervalo.length()==0) && !(intervalo.equals(" - "))){
            mensaje += ", " + intervalo + " " + tiempoRelativo.toLowerCase() + " antes.";
            infoAlarma.add(intervalo);
            infoAlarma.add(tiempoRelativo);
        }
        infoAlarmas.add(infoAlarma); //aviso - intervalo - tRelativo
        var alarmas = listaAlarmas.getItems();
        if (alarmas.get(0).equals("Sin alarmas")){
            alarmas.remove(0);
        }
        alarmas.add(mensaje);
    }

    public List<List<String>> getInfoAlarmas_(){
        return infoAlarmas;
    }

    public abstract void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo);

}