package Calendario.Repeticiones;


import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionDiaria extends Repeticion {
    public RepeticionDiaria(int intervaloDiaria,  LocalDate fechaHasta){
        super(intervaloDiaria, fechaHasta);
    }
    public RepeticionDiaria(int intervaloDiaria, int ocurrencias){
        super(intervaloDiaria, ocurrencias);

    }
    public RepeticionDiaria(int intervaloDiaria){
        super(intervaloDiaria);
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusDays(super.getIntervalo()), evento.getDiaFin().plusDays(super.getIntervalo()));
    }

}