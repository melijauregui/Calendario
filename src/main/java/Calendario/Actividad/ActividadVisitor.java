package Calendario.Actividad;

import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;

public interface ActividadVisitor {
    void visitarEvento(Evento evento);
    void visitarTarea(Tarea tarea);
    void visitarInstancia(InstanciaEvento instancia);
}
