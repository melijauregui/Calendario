package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Duracion.Duracion;
import Calendario.Main.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InstanciaEvento extends Actividad{
    Duracion duracion;
    public InstanciaEvento(String titulo, String descripcion,
                           LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        super(titulo,descripcion);
        this.duracion = new Duracion(fechaInicio, fechaFin);
    }

    public InstanciaEvento(String titulo, String descripcion,
                           LocalDate fechaInicio, LocalDate fechaFin) {
        super(titulo,descripcion);
        this.duracion = new Duracion(fechaInicio, fechaFin);
    }

    // empiezaDespues devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
    public boolean empiezaDespues(LocalDateTime fecha){

        return this.getFechaInicio().isAfter(fecha);
    }

    public LocalDateTime getFechaInicio(){
        return this.duracion.getFechaInicio();
    }
    public LocalDateTime getFechaFin(){
        return this.duracion.getFechaFin();
    }

    public boolean EstaEnElIntervalo(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return (fechaInicio.isBefore(getFechaInicio()) && fechaFin.isAfter(getFechaInicio()));
    }
    public void configurarAlarma(Alarma alarma){
        if (alarma != null){
            setAlarma(alarma);
        }
    }
}
