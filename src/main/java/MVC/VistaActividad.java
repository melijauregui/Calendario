package MVC;

import Calendario.Actividad.Actividad;
import Calendario.Alarmas.Alarma;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VistaActividad {
    public abstract void abrirVistaDetallada(Stage stage) throws IOException;
    public abstract void cerrarVistaDetallada();
    public abstract void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);
    abstract void abrirVentanaCrearAlarma() throws IOException;
    abstract void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);
    abstract void eliminarAlarmasSeleccionadas();
    abstract void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);
    abstract void habilitarBorrarAlarma();


    protected void eliminarAlarmasSeleccionadas_(ListView<String> listaAlarmas, Button botonEliminarAlarma, List<List<String>> infoAlarmas, Map<String, List<String>> alarmas){
        int indiceSeleccionado = listaAlarmas.getSelectionModel().getSelectedIndex();
        String mensaje = listaAlarmas.getItems().remove(indiceSeleccionado);
        List<String> lista = alarmas.remove(mensaje);
        infoAlarmas.remove(lista);
        if (listaAlarmas.getItems().isEmpty()){
            listaAlarmas.getItems().add("Sin alarmas");
        }
        botonEliminarAlarma.setDisable(true);
    }

    protected void inicializarListasAlarmas_(Actividad act, ListView<String> listaAlarmas, List<List<String>> infoAlarmas, Map<String, List<String>> alarmas) {
        alarmas.clear();
        listaAlarmas.getItems().clear();
        infoAlarmas.clear();
        if (act.getAlarmas().isEmpty()){
            listaAlarmas.getItems().add("Sin alarmas");
                return;

        }
        for (Alarma alarma : act.getAlarmas()) {
            List<String> infoAlarma = new ArrayList<>();
            String intervalo = "0";
            String tiempoRelativo = "-";
            infoAlarma.add(alarma.getAviso());
            String mensaje = "Alarma con " + alarma.getAviso();
            if ((alarma.esConTiempoRelativo())){
                intervalo = alarma.getIntervalo();
                tiempoRelativo= alarma.getTiempoRelativo();
                mensaje += ", " + alarma.getIntervalo() + " " + alarma.getTiempoRelativo().toLowerCase() + " antes.";
            }
            infoAlarma.add(intervalo);
            infoAlarma.add(tiempoRelativo);
            infoAlarmas.add(infoAlarma);
            alarmas.put(mensaje,infoAlarma);
            listaAlarmas.getItems().add(mensaje);
        }
    }

    public abstract void inicializarListasAlarmas();
    protected void getEscuchaGuardarAlarma(VentanaCrearAlarma ventanaCrearAlarma, List<List<String>> infoAlarmas, ListView<String> listaAlarmas, Map<String, List<String>> alarmas){
        ventanaCrearAlarma.registrarEscuchaCrearAlarma(actionEvent -> {
            String aviso = ventanaCrearAlarma.getAviso();
            String tiempoRelativo = ventanaCrearAlarma.getTiempoRelativo();
            String intervalo = ventanaCrearAlarma.getIntervalo();
            if (intervalo.length() > 0) {
                if (ventanaCrearAlarma.manejarErrorIntervalo() || ventanaCrearAlarma.manejarErrorTiempoRelativo()) {
                    return;
                }
            }
            List<String> infoAlarma = new ArrayList<>();
            infoAlarma.add(aviso);
            infoAlarma.add(intervalo);
            infoAlarma.add(tiempoRelativo);
            String mensaje = "Alarma con " + aviso;
            mensaje += (!(intervalo.length()==0) && !(intervalo.equals(" - "))) ? ", " + intervalo + " "
                    + tiempoRelativo.toLowerCase() + " antes." : "";
            alarmas.put(mensaje,infoAlarma);
            if (listaAlarmas.getItems().get(0).equals("Sin alarmas")){
                listaAlarmas.getItems().remove(0);
            }
            listaAlarmas.getItems().add(mensaje);
            infoAlarmas.add(infoAlarma);
            ventanaCrearAlarma.cerrarVentana();
        });
    }

    public abstract List<List<String>> getInfoAlarmas();

    public abstract Actividad getActividad();


}
