package Calendario.Main;

import Calendario.Alarmas.Alarma;
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

   private boolean sonMasProximas(Set<Alarma> primerasAlarmas, Set<Alarma> otras){
       Alarma primerAlarma = primerasAlarmas.iterator().next();
       Alarma otra = otras.iterator().next();
       return !otra.suenaAntes(fechaActual) && (primerasAlarmas.size() == 0 || otra.suenaAntes(primerAlarma));
   }
   private boolean todasSonProximas(Set<Alarma> primerasAlarmas, Set<Alarma> otras){//return primerAlarma != null && otra.suenaIgual(primerAlarma);
       Alarma primerAlarma = primerasAlarmas.iterator().next();
       Alarma otra = otras.iterator().next();
       return primerasAlarmas.size() != 0 && otra.suenaIgual(primerAlarma);
   }

   // completarTarea completa la tarea pasada por parámetro
    public void completarTarea(Tarea tarea){
        tarea.completar();
    }

    // modificarTitulo cambia el título de la actividad recibida
    public void modificarTitulo(Actividad actividad, String titulo){
        actividad.setTitulo(titulo);
    }

    // modificarDescripcion cambia la descripción de la actividad recibida
    public void modificarDescripcion(Actividad actividad, String descripcion){
        actividad.setDescripcion(descripcion);
    }

    // modificarFechaTarea cambia la fecha de la tarea recibida
    public void modificarDia(Tarea tarea, LocalDate dia){
        tarea.setDia(dia);
    }

    public void modificarHora(Tarea tarea, LocalTime hora){
        tarea.setHora(hora);
    }


    // modificarFechaInicioEvento cambia la fecha de inicio del evento de día completo
    public void modificarFechaInicioEvento(Evento evento, InstanciaEvento eventoInicial){
        evento.setEventoInicial(eventoInicial);
    }


    /* configurarAlarma recibe Alarma y se la agrega a la Actividad dada. */
    public void configurarAlarma(Actividad actividad, Alarma alarma){
        actividad.configurarAlarma(alarma);
    }

    // modificarAlarma cambia una determinada alarma de la actividad
    public void modificarAlarma(Actividad actividad, Alarma anterior, Alarma nueva){
        actividad.eliminarAlarma(anterior);
        actividad.configurarAlarma(nueva);
    }

    // eliminarAlarma elimina una determinada alarma de la actividad
    public void eliminarAlarma(Actividad actividad, Alarma alarma){
        actividad.eliminarAlarma(alarma);
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
            while(instancia != null){
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

}
