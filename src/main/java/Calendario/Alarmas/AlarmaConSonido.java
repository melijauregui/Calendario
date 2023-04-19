package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaConSonido extends  Alarma{
    public AlarmaConSonido(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConSonido(int intervalo, TiempoRelativo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }
}
