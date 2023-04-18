package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Enums.Tiempo;
import Calendario.Eventos.Evento;
import Calendario.Eventos.EventoSinRepeticion;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.Repeticion;
import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendario {

    //FALTA --> modificar, marcar T como completa,  revisar principios dediseño
    // Ppios de diseño

    private List<Evento> eventos;
    private List<Tarea> tareas;
    private Actividad proximaActividad;
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
        cambiarProximaActividad(tarea);
    }

    // agregarEvento recibe un Evento y lo agrega al Calendario. Cambia la próxima actividad de ser necesario
    public void agregarEvento(Evento evento){
        eventos.add(evento);
        cambiarProximaActividad(evento);
    }

    // configurarAlarma recibe Alarma y se la configura a la Actividad dada.
    // Cambia la próxima alarma de ser necesario
    public void configurarAlarma(Actividad actividad, Alarma alarma){
        actividad.configurarAlarma(alarma);
        cambiarProximaAlarma(alarma);
    }

    // getProximaAlarma devuelve la próxima Alarma
    public Alarma getProximaAlarma(){
        return this.proximaActividad.getAlarma();
    }

    // getProximaActividad devuelve la próxima Actividad, sea Evento o Tarea
    public Actividad getProximaActividad(){return  this.proximaActividad;}

    // getActividades recibe un intervalo de fechas y devuelve la lista de Actividades dentro del mismo
    public List<Actividad> getActividades(LocalDateTime desde, LocalDateTime hasta){
        List<Actividad> actividades = new ArrayList<>();
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

    // cambiarProximaAlarma recibe una Alarma. Si la Alarma suena antes que la
    // 'proximaAlarma', cambia ese atributo
    private void cambiarProximaAlarma(Alarma alarma){
        if (proximaAlarma == null || alarma.suenaAntes(proximaAlarma)){
            this.proximaAlarma = alarma;
        }
    }

    // cambiarProximaActividad recibe una Actividad. Si su fecha es más reciente que la de la
    // 'proximaActividad', cambia ese atributo
    private void cambiarProximaActividad(Actividad actividad){
        if (proximaActividad == null || actividad.comienzaAntes(proximaActividad)){
            this.proximaActividad = actividad;
        }
    }

    // getEventos recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones) de los Eventos
    // que se encuentran dentro del mismo
    private void getEventos(LocalDateTime desde, LocalDateTime hasta, List<Actividad> actividades){
        for (Evento evento : eventos){
            var instancia = evento.getProximaRepeticion(desde);
            while(instancia != null && instancia.estaEnElIntervalo(desde, hasta)){
                actividades.add(instancia);
                if (evento instanceof EventoSinRepeticion){
                    break;
                }
                instancia = evento.getProximaRepeticion(instancia.getFecha());
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
