package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaConNotificacion extends Alarma{
    public AlarmaConNotificacion(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConNotificacion(int intervalo, TiempoRelativo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }



}
