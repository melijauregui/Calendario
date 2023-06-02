package Calendario.Repeticiones;


import java.time.LocalDate;

public class RepeticionMensual extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionMensual(int intervaloMensual,  LocalDate fechaHasta, int ocurrencias){
        super(intervaloMensual, fechaHasta, ocurrencias);
    }

    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de meses
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusMonths(getIntervalo());
    }
}