package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alarma implements Serializable {
    private int intervalo;
    private TiempoRelativo tiempoRelativo;
    private LocalDateTime fechaAlarma;
    private TipoAviso aviso;
    private String tituloAlarma;
    private String descripcionAlarma;

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public Alarma(LocalDateTime fechaAlarma,  TipoAviso aviso){
        this.aviso = aviso;
        this.fechaAlarma = fechaAlarma;
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo
     */
    public Alarma(int intervalo, TiempoRelativo tiempoRelativo, LocalDateTime fecha, TipoAviso aviso){
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
        fechaAlarma = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);
        this.aviso = aviso;
    }

    /**
     * Devuelve true si la alarma suena antes de la alarma recibida por parámetro
     */
    public boolean suenaAntes(Alarma otra){
        return this.fechaAlarma.isBefore(otra.getFechaAlarma());
    }

    /**
     * Devuelve true si la alarma suena antes de la fecha recibida por parámetro
     */
    public boolean suenaAntes(LocalDateTime fecha){
        return this.fechaAlarma.isBefore(fecha);
    }

    /**
     * Devuelve true si la alarma suena al mismo tiempo que la fecha recibida por parámetro
     */
    public boolean suenaDespues(LocalDateTime fecha){
        return this.fechaAlarma.isAfter(fecha);
    }

    /**
     * Devuelve true si la alarma suena al mismo tiempo que la recibida por parámetro
     */
    public boolean suenaIgual(Alarma otra){
        return getFechaAlarma().isEqual(otra.getFechaAlarma());
    }

    /**
     * Devuelve la fecha de la Alarma
     */
    public LocalDateTime getFechaAlarma() {
        return fechaAlarma;
    }

    /**
     * Devuelve el Tipo de Aviso de la alarma
     */
    public TipoAviso getAviso() {
        return aviso;
    }

    /**
     * Devuelve el Tipo de Tiempo Relativo de la Alarma
     */
    public TiempoRelativo getTiempoRelativo(){
       return tiempoRelativo;
    }

    /**
     * Devuelve el intervalo relativo respecto al cual debe sonar la Alarma
     */
    public int getIntervalo(){
        return intervalo;
    }

    /**
     * Devuelve true si la alarma tiene fecha relativa, false en caso contrario
     */
    public boolean esConTiempoRelativo(){
        return intervalo > 0;
    }

    /**
     * Le setea el título a la Alarma
     */
    public void setTituloAlarma(String titulo){
        this.tituloAlarma = titulo;
    }

    /**
     * Le setea la descripción a la Alarma
     */
    public String getDescripcionAlarma() {
        return descripcionAlarma;
    }

    /**
     * Devuelve el título de la Alarma
     */
    public String getTituloAlarma() {
        return tituloAlarma;
    }

    /**
     * Devuelve la descripción de la Alarma
     */
    public void setDescripcionAlarma(String descripcion){
        this.descripcionAlarma = descripcion;
    }
}
