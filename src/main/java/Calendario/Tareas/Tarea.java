package Calendario.Tareas;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
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

    // setter de fecha
    public void setDia(LocalDate dia){
        this.dia = dia;
    }

    // setter de hora
    public void setHora(LocalTime hora){
        this.hora = hora;
    }

    // esDiaCompleto devuelve true si la tarea dura todo el día
    public boolean esDiaCompleto(){
        return this.hora == null;
    }

    // estaEnElIntervalo devuelve true si la fecha de la tarea se encuentra dentro del intervalo
    // de fechas pasado por parámetro
    public boolean estaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return !this.estaCompleta() && (this.getFecha().isAfter(desde) || estaEnElIntervaloDiaCompleto(desde.toLocalDate())) && this.getFecha().isBefore(hasta);
    }

    // configurarAlarma agrega la Alarma a la Tarea
    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(this.getFecha());
        agregarAlarma(alarma);
    }

    // completar marca la tarea como completa
    public void completar(){
        this.completa = true;
    }

    // getter de fecha y hora
    public LocalDateTime getFecha(){
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(this.dia, this.hora);
        }
        return LocalDateTime.of(this.dia, LocalTime.of(Constantes.horaFinDiaCompleto, Constantes.minutoFinDiaCompleto));
    }

    // estaEnElIntervalo devuelve true si la tarea se encuentra dentro del intervalo de fechas dado
    private boolean estaEnElIntervaloDiaCompleto(LocalDate fecha){
        return this.getFecha().toLocalDate().equals(fecha) && esDiaCompleto();
    }

    // estaCompleta devuelve true si la tarea ha sido completada, false en caso contrario
    private boolean estaCompleta(){
        return this.completa;
    }
}

