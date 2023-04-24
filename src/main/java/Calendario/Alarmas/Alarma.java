package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class Alarma{
    private LocalDateTime fechaAlarma;
    private int intervalo;
    private TiempoRelativo tiempoRelativo;

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public Alarma(LocalDateTime fechaAlarma){
        this.fechaAlarma = fechaAlarma;
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo a otra fecha
     */
    public Alarma(int intervalo, TiempoRelativo tiempoRelativo){ // falta-> cada instancia de Evento tiene Alarma
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
    }

    /**
     * Devuelve true si la alarma suena antes de la alarma recibida por parámetro
     */
    public boolean suenaAntes(Alarma otra){
        return this.fechaAlarma.isBefore(otra.getFechaAlarma());
    }

    /**
     * Devuelve true si la alarma suena antes de la fecha recibida por parámetro
     */
    public boolean suenaAntes(LocalDateTime fecha){
        return this.fechaAlarma.isBefore(fecha);
    }

    /**
     * Devuelve true si la alarma suena al mismo tiempo que la recibida por parámetro
     */
    public boolean suenaIgual(Alarma otra){
        return getFechaAlarma().isEqual(otra.getFechaAlarma());
    }

    /**
     * Determina la fecha de la Alarma según el intervalo de tiempo relativo a la
     * fecha pasada
     */
    public void determinarFecha(LocalDateTime fecha){
        if (fechaAlarma != null){ return;}
        fechaAlarma = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);
    }

    /**
     * Devuelve la fecha de la Alarma
     */
    private LocalDateTime getFechaAlarma() {
        return fechaAlarma;
    }

}
