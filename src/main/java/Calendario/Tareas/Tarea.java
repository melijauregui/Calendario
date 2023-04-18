package Calendario.Tareas;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends Actividad {
    private LocalDateTime fecha;
    private boolean completa;

    // de día completo
    public Tarea(String titulo, String descripcion, LocalDate fecha){
        super(titulo, descripcion);
        this.fecha =  LocalDateTime.of(fecha, LocalTime.of(0, 0));
    }
    // tiene fecha y hora
    public Tarea(String titulo, String descripcion, LocalDateTime fecha){
        super(titulo, descripcion);
        this.fecha = fecha;
    }

    /* esDiaCompleto devuelve true si la tarea es de día completo, false en caso contrario --> usar en la
    etapa 3 porque se muestra distinto */
    public boolean esDiaCompleto(){
        return (fecha.getHour() == 0 && fecha.getMinute() == 0);
    }

    // EstaEnElIntervalo devuelve true si la tarea se encuentra dentro del intervalo dado
    public boolean EstaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return this.fecha.isAfter(desde) && this.fecha.isBefore(hasta);
    }

    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(fecha);
        setAlarma(alarma);
    }

    public void completar(){
        this.completa = true;
    }

    public boolean estaCompleta(){
        return this.estaCompleta();
    }

    public LocalDateTime getFechaInicio(){
        return fecha;
    }

}

