package MVC.VistasActividades;

import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;

public interface VistaActividadVisitor {

    /**
     * Vistita una VistaEvento para realizar una operación
     */
    void visitarVistaEvento(VistaEvento vistaEvento);

    /**
     * Vistita una VistaTarea para realizar una operación
     */
    void visitarVistaTarea(VistaTarea vistaTarea);

}