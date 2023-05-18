package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Main.Builders.BuilderRepeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class BuilderEvento {
    private Evento evento  = new Evento();

    public BuilderEvento(String titulo, String descripcion, Duracion duracion, BuilderRepeticion builderRepeticion){
        setInformacion(titulo, descripcion, duracion);
        evento.setRepeticion(builderRepeticion.crearRepeticion());
    }
    public BuilderEvento(String titulo, String descripcion, Duracion duracion){
        setInformacion(titulo, descripcion, duracion);
    }
    private void setInformacion(String titulo, String descripcion, Duracion duracion){
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);
        evento.setDuracion(duracion);
    }
    public Evento crearEvento(){
        return evento;
    }
}
