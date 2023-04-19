package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaConEmail extends Alarma{
    public AlarmaConEmail(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConEmail(int intervalo, TiempoRelativo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }

}
