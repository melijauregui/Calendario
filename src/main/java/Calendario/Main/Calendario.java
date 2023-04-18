package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;

import java.lang.reflect.Array;
import java.time.*;
import java.util.*;

public class Calendario {

    //FALTA --> modificar, marcar T como completa,  revisar principios dediseño
    // Ppios de diseño
    private List<Evento> eventos;
    private List<Tarea> tareas;
    private Alarma proximaAlarma;
    private LocalDateTime fechaActual;

    public Calendario(){
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.fechaActual = LocalDateTime.now();
    }

    // agrearTarea recibe una Tarea y la agrega al Calendario. Cambia la próxima actividad de ser necesario
    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    // agregarEvento recibe un Evento y lo agrega al Calendario. Cambia la próxima actividad de ser necesario
    public void agregarEvento(Evento evento){
        eventos.add(evento);
    }

    /* configurarAlarma recibe Alarma y se la configura a la Actividad dada.
    Cambia la próxima alarma de ser necesario */
   public void configurarAlarma(Actividad actividad, Alarma alarma){
        actividad.configurarAlarma(alarma);
        cambiarProximaAlarma(alarma);
    }

    // getProximaAlarma devuelve la próxima Alarma
   public ArrayList<Alarma> getProximasAlarmas(){
       ArrayList<Alarma> alarmasProximas = null;
       LocalDateTime fechaProxima = null;

       var alarmasEventosProximos = getAlarmasEventosProximas();
       var primerFechaEvento = alarmasEventosProximos.get(0).getFechaAlarma();

       var alarmasTareasProximas = getAlarmasTareasProximas();
       var primerFechaTarea = alarmasTareasProximas.get(0).getFechaAlarma();

       if (primerFechaTarea.isEqual(primerFechaEvento)){
           alarmasProximas.addAll(alarmasEventosProximos);
           alarmasProximas.addAll(alarmasTareasProximas);
       }else {
           alarmasProximas.addAll(primerFechaEvento.isBefore(primerFechaTarea) ? alarmasEventosProximos : alarmasTareasProximas);
       }
       return alarmasProximas;
    }

    public ArrayList<Alarma> getAlarmasEventosProximas() {
        ArrayList<Alarma> AlarmasEventosProximos = null;
        LocalDateTime fechaProxima = null;

        for (Evento evento : eventos) {
            var proximaAlarma = evento.getProximaAlarma(fechaActual);
            if (fechaProxima == null ||
                    proximaAlarma.getFechaAlarma().isBefore(fechaProxima)) {
                AlarmasEventosProximos = new ArrayList<>();
                AlarmasEventosProximos.add(proximaAlarma);
            } else if (proximaAlarma.getFechaAlarma().isEqual(fechaProxima)) {
                AlarmasEventosProximos.add(proximaAlarma);
            }
        }
        return AlarmasEventosProximos;
    }

    public ArrayList<Alarma> getAlarmasTareasProximas(){
        ArrayList<Alarma> alarmaTareasProximas = null;
        LocalDateTime fechaProxima = null;

        for (Tarea tarea : tareas) {
            var alarmaTarea = tarea.getAlarma();
            if (fechaProxima == null || alarmaTarea.getFechaAlarma().isBefore(fechaProxima)){
                alarmaTareasProximas = new ArrayList<>();
                alarmaTareasProximas.add(alarmaTarea);
            } else if (tarea.getFechaInicio().isEqual(fechaProxima)) {
                alarmaTareasProximas.add(alarmaTarea);
            }
        }
        return alarmaTareasProximas;
    }

    public Set<Actividad> ProximasActividades() {
        Set<Actividad> actividadesProximas = null;
        LocalDateTime fechaProxima = null;

        var eventosProximos = getEventosProximos();
        var primerFechaEvento = eventosProximos.get(0).getFechaInicio();

        var tareasProximas = getTareasProximas();
        var primerFechaTarea = tareasProximas.get(0).getFechaInicio();

        if (primerFechaTarea.isEqual(primerFechaEvento)){
            actividadesProximas.addAll(eventosProximos);
            actividadesProximas.addAll(tareasProximas);
        }else {
            actividadesProximas.addAll(primerFechaEvento.isBefore(primerFechaTarea) ? eventosProximos : tareasProximas);
        }
        return actividadesProximas;
    }

    public ArrayList<InstanciaEvento> getEventosProximos(){
       ArrayList<InstanciaEvento> eventosProximos = null;
        LocalDateTime fechaProxima = null;

        for (Evento evento : eventos){
            var proximoEvento = evento.getProximaRepeticion(fechaActual);
            if (fechaProxima == null ||
                    proximoEvento.getFechaInicio().isBefore(fechaProxima)){
                eventosProximos = new ArrayList<>();
                eventosProximos .add(proximoEvento);
            } else if (proximoEvento.getFechaInicio().isEqual(fechaProxima)){
                eventosProximos .add(proximoEvento);
            }
        }
        return eventosProximos;
    }

    public ArrayList<Tarea> getTareasProximas(){
        ArrayList<Tarea> tareasProximas = null;
        LocalDateTime fechaProxima = null;

        for (Tarea tarea : tareas) {
            if (fechaProxima == null || tarea.getFechaInicio().isBefore(fechaProxima)){
                tareasProximas = new ArrayList<>();
                tareasProximas.add(tarea);
            } else if (tarea.getFechaInicio().isEqual(fechaProxima)) {
                tareasProximas.add(tarea);
            }
        }
        return tareasProximas;
    }


    // getActividades recibe un intervalo de fechas y devuelve la lista de Actividades dentro del mismo
    public Set<Actividad> getActividades(LocalDateTime desde, LocalDateTime hasta){
        Set<Actividad> actividades = new HashSet<>();
        getEventos(desde, hasta, actividades);
        getTareas(desde, hasta, actividades);
        return actividades;
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

    /* cambiarProximaAlarma recibe una Alarma. Si la Alarma suena antes que la 'proximaAlarma',
    cambia ese atributo*/
   private void cambiarProximaAlarma(Alarma alarma){
        if (proximaAlarma == null || alarma.suenaAntes(proximaAlarma)){
            this.proximaAlarma = alarma;
        }
    }


    /* getEventos recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones)
    de los Eventos que se encuentran dentro del mismo */
    private void getEventos(LocalDateTime desde, LocalDateTime hasta, Set<Actividad> actividades){
        for (Evento evento : eventos){
            var instancia = evento.getProximaRepeticion(desde);
            while(instancia != null && instancia.EstaEnElIntervalo(desde, hasta)){
                actividades.add(instancia);
                instancia = evento.getProximaRepeticion(instancia.getFechaInicio());
            }
        }
    }

    // getTareas recibe un intervalo de tiempo y guarda en una lista las tareas que se encuentran dentro del mismo
    private void getTareas(LocalDateTime desde, LocalDateTime hasta, Set<Actividad> actividades){
        for (Tarea tarea : tareas){
            if (tarea.EstaEnElIntervalo(desde, hasta) && !tarea.estaCompleta()){
                actividades.add(tarea);
            }
        }
    }

}
