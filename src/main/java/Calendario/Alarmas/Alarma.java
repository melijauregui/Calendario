package Calendario.Alarmas;

import Calendario.Enums.Tiempo;

import java.time.LocalDateTime;

public class Alarma{
    private LocalDateTime fechaAlarma;
    private int intervalo;
    private Tiempo tiempoRelativo;
    public Alarma(LocalDateTime fechaAlarma){
        this.fechaAlarma = fechaAlarma;
    }
    public Alarma(int intervalo, Tiempo tiempoRelativo){ // falta-> cada instancia de Evento tiene Alarma
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
    }


    // getFechaAlarma devuelve la fecha de la Alarma
    public LocalDateTime getFechaAlarma() {
        return fechaAlarma;
    }

    // suenaAntes devuelve true si la alarma suena antes de la recibida por parámetro
    public boolean suenaAntes(Alarma otra){
        return this.fechaAlarma.isBefore(otra.getFechaAlarma());
    }

    // determinarFechaAlarma determina la fecha de la Alarma según el intervalo de tiempo relativo a la
    // fecha de la Actividad para la que está configurada
    public void determinarFecha(LocalDateTime fechaActividad){
        if (fechaAlarma != null){ return;}
        switch (tiempoRelativo){
            case MINUTOS -> this.fechaAlarma = fechaActividad.minusMinutes(intervalo);
            case HORAS -> this.fechaAlarma = fechaActividad.minusHours(intervalo);
            case DIAS -> this.fechaAlarma = fechaActividad.minusDays(intervalo);
            case SEMANAS -> this.fechaAlarma = fechaActividad.minusWeeks(intervalo);
        }
    }


}
