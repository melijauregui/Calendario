package Calendario.Duracion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Duracion {
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;

    protected static final int horaInicioDiaCompleto = 0;
    protected static  final int minutoInicioDiaCompleto = 0;
    private static final int horaFinDiaCompleto = 23;
    private static final int minutoFinDiaCompleto = 59;

    public Duracion(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Duracion(LocalDate fechaInicio, LocalDate fechaFin){
        this.fechaInicio = LocalDateTime.of(fechaInicio, LocalTime.of(horaInicioDiaCompleto, minutoInicioDiaCompleto));
        this.fechaFin = LocalDateTime.of(fechaFin, LocalTime.of(horaFinDiaCompleto, minutoFinDiaCompleto));
    }

    public boolean esDiaCompleto(){
        return (this.fechaInicio.getHour() == horaInicioDiaCompleto &&
                this.fechaInicio.getMinute() == minutoInicioDiaCompleto &&
                this.fechaFin.getHour() == horaFinDiaCompleto &&
                this.fechaFin.getMinute() == minutoFinDiaCompleto);
    }

    public LocalDateTime getFechaInicio(){
        return fechaInicio;
    }
    public LocalDateTime getFechaFin(){
        return fechaFin;
    }
}
