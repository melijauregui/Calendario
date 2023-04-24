package Calendario.Repeticiones;

import Calendario.Eventos.InstanciaEvento;
import java.time.LocalDate;

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
