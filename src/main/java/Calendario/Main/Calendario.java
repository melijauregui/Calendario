package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
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


    /**
     * Recibe la información de un evento sin repetición. Lo crea, lo agrega al Calendario y lo devuelve
     */
    public Evento crearEvento(String titulo, String descripcion, Duracion duracion){
        Evento evento = new Evento();
        agregarInformacionEvento(evento, titulo, descripcion, duracion);
        return evento;
    }

    /**
     * Recibe la información de un evento con repetición. Lo crea, lo agrega al Calendario y lo devuelve
     */
    public Evento crearEvento(String titulo, String descripcion, Duracion duracion, Repeticion repeticion){
        Evento evento = new Evento();
        agregarInformacionEvento(evento, titulo, descripcion, duracion);
        modificarRepeticionEvento(evento, repeticion);
        return evento;
    }

    /**
     * Recibe la información de una tarea de día completo. La crea, la agreaga al calendario y la devuelve
     */
    public Tarea crearTarea(String titulo, String descripcion, LocalDate dia){
        Tarea tarea = new Tarea();
        agregarInformacionTarea(tarea, titulo, descripcion, dia);
        return tarea;
    }

    /**
     * Recibe la información de una tarea con fecha y hora de vencimiento. La crea, la agreaga al calendario
     * y la devuelve
     */
    public Tarea crearTarea(String titulo, String descripcion, LocalDateTime fecha){
        Tarea tarea = new Tarea();
        agregarInformacionTarea(tarea, titulo, descripcion, fecha.toLocalDate());
        modificarHoraTarea(tarea, fecha.toLocalTime());
        return tarea;
    }


    /**
     * Recibe un intervalo de fechas y devuelve la lista de Actividades que inician (Eventos) o vencen (Tareas) dentro
     * del mismo
     */
    public List<ActividadParticular> getActividadesEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        List<ActividadParticular> actividades = new ArrayList<>();
        actividades.addAll(getEventos(desde, hasta));
        actividades.addAll(getTareas(desde, hasta));
        return actividades;
    }

    /**
     * Dada la próxima alarma que debe sonar (tiene la fecha más próxima a la actual), obtiene todas las
     * alarmas de cada Actividad que suenan a esa misma fecha. Las guarda en un conjunto y lo devueve.
     */
    public Set<Alarma> getProximasAlarmas(){
        Set<Alarma> proximasAlarmas = new HashSet<>();
        Set<Alarma> primerasAlarmas = new HashSet<>();
        for(Actividad actividad : actividades){
            Set<Alarma> alarmas = actividad.getProximasAlarmas(fechaActual);
            if (sonMasProximas(primerasAlarmas, alarmas)){
                primerasAlarmas = alarmas;
                proximasAlarmas.clear();
            }
            if (sonMasProximas(primerasAlarmas, alarmas) || todasSonProximas(primerasAlarmas, alarmas)){
                proximasAlarmas.addAll(alarmas);
            }
        }
        return proximasAlarmas;
    }

    /**
     * Completa la tarea pasada por parámetro
     */
    public void completarTarea(Tarea tarea){
        tarea.completar();
    }

    /**
     * Cambia el título de la actividad recibida
     */
    public void modificarTitulo(Actividad actividad, String titulo){
        actividad.setTitulo(titulo);
    }

    /**
     * Cambia la descripción de la actividad recibida
     */
    public void modificarDescripcion(Actividad actividad, String descripcion){
        actividad.setDescripcion(descripcion);
    }

    /**
     * Cambia la fecha de la tarea recibida
     */
    public void modificarDiaTarea(Tarea tarea, LocalDate dia){
        tarea.setDia(dia);
    }

    /**
     * Cambia la hora de la tarea recibida
     */
    public void modificarHoraTarea(Tarea tarea, LocalTime hora){
        tarea.setHora(hora);
    }

    /**
     * Cambia la fecha de la primera instancia del evento
     */
    public void modificarFechaEvento(Evento evento, Duracion duracion){
        evento.setDuracion(duracion);
    }

    /**
     * Recibe una Alarma y se la agrega al evento dado.
     */
    public void agregarAlarmaEvento(Evento evento, AlarmaEvento alarma){
        evento.agregarAlarma(alarma);
    }
    /**
     * Recibe una Alarma y se la agrega a la tarea dada.
     */
    public void agregarAlarmaTarea(Tarea tarea, Alarma alarma){
        tarea.agregarAlarma(alarma);
    }

    /**
     * Elimina una determinada alarma de la actividad
     */
    public void eliminarAlarmaTarea(Tarea tarea, Alarma alarma){
        tarea.eliminarAlarma(alarma);
    }

    public void eliminarAlarmaEvento(Evento evento, AlarmaEvento alarma){
        evento.eliminarAlarma(alarma);
    }


    /**
     * Cambia la repetición del evento
     */
    public void modificarRepeticionEvento(Evento evento, Repeticion repeticion){
        evento.setRepeticion(repeticion);
    }

    /**
     * Saca la tarea del Calendario y borra su información
     */
    public void eliminarTarea(Tarea tarea){
        tareas.remove(tarea);
        tarea = null;
    }

    /**
     * Saca el evento del Calendario y borra su información
     */
    public void eliminarEvento(Evento evento){
        eventos.remove(evento);
        evento = null;
    }

    /**
     * Recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones)
     * de los Eventos del calendario que inician dentro del mismo
     */
    private List<InstanciaEvento> getEventos(LocalDateTime desde, LocalDateTime hasta){
        List<InstanciaEvento> proximosEventos = new ArrayList<>();
        for (Evento evento : eventos){
            var instancias = evento.getProximasRepeticiones(desde, hasta);
            if (instancias != null){
                proximosEventos.addAll(instancias);
            }
        }
        return  proximosEventos;
    }

    /**
     * Recibe un intervalo de tiempo y guarda en una lista las tareas del calendario que vencen dentro del mismo
     */
    private List<ActividadParticular> getTareas(LocalDateTime desde, LocalDateTime hasta){
        List<ActividadParticular> proximasTareas = new ArrayList<>();
        for (Tarea tarea : tareas){
            if (tarea.estaEnElIntervalo(desde, hasta)){
                proximasTareas.add(tarea);
            }
        }
        return proximasTareas;
    }

    /**
     * Recibe dos conjuntos de alarmas. Cada uno tiene distintas alarmas que suenan al mismo tiempo.
     * La función devuelve true si una alarma del conjunto OTRAS es más próxima a la fecha actual que una del conjunto
     * PRIMERAS_ALARMAS
     */
    private boolean sonMasProximas(Set<Alarma> primerasAlarmas, Set<Alarma> otras){
        if (primerasAlarmas.size() == 0){
            return true;
        }
        Alarma primerAlarma = primerasAlarmas.iterator().next();
        Alarma otra = otras.iterator().next();
        return !otra.suenaAntes(fechaActual) && otra.suenaAntes(primerAlarma);
    }

    /**
     * Recibe dos conjuntos de alarmas. Cada uno tiene distintas alarmas que suenan al mismo tiempo.
     * La función devuelve true si una alarma del conjunto OTRAS es igual de próxima a la fecha actual que una del conjunto
     * PRIMERAS_ALARMAS
     */
    private boolean todasSonProximas(Set<Alarma> primerasAlarmas, Set<Alarma> otras){
        if (primerasAlarmas.size() == 0){
            return true;
        }
        Alarma primerAlarma = primerasAlarmas.iterator().next();
        Alarma otra = otras.iterator().next();
        return primerasAlarmas.size() != 0 && otra.suenaIgual(primerAlarma);
    }


    /**
     *  Recibe una Actividad y la información de la misma. La agrega a las actividades del Calendario
     *  y modifica su título y descripción
     */
    private void agregarInformacionActividad(Actividad actividad, String titulo, String descripcion) {
        actividades.add(actividad);
        modificarTitulo(actividad, titulo);
        modificarDescripcion(actividad, descripcion);

    }

    /**
     *  Recibe un Evento y la información del mismo. Lo agrega a los eventos del Calendario y modifica
     *  su duración, título y descripción
     */
    private void agregarInformacionEvento(Evento evento, String titulo, String descripcion, Duracion duracion){
        eventos.add(evento);
        agregarInformacionActividad(evento, titulo, descripcion);
        modificarFechaEvento(evento, duracion);
    }

    /**
     * Recibe una Tarea y la información de la misma. La agrega a las tareas del calendario y modifica su día,
     * título y descripción
     */
    public void agregarInformacionTarea(Tarea tarea, String titulo, String descripcion, LocalDate dia){
        tareas.add(tarea);
        agregarInformacionActividad(tarea, titulo, descripcion);
        modificarDiaTarea(tarea, dia);
    }


}