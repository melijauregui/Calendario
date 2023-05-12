package Calendario.Tareas;

import Calendario.Main.Actividad;
import Calendario.Main.ActividadParticular;
import Calendario.Main.Constantes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends ActividadParticular implements Actividad {

    private boolean completada;

    private LocalDate dia;

    private LocalTime hora;

    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
    }
    /**
     * Setea el título
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Setea la descripción
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la fecha de la tarea
     */
    public void setDia(LocalDate dia){
        this.dia = dia;
    }

    /**
     * Devuelve la hora de la tarea
     */
    public void setHora(LocalTime hora){
        this.hora = hora;
    }

    /**
     * Devuelve true si la tarea dura todo el día
     */
    public boolean esDiaCompleto(){
        return this.hora == null;
    }

    /**
     * Devuelve true si la fecha de la tarea se encuentra dentro del intervalo
     * de fechas pasado por parámetro
     */
    public boolean estaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return !this.estaCompleta() && (this.getFecha().isAfter(desde) || estaEnElIntervaloDiaCompleto(desde.toLocalDate())) && this.getFecha().isBefore(hasta);
    }

    /**
     * Marca la tarea como completada
     */
    public void completar(){
        this.completada = true;
    }

    /**
     * Devuelve la fecha y hora de la tarea
     */
    public LocalDateTime getFecha(){
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(this.dia, this.hora);
        }
        return LocalDateTime.of(this.dia, LocalTime.of(Constantes.horaFinDiaCompleto, Constantes.minutoFinDiaCompleto));
    }

    /**
     * Devuelve true si la tarea es de dia completo y se encuentra dentro del intervalo de fechas dado
     */
    private boolean estaEnElIntervaloDiaCompleto(LocalDate fecha){
        return this.getFecha().toLocalDate().equals(fecha) && esDiaCompleto();
    }

    /**
     * Devuelve true si la tarea ha sido completada, false en caso contrario
     */
    private boolean estaCompleta(){
        return this.completada;
    }

}

