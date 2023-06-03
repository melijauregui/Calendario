package Calendario.Repeticiones;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Repeticion implements Serializable {

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



    /**
     * Dada la fecha de inicio de la repetición anterior, devuelve la de la siguiente
     * o null si la misma ya acabó
     */
    public LocalDate getProximaFechaInicio(LocalDate fecha){
        LocalDate fechaProxima =  getProximaFecha(fecha);
        if (terminoRepeticion(fechaProxima)){
            ocurrenciasRelativas = ocurrencias;
            return null;
        }
        disminuirOcurrencias();
        return fechaProxima;
    }

    /**
     * Dada la fecha de finalización de la repetición anterior, devuelve la de la siguiente
     **/
    public LocalDate getProximaFechaFin(LocalDate fecha){
        return getProximaFecha(fecha);

    }

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
    /**
     * Devuelve la próxima fecha a la pasada por parámetro, según el intervalo de repetición
     */
    protected abstract LocalDate getProximaFecha(LocalDate fecha);


}