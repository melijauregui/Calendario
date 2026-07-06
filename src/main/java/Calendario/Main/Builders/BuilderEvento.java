package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Main.Argumentos.EventoArgs;


public class BuilderEvento {
    private Evento evento  = new Evento();

    /**
     * Recibe la información de un Evento con Repetición y se la setea
     */
    public BuilderEvento(EventoArgs eventoArgs){
        setInformacion(eventoArgs.getTitulo(), eventoArgs.getDescripcion(), new BuilderDuracion(eventoArgs.getDuracion()));
        if (eventoArgs.hayRepeticion()) {
            evento.setRepeticion(new BuilderRepeticion(eventoArgs.getRepeticion()).crearRepeticion());
        }
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