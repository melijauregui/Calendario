package Calendario.Alarmas;

import Calendario.Enums.Tiempo;

import java.time.LocalDateTime;

public class AlarmaConSonido extends  Alarma{
    public AlarmaConSonido(LocalDateTime fecha){
        super(fecha);
    }
    public AlarmaConSonido(int intervalo, Tiempo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }
}
