package Calendario.Repeticiones;


import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionDiaria extends Repeticion {

    // cada "intervaloDias" dias
    public RepeticionDiaria(int intervaloDias, LocalDate fechaHasta) {

        super(intervaloDias, fechaHasta);
    }

    public RepeticionDiaria(int intervaloDias, int ocurrencias) {

        super(intervaloDias, ocurrencias);
    }

    public RepeticionDiaria(int intervaloDias) {
        super(intervaloDias);
    }


    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return new InstanciaEvento(evento.getTitulo(), evento.getDescripcion(), evento.getFechaInicio().plusYears(getIntervalo()), evento.getFechaFin().plusYears(getIntervalo()));
    }

}