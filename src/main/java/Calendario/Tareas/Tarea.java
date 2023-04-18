package Calendario.Tareas;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends Actividad {
    private boolean esDiaCompleto;
    private boolean completa;

    // de día completo
    public Tarea(String titulo, String descripcion, LocalDate fecha){
        super(titulo, descripcion, LocalDateTime.of(fecha, LocalTime.of(0, 0)));
        this.esDiaCompleto = true;
    }
    // tiene fecha y hora
    public Tarea(String titulo, String descripcion, LocalDateTime fechaHora){
        super(titulo, descripcion, fechaHora);
    }

    // esDiaCompleto devuelve true si la tarea es de día completo, false en caso contrario --> usar en la etapa 3
    // porque se muestra distinto
    public boolean esDiaCompleto(){
        return esDiaCompleto == true;
    }

    // estaEnElIntervalo devuelve true si la tarea se encuentra dentro del intervalo dado
    public boolean estaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return this.getFecha().isAfter(desde) && this.getFecha().isBefore(hasta);
    }

    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(getFecha());
        setAlarma(alarma);
    }

    public void completar(){
        this.completa = true;
    }

    public boolean estaCompleta(){
        return this.estaCompleta();
    }


/*    public String getFecha() {
        // con LocalDateTime
        String diaSemana = Calendario.Enums.Dia.values()[fechaHora.getDayOfWeek().getValue()].toString();
        // getDayOfWeek devuelve el valor del enum en inglés, ej SATURDAY. Lo paso a español. Debe haber alguna manera más fácil
        diaSemana = diaSemana.substring(0,1).toUpperCase() + diaSemana.substring(1).toLowerCase();// SABADO --> Sabado
        String mes = Calendario.Enums.Mes.values()[fechaHora.getMonth().getValue()].toString();
        mes = mes.substring(0,1).toUpperCase() + mes.substring(1).toLowerCase();
        return diaSemana + " " + Integer.toString(fechaHora.getDayOfYear()) + " de " + mes + " del año "+
                Integer.toString(fechaHora.getYear());
    }*/

    /*public String getHora() {
        if (esDiaCompleto){
            return null;
        }
        return Integer.toString(fechaHora.getHour()) + ":" + Integer.toString(fechaHora.getMinute());
        //return Integer.toString(hora[0])+":"+Integer.toString(hora[1]);
    }*/


}

