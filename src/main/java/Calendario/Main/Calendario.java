package Calendario.Main;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadMutable;
import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Builders.*;
import Calendario.Repeticiones.Repeticion;
import Calendario.Tareas.Tarea;

import java.time.*;
import java.util.*;

public class Calendario {
    private Set<Evento> eventos;
    private Set<Tarea> tareas;
    private Set<ActividadMutable> actividades;
    private LocalDateTime fechaActual;

    public Calendario(){
        this.eventos = new HashSet<>();
        this.tareas = new HashSet<>();
        this.actividades = new HashSet<>();
        this.fechaActual = LocalDateTime.now();
    }

    /**
     * Recibe un BuilderEvento. Crea un Evento a partir de éste, lo agrega al Calendario y lo devuelve
     */
    public Evento crearEvento(BuilderEvento builderEvento){
        Evento evento = builderEvento.crearEvento();
        eventos.add(evento);
        actividades.add(evento);
        return evento;
    }

    /**
     * Recibe un BuilderTarea. Crea una Tarea a partir de éste, la agrega al Calendario y la devuelve
     */
    public Tarea crearTarea(BuilderTarea builderTarea){
        Tarea tarea = builderTarea.crearTarea();
        tareas.add(tarea);
        actividades.add(tarea);
        return tarea;
    }


    /**
     * Recibe un intervalo de fechas y devuelve la lista de Actividades que inician (Eventos) o vencen (Tareas) dentro
     * del mismo
     */
    public List<Actividad> getActividadesEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        List<Actividad> actividadesProximas = new ArrayList<>();
        actividadesProximas.addAll(getEventos(desde, hasta));
        actividadesProximas.addAll(getTareas(desde, hasta));
        return actividadesProximas;
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
    public void modificarTitulo(ActividadMutable actividad, String titulo){
        actividad.setTitulo(titulo);
    }

    /**
     * Cambia la descripción de la actividad recibida
     */
    public void modificarDescripcion(ActividadMutable actividad, String descripcion){
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
     * Cambia la fecha la duración del evento
     */
    public void modificarFechaEvento(Evento evento, Duracion duracion){
        evento.setDuracion(duracion);
    }

    /**
     * Recibe un BuilderAlarmaEvento. Crea la AlarmaEvento a partir de éste, se la agrega al Evento y la
     * devuelve
     */
    public AlarmaEvento agregarAlarmaEvento(Evento evento, BuilderAlarmaEvento builderAlarmaEvento){
        var alarmaEvento = builderAlarmaEvento.CrearAlarmaEvento();
        evento.agregarAlarma(alarmaEvento);
        return alarmaEvento;
    }

    /**
     * Recibe un BuilderAlarma. Crea la Alarma a partir de éste, se la agrega a la Tarea y la
     * devuelve
     */
    public Alarma agregarAlarmaTarea(Tarea tarea, BuilderAlarma builderAlarma){
        var alarma = builderAlarma.CrearAlarma();
        tarea.agregarAlarma(alarma);
        return alarma;
    }

    /**
     * Recibe una alarma existente y un BuilderAlarmaEvento que crea una nueva alarma. Elimina la alarma vieja y agrega la nueva.
     */
    public AlarmaEvento modificarAlarmaEvento(Evento evento, AlarmaEvento alarmaVieja, BuilderAlarmaEvento builderAlarmaEvento){
        evento.eliminarAlarma(alarmaVieja);
        return agregarAlarmaEvento(evento, builderAlarmaEvento);
    }

    /**
     * Recibe una alarma existente y un BuilderAlarma que crea una nueva alarma. Elimina la alarma vieja y agrega la nueva.
     */
    public Alarma modificarAlarmaTarea(Tarea tarea, Alarma alarmaVieja, BuilderAlarma builderAlarma){
        tarea.eliminarAlarma(alarmaVieja);
        return agregarAlarmaTarea(tarea, builderAlarma);
    }

    /**
     * Elimina una determinada alarma de la tarea
     */
    public void eliminarAlarmaTarea(Tarea tarea, Alarma alarma){
        tarea.eliminarAlarma(alarma);
    }

    /**
     * Elimina una determinada alarma del evento
     */
    public void eliminarAlarmaEvento(Evento evento, AlarmaEvento alarma){
        evento.eliminarAlarma(alarma);
    }


    /**
     * Recibe un BuilderRepeticion. Crea la Repetición a partir de éste y se la setea al Evento
     */
    public void modificarRepeticionEvento(Evento evento, BuilderRepeticion builderRepeticion){
        evento.setRepeticion(builderRepeticion.crearRepeticion());
    }

    /**
     * Saca la tarea del Calendario y borra su información
     */
    public void eliminarTarea(Tarea tarea){
        actividades.remove(tarea);
        tareas.remove(tarea);
        tarea = null; //borra la información de la tarea
    }

    /**
     * Saca el evento del Calendario y borra su información
     */
    public void eliminarEvento(Evento evento){
        actividades.remove(evento);
        eventos.remove(evento);
        evento = null;  //borra la información del evento
    }

    /**
     * Recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones)
     * de los Eventos del calendario que inician dentro del mismo
     */
    private List<InstanciaEvento> getEventos(LocalDateTime desde, LocalDateTime hasta){
        List<InstanciaEvento> proximosEventos = new ArrayList<>();
        for (Evento evento : eventos){
            var instancias = evento.getRepeticionesEnIntervalo(desde, hasta);
            if (instancias != null){
                proximosEventos.addAll(instancias);
            }
        }
        return  proximosEventos;
    }

    /**
     * Recibe un intervalo de tiempo y guarda en una lista las tareas del calendario que vencen dentro del mismo
     */
    private List<Actividad> getTareas(LocalDateTime desde, LocalDateTime hasta){
        List<Actividad> proximasTareas = new ArrayList<>();
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

}