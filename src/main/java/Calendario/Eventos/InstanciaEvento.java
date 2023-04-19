package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Duracion.Duracion;
import Calendario.Main.Actividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InstanciaEvento extends Actividad{
    Duracion duracion;
    public InstanciaEvento(String titulo, String descripcion,
                           LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        super(titulo,descripcion);
        this.duracion = new Duracion(fechaInicio, fechaFin);
    }

    public InstanciaEvento(String titulo, String descripcion,
                           LocalDate fechaInicio, LocalDate fechaFin) {
        super(titulo,descripcion);
        this.duracion = new Duracion(fechaInicio, fechaFin);
    }

    // empiezaDespues devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
    public boolean empiezaDespues(LocalDateTime fecha){
        return this.getFechaInicio().isAfter(fecha);
    }

    public LocalDateTime getFechaInicio(){
        return this.duracion.getFechaInicio();
    }
    public LocalDateTime getFechaFin(){
        return this.duracion.getFechaFin();
    }

    // estaEnElIntervalo devuelve true si la instancia se encuentra dentro del intervalo definido
    // por las fechas pasadas por parámetro
    public boolean estaEnElIntervalo(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return (fechaInicio.isBefore(getFechaInicio()) && fechaFin.isAfter(getFechaInicio()));
    }
    // configurarAlarma le agrega a la Instancia la alarma pasada
    public void configurarAlarma(Alarma alarma){
        if (alarma != null){
            agregarAlarma(alarma);
        }
    }

    // configurarAlarmas configura todas las alarmas recibidas por parámetro
    public void configurarAlarmas(Set<Alarma> alarmas){
        for (Alarma alarma : alarmas){
            configurarAlarma(alarma);
        }
    }


}
