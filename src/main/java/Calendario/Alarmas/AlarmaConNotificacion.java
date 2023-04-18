package Calendario.Alarmas;

import Calendario.Enums.Tiempo;

import java.time.LocalDateTime;

public class AlarmaConNotificacion extends Alarma{
    public AlarmaConNotificacion(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConNotificacion(int intervalo, Tiempo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }



}
