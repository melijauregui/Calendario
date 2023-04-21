package Calendario.Repeticiones;


import Calendario.Eventos.InstanciaEvento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RepeticionSemanal extends Repeticion{
    // "todos los martes y jueves" --> Dia[MARTES, JUEVES]
    private List<DayOfWeek> diasSemana;
    //cada 'x' semanas

    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana ,LocalDate fechaHasta){
        super(intervaloSemanas, fechaHasta);
        this.diasSemana = diasSemana;
    }
    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana , int ocurrencias){
        super(intervaloSemanas, ocurrencias);
        this.diasSemana = diasSemana;
    }
    public RepeticionSemanal(int intervaloSemanas, List<DayOfWeek> diasSemana){
        super(intervaloSemanas);
        this.diasSemana = diasSemana;

    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        int diferencia = getDiferenciaEntreInstancias(evento);
        LocalDate diaInicioSiguienteEvento = evento.getDiaInicio().plusDays(diferencia);
        LocalDate diaFinSiguienteEvento = evento.getDiaFin().plusDays(diferencia);
        return evento.Clone(diaInicioSiguienteEvento, diaFinSiguienteEvento);
    }

    // getDiferenciaEntreInstancias calcula la cantidad de días entre el evento pasado por parámetro
    // y la siguiente repetición
    private int getDiferenciaEntreInstancias(InstanciaEvento evento){
        DayOfWeek diaEvento = evento.getFechaInicio().getDayOfWeek();
        DayOfWeek proximoDia =  getProximoDiaMismaSemana(diaEvento);
        int diferencia = 0;
        if (proximoDia == null){ // 1er día siguiente semana
            diferencia = calcularDiferenciaSemanas(diaEvento);

        } else {
            diferencia = calcularDiferenciaDias(diaEvento, proximoDia);
        }
        return diferencia;
    }


    // getProximoDiaMismaSemana devuelve el número del enum Dia del siguiente día de la semana en el que debe
    // repetirse el Evento
    private DayOfWeek getProximoDiaMismaSemana (DayOfWeek diaActual){
        // busco el sgte día de la semana en el <diasSemana> xq pueden estar desordenados, ej DOMINGO, VIERNES, LUNES
        DayOfWeek proximoDia = diaActual;
        for(DayOfWeek dia : diasSemana){
            if (dia.ordinal() > proximoDia.ordinal()){
                proximoDia = dia;
            }
        }
        return proximoDia.ordinal() > diaActual.ordinal() ? proximoDia : null;
    }


    // getPrimerDiaSemana devuelve el número del enum Dia del primer día semanal en el que debe
    // repetirse el Evento
    private DayOfWeek getPrimerDiaSemana(){
        var proximoDia = diasSemana.get(0);
        for(DayOfWeek dia : diasSemana){
            if (dia.ordinal() < proximoDia.ordinal()){
                proximoDia = dia;
            }
        }
        return proximoDia;
    }

    // calcularDiferenciaDias calcula la cantidad de días en que difieren dos repeticiones consecutivas
    // de la misma semana
    private int calcularDiferenciaDias(DayOfWeek dia1, DayOfWeek dia2){
        return Math.abs(dia2.ordinal()-dia1.ordinal());
    }

    // calcularDiferenciaSemanas calcula la cantidad de días en que difieren dos repeticiones consecutivas de
    // distinta semana
    private int calcularDiferenciaSemanas(DayOfWeek diaEvento){
        return 7*getIntervalo()-calcularDiferenciaDias(diaEvento, getPrimerDiaSemana());
    }
}
