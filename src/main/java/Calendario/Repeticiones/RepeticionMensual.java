package Calendario.Repeticiones;


import Calendario.Eventos.InstanciaEvento;
import java.time.LocalDate;

public class RepeticionMensual extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionMensual(int intervaloMensual,  LocalDate fechaHasta){
        super(intervaloMensual, fechaHasta);
    }

    /**
     * Crea una Repetición que termina luego de una cantidad de repeticiones dada
     */
    public RepeticionMensual(int intervaloMensual, int ocurrencias){
        super(intervaloMensual, ocurrencias);

    }

    /**
     * Crea una Repetición infinita
     */
    public RepeticionMensual(int intervaloMensual){
        super(intervaloMensual);
    }

    /**
     * Devuelve el siguiente evento del pasado por parámetro
     */
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusMonths(super.getIntervalo()), evento.getDiaFin().plusMonths(super.getIntervalo()));
    }

}