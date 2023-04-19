package Calendario.Repeticiones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Calendario.Enums.Frecuencias;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

public abstract class Repeticion {
    private LocalDate fechaHasta;
    private int intervalo;
    private int ocurrencias;
    private static final int sinRepeticion = 0;
    private static final int repeticionInfinita = -1;

    // hasta determinda fecha
    public Repeticion(int intervalo, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.fechaHasta = fechaHasta;
    }

    //hasta n ocurrencias
    public Repeticion(int intervalo, int ocurrencias){
        this.intervalo = intervalo;
        this.ocurrencias = ocurrencias;
    }
    // infinita
    public Repeticion(int intervalo){
        this.intervalo = intervalo;
        this.ocurrencias = repeticionInfinita;
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public abstract InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento);

    /* disminuirOcurrencias disminuye en 1 las ocurrencias de la repetición, mientras haya alguna*/
    public void disminuirOcurrencias(){
        if (this.ocurrencias > sinRepeticion){
            this.ocurrencias-=1;
        }
    }

    // esInifinita devuelve true si la repetición nunca acaba, false en caso contrario
    public boolean esInfinita(){
        return (this.ocurrencias == repeticionInfinita);
    }

    // terminoRepeticion devuelve true si no hay más repeticiones del Evento, false en caso contrario
    public boolean terminoRepeticion(LocalDate fechaActual){
        if (fechaHasta == null){
            return this.ocurrencias == sinRepeticion;
        }
        return fechaActual.isAfter(fechaHasta); //isEqual?
    }

    // getIntervalo devuelve el intervalo de duración entre una repetición y la siguiente
    public int getIntervalo(){
        return intervalo;
    }
}