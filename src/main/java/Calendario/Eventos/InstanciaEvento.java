package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Duracion.Duracion;
import Calendario.Main.Actividad;
import java.time.temporal.Temporal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InstanciaEvento extends Actividad{
    Duracion duracion;
    public InstanciaEvento() {
    }

    // setter de duración
    public void setDuracion(Duracion duracion){
        this.duracion = duracion;
    }

    // empiezaDespues devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
    public boolean empiezaDespues(LocalDateTime fecha){
        return this.getFechaInicio().isAfter(fecha);
    }

    // getter de fecha y hora de inicio
    public LocalDateTime getFechaInicio(){
        return this.duracion.getFechaInicio();
    }

    // getter de fecha y hora de finalización
    public LocalDateTime getFechaFin(){
        return this.duracion.getFechaFin();
    }

    // getter de fecha de inicio de un evento de día completo
    public LocalDate getDiaInicio(){
        return this.duracion.getDiaInicio();
    }

    // getter de fecha de finalización de un evento de día completo
    public LocalDate getDiaFin(){
        return this.duracion.getDiaFin();
    }

    // configurarAlarma le agrega a la Instancia la alarma pasada
    public void configurarAlarma(Alarma alarma){
        if (alarma != null){
            agregarAlarma(alarma);
        }
    }

    // configurarAlarmas configura todas las alarmas recibidas por parámetro
    public void configurarAlarmas(Set<Alarma> alarmas){
        if (alarmas != null) {
            for (Alarma alarma : alarmas) {
                configurarAlarma(alarma);
            }
        }
    }

    // Clone devuelve una copia de la Instancia con las nuevas fechas recibidas como argumento
    public InstanciaEvento Clone(LocalDate diaInicio, LocalDate diaFin){
        InstanciaEvento eventoClonado = new InstanciaEvento();
        eventoClonado.setTitulo(this.getTitulo());
        eventoClonado.setDescripcion(this.getDescripcion());
        Duracion nuevaDuracion = this.duracion.Clone();
        nuevaDuracion.setDiaInicio(diaInicio);
        nuevaDuracion.setDiaFin(diaFin);
        eventoClonado.setDuracion(nuevaDuracion);
        eventoClonado.configurarAlarmas(this.getAlarmas());
        return eventoClonado;
    }

}

