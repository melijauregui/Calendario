package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class Alarma{
    private LocalDateTime fechaAlarma;
    private int intervalo;
    private TiempoRelativo tiempoRelativo;
    public Alarma(LocalDateTime fechaAlarma){
        this.fechaAlarma = fechaAlarma;
    }
    public Alarma(int intervalo, TiempoRelativo tiempoRelativo){ // falta-> cada instancia de Evento tiene Alarma
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
    }

    // suenaAntes devuelve true si la alarma suena antes de la alarma recibida por parámetro
    public boolean suenaAntes(Alarma otra){
        return this.fechaAlarma.isBefore(otra.getFechaAlarma());
    }

    // suenaAntes devuelve true si la alarma suena antes de la fecha recibida por parámetro
    public boolean suenaAntes(LocalDateTime fecha){
        return this.fechaAlarma.isBefore(fecha);
    }

    // suenaIgual devuelve true si la alarma suena al mismo tiempo que la recibida por parámetro
    public boolean suenaIgual(Alarma otra){
        return getFechaAlarma().isEqual(otra.getFechaAlarma());
    }

    /* determinarFechaAlarma determina la fecha de la Alarma según el intervalo de tiempo relativo a la
    fecha de la Actividad para la que está configurada */
    public void determinarFecha(LocalDateTime fechaActividad){
        if (fechaAlarma != null){ return;}
        fechaAlarma = tiempoRelativo.determinarFechaRelativa(fechaActividad, intervalo);
    }

    // getFechaAlarma devuelve la fecha de la Alarma
    private LocalDateTime getFechaAlarma() {
        return fechaAlarma;
    }

}
