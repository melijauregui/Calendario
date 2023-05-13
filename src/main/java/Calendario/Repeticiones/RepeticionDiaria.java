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
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de días
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        return fecha.plusDays(getIntervalo());
    }

}