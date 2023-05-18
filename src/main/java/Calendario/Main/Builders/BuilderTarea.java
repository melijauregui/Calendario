package Calendario.Main.Builders;

import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class BuilderTarea {
    private Tarea tarea  = new Tarea();

    public BuilderTarea(String titulo, String descripcion, LocalDate dia){
        setInformacion(titulo, descripcion, dia);
    }
    public BuilderTarea(String titulo, String descripcion, LocalDateTime diaHora){
        setInformacion(titulo, descripcion, diaHora.toLocalDate());
        tarea.setHora(diaHora.toLocalTime());
    }
    private void setInformacion(String titulo, String descripcion, LocalDate dia){
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setDia(dia);
    }
    public Tarea crearTarea(){
        return tarea;
    }
}

