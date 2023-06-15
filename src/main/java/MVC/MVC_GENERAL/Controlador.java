package MVC.MVC_GENERAL;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadVisitor;
import Calendario.Alarmas.Alarma;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Main.Calendario;
import Calendario.Tareas.Tarea;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;


import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Controlador  {
    private Vista vista;
    private Calendario calendario;
    private Set<Alarma> alarmasTareas = new HashSet<>();
    private Map<Evento, Set<Alarma>>  alarmasProximasEventos = new HashMap<>();
    private Map<Alarma, Timer> timers = new HashMap<>();
    public Controlador(Calendario calendario, Vista vista){
        this.calendario = calendario;
        this.vista = vista;
    }
    public void start(){
        setTimers();
        vista.registrarEscuchaRango(actionEvent -> {
            vista.setTipoRango(vista.getEscuchaRango());
            verActividad();
        });
        vista.registrarEscuchaSiguiente(actionEvent -> {vista.getEscuchaSiguiente();
            verActividad();
        });
        vista.registrarEscuchaAnterior(actionEvent -> {vista.getEscuchaAnterior();
            verActividad();
        });

        vista.registrarEscuchaCrearTarea(actionEvent -> {
            try {
                vista.abrirVentanaCrearTarea();
                guardarTarea();
                vista.actualizarVistaActividades();
                verActividad();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        vista.registrarEscuchaCrearEvento(actionEvent -> {
            try {
                vista.abrirVentanaCrearEvento();
                guardarEvento();
                vista.actualizarVistaActividades();
                verActividad();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        verActividad();
    }

    /**
     * Crea una Tarea guardándola en el Calendario
     */
    private void guardarTarea() {
        TareaArgs tareaArgs = vista.getInfoTarea();
        if (tareaArgs != null){
            Tarea tarea = calendario.crearTarea(tareaArgs);
            guardarAlarma(tarea);
            alarmasTareas.addAll(tarea.getAlarmas());
            vista.eliminarTareaActual();
        }
    }

    /**
     * Crea las Alarmas de la actividad recibida, guardándolas en el Calendario
     */
    private void guardarAlarma(Actividad actividad) {
        actividad.eliminarAlarmas();
        var infoAlarmas = vista.getInfoAlarmaCreada();
        if (infoAlarmas == null){
            return;
        }
        for (List<String> infoAlarma : infoAlarmas) {
            TipoAviso aviso = getTipoAviso(infoAlarma.get(0));
            TiempoRelativo tiempoRelativo = getTiempoRelativo(infoAlarma.get(2));
            actividad.aceptarVisitor(new ActividadVisitor() {
                @Override
                public void visitarEvento(Evento evento) {
                    if (tiempoRelativo == null) {
                        calendario.agregarAlarmaEvento(evento, aviso);
                    } else {
                        int intervalo = Integer.parseInt(infoAlarma.get(1));
                        calendario.agregarAlarmaEvento(evento, intervalo, tiempoRelativo, aviso);
                    }
                    vista.eliminarEventoActual();
                    vista.setTipoRango(vista.getEscuchaRango());
                }
                @Override
                public void visitarTarea(Tarea tarea) {
                    if (tiempoRelativo == null) {
                        calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), aviso);
                    } else {
                        int intervalo = Integer.parseInt(infoAlarma.get(1));
                        calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), intervalo, tiempoRelativo, aviso);
                    }
                    vista.eliminarTareaActual();
                    vista.setTipoRango(vista.getEscuchaRango());
                }

                @Override
                public void visitarInstancia(InstanciaEvento instancia) {
                }
            });
        }
    }

    /**
     * Crea un Evento guardándolo en el Calendario
     */
    private void guardarEvento() {
        EventoArgs eventoArgs = vista.getInfoEvento();
        if (eventoArgs != null){
            Evento evento = calendario.crearEvento(eventoArgs);
            var repeticionArgs = vista.getInfoRepeticionEvento();
            if (repeticionArgs != null){
                calendario.modificarRepeticionEvento(evento, repeticionArgs);
            }
            guardarAlarma(evento);
            alarmasProximasEventos.put(evento, evento.getProximasAlarmas(LocalDateTime.now()));
            vista.eliminarEventoActual();
        }
    }

    /**
     * Devuelve el Tipo de Aviso según el String pasado. Nunca devuelve null
     */
    private TipoAviso getTipoAviso(String aviso){
        switch (aviso){
            case "Notificación" -> {return TipoAviso.NOTIFICACION;}
            case "Sonido" -> {return TipoAviso.SONIDO;}
            case "Mail" -> {return  TipoAviso.EMAIL;}
            default -> {return null;}
        }
    }

    /**
     * Devuelve el Tiempo Relativo según el String pasado. Nunca devuelve null
     */
    private TiempoRelativo getTiempoRelativo(String tiempoRelativo){
        switch (tiempoRelativo){
            case "Minutos" -> {return TiempoRelativo.MINUTOS;}
            case "Días" -> {return TiempoRelativo.DIAS;}
            case "Horas" -> {return  TiempoRelativo.HORAS;}
            case "Semanas" -> {return TiempoRelativo.SEMANAS;}
            default -> {return null;}
        }
    }

    /**
     * Si se selecciona una Actividad, se abre su vista detallada
     */
    private void verActividad(){
        switch (vista.getEscuchaRango()){
            case "Semana" -> vista.registrarEscuchaVerActividadLabel(mouseEvent -> {
                try {
                    vista.abrirVistaDetalladaSemana();
                    actualizarActividad();
                    verActividad();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            default -> {
                vista.verActividadMenu();
                }
        }
    }

    /**
     * Actualiza la información de la Actividad. La elimina si el usuario ha indicado eso
     */
    public void actualizarActividad(){
        Actividad act = vista.getActividadActual();
        if (vista.eliminarActividad()){
            eliminarActividad(act);
            vista.actualizarEliminar();
            return;
        }
        guardarAlarma(act);
    }

    /**
     * Busca en alarmasProximasEventos el Evento cuyo valor es el conjunto de alarmas paso
     */
    private Evento buscarEvento(Set<Alarma> alarmas){
        for (Evento evento: alarmasProximasEventos.keySet()){
            if (alarmasProximasEventos.get(evento).equals(alarmas)){
                return evento;
            }
        }
        return null;
    }

    /**
     * Crea un timer para cada Alarma creada por el usuario
     */
    private void setTimers(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Alarma alarma: alarmasTareas){
                    if (debeIncluirAlarma(alarma)){
                        crearTimer(alarma,crearTimerTaskTarea(alarma));
                    }
                }
                for (Set<Alarma> alarmasProximas : alarmasProximasEventos.values()){
                    for (Alarma alarma : alarmasProximas){
                        if (debeIncluirAlarma(alarma)){
                            crearTimer(alarma,crearTimerTaskEvento(alarma, buscarEvento(alarmasProximas)));
                        }
                    }
                }
            }
        };
        animationTimer.start();
    }

    /**
     * Devuelve true si la Alarma no tiene un timer y suena igual o después que la fecha actual
     */
    private boolean debeIncluirAlarma(Alarma alarma){
        return !timers.containsKey(alarma) && !alarma.suenaAntes(LocalDateTime.now());
    }

    /**
     * Crea un TimerTask para la Alarma pasada, la cual corresponde a una Tarea
     * Si la Alarma es de Notificación, le indica a la vista que  muestra una Alerta
     */
    private TimerTask crearTimerTaskTarea(Alarma alarma){
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    if (alarma.getAviso().equals(TipoAviso.NOTIFICACION)) {
                        vista.mostrarNotificacionAlarma(alarma);
                    }
                });
            }
        };
    }

    /**
     * Crea un TimerTask para la Alarma pasada, la cual corresponde a un Evento
     * Si la Alarma es de Notificación, le indica a la vista que  muestra una Alerta
     */
    private TimerTask crearTimerTaskEvento(Alarma alarma, Evento evento){
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    alarmasProximasEventos.get(evento).remove(alarma);
                    if (alarmasProximasEventos.get(evento).isEmpty()){
                        alarmasProximasEventos.put(evento, evento.getProximasAlarmas(LocalDateTime.now()));
                    }
                    if (alarma.getAviso().equals(TipoAviso.NOTIFICACION)) {
                        vista.mostrarNotificacionAlarma(alarma);
                    }
                });
            }
        };
    }

    /**
     * Crea un Timer para la Alarma, según la task pasada
     */
    private void crearTimer(Alarma alarma, TimerTask task){
        Date fecha = Date.from(alarma.getFechaAlarma().atZone(ZoneId.systemDefault()).toInstant());
        Timer timer = new java.util.Timer();
        timer.schedule(task, fecha);
        timers.put(alarma, timer);
    }

    /**
     * Elimina la actividad del calendario
     */
    private void eliminarActividad(Actividad actividad){
        actividad.aceptarVisitor(new ActividadVisitor() {
            @Override
            public void visitarEvento(Evento evento) {
                calendario.eliminarEvento(evento);
                vista.actualizarEliminar();
                vista.setTipoRango(vista.getEscuchaRango());
            }
            @Override
            public void visitarTarea(Tarea tarea) {
                calendario.eliminarTarea(tarea);
                vista.actualizarEliminar();
                vista.setTipoRango(vista.getEscuchaRango());
            }

            @Override
            public void visitarInstancia(InstanciaEvento instancia) {
            }
        });
    }

}