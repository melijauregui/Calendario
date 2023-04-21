package Calendario.Repeticiones;


import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class RepeticionAnual extends Repeticion {
    public RepeticionAnual(int intervaloAnios, LocalDate fechaHasta) {
        super(intervaloAnios, fechaHasta);
    }

    public RepeticionAnual(int intervaloAnios, int ocurrencias) {
        super(intervaloAnios, ocurrencias);

    }

    public RepeticionAnual(int intervaloAnios) {
        super(intervaloAnios);

    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusYears(getIntervalo()), evento.getDiaFin().plusYears(getIntervalo()));
    }
}
