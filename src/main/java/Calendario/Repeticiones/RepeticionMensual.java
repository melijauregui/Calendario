package Calendario.Repeticiones;


import Calendario.Eventos.InstanciaEvento;
import java.time.LocalDate;

public class RepeticionMensual extends Repeticion {
    public RepeticionMensual(int intervaloMensual,  LocalDate fechaHasta){
        super(intervaloMensual, fechaHasta);
    }
    public RepeticionMensual(int intervaloMensual, int ocurrencias){
        super(intervaloMensual, ocurrencias);

    }
    public RepeticionMensual(int intervaloMensual){
        super(intervaloMensual);
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return evento.Clone(evento.getDiaInicio().plusMonths(super.getIntervalo()), evento.getDiaFin().plusMonths(super.getIntervalo()));
    }

}