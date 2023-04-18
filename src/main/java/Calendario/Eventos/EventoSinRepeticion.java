package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoSinRepeticion extends Evento{
    //ArrayList<Evento> almacenamientoFecha;

    private final InstanciaEvento instanciaActual;


    //no es de dia completo, tiene fecha limite
    public EventoSinRepeticion(String titulo, String descripcion, LocalDateTime fechaHoraInicio,
                               LocalDateTime fechaHoraFin) {
        super(titulo, descripcion, fechaHoraInicio, fechaHoraFin);
        this.instanciaActual = new InstanciaEvento(titulo, descripcion, fechaHoraInicio, fechaHoraFin);
        //this.almacenamientoFechas = new ArrayList<Evento>();
        //repeticion.RepetirEvento(almacenamientoFechas, fechaInicio, fechaFin);
    }

    // es de dia completo
    public EventoSinRepeticion(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin) {
        super(titulo, descripcion, fechaInicio, fechaFin);
        this.instanciaActual = new InstanciaEvento(titulo, descripcion, super.getFecha(), super.getFechaFin());
        //this.almacenamientoFechas = new ArrayList<>();
        //repeticion.RepetirEvento(almacenamientoFechas, fechaInicio, fechaFin);

    }

    // getProximaRepeticion devuelve una instancia del evento actual, que es única
    public InstanciaEvento getProximaRepeticion(LocalDateTime fechaActual){
        return instanciaActual;
    }

    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(getFecha());
        setAlarma(alarma);
    }
}