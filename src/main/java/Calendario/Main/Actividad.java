package Calendario.Main;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private Alarma alarma;


    public Actividad(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public abstract LocalDateTime getFechaInicio();

    protected void setAlarma(Alarma alarma){
        this.alarma = alarma;
    }

    // getAlarma devuelve la Alarma configurada para la actividad
    public Alarma getAlarma() {
        return alarma;
    }
}
