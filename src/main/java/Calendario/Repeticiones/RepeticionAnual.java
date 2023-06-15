package Calendario.Repeticiones;

import Calendario.Duracion.Duracion;
import Calendario.Enums.TipoRepeticion;

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

    /**
     * Devuelve el tipo de repetición
     */
    public TipoRepeticion getTipoRepeticion() {
        return TipoRepeticion.ANUAL;
    }

    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de años
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusYears(getIntervalo());
    }

}
