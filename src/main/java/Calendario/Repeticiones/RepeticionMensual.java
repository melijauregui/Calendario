package Calendario.Repeticiones;


import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Builders.Frecuencia;

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
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de meses
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusMonths(getIntervalo());
    }

    public Frecuencia getFrecuencia(){
        return Frecuencia.MENSUAL;
    }
}