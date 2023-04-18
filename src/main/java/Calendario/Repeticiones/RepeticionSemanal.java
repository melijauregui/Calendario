package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        DayOfWeek diaEvento = getDia(evento.getFechaInicio());
        DayOfWeek proximoDia =  getProximoDiaMismaSemana(diaEvento);
        LocalDateTime fechaSiguienteEvento;
        if (proximoDia == null){ // 1er día siguiente semana
            fechaSiguienteEvento = FechaSiguienteSemana(evento.getFechaInicio(), getPrimerDiaSemana());
            return new InstanciaEvento(evento.getTitulo(), evento.getDescripcion(), fechaSiguienteEvento, fechaSiguienteEvento);

        } else {
            fechaSiguienteEvento = evento.getFechaInicio().plusDays((calcularDiferenciaDias(diaEvento, proximoDia)));
            return new InstanciaEvento(evento.getTitulo(), evento.getDescripcion(), fechaSiguienteEvento, fechaSiguienteEvento);
        }
    }

    private LocalDateTime FechaSiguienteSemana(LocalDateTime fechaEvento, DayOfWeek primerDiaSemana){
        var proximaFechaInicio = fechaEvento.plusWeeks(getIntervalo());
        return proximaFechaInicio.minusDays(calcularDiferenciaDias(getDia(fechaEvento), primerDiaSemana));
    }


    // getNumeroDia devuelve el número del enum Dia de la fecha recibida
    private DayOfWeek getDia(LocalDateTime fecha){
        return fecha.getDayOfWeek();
    }

    // getProximoDiaSemana devuelve el número del enum Dia del siguiente día semanal en el que debe
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
    private int calcularDiferenciaDias(DayOfWeek dia1, DayOfWeek dia2){
        return Math.abs(dia2.ordinal()-dia1.ordinal());
    }
}
