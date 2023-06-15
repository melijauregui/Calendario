package Calendario.Actividad;

import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;

import java.io.IOException;

public interface ActividadVisitor {

    /**
     * Vistita un Evento para realizar una operación
     */
    void visitarEvento(Evento evento);

    /**
     * Vistita una Tarea para realizar una operación
     */
    void visitarTarea(Tarea tarea);

    /**
     * Vistita una Instancia de Evento para realizar una operación
     */
    void visitarInstancia(InstanciaEvento instancia);
}
