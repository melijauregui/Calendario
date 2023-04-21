package Calendario.Duracion;

import Calendario.Main.Constantes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Duracion {
    private LocalDate diaInicio;
    private LocalDate diaFin;

    private LocalTime horaInicio;
    private LocalTime horaFin;


    public void setDiaInicio(LocalDate diaInicio){
        this.diaInicio = diaInicio;
    }

    public void setDiaFin(LocalDate diaFin){
        this.diaFin = diaFin;
    }

    public void setHoraInicio(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }
    public void setHoraFin(LocalTime horaFin){
        this.horaFin = horaFin;
    }

    public LocalDateTime getFechaInicio() {
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(diaInicio, horaInicio);
        }
        return LocalDateTime.of(diaInicio, LocalTime.of(Constantes.horaInicioDiaCompleto, Constantes.minutoInicioDiaCompleto));
    }

    public LocalDateTime getFechaFin() {
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(diaFin, horaFin);
        }
        return LocalDateTime.of(diaFin, LocalTime.of(Constantes.horaFinDiaCompleto, Constantes.minutoFinDiaCompleto));
    }

    public LocalDate getDiaInicio(){
        return this.diaInicio;
    }

    public LocalDate getDiaFin(){
        return this.diaFin;
    }

    private boolean esDiaCompleto(){
        return (horaInicio == null && horaFin == null);
    }

    public Duracion Clone(){
        Duracion nuevaDuracion = new Duracion();
        nuevaDuracion.setDiaInicio(this.diaInicio);
        nuevaDuracion.setDiaFin(this.diaFin);
        if (this.esDiaCompleto()) {
            nuevaDuracion.setHoraInicio(this.horaInicio);
            nuevaDuracion.setHoraFin(this.horaFin);
        }
        return nuevaDuracion;
    }
}
