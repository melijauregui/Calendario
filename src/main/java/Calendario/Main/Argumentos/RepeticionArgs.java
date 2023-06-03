package Calendario.Main.Argumentos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RepeticionArgs {
    private int intervalo;
    private Frecuencia frecuencia;
    private LocalDate fechaHasta;
    private int ocurrencias;
    private List<DayOfWeek> diasSemana;
    private TipoRepeticion tipo;

    /**
     * Recibe la información de una Repetición con fecha de vencimiento
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
        this.fechaHasta = fechaHasta;
        this.tipo = TipoRepeticion.FECHA_LIMITE;
    }

    /**
     * Recibe la información de una Repetición con límite de ocurrencias
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia, int ocurrencias){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
        this.ocurrencias = ocurrencias;
        this.tipo = TipoRepeticion.OCURRENCIAS;
    }

    /**
     * Recibe la información de una Repetición infinita
     */
    public RepeticionArgs(int intervalo, Frecuencia frecuencia){
        this.intervalo = intervalo;
        this.frecuencia = frecuencia;
        this.tipo = TipoRepeticion.INFINITA;
    }

    /**
     * Recibe la información de una RepeticiónSemanal con límite de ocurrencias
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana, int ocurrencias){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
        this.ocurrencias = ocurrencias;
        this.tipo = TipoRepeticion.OCURRENCIAS;
    }

    /**
     * Recibe la información de una RepeticiónSemanal con fecha de vencimiento
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
        this.fechaHasta = fechaHasta;
        this.tipo = TipoRepeticion.FECHA_LIMITE;
    }

    /**
     * Recibe la información de una RepeticiónSemanal infinita
     */
    public RepeticionArgs(int intervalo, List<DayOfWeek> diasSemana){
        this.intervalo = intervalo;
        this.diasSemana = diasSemana;
        this.tipo = TipoRepeticion.INFINITA;
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

    public TipoRepeticion getTipo(){return  tipo;}
}
