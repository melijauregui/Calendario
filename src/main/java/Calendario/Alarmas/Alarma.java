package Calendario.Alarmas;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Enums.TiempoRelativo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alarma implements Serializable {
    private int intervalo;
    private TiempoRelativo tiempoRelativo;
    private LocalDateTime fechaAlarma;
    private Aviso aviso;
    private String tituloAlarma;
    private String descripcionAlarma;

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public Alarma(LocalDateTime fechaAlarma,  Aviso aviso){
        this.aviso = aviso;
        this.fechaAlarma = fechaAlarma;
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo
     */
    public Alarma(int intervalo, TiempoRelativo tiempoRelativo, LocalDateTime fecha, Aviso aviso){
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
     * Envía el Aviso correspondiente
     */
    public void avisar(){
        aviso.avisar();
    }

    public String getAviso() {
        return aviso.getAvisoToString();
    }

    public String getTiempoRelativo(){
       return tiempoRelativo.getTiempoRelativoToString();
    }

    public String getIntervalo(){
        return Integer.toString(intervalo);
    }

    public boolean esConTiempoRelativo(){
        return intervalo > 0;
    }

    public void setTituloAlarma(String titulo){
        this.tituloAlarma = titulo;
    }

    public String getDescripcionAlarma() {
        return descripcionAlarma;
    }

    public String getTituloAlarma() {
        return tituloAlarma;
    }

    public void setDescripcionAlarma(String descripcion){
        this.descripcionAlarma = descripcion;
    }
}
