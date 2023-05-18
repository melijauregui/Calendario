package Calendario.Main.Builders;

import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class BuilderTarea {
    private Tarea tarea  = new Tarea();

    /**
     * Recibe la información de una Tarea de día completo y se la setea
     */
    public BuilderTarea(String titulo, String descripcion, LocalDate dia){
        setInformacion(titulo, descripcion, dia);
    }

    /**
     * Recibe la información de una Tarea con fecha y hora, y se la setea
     */
    public BuilderTarea(String titulo, String descripcion, LocalDateTime diaHora){
        setInformacion(titulo, descripcion, diaHora.toLocalDate());
        tarea.setHora(diaHora.toLocalTime());
    }

    /**
     * Devuelve la Tarea creada
     */
    public Tarea crearTarea(){
        return tarea;
    }

    /**
     * Modifica el título, la descripción y la fecha de la Tarea
     */
    private void setInformacion(String titulo, String descripcion, LocalDate dia){
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setDia(dia);
    }
}

