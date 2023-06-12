package Calendario.Actividad;

import java.io.IOException;
import java.io.Serializable;

public abstract class ActividadMutable extends Actividad implements Serializable {

    private String titulo;
    private String descripcion;

    public ActividadMutable(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public ActividadMutable(){

    }

    /**
     * Devuelve el título de la ActividadMutable
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la descripción de la ActividadMutable
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia el título de la ActividadMutable
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Cambia la descripción de la ActividadMutable
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Acepta un Visitor
     */
    public abstract void aceptarVisitor(ActividadVisitor actividadVisitor);

}
