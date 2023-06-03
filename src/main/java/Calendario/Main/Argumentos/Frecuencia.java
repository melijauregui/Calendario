package Calendario.Main.Argumentos;

import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionAnual;
import Calendario.Repeticiones.RepeticionDiaria;
import Calendario.Repeticiones.RepeticionMensual;

import java.time.LocalDate;

public enum Frecuencia {
    ANUAL {
        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionAnual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionAnual(intervalo, fechaHasta);
        }

        /**
         * Recibe la información de una Repeticion con límite de ocurrencias, crea una RepeticionAnual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionAnual(intervalo, ocurrencias);


        }

        /**
         * Recibe la información de una Repeticion con infinita, crea una RepeticionAnual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionAnual(intervalo);


        }
    },
    DIARIA{

        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionDiaria y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionDiaria(intervalo, fechaHasta);


        }

        /**
         * Recibe la información de una Repeticion con límite de ocurrencias, crea una RepeticionDiaria y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionDiaria(intervalo, ocurrencias);


        }

        /**
         * Recibe la información de una Repeticion infinita, crea una RepeticionDiaria y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionDiaria(intervalo);


        }
    },
    MENSUAL{

        /**
         * Recibe la información de una Repeticion con fecha de vencimiento, crea una RepeticionMensual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionMensual(intervalo, fechaHasta);


        }

        /**
         * Recibe la información de una Repeticion con límite de ocurrencias, crea una RepeticionMensual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionMensual(intervalo, ocurrencias);
        }

        /**
         * Recibe la información de una Repeticion infinita, crea una RepeticionMensual y la devuelve
         */
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionMensual(intervalo);

        }
    };

    Frecuencia(){}

    /**
     * Recibe la información de una Repeticion con fecha de vencimiento, la crea y la devuelve
     */
    public abstract Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta);

    /**
     * Recibe la información de una Repeticion con límite de ocurrencias, la crea y la devuelve
     */
    public abstract Repeticion crearRepeticion(int intervalo, int ocurrencias);

    /**
     * Recibe la información de una Repeticion infinita, la crea y la devuelve
     */
    public abstract Repeticion crearRepeticion(int intervalo);
}
