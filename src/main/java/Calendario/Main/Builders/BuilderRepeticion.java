package Calendario.Main.Builders;

import Calendario.Enums.Frecuencia;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class BuilderRepeticion {
    Repeticion repeticion;

    /**
     * Recibe la información de una Repetición con fecha de vencimiento y la crea según el tipo de Frecuencia pasado
     */
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia, LocalDate fechaHasta){
        repeticion = frecuencia.crearRepeticion(intervalo, fechaHasta);
    }

    /**
     * Recibe la información de una Repetición con límite de ocurrencias y la crea según el tipo de Frecuencia pasado
     */
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia, int ocurrencias){
        repeticion = frecuencia.crearRepeticion(intervalo, ocurrencias);
    }

    /**
     * Recibe la información de una Repetición infinita y la crea según el tipo de Frecuencia pasado
     */
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia){
        repeticion = frecuencia.crearRepeticion(intervalo);
    }

    /**
     * Recibe la información de una RepeticiónSemanal con límite de ocurrencias y la crea
     */
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana, int ocurrencias){
        repeticion = new RepeticionSemanal(intervalo, diasSemana, ocurrencias);
    }

    /**
     * Recibe la información de una RepeticiónSemanal con fecha de vencimiento y la crea
     */
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana, LocalDate fechaHasta){
        repeticion = new RepeticionSemanal(intervalo, diasSemana, fechaHasta);
    }

    /**
     * Recibe la información de una RepeticiónSemanal infinita y la crea
     */
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana){
        repeticion = new RepeticionSemanal(intervalo, diasSemana);
    }

    /**
     * Devuelve la Repetición creada
     */
    public Repeticion crearRepeticion(){
        return repeticion;
    }
}


