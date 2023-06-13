package Calendario.Tareas;

import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Actividad.ActividadMutable;
import Calendario.Main.Constantes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

public class Tarea extends ActividadMutable {

    private boolean completada;

    private LocalDate dia;

    private LocalTime hora;

    public Tarea(){
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
        return (estaEnElIntervaloConHora(desde, hasta) || estaEnElIntervaloDiaCompleto(desde.toLocalDate()));
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
     * Agrega la Alarma pasada por parámetro al conjunto de alarmas de la Tarea
     */
    public void agregarAlarma(Alarma alarma){
        getAlarmas().add(alarma);
    }

    /**
     * Elimina la Alarma pasada por parámetro del conjunto de alarmas de la Tarea
     */
    public void eliminarAlarma(Alarma alarma){
        getAlarmas().remove(alarma);
    }

    /**
     * Acepta un Visitor
     */
    public void aceptarVisitor(ActividadVisitor actividadVisitor) {
        actividadVisitor.visitarTarea(this);
    }

    /**
     * Devuelve true si la tarea es de día completo y se encuentra dentro del intervalo de fechas dado
     */
    private boolean estaEnElIntervaloDiaCompleto(LocalDate fecha){
        return this.getFecha().toLocalDate().equals(fecha) && esDiaCompleto();
    }

    /**
     * Devuelve true si la tarea con hora se encuentra dentro del intervalo de fechas dado
     */
    private boolean estaEnElIntervaloConHora(LocalDateTime desde, LocalDateTime hasta){
        return !this.getFecha().isBefore(desde) && this.getFecha().isBefore(hasta);
    }

    /**
     * Devuelve true si la tarea ha sido completada, false en caso contrario
     */
    public boolean estaCompleta(){
        return this.completada;
    }

    public void eliminarAlarmas(){
        getAlarmas().clear();
    }


}

