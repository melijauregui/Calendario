package Calendario.Main.Builders;

import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionAnual;
import Calendario.Repeticiones.RepeticionDiaria;
import Calendario.Repeticiones.RepeticionMensual;


import java.time.LocalDate;

public enum Frecuencia {
    ANUAL {
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionAnual(intervalo, fechaHasta);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionAnual(intervalo, ocurrencias);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionAnual(intervalo);
        }
    },
    DIARIA{
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionDiaria(intervalo, fechaHasta);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionDiaria(intervalo, ocurrencias);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionDiaria(intervalo);
        }
    },
    MENSUAL{
        @Override
        public Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta){
            return new RepeticionMensual(intervalo, fechaHasta);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo, int ocurrencias){
            return new RepeticionMensual(intervalo, ocurrencias);
        }
        @Override
        public Repeticion crearRepeticion(int intervalo){
            return new RepeticionMensual(intervalo);
        }
    };

    Frecuencia(){}

    /**
     * Calcula una fecha relativa a otra
     */
    public abstract Repeticion crearRepeticion(int intervalo, LocalDate fechaHasta);
    public abstract Repeticion crearRepeticion(int intervalo, int ocurrencias);
    public abstract Repeticion crearRepeticion(int intervalo);
}

