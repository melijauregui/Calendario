package Calendario.Eventos;

import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Duracion.Duracion;
import Calendario.Actividad.Actividad;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class InstanciaEvento extends Actividad implements Serializable {
    private String titulo;
    private String descripcion;
    private Evento referenciaEvento;
    Duracion duracion;

    public InstanciaEvento(String titulo, String descripcion, Duracion duracion, Set<AlarmaEvento> alarmas){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        configurarAlarmas(alarmas);

    }

    /**
     * Devuelve el título de la Instancia
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la descripción de la Instancia
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
     */
    public boolean empiezaDespues(LocalDateTime fecha){
        return this.getFechaInicio().isAfter(fecha);
    }

    /**
     * Devuelve true si la instancia del evento comienza el mismo día y hora que la fecha recibida como argumento
     */
    public boolean empiezaIgual(LocalDateTime fecha){
        return this.getFechaInicio().isEqual(fecha);
    }

    /**
     * Devuelve true si la instancia del evento termina antes que la fecha recibida
     */
    public boolean terminaAntes(LocalDateTime fecha){return  this.getFechaFin().isBefore(fecha);}
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
     * Acepta un Visitor
     */
    public void aceptarVisitor(ActividadVisitor actividadVisitor){
        actividadVisitor.visitarInstancia(this);
    }

    /**
     * Guarda la referencia del evento del cual procede
     */
    public void setReferenciaEvento(Evento evento){
        this.referenciaEvento = evento;
    }

    /**
     * Devuelve la referencia del evento del cual procede
     */
    public Evento getReferenciaEvento(){
        return referenciaEvento;
    }

    /**
     * Le agrega a la Instancia la alarma pasada
     */
    public void configurarAlarma(AlarmaEvento alarmaEvento){
        if (alarmaEvento != null){
            Alarma alarma = alarmaEvento.crearAlarmaInstaciaEvento(getFechaInicio());
            alarma.setTituloAlarma(titulo);
            alarma.setDescripcionAlarma(descripcion);
            super.getAlarmas().add(alarma);
        }
    }

    /**
     * Configura todas las alarmas recibidas por parámetro
     */
    private void configurarAlarmas(Set<AlarmaEvento> alarmas){
        if (alarmas != null) {
            for (AlarmaEvento alarma : alarmas) {
                configurarAlarma(alarma);
            }
        }
    }

    public boolean esDiaCompleto(){
        return duracion.esDiaCompleto();
    }

    public void eliminarAlarmas(){
        super.getAlarmas().clear();
        referenciaEvento.eliminarAlarmas();
    }
}

