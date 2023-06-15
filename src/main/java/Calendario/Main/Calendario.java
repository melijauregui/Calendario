package Calendario.Main;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadMutable;
import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Argumentos.DuracionArgs;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.RepeticionArgs;
import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Main.Builders.*;
import Calendario.Tareas.Tarea;
import java.io.*;
import java.time.*;
import java.util.*;

public class Calendario implements Serializable {
    private Set<Evento> eventos;
    private Set<Tarea> tareas;
    private Set<ActividadMutable> actividades;

    public Calendario(){
        this.eventos = new HashSet<>();
        this.tareas = new HashSet<>();
        this.actividades = new HashSet<>();
    }

    //CREACIÓN DE ACTIVIDADES

    /**
     * Recibe la información de un Evento con fecha y hora, y los datos de una Repetición con fecha de vencimiento.
     * Crea el Evento a partir de los Builders 'Duracion, Repeticion y Evento' correspondientes y lo devuelve
     */
    public Evento crearEvento(EventoArgs eventoArgs){
        return crearEvento(new BuilderEvento(eventoArgs));
    }

    /**
     * Recibe la información de una Tarea de día completo. Usa un BuilderTarea para crearla, la agrega al Calendario y la devuelve
     */
    public Tarea crearTarea(TareaArgs tareaArgs){
        return crearTarea(new BuilderTarea(tareaArgs));
    }


