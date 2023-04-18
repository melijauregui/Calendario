package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepeticionSemanal extends Repeticion{
    // "todos los martes y jueves" --> Dia[MARTES, JUEVES]
    private List<Dia> diasSemana;
    //cada 'x' semanas
    private static final int diaTope = 7; //fuera de rango, sábado=6
    private static final int diaInicial = 0; //domingo=0
    public RepeticionSemanal(int intervaloSemanas, List<Dia> diasSemana ,LocalDate fechaHasta){
        super(intervaloSemanas, fechaHasta);
        this.diasSemana = diasSemana;
    }
    public RepeticionSemanal(int intervaloSemanas, List<Dia> diasSemana , int ocurrencias){
        super(intervaloSemanas, ocurrencias);
        this.diasSemana = diasSemana;
    }
    public RepeticionSemanal(int intervaloSemanas, List<Dia> diasSemana){
        super(intervaloSemanas);
        this.diasSemana = diasSemana;

    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de semanas
    @Override
    public LocalDateTime getProximaFechaInicio(LocalDateTime fecha) {
        disminuirOcurrencias();
        return calcularProximaFecha(fecha);
    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de semanas
    @Override
    public LocalDateTime getProximaFechaFin(LocalDateTime fecha) {
        return calcularProximaFecha(fecha);
    }

    // calcularProximaFecha devuelve la fecha de la siguiente repetición, teniendo en cuenta
    // el intervalo semanal y los días de la semana en los que debe repetirse el Evento
    private LocalDateTime calcularProximaFecha(LocalDateTime fecha){
        int diaActual = getNumeroDia(fecha);
        LocalDateTime proximaFechaInicio = fecha;
        int minimoDia = getProximoDiaSemana(diaActual); // dentro de la misma semana
        if (minimoDia == -1){ // 1er día sgte semana
            minimoDia = getPrimerDiaSemana(diaActual);
            proximaFechaInicio = fecha.plusWeeks(getIntervalo());
            proximaFechaInicio = proximaFechaInicio.minusDays(calcularDiferenciaDias(diaActual, minimoDia));
        } else{
            proximaFechaInicio = fecha.plusDays(calcularDiferenciaDias(diaActual, minimoDia));
        }
        return proximaFechaInicio;
    }

    // getNumeroDia devuelve el número del enum Dia de la fecha recibida
    private int getNumeroDia(LocalDateTime fecha){
        int dia = fecha.getDayOfWeek().ordinal()+1; // +1 xq DayOfWeek empieza desde LUNES
        if (dia == diaTope){
            dia = diaInicial;
        }
        return dia;
    }

    // getProximoDiaSemana devuelve el número del enum Dia del siguiente día semanal en el que debe
    // repetirse el Evento
    private int getProximoDiaSemana(int diaActual){
        int minimoDia = diaTope;
        // busco el sgte día de la semana en el <diasSemana> xq pueden estar desordenados, ej DOMINGO, VIERNES, LUNES
        for(Dia dia : diasSemana){
            int proximoDia = dia.ordinal();
            if (proximoDia > diaActual && proximoDia < minimoDia){ // 'viene después', ej: MARTES > LUNES, LUNES> DOMINGO
                minimoDia = proximoDia;
            }
        }
        if (minimoDia == diaTope){ // no hay más días de la semana actual en la q debe repetirse el evento
            return -1;
        }
        return minimoDia;
    }

    // getPrimerDiaSemana devuelve el número del enum Dia del primer día semanal en el que debe
    // repetirse el Evento
    private int getPrimerDiaSemana(int diaActual){
        int minimoDia = diaTope;
        for(Dia dia : diasSemana){
            int proximoDia = dia.ordinal();
            if (proximoDia < minimoDia){
                minimoDia = proximoDia;
            }
        }
        return minimoDia;
    }

    // calcularDiferenciaDias calcula la cantidad de días en que difieren dos repeticiones consecutivas
    private int calcularDiferenciaDias(int dia1, int dia2){
        return Math.abs(dia2-dia1); // > 0 si es de la misma semana, < 0 si es de la sgte
    }

    /*    public void RepetirEvento(ArrayList<Evento> almacenamientoFechas, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if (fechaHasta == null){
            fechaHasta = LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth());
        }
        while (this.fechaHasta.isAfter(LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth())) ||
                this.ocurrencias > 0){
            almacenamientoFechas.add(new Evento(fechaInicio, fechaFin));
            fechaInicio = fechaInicio.plusWeeks(this.intervalo);
            fechaFin = fechaFin.plusWeeks(this.intervalo);
        }
    }*/
}