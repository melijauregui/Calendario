package Calendario.Eventos;

import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoConDuracion extends Evento{
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    public EventoConDuracion(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        super(titulo, descripcion, new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin));;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public EventoConDuracion(String titulo, String descripcion, Repeticion repeticion, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        super(titulo, descripcion, repeticion, new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin));;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // setFechaInicio modifica la fecha de inicio del evento y, a partir de ello, determina las nuevas instancias
    // de repetición del mismo
    public void setFechaInicio(LocalDateTime fecha){
        InstanciaEvento primerEvento = new InstanciaEvento(getTitulo(), getDescripcion(), fecha,fechaFin);
        reiniciarAlmacenamientoFechas(primerEvento);
    }

    // setFechaFin modifica la fecha de finalización del evento y, a partir de ello, determina las nuevas instancias
    // de repetición del mismo
    public void setFechaFin(LocalDateTime fecha){
        InstanciaEvento primerEvento = new InstanciaEvento(getTitulo(), getDescripcion(), fechaInicio,fecha);
        reiniciarAlmacenamientoFechas(primerEvento);
    }
}
