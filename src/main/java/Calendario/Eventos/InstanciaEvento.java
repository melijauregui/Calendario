package Calendario.Eventos;

import Calendario.Alarmas.AlarmaEvento;
import Calendario.Duracion.Duracion;
import Calendario.Main.ActividadParticular;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InstanciaEvento extends ActividadParticular {
    Duracion duracion;
    public InstanciaEvento() {
    }

    /**
     * Devuelve la duración
     */
    public void setDuracion(Duracion duracion){
        this.duracion = duracion;
    }

    /**
     * Devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
     */
    public boolean empiezaDespues(LocalDateTime fecha){
        return this.getFechaInicio().isAfter(fecha);
    }

    /**
     * Devuelve la fecha y hora de inicio
     */
    public LocalDateTime getFechaInicio(){
        return this.duracion.getFechaInicio();
    }

    /**
     * Devuelve la fecha y hora de finalización
     */
    public LocalDateTime getFechaFin(){
        return this.duracion.getFechaFin();
    }

    /**
     * Devuelve la fecha de inicio de un evento de día completo
     */
    public LocalDate getDiaInicio(){
        return this.duracion.getDiaInicio();
    }

    /**
     * Devuelve la fecha de finalización de un evento de día completo
     */
    public LocalDate getDiaFin(){
        return this.duracion.getDiaFin();
    }

    /**
     * Le agrega a la Instancia la alarma pasada
     */
    public void configurarAlarma(AlarmaEvento alarma){
        if (alarma != null){
            agregarAlarma(alarma.crearAlarmaInstaciaEvento(getFechaInicio()));
        }
    }

    /**
     * Configura todas las alarmas recibidas por parámetro
     */
    public void configurarAlarmas(Set<AlarmaEvento> alarmas){
        if (alarmas != null) {
            for (AlarmaEvento alarma : alarmas) {
                configurarAlarma(alarma);
            }
        }
    }

}

