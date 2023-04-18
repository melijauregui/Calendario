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
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public abstract InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento);

    /* disminuirOcurrencias --> si el Evento termina luego de una cantidad de repeticiones, al obtener cada
    repetición se resta en 1 la cantidad de ocurrencias*/
    public void disminuirOcurrencias(){
        if (this.ocurrencias > 1){
            this.ocurrencias-=1;
        }
    }

    // esInifinita devuelve true si la repetición nunca acaba, false en caso contrario
    public boolean esInfinita(){
        return (this.ocurrencias == 0 && this.fechaHasta == null);
    }

    // terminoRepeticion devuelve true si no hay más repeticiones del Evento, false en caso contrario
    public boolean terminoRepeticion(LocalDate fechaActual){
        if (fechaHasta == null){
            return this.ocurrencias == 0;
        }
        return fechaActual.isAfter(fechaHasta); //isEqual?
    }

    // getIntervalo devuelve el intervalo de duración entre una repetición y la siguiente
    public int getIntervalo(){
        return intervalo;
    }
}