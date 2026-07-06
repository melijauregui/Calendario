package MVC.Crear;

import Calendario.Main.Calendario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class VentanaCrear {

    private List<List<String>> infoAlarmas = new ArrayList<>();

    /**
     * Abre la ventana para crear una Alarma
     */
    public abstract void abrirVentanaCrearAlarma() throws IOException;

    /**
     * Registra el eventHandler para eliminar una alarma
     */
    public abstract void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);

    /**
     * Elimina la información guradada de las Alarmas seleccionadas
     */
    public abstract void eliminarAlarmasSeleccionadas();

    /**
     * Registra el eventHandler para seleccionar una alarma
     */
    public abstract void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);

    /**
     * Habilita el botón para eliminar alarmas
     */
    public abstract void habilitarBorrarAlarma();

    /**
     * Registra el eventHandler al seleccionar la checkBox de día completo
     */
    public abstract void registrarEscuchaSeleccionarDiaCompleto(EventHandler<ActionEvent> eventHandler);

    /**
     * Devuelve true si la checkBox de día completo fue seleccionada
     */
    public abstract boolean esDiaCompleto();

    /**
     * Deshabilita las ComboBox de hora y minuto
     */
    public abstract void setFechaDiaCompleto();

    /**
     * Habilita las ComboBox de hora y minuto
     */
    public abstract void setFechaConHora();

    /**
     * Registra el eventHandler para crear una alarma
     */
    public abstract void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);

    /**
     * Deshabilita el botón para eliminar alarmas
     */
    public void deshabilitarBorrarAlarma_(Button botonEliminarAlarma){
        botonEliminarAlarma.setDisable(true);
    }

    /**
     * Devuelve la información de todas las alarmas a crear
     */
    public List<List<String>> getInfoAlarmas(){
        return infoAlarmas;
    }

    /**
     * Guarda la información de la alarma a crear
     */
    public abstract void agregarAlarmaALaLista(String aviso, String intervalo, String tiempoRelativo);

    /**
     * Escribe en la label errorFecha el error correspondiente
     */
    protected abstract void setMensajeErrorFecha(String mensaje);

    /**
     * Elimina la información de la Alarma seleccionada y deshabilita el botón de Eliminar
     */
    protected void eliminarAlarmasSeleccionadas_(ListView<String> listaAlarmas, Button botonEliminarAlarma){
        var indiceSeleccionado = listaAlarmas.getSelectionModel().getSelectedIndex();
        listaAlarmas.getItems().remove(indiceSeleccionado);
        if (listaAlarmas.getItems().isEmpty()){
            inicializarValorListaAlarmas_(listaAlarmas);
        }
        deshabilitarBorrarAlarma_(botonEliminarAlarma);
    }

    /**
     * Inicializa el valor de la ListView que contiene la información de las alarmas
     */
    protected void inicializarValorListaAlarmas_(ListView<String> listaAlarmas){
        listaAlarmas.getItems().add(Constantes.SIN_ALARMAS);
    }

    /**
     * Maneja el evento de guardar la información de la alarma a crear
     */
    protected void getEscuchaGuardarAlarma_(VentanaCrearAlarma ventanaCrearAlarma){
        ventanaCrearAlarma.registrarEscuchaCrearAlarma(actionEvent -> {
            String aviso = ventanaCrearAlarma.getAviso();
            String tiempoRelativo = ventanaCrearAlarma.getTiempoRelativo();
            String intervalo = ventanaCrearAlarma.getIntervalo();
            if (intervalo.length() > 0) {
                if (ventanaCrearAlarma.manejarErrorIntervalo() || ventanaCrearAlarma.manejarErrorTiempoRelativo()) {
                    return;
                }
            }
            agregarAlarmaALaLista(aviso, intervalo, tiempoRelativo);
            ventanaCrearAlarma.cerrarVentana();
        });;
    }

    /**
     * Guarda la información de la alarma a crear en listaAlarmas
     */
    protected void agregarAlarmaALaLista_(ListView<String> listaAlarmas, String aviso, String intervalo, String tiempoRelativo){
        String mensaje = "Alarma con " + aviso;
        List<String> infoAlarma = new ArrayList<>();
        infoAlarma.add(aviso);
        if (!(intervalo.length()==0) && !(intervalo.equals(" - "))){
            mensaje += ", " + intervalo + " " + tiempoRelativo.toLowerCase() + " antes.";

        }
        infoAlarma.add(intervalo);
        infoAlarma.add(tiempoRelativo);
        infoAlarmas.add(infoAlarma);
        var alarmas = listaAlarmas.getItems();
        if (alarmas.get(0).equals(Constantes.SIN_ALARMAS)){
            alarmas.remove(0);
        }
        alarmas.add(mensaje);
    }

}