package Calendario.Duracion;

import Calendario.Main.Constantes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Duracion {
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;

    // para EventoConDuracion
    public Duracion(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // para EventoDiaCompleto
    public Duracion(LocalDate fechaInicio, LocalDate fechaFin){
        this.fechaInicio = LocalDateTime.of(fechaInicio, LocalTime.of(Constantes.horaInicioDiaCompleto, Constantes.minutoInicioDiaCompleto));
        this.fechaFin = LocalDateTime.of(fechaFin, LocalTime.of(Constantes.horaFinDiaCompleto, Constantes.minutoFinDiaCompleto));
    }


    public LocalDateTime getFechaInicio(){
        return fechaInicio;
    }
    public LocalDateTime getFechaFin(){
        return fechaFin;
    }

}
