package Calendario.Main.Builders;

import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class BuilderTarea {
    private Tarea tarea  = new Tarea();

    /**
     * Recibe la información de una Tarea de día completo y se la setea
     */
    public BuilderTarea(TareaArgs tareaArgs){
        setInformacion(tareaArgs.getTitulo(), tareaArgs.getDescripcion(), tareaArgs.getDia());
        if (!tareaArgs.esDiaCompleto()){
            tarea.setHora(tareaArgs.getHora());
        }
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

