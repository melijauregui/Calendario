package Calendario.Repeticiones;

import java.time.LocalDate;
import java.util.List;
import Calendario.Eventos.InstanciaEvento;

public abstract class Repeticion {

    private LocalDate fechaHasta;
    private int intervalo;
    private int ocurrencias;
    private int ocurrenciasRelativas = 0;
    private static final int sinRepeticion = 0;
    private static final int repeticionInfinita = -1;

    /**
     * Crea una Repetición que termina en una determinda fecha
     */
    public Repeticion(int intervalo, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.fechaHasta = fechaHasta;
    }

    /**
     * Crea una Repetición que termina luego de una cantidad de repeticiones dada
     */
    public Repeticion(int intervalo, int ocurrencias){
        this.intervalo = intervalo;
        this.ocurrencias = ocurrencias;
        this.ocurrenciasRelativas = ocurrencias;
    }

    /**
     * Crea una Repetición infinita
     */
    public Repeticion(int intervalo){
        this.intervalo = intervalo;
        this.ocurrencias = repeticionInfinita;
    }

/*    *//**
     * Devuelve true si la repetición nunca acaba
     *//*
    public boolean esInfinita(){
        return (this.ocurrencias == repeticionInfinita);
    }*/


    public LocalDate getProximaFechaInicio(LocalDate fecha){
        if (terminoRepeticion(fecha)){
            ocurrenciasRelativas = ocurrencias;
            return null;
        }
        disminuirOcurrencias();
        return getProximaFecha(fecha);
    }

    public LocalDate getProximaFechaFin(LocalDate fecha){
        return getProximaFecha(fecha);

    }

    protected abstract LocalDate getProximaFecha(LocalDate fecha);


/*
    *//**
     * Guarda en una lista las sucesivas instancias del evento. Si la repetición es infinita, solo
     * guarda la pasada por parámetro.
     *//*
    public void almacenarRepeticiones(List<InstanciaEvento> almacenamiento, InstanciaEvento primerEvento) {
        this.ocurrenciasRelativas = ocurrencias;
        almacenamiento.add(primerEvento);
        InstanciaEvento utlimoEventoAlmacenado = primerEvento;
        this.disminuirOcurrencias();
        if (!esInfinita()) {
            while (!esUltimaInstanciaEvento(utlimoEventoAlmacenado)) {
                this.disminuirOcurrencias();
                var instancia = this.getProximaInstanciaEvento(utlimoEventoAlmacenado);
                almacenamiento.add(instancia);
                utlimoEventoAlmacenado = instancia;
            }
        }
    }*/

    /**
     * Devuelve el intervalo
     */
    public int getIntervalo(){
        return intervalo;
    }

    /**
     * Devuelve true si la instancia pasada por parámetro es la última del
     * evento
     */
    protected boolean terminoRepeticion(LocalDate fecha){
        if (fechaHasta == null){
            return ocurrenciasRelativas-1 == sinRepeticion;
        }
        return fecha.isAfter(fechaHasta);
    }

    /**
     * Disminuye en 1 la cantidad de ocurrencias del evento
     */
    protected void disminuirOcurrencias(){
        if (this.ocurrenciasRelativas > sinRepeticion){
            this.ocurrenciasRelativas-=1;
        }
    }

}