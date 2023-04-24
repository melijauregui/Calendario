package Calendario.Repeticiones;

import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;

public class RepeticionDiaria extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionDiaria(int intervaloDiaria,  LocalDate fechaHasta){
        super(intervaloDiaria, fechaHasta);
    }

    /**
     * Crea una Repetición que termina luego de una cantidad de repeticiones dada
     */
    public RepeticionDiaria(int intervaloDiaria, int ocurrencias){
        super(intervaloDiaria, ocurrencias);

    }

    /**
     * Crea una Repetición infinita
     */
    public RepeticionDiaria(int intervaloDiaria){
        super(intervaloDiaria);
    }

    /**
     * Devuelve el siguiente evento del pasado por parámetro
     */
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusDays(super.getIntervalo()), evento.getDiaFin().plusDays(super.getIntervalo()));
    }

}