package MVC.VistasActividades;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import MVC.Crear.Constantes;
import MVC.Crear.VentanaCrearAlarma;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class VistaActividad {

    /**
     * Abre la vista detallada
     */
    public abstract void abrirVistaDetallada(Stage stage) throws IOException;

    /**
     * Cierra la vista detallada
     */
    public abstract void cerrarVistaDetallada();

    /**
     * Registra el eventHandler para crear alarmas
     */
    public abstract void registrarEscuchaCrearAlarma(EventHandler<ActionEvent> eventHandler);

    /**
     * Abre la ventana para crear alarmas
     */
    public abstract void abrirVentanaCrearAlarma() throws IOException;

    /**
     * Registra el eventHandler para eliminar alarmas
     */
    public abstract void registrarEscuchaEliminarAlarma(EventHandler<ActionEvent> eventHandler);

    /**
     * Elimina la información asociada a la alarma seleccionada
     */
    public abstract void eliminarAlarmasSeleccionadas();

    /**
     * Registra el eventHandler para seleccionar alarmas
     */
    public abstract void registrarEscuchaSeleccionarAlarma(EventHandler<MouseEvent> eventHandler);

    /**
     * Habilita el botón para borrar alarmas
     */
    public abstract void habilitarBorrarAlarma();

    /**
     * Registra el eventHandler para eliminar la Actividad
     * */
    public abstract void registrarEscuchaEliminar(EventHandler<ActionEvent> eventHandler);

    /**
     * Inicializa la lista de alarmas
     */
    public abstract void inicializarListasAlarmas();

    /**
     * Devuelve la información de las alarmas a crear
     */
    public abstract List<List<String>> getInfoAlarmas();

    /**
     * Devuelve la actividad asociada a la vista detallada
     */
    public abstract Actividad getActividad();

    /**
     * Elimina la información asociada a la alarma seleccionada de la listaAlarmas recibida
     */
    protected void eliminarAlarmasSeleccionadas_(ListView<Label> listaAlarmas, Button botonEliminarAlarma, List<List<String>> infoAlarmas, Map<Label, List<String>> alarmas){
        int indiceSeleccionado = listaAlarmas.getSelectionModel().getSelectedIndex();
        Label mensaje = listaAlarmas.getItems().remove(indiceSeleccionado);
        List<String> lista = alarmas.remove(mensaje);
        infoAlarmas.remove(lista);
        if (listaAlarmas.getItems().isEmpty()){
            listaAlarmas.getItems().add(new Label(Constantes.SIN_ALARMAS));
        }
        botonEliminarAlarma.setDisable(true);
    }

    /**
     * Carga la información de las alarmas de la Actividad
     */
    protected void inicializarListasAlarmas_(Actividad act, ListView<Label> listaAlarmas, List<List<String>> infoAlarmas, Map<Label, List<String>> alarmas) {
        alarmas.clear();
        listaAlarmas.getItems().clear();
        infoAlarmas.clear();
        if (act.getAlarmas().isEmpty()){
            listaAlarmas.getItems().add(new Label(Constantes.SIN_ALARMAS));
            return;

        }
        for (Alarma alarma : act.getAlarmas()) {
            String intervalo = "0";
            String tiempoRelativo = " - ";
            if ((alarma.esConTiempoRelativo())){
                intervalo = String.valueOf(alarma.getIntervalo());
                tiempoRelativo= getTiempoRelativoToString(alarma.getTiempoRelativo());
            }
            guardarInfoAlarma(getAvisoToString(alarma.getAviso()), intervalo, tiempoRelativo,infoAlarmas, listaAlarmas, alarmas);
        }
    }

    /**
     * Guarda la información de la Alarma a crear
     */
    protected void getEscuchaGuardarAlarma(VentanaCrearAlarma ventanaCrearAlarma, List<List<String>> infoAlarmas, ListView<Label> listaAlarmas, Map<Label, List<String>> alarmas){
        ventanaCrearAlarma.registrarEscuchaCrearAlarma(actionEvent -> {
            String aviso = ventanaCrearAlarma.getAviso();
            String tiempoRelativo = ventanaCrearAlarma.getTiempoRelativo();
            String intervalo = ventanaCrearAlarma.getIntervalo();
            if (intervalo.length() > 0) {
                if (ventanaCrearAlarma.manejarErrorIntervalo() || ventanaCrearAlarma.manejarErrorTiempoRelativo()) {
                    return;
                }
            }
            if (listaAlarmas.getItems().get(0).getText().equals(Constantes.SIN_ALARMAS)){
                listaAlarmas.getItems().remove(0);
            }
            guardarInfoAlarma(aviso, intervalo, tiempoRelativo,infoAlarmas, listaAlarmas, alarmas);
            ventanaCrearAlarma.cerrarVentana();
        });
    }

    /**
     * Guarda el MenuItem creado
     */
    protected void guardarItem(MenuButton menu, Map<MenuItem,VistaActividad> vistas, String mensaje, String background){
        if(menu.getItems().isEmpty()){
            menu.setText(Constantes.VER_MAS);
        }
        MenuItem item = new MenuItem(mensaje);
        item.setStyle(background);
        menu.getItems().add(item);
        vistas.put(item, this);
    }


    /**
     * Guarda la información de la Alarma a crear
     */
    private void guardarInfoAlarma(String aviso, String intervalo, String tiempoRelativo, List<List<String>> infoAlarmas, ListView<Label> listaAlarmas, Map<Label, List<String>> alarmas){
        List<String> infoAlarma = new ArrayList<>();
        infoAlarma.add(aviso);
        infoAlarma.add(intervalo);
        infoAlarma.add(tiempoRelativo);
        String mensaje = "Alarma con " + aviso;
        if ((!intervalo.equals("0") || !intervalo.equals("")) && !tiempoRelativo.equals(" - ")){
            mensaje+= ", " + intervalo + " "
                    + tiempoRelativo.toLowerCase() + " antes.";
        }
        Label label = new Label(mensaje);
        alarmas.put(label,infoAlarma);
        listaAlarmas.getItems().add(label);
        infoAlarmas.add(infoAlarma);
    }

    /**
     * Convierte el Tipo de Aviso a String
     */
    private String getAvisoToString(TipoAviso aviso){
        switch (aviso){
            case NOTIFICACION -> {return "Notificación";}
            case EMAIL -> {return "Email";}
            default -> {return "Sonido";}
        }
    }

    /**
     * Convierte el Tiempo Relativo a String
     */
    private String getTiempoRelativoToString(TiempoRelativo tiempoRelativo){
        switch (tiempoRelativo){
            case SEMANAS -> {return "semanas";}
            case HORAS -> {return "horas";}
            case DIAS -> {return "días";}
            default -> {return "minutos";}
        }
    }

    public abstract void aceptarVisitor(VistaActividadVisitor actividadVisitor);

}
