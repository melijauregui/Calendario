package Calendario.Repeticiones;

import Calendario.Eventos.InstanciaEvento;
import java.time.LocalDate;

public class RepeticionAnual extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionAnual(int intervaloAnual,  LocalDate fechaHasta){
        super(intervaloAnual, fechaHasta);
    }

    /**
     * Crea una Repetición que termina luego de una cantidad de repeticiones dada
     */
    public RepeticionAnual(int intervaloAnual, int ocurrencias){
        super(intervaloAnual, ocurrencias);

    }

    /**
     * Crea una Repetición infinita
     */
    public RepeticionAnual(int intervaloAnual){
        super(intervaloAnual);
    }

    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusYears(getIntervalo());
    }


}
