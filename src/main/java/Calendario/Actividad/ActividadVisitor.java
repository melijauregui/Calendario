package Calendario.Actividad;

import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;

import java.io.IOException;

public interface ActividadVisitor {
    void visitarEvento(Evento evento);
    void visitarTarea(Tarea tarea);
    void visitarInstancia(InstanciaEvento instancia);
}
