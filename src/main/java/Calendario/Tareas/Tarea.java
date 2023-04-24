package Calendario.Tareas;

import Calendario.Alarmas.Alarma;
import Calendario.Main.Actividad;
import Calendario.Main.Constantes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends Actividad {
    private boolean completa;

    private LocalDate dia;

    private LocalTime hora;

    // de día completo
    public Tarea(){
        this.dia = LocalDate.now(); // por default es de día completo
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
     * Agrega la Alarma a la Tarea
     */
    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(this.getFecha());
        agregarAlarma(alarma);
    }

    /**
     * Marca la tarea como completa
     */
    public void completar(){
        this.completa = true;
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
     * Devuelve true si la tarea se encuentra dentro del intervalo de fechas dado
     */
    private boolean estaEnElIntervaloDiaCompleto(LocalDate fecha){
        return this.getFecha().toLocalDate().equals(fecha) && esDiaCompleto();
    }

    /**
     * Devuelve true si la tarea ha sido completada, false en caso contrario
     */
    private boolean estaCompleta(){
        return this.completa;
    }
}

