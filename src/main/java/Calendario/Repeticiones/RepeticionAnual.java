package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionAnual extends Repeticion{
    public RepeticionAnual(int intervaloAnios,  LocalDate fechaHasta){
        super(intervaloAnios, fechaHasta);
    }
    public RepeticionAnual(int intervaloAnios, int ocurrencias){
        super(intervaloAnios, ocurrencias);

    }
    public RepeticionAnual(int intervaloAnios){
        super(intervaloAnios);

    }
    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return new InstanciaEvento(evento.getTitulo(), evento.getDescripcion(), evento.getFechaInicio().plusYears(getIntervalo()), evento.getFechaFin().plusYears(getIntervalo()));
    }
}
