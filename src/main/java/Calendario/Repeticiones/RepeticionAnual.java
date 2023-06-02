package Calendario.Repeticiones;

import java.time.LocalDate;

public class RepeticionAnual extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionAnual(int intervaloAnual,  LocalDate fechaHasta, int ocurrencias){
        super(intervaloAnual, fechaHasta, ocurrencias);
    }

    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de años
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusYears(getIntervalo());
    }

}
