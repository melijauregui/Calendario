package Calendario.Repeticiones;


import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collection;

public class RepeticionAnual extends Repeticion {

    public RepeticionAnual(int intervaloAnual,  LocalDate fechaHasta){
        super(intervaloAnual, fechaHasta);
    }
    public RepeticionAnual(int intervaloAnual, int ocurrencias){
        super(intervaloAnual, ocurrencias);

    }
    public RepeticionAnual(int intervaloAnual){
        super(intervaloAnual);
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusYears(super.getIntervalo()), evento.getDiaFin().plusYears(super.getIntervalo()));
    }

}
