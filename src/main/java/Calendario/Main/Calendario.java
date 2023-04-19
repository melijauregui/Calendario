package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Eventos.Evento;
import Calendario.Eventos.EventoConDuracion;
import Calendario.Eventos.EventoDiaCompleto;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.Repeticion;
import Calendario.Tareas.Tarea;

import java.time.*;
import java.util.*;

public class Calendario {
    private Set<Evento> eventos;
    private Set<Tarea> tareas;
    private Set<Actividad> actividades;
    private LocalDateTime fechaActual;

    public Calendario(){
        this.eventos = new HashSet<>();
        this.tareas = new HashSet<>();
        this.actividades = new HashSet<>();
        this.fechaActual = LocalDateTime.now();
    }

    // agrearTarea recibe una Tarea y la agrega al Calendario.
    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
        actividades.add(tarea);
    }

    // agregarEvento recibe un Evento y lo agrega al Calendario.
    public void agregarEvento(Evento evento){
        eventos.add(evento);
        actividades.add(evento);
    }

    // getActividades recibe un intervalo de fechas y devuelve la lista de Actividades dentro del mismo
    public List<Actividad> getActividades(LocalDateTime desde, LocalDateTime hasta){
        List<Actividad> actividades = new ArrayList<>();
        getEventos(desde, hasta, actividades);
        getTareas(desde, hasta, actividades);
        return actividades;
    }

    // getProximasAlarmas devuelve un conjunto de alarmas que suenan a la misma fecha y hora, y son las más
    // próximas a la fecha actual
   public Set<Alarma> getProximasAlarmas(){
       Alarma primerAlarma = determinarAlarmaProxima();
       return getAlarmasMismaFecha(primerAlarma);
   }

   // completarTarea completa la tarea pasada por parámetro
    public void completarTarea(Tarea tarea){
        tarea.completar();
    }

    // modificarTituloTarea cambia el título de la tarea recibida
   public void modificarTituloTarea(Tarea tarea, String titulo){
        tarea.setTitulo(titulo);
   }

    // modificarTituloEvento cambia el título del evento recibido
    public void modificarTituloEvento(Evento evento, String titulo){
        evento.setTitulo(titulo);
    }

    // modificarDescipcionTarea cambia la descripción de la tarea recibida
    public void modificarDescipcionTarea(Tarea tarea, String descripcion){
        tarea.setDescripcion(descripcion);
    }

    // modificarDescipcionEvento cambia la descripción del evento recibido
    public void modificarDescipcionEvento(Evento evento, String descripcion){
        evento.setDescripcion(descripcion);
    }

    // modificarFechaTarea cambia la fecha de la tarea recibida
    public void modificarFechaTarea(Tarea tarea, LocalDateTime fecha){
        tarea.setFecha(fecha);
    }

    // modificarFechaTarea cambia la fecha de la tarea recibida
    public void modificarFechaTarea(Tarea tarea, LocalDate fecha){
        tarea.setFecha(fecha);
    }

    // modificarFechaInicioEvento cambia la fecha de inicio del evento de día completo
    public void modificarFechaInicioEvento(EventoDiaCompleto evento, LocalDate fecha){
        evento.setFechaInicio(fecha);
    }

    // modificarFechaFinEvento cambia la fecha de finalización del evento de día completo
    public void modificarFechaFinEvento(EventoDiaCompleto evento, LocalDate fecha){
        evento.setFechaFin(fecha);
    }

    // modificarFechaInicioEvento cambia la fecha de inicio del evento con duración
    public void modificarFechaInicioEvento(EventoConDuracion evento, LocalDateTime fecha){
        evento.setFechaInicio(fecha);
    }

    // modificarFechaFinEvento cambia la fecha de finalización del evento con duración
    public void modificarFechaFinEvento(EventoConDuracion evento, LocalDateTime fecha){
        evento.setFechaFin(fecha);
    }

    /* configurarAlarma recibe Alarma y se la agrega a la Actividad dada. */
    public void configurarAlarma(Actividad actividad, Alarma alarma){
        actividad.configurarAlarma(alarma);
    }

    // modificarAlarmaTarea cambia una determinada alarma de la tarea
    public void modificarAlarmaTarea(Tarea tarea, Alarma anterior, Alarma nueva){
        tarea.eliminarAlarma(anterior);
        tarea.configurarAlarma(nueva);
    }

    // eliminarAlarmaTarea elimina una determinada alarma de la tarea
    public void eliminarAlarmaTarea(Tarea tarea, Alarma alarma){
        tarea.eliminarAlarma(alarma);
    }

    // modificarAlarmaEvento cambia una determinada alarma del evento
    public void modificarAlarmaEvento(Evento evento, Alarma anterior, Alarma nueva){
        evento.eliminarAlarma(anterior);
        evento.configurarAlarma(nueva);
    }

    // eliminarAlarmaEvento elimina una determinada alarma del evento
    public void eliminarAlarmaEvento(Evento evento, Alarma alarma){
        evento.eliminarAlarma(alarma);
    }

    // modificarRepeticionEvento cambia la repetición del evento
    public void modificarRepeticionEvento(Evento evento, Repeticion repeticion){
        evento.setRepeticion(repeticion);
    }

    // eliminarTarea saca la tarea del Calendario y borra su información
    public void eliminarTarea(Tarea tarea){
        tareas.remove(tarea);
        tarea = null;
    }

    // eliminarEvento saca el evento del Calendario y borra su información
    public void eliminarEvento(Evento evento){
        eventos.remove(evento);
        evento = null;
    }

    /* getEventos recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones)
    de los Eventos que se encuentran dentro del mismo */
    private void getEventos(LocalDateTime desde, LocalDateTime hasta, List<Actividad> actividades){
        for (Evento evento : eventos){
            var instancia = evento.getProximaRepeticion(desde);
            while(instancia != null && instancia.estaEnElIntervalo(desde, hasta)){
                actividades.add(instancia);
                instancia = evento.getProximaRepeticion(instancia.getFechaInicio());
            }
        }
    }

    // getTareas recibe un intervalo de tiempo y guarda en una lista las tareas que se encuentran dentro del mismo
    private void getTareas(LocalDateTime desde, LocalDateTime hasta, List<Actividad> actividades){
        for (Tarea tarea : tareas){
            if (tarea.estaEnElIntervalo(desde, hasta) && !tarea.estaCompleta()){
                actividades.add(tarea);
            }
        }
    }

    // getAlarmasMismaFecha devuelve un conjunto de alarmas que suenan a la misma fecha y hora
    private Set<Alarma>  getAlarmasMismaFecha(Alarma primerAlarma){
        Set <Alarma> alarmasProximas = new HashSet<>();
        for(Actividad actividad : actividades){
            var alarmaActividad = actividad.getProximaAlarma(fechaActual);
            if (alarmaActividad.suenaIgual(primerAlarma)){
                alarmasProximas.add(alarmaActividad);
            }
        }
        return alarmasProximas;
    }

    // determinarAlarmaProxima busca en las actividades del calendario la siguiente alarma a sonar
    private Alarma determinarAlarmaProxima(){
        Alarma primerAlarma = null;
        for(Actividad actividad : actividades){
            var alarmaActividad = actividad.getProximaAlarma(fechaActual);
            if (alarmaActividad != null && (primerAlarma == null || alarmaActividad.suenaAntes(primerAlarma))){
                primerAlarma = alarmaActividad;
            }
        }
        return primerAlarma;
    }

}