    //GETTERS DE ACTIVIDADES
    /**
     * Recibe un intervalo de fechas y devuelve la lista de Actividades que inician (InstanciaEventos) o vencen (Tareas) dentro
     * del mismo
     */
    public List<Actividad> getActividadesEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        List<Actividad> actividadesProximas = new ArrayList<>();
        actividadesProximas.addAll(getInstanciasEventos(desde, hasta));
        actividadesProximas.addAll(getTareas(desde, hasta));
        return actividadesProximas;
    }


    /**
     * Dada la próxima alarma que debe sonar (tiene la fecha más próxima a la pasada por parámetro), obtiene todas las
     * alarmas de cada Actividad que suenan a esa misma fecha. Las guarda en un conjunto y lo devueve.
     */
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        Set<Alarma> proximasAlarmas = new HashSet<>();
        Set<Alarma> primerasAlarmas = new HashSet<>();
        for(Actividad actividad : actividades){
            Set<Alarma> alarmas = actividad.getProximasAlarmas(fecha);
            if (debeIncluirAlarma(proximasAlarmas, primerasAlarmas,alarmas, fecha)){
                primerasAlarmas = alarmas;
                proximasAlarmas.addAll(alarmas);
            }
        }
        return proximasAlarmas;
    }


    //AGREGAR ALARMAS ACTIVIDADES

    /**
     * Recibe la información de una AlarmaEvento con tiempo relativo. La crea a partir del BuilderAlarmaEvento
     * correspondiente, se la agrega al Evento y la devuelve
     */
    public  AlarmaEvento agregarAlarmaEvento(Evento evento, int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        BuilderAlarmaEvento builderAlarmaEvento = new BuilderAlarmaEvento(intervalo, tiempoRelativo, aviso);
        return agregarAlarmaEvento(evento, builderAlarmaEvento);
    }

    /**
     * Recibe la información de una AlarmaEvento sin tiempo relativo. La crea a partir del BuilderAlarmaEvento
     * correspondiente, se la agrega al Evento y la devuelve
     */
    public  AlarmaEvento agregarAlarmaEvento(Evento evento, TipoAviso aviso){
        BuilderAlarmaEvento builderAlarmaEvento = new BuilderAlarmaEvento(aviso);
        return agregarAlarmaEvento(evento, builderAlarmaEvento);
    }

    /**
     * Recibe la información de una Alarma con tiempo relativo. La crea a partir del BuilderAlarma
     * correspondiente, se la agrega a la Tarea y la devuelve
     */
    public Alarma agregarAlarmaTarea(Tarea tarea, LocalDateTime fecha ,int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        BuilderAlarma builderAlarma = new BuilderAlarma(fecha, intervalo, tiempoRelativo, aviso);
        return agregarAlarmaTarea(tarea, builderAlarma);
    }

    /**
     * Recibe la información de una Alarma con fecha absoluta. La crea a partir del BuilderAlarma
     * correspondiente, se la agrega a la Tarea y la devuelve
     */
    public Alarma agregarAlarmaTarea(Tarea tarea, LocalDateTime fecha ,TipoAviso aviso){
        BuilderAlarma builderAlarma = new BuilderAlarma(fecha, aviso);
        return agregarAlarmaTarea(tarea, builderAlarma);
    }



    //MODIFICAR ACTIVIDADES
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
    public void modificarFechaEvento(Evento evento, DuracionArgs duracionArgs){
        BuilderDuracion builderDuracion = new BuilderDuracion(duracionArgs);
        evento.setDuracion(builderDuracion.crearDuracion());
    }


    /**
     * Recibe la información de una AlarmaEvento con tiempo relativo y la crea a partir del BuilderAlarmaEvento
     * correspondiente. La intercambia por alarmaVieja y la devuelve
     */
    public AlarmaEvento modificarAlarmaEvento(Evento evento, AlarmaEvento alarmaVieja, int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        BuilderAlarmaEvento builderAlarmaEvento = new BuilderAlarmaEvento(intervalo, tiempoRelativo, aviso);
        return modificarAlarmaEvento(evento, alarmaVieja, builderAlarmaEvento);
    }

    /**
     * Recibe la información de una AlarmaEvento sin tiempo relativo y la crea a partir del BuilderAlarmaEvento
     * correspondiente. La intercambia por alarmaVieja y la devuelve
     */
    public AlarmaEvento modificarAlarmaEvento(Evento evento, AlarmaEvento alarmaVieja, TipoAviso aviso){
        BuilderAlarmaEvento builderAlarmaEvento = new BuilderAlarmaEvento(aviso);
        return modificarAlarmaEvento(evento, alarmaVieja, builderAlarmaEvento);
    }

    /**
     * Recibe la información de una Alarma con tiempo relativo y la crea a partir del BuilderAlarma
     * correspondiente. La intercambia por alarmaVieja y la devuelve
     */
    public Alarma modificarAlarmaTarea(Tarea tarea, Alarma alarmaVieja, LocalDateTime fecha, int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        BuilderAlarma builderAlarma = new BuilderAlarma(fecha,intervalo, tiempoRelativo, aviso);
        return modificarAlarmaTarea(tarea, alarmaVieja, builderAlarma);
    }

    /**
     * Recibe la información de una Alarma con tiempo relativo y la crea a partir del BuilderAlarma
     * correspondiente. La intercambia por alarmaVieja y la devuelve
     */
    public Alarma modificarAlarmaTarea(Tarea tarea, Alarma alarmaVieja, LocalDateTime fecha, TipoAviso aviso){
        BuilderAlarma builderAlarma = new BuilderAlarma(fecha,aviso);
        return modificarAlarmaTarea(tarea, alarmaVieja, builderAlarma);
    }

    /**
     * Recibe la información de una Repetición con fecha de vencimiento, la crea a partir del BuilderRepeticion
     * correspondiente y se la setea al Evento
     */
    public void modificarRepeticionEvento(Evento evento, RepeticionArgs repeticionArgs){
        BuilderRepeticion builderRepeticion = new BuilderRepeticion(repeticionArgs);
        evento.setRepeticion(builderRepeticion.crearRepeticion());
    }


    //ELIMINAR ACTIVIDADES Y/O ALARMAS
    /**
     * Saca la tarea del Calendario y borra su información
     */
    public void eliminarTarea(Tarea tarea){
        actividades.remove(tarea);
        tareas.remove(tarea);
    }

    /**
     * Saca el evento del Calendario y borra su información
     */
    public void eliminarEvento(Evento evento){
        actividades.remove(evento);
        eventos.remove(evento);
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


    //SERIALIZACIÓN
    /**
     * Reconstruye el Calendario a partir de la secuencia de bytes pasada. Devuelve un nuevo Calendario con
     * la misma información
     */
    public static Calendario deserializar(InputStream bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInStream = new ObjectInputStream(bytes);
        Calendario calendario = (Calendario) objectInStream.readObject();
        objectInStream.close();
        return calendario;
    }

    /**
     * Guarda el estado actual del Calendario.
     */
    public void serializar(OutputStream bytes) throws IOException {
        ObjectOutputStream objectOutStream = new ObjectOutputStream(bytes);
        objectOutStream.writeObject(this);
        objectOutStream.flush();
        objectOutStream.close();
    }

    //MÉTODOS PRIVADOS ACTIVIDADES
    /**
     * Recibe un BuilderEvento. Crea un Evento a partir de éste, lo agrega al Calendario y lo devuelve
     */
    private Evento crearEvento(BuilderEvento builderEvento){
        Evento evento = builderEvento.crearEvento();
        eventos.add(evento);
        actividades.add(evento);
        return evento;
    }

    /**
     * Recibe un BuilderTarea. Crea una Tarea a partir de éste, la agrega al Calendario y la devuelve
     */
    private Tarea crearTarea(BuilderTarea builderTarea){
        Tarea tarea = builderTarea.crearTarea();
        tareas.add(tarea);
        actividades.add(tarea);
        return tarea;
    }

    /**
     * Recibe un intervalo de tiempo y guarda en una lista las instancias (repeticiones)
     * de los Eventos del calendario que inician dentro del mismo
     */
    private List<InstanciaEvento> getInstanciasEventos(LocalDateTime desde, LocalDateTime hasta){
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
    private List<Tarea> getTareas(LocalDateTime desde, LocalDateTime hasta){
        List<Tarea> proximasTareas = new ArrayList<>();
        for (Tarea tarea : tareas){
            if (tarea.estaEnElIntervalo(desde, hasta)){
                proximasTareas.add(tarea);
            }
        }
        return proximasTareas;
    }
    //METODOS PARA ALARMAS PRIVADOS

    /**
     * Recibe un BuilderAlarmaEvento. Crea la AlarmaEvento a partir de éste, se la agrega al Evento y la
     * devuelve
     */
    private AlarmaEvento agregarAlarmaEvento(Evento evento, BuilderAlarmaEvento builderAlarmaEvento){
        var alarmaEvento = builderAlarmaEvento.crearAlarmaEvento();
        evento.agregarAlarma(alarmaEvento);
        return alarmaEvento;
    }

    /**
     * Recibe un BuilderAlarma. Crea la Alarma a partir de éste, se la agrega a la Tarea y la
     * devuelve
     */
    private Alarma agregarAlarmaTarea(Tarea tarea, BuilderAlarma builderAlarma){
        var alarma = builderAlarma.crearAlarma();
        tarea.agregarAlarma(alarma);
        alarma.setTituloAlarma(tarea.getTitulo());
        alarma.setDescripcionAlarma(tarea.getDescripcion());
        return alarma;
    }


    /**
     * Recibe una alarma existente y un BuilderAlarmaEvento que crea una nueva alarma. Elimina la alarma vieja y agrega la nueva.
     */
    private AlarmaEvento modificarAlarmaEvento(Evento evento, AlarmaEvento alarmaVieja, BuilderAlarmaEvento builderAlarmaEvento){
        evento.eliminarAlarma(alarmaVieja);
        return agregarAlarmaEvento(evento, builderAlarmaEvento);
    }

    /**
     * Recibe una alarma existente y un BuilderAlarma que crea una nueva alarma. Elimina la alarma vieja y agrega la nueva.
     */
    private Alarma modificarAlarmaTarea(Tarea tarea, Alarma alarmaVieja, BuilderAlarma builderAlarma){
        tarea.eliminarAlarma(alarmaVieja);
        return agregarAlarmaTarea(tarea, builderAlarma);
    }

    /**
     * Recibe dos conjuntos de alarmas. Cada uno tiene distintas alarmas que suenan al mismo tiempo.
     * Devuelve true si una alarma del conjunto OTRAS es más próxima a la fecha pasada que una del conjunto
     * PRIMERAS_ALARMAS
     */
    private boolean esMasProxima(Alarma primerAlarma, Alarma otra, LocalDateTime fecha){
        return !otra.suenaAntes(fecha) && otra.suenaAntes(primerAlarma);
    }

    /**
     * Recibe dos conjuntos de alarmas. Cada uno tiene distintas alarmas que suenan al mismo tiempo.
     * Devuelve true si una alarma del conjunto OTRAS es más próxima a la fecha pasada que una del conjunto
     * PRIMERAS_ALARMAS, o si suenan al mismo tiempo
     */
    private boolean debeIncluirAlarma(Set<Alarma> proximasAlarmas, Set<Alarma> primerasAlarmas, Set<Alarma> otras, LocalDateTime fecha){
        if (primerasAlarmas.size() == 0){
            return true;
        }
        if (otras.size() == 0){
            return false;
        }
        Alarma primerAlarma = primerasAlarmas.iterator().next();
        Alarma otra = otras.iterator().next();
        if (esMasProxima(primerAlarma, otra, fecha)){
            proximasAlarmas.clear();
            return true;
        }
        return (ambasSonProximas(primerAlarma, otra));

    }

    /**
     * Recibe dos conjuntos de alarmas. Cada uno tiene distintas alarmas que suenan al mismo tiempo.
     * Devuelve true si una alarma del conjunto OTRAS es igual de próxima a la fecha pasada que una del conjunto
     * PRIMERAS_ALARMAS
     */
    private boolean ambasSonProximas(Alarma primerAlarma, Alarma otra){
        return otra.suenaIgual(primerAlarma);
    }



}