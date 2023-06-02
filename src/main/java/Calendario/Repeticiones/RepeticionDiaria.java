package Calendario.Repeticiones;

import java.time.LocalDate;

public class RepeticionDiaria extends Repeticion {

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionDiaria(int intervaloDiaria,  LocalDate fechaHasta, int ocurrencias){

        super(intervaloDiaria, fechaHasta, ocurrencias);
    }


    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de días
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusDays(getIntervalo());
    }

}