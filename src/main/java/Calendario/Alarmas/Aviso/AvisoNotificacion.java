package Calendario.Alarmas.Aviso;

import java.io.Serializable;

public class AvisoNotificacion implements Aviso, Serializable {
    private String tituloAlarma;
    private String descripcionAlarma;
    public AvisoNotificacion(){

    }

    /**
     * Envía un Aviso por notificación
     */
    @Override
    public void avisar() {
    }

    @Override
    public String getAvisoToString() {
        return "Notificación";
    }
}
