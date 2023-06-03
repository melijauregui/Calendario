package Calendario.Actividad;

import Calendario.Eventos.Evento;
import Calendario.Tareas.Tarea;

public interface ActividadVisitor {
    void visitarEvento(Evento evento);
    void visitarTarea(Tarea tarea);
}
