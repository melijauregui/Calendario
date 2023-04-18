package Calendario.Alarmas;

import Calendario.Enums.Tiempo;

import java.time.LocalDateTime;

public class AlarmaConEmail extends Alarma{
    public AlarmaConEmail(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConEmail(int intervalo, Tiempo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }

}
