package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaConSonido extends  Alarma{

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public AlarmaConSonido(LocalDateTime fecha){
        super(fecha);
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo a otra fecha
     */
    public AlarmaConSonido(int intervalo, TiempoRelativo tiempoRelativo){
        super(intervalo, tiempoRelativo);
    }
}
