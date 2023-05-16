package Calendario.Enums;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public enum TipoRepeticion {
    ANUAL{
        /**
         * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de años
         */
        public LocalDate getProximaFecha(LocalDate fecha, int intervalo){
            return fecha.plusYears(intervalo);
        }
    },

    DIARIA{
        /**
         * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de días
         */
        public LocalDate getProximaFecha(LocalDate fecha, int intervalo){
            return fecha.plusDays(intervalo);
        }
    },

    MENSUAL{
        /**
         * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de meses
         */
        public LocalDate getProximaFecha(LocalDate fecha, int intervalo){
            return fecha.plusMonths(intervalo);
        }
    },


}
