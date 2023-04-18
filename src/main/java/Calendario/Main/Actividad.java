package Calendario.Main;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private Alarma alarma;
    private LocalDateTime fecha;
    protected static final int horaInicialDiaCompleto = 0;
    protected static  final int minutoInicialDiaCompleto = 0;

    public Actividad(String titulo, String descripcion, LocalDateTime fecha){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // getTitulo devuelve el título de la Actividad
    public String getTitulo() {
        return titulo;
    }

    // getDescripción devuelve la descripción de la Actividad
    public String getDescripcion() {
        return descripcion;
    }

    // configurarAlarma recibe una Alarma y se la agrega a la Actividad
    public abstract void configurarAlarma(Alarma alarma);

    protected void setAlarma(Alarma alarma){
        this.alarma = alarma;
    }

    // getAlarma devuelve la Alarma configurada para la actividad
    public Alarma getAlarma() {
        return alarma;
    }

    // getFecha devuelve la fecha (del inicio) de la Actividad
    public LocalDateTime getFecha(){
        return this.fecha;
    }

    // comienzaAntes devuelve true si la actividad comienza antes de la recibida por parámetro
    public boolean comienzaAntes(Actividad otra){
        return this.fecha.isBefore(otra.getFecha());
    }
}
