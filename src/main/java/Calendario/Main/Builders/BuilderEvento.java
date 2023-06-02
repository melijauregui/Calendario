package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;


public class BuilderEvento {
    private Evento evento  = new Evento();

    /**
     * Recibe la información de un Evento con Repetición y se la setea
     */
    public BuilderEvento(String titulo, String descripcion, BuilderDuracion builderDuracion, BuilderRepeticion builderRepeticion){
        setInformacion(titulo, descripcion, builderDuracion);
        evento.setRepeticion(builderRepeticion.crearRepeticion());
    }
    public BuilderEvento(String titulo, String descripcion, BuilderDuracion builderDuracion){
        setInformacion(titulo, descripcion, builderDuracion);
    }

    /**
     * Devuelve el Evento creado
     */
    public Evento crearEvento(){
        return evento;
    }

    /**
     * Modifica el título, la descripción y la duración del Evento
     */
    private void setInformacion(String titulo, String descripcion, BuilderDuracion builderDuracion){
        Duracion duracion = builderDuracion.crearDuracion();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);
        evento.setDuracion(duracion);
    }

}