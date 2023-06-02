package Calendario.Enums;

import Calendario.Repeticiones.*;



import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Frecuencia {
    ANUAL {
        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionAnual y la devuelve
         */
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta, int ocurrencias){
            return new RepeticionAnual(intervalo, fechaHasta, ocurrencias);
        }

    },
    DIARIA{

        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionDiaria y la devuelve
         */
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta, int ocurrencias){
            return new RepeticionDiaria(intervalo, fechaHasta, ocurrencias);
        }
    },
    MENSUAL{

        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionMensual y la devuelve
         */

        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta, int ocurrencias){
            return new RepeticionMensual(intervalo, fechaHasta, ocurrencias);
        }
    };

    Frecuencia(){}

    /**
     * Recibe la información de una Repeticion con fecha de vencimiento, la crea y la devuelve
     */
    public abstract Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta, int ocurrencias);

}

