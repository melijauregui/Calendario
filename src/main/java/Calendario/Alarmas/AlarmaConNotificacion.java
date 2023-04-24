package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaConNotificacion extends Alarma{

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public AlarmaConNotificacion(LocalDateTime fecha){
        super(fecha);
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo a otra fecha
     */
    public AlarmaConNotificacion(int intervalo, TiempoRelativo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }



}
