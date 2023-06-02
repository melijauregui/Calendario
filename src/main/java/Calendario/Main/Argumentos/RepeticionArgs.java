package Calendario.Main.Argumentos;

import Calendario.Enums.Frecuencia;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RepeticionArgs {
    private int intervalo;
    private Frecuencia frecuencia;
    private LocalDate fechaHasta;
    private int ocurrencias;
    private List<DayOfWeek> diasSemana;

    /**
     * Recibe la información de una Repetición con fecha de vencimiento y la crea según el tipo de Frecuencia pasado
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
        this.fechaHasta = fechaHasta;
    }

    /**
     * Recibe la información de una Repetición con límite de ocurrencias y la crea según el tipo de Frecuencia pasado
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia, int ocurrencias){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
        this.ocurrencias = ocurrencias;
    }

    /**
     * Recibe la información de una Repetición infinita y la crea según el tipo de Frecuencia pasado
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
    }

    /**
     * Recibe la información de una RepeticiónSemanal con límite de ocurrencias y la crea
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana, int ocurrencias){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
        this.ocurrencias = ocurrencias;
    }

    /**
     * Recibe la información de una RepeticiónSemanal con fecha de vencimiento y la crea
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
        this.fechaHasta = fechaHasta;
    }

    /**
     * Recibe la información de una RepeticiónSemanal infinita y la crea
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
    }

    public List<DayOfWeek> getDiasSemana() {
        return diasSemana;
    }

    public int getOcurrencias() {
        return ocurrencias;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public Frecuencia getFrecuencia() {
        return frecuencia;
    }
}
