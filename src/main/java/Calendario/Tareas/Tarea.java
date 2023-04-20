package Calendario.Tareas;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;
import Calendario.Main.Constantes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends Actividad {
    private LocalDateTime fecha;
    private boolean completa;

    // de día completo
    public Tarea(String titulo, String descripcion, LocalDate fecha){
        super(titulo, descripcion);
        this.fecha =  LocalDateTime.of(fecha, LocalTime.of(Constantes.horaInicioDiaCompleto, Constantes.minutoInicioDiaCompleto));
    }
    // tiene fecha y hora
    public Tarea(String titulo, String descripcion, LocalDateTime fecha){
        super(titulo, descripcion);
        this.fecha = fecha;
    }

    // ver si sacarlo -->
    /* esDiaCompleto devuelve true si la tarea es de día completo, false en caso contrario --> usar en la
    etapa 3 porque se muestra distinto */
    public boolean esDiaCompleto(){
        return (fecha.getHour() == Constantes.horaInicioDiaCompleto && fecha.getMinute() == Constantes.minutoInicioDiaCompleto);
    }

    // estaEnElIntervalo devuelve true si la tarea se encuentra dentro del intervalo de fechas dado
    public boolean estaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return this.fecha.isAfter(desde) && this.fecha.isBefore(hasta);
    }

    // configurarAlarma agrega la Alarma a la Tarea
    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(fecha);
        agregarAlarma(alarma);
    }

    // completar() marca la tarea como completa
    public void completar(){
        this.completa = true;
    }

    // estaCompleta devuelve true si la tarea ha sido completada, false en caso contrario
    public boolean estaCompleta(){
        return this.completa;
    }

    // setFecha modifica la fecha y hora de la tarea
    public void setFecha(LocalDateTime fechaNueva) {
        this.fecha = fechaNueva;
    }

    // setFecha modifica la fecha de la tarea
    public void setFecha(LocalDate fechaNueva) {
        this.fecha = LocalDateTime.of(fechaNueva, fecha.toLocalTime());
    }

}

