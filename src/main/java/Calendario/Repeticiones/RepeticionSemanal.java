package Calendario.Repeticiones;


import Calendario.Duracion.Duracion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RepeticionSemanal extends Repeticion{
    private List<DayOfWeek> diasSemana;
    int diferenciaEntreRepeticiones;

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana ,LocalDate fechaHasta){
        super(intervaloSemanas, fechaHasta);
        this.diasSemana = diasSemana;
    }

    /**
     * Crea una Repetición que termina luego de una cantidad de repeticiones dada
     */
    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana , int ocurrencias){
        super(intervaloSemanas, ocurrencias);
        this.diasSemana = diasSemana;
    }

    /**
     * Crea una Repetición infinita
     */
    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana){
        super(intervaloSemanas);
        this.diasSemana = diasSemana;

    }

    /**
     * Dada la fecha de finalización de la repetición anterior, devuelve la de la siguiente
     **/
    @Override
    public LocalDate getProximaFechaFin(LocalDate fecha){
        return getProximaFecha(fecha, diferenciaEntreRepeticiones);

    }

    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de semanas
     */
    public LocalDate getProximaFecha(LocalDate fecha){
        diferenciaEntreRepeticiones = getDiferenciaEntreFechas(fecha);
        return getProximaFecha(fecha, diferenciaEntreRepeticiones);
    }

    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según la diferencia de días entre las
     * repeticiones
     */
    private LocalDate getProximaFecha(LocalDate fecha, int diferencia){
        return fecha.plusDays(diferencia);
    }



    /**
     * Calcula la cantidad de días entre la fecha pasada por parámetro
     * y la siguiente repetición
     */
    private int getDiferenciaEntreFechas(LocalDate fecha){
        DayOfWeek dia = fecha.getDayOfWeek();
        DayOfWeek proximoDia =  getProximoDiaMismaSemana(dia);
        int diferencia = 0;
        if (proximoDia == null){ // 1er día siguiente semana
            diferencia = calcularDiferenciaSemanas(dia);

        } else {
            diferencia = calcularDiferenciaDias(dia, proximoDia);
        }
        return diferencia;
    }


    /**
     * Devuelve el número del enum Dia del siguiente día de la semana en el que debe
     * repetirse el Evento
     */
    private DayOfWeek getProximoDiaMismaSemana (DayOfWeek diaActual){
        // busco el sgte día de la semana en el <diasSemana> xq pueden estar desordenados, ej DOMINGO, VIERNES, LUNES
        DayOfWeek proximoDia = diaActual;
        for(DayOfWeek dia : diasSemana){
            if (dia.ordinal() > proximoDia.ordinal()){
                proximoDia = dia;
                break;
            }
        }
        return proximoDia.ordinal() > diaActual.ordinal() ? proximoDia : null;
    }


    /**
     * Devuelve el número del enum Dia del primer día semanal en el que debe
     * repetirse el Evento
     */
    private DayOfWeek getPrimerDiaSemana(){
        var proximoDia = diasSemana.get(0);
        for(DayOfWeek dia : diasSemana){
            if (dia.ordinal() < proximoDia.ordinal()){
                proximoDia = dia;
            }
        }
        return proximoDia;
    }

    /**
     * Calcula la cantidad de días en que difieren dos repeticiones consecutivas
     * de la misma semana
     */
    private int calcularDiferenciaDias(DayOfWeek dia1, DayOfWeek dia2){
        return Math.abs(dia2.ordinal()-dia1.ordinal());
    }

    /**
     * Calcula la cantidad de días en que difieren dos repeticiones consecutivas de
     * distinta semana
     */
    private int calcularDiferenciaSemanas(DayOfWeek diaEvento){
        return 7*getIntervalo()-calcularDiferenciaDias(diaEvento, getPrimerDiaSemana());
    }
    public void actualizarDuracion(Duracion d){
        if (diasSemana.contains(d.getFechaInicio().getDayOfWeek())){
            return;
        }
        d.setDiaInicio(getProximaFechaInicio(d.getDiaInicio()));
        d.setDiaFin(getProximaFechaFin(d.getDiaFin()));

    }
}