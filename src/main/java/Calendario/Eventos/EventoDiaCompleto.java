package Calendario.Eventos;

import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoDiaCompleto extends Evento{
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    public EventoDiaCompleto(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin){
        super(titulo, descripcion, new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin));;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public EventoDiaCompleto(String titulo, String descripcion, Repeticion repeticion, LocalDate fechaInicio, LocalDate fechaFin){
        super(titulo, descripcion, repeticion, new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin));;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // setFechaInicio modifica la fecha de inicio del evento y, a partir de ello, determina las nuevas instancias
    // de repetición del mismo
    public void setFechaInicio(LocalDate fecha){
        InstanciaEvento primerEvento = new InstanciaEvento(getTitulo(), getDescripcion(), fecha,fechaFin);
        reiniciarAlmacenamientoFechas(primerEvento);
    }

    // setFechaFin modifica la fecha de finalización del evento y, a partir de ello, determina las nuevas instancias
    // de repetición del mismo
    public void setFechaFin(LocalDate fecha){
        InstanciaEvento primerEvento = new InstanciaEvento(getTitulo(), getDescripcion(), fechaInicio,fecha);
        reiniciarAlmacenamientoFechas(primerEvento);
    }
}
