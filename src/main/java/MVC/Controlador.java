package MVC;

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
import com.google.gson.internal.LinkedTreeMap;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Controlador  {
    private Vista vista;
    private Calendario calendario;
    private Map<Alarma, Timer> timers = new HashMap<>();
    public Controlador(Calendario calendario, Vista vista){
        this.calendario = calendario;
        this.vista = vista;
    }
    public void start(){
        getAlarmas();
        vista.registrarEscuchaFrecuencia(actionEvent -> {
            vista.tipoRango(vista.getEscuchaFrecuencia());
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

    }

    private void guardarTarea() {
        TareaArgs tareaArgs = vista.getInfoTarea();
        if (tareaArgs != null){
            Tarea tarea = calendario.crearTarea(tareaArgs);
            guardarAlarma(tarea);
            vista.eliminarTareaActual();
        }
    }
    private void guardarAlarma(Actividad actividad) {
        actividad.eliminarAlarmas();
        var infoAlarmas = vista.getInfoAlarmaCreada();
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
                        //vista.actualizarVistaActividades();
                    }
                    vista.eliminarEventoActual();
                }
                @Override
                public void visitarTarea(Tarea tarea) {
                    if (tiempoRelativo == null) {
                        calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), aviso);
                    } else {
                        int intervalo = Integer.parseInt(infoAlarma.get(1));
                        calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), intervalo, tiempoRelativo, aviso);
                        //vista.actualizarVistaActividades();
                    }
                    vista.eliminarTareaActual();
                }

                @Override
                public void visitarInstancia(InstanciaEvento instancia) {
                    if (tiempoRelativo == null) {
                        calendario.agregarAlarmaEvento(instancia, aviso);
                    } else {
                        int intervalo = Integer.parseInt(infoAlarma.get(1));
                        calendario.agregarAlarmaEvento(instancia, intervalo, tiempoRelativo, aviso);
                        //vista.actualizarVistaActividades();
                    }
                    vista.eliminarEventoActual();
                }
            });
        }
    }

    private void guardarEvento() {
        EventoArgs eventoArgs = vista.getInfoEvento();
        if (eventoArgs != null){
            Evento evento = calendario.crearEvento(eventoArgs);
            var repeticionArgs = vista.getInfoRepeticionEvento();
            if (repeticionArgs != null){
                calendario.modificarRepeticionEvento(evento, repeticionArgs);
            }
            guardarAlarma(evento);
            vista.guardarEvento(evento);
            vista.eliminarEventoActual();
        }
    }

    private TipoAviso getTipoAviso(String aviso){
        switch (aviso){
            case "Notificación" -> {return TipoAviso.NOTIFICACION;}
            case "Sonido" -> {return TipoAviso.SONIDO;}
            case "Mail" -> {return  TipoAviso.EMAIL;}
            default -> {return null;}
        }
    }

    private TiempoRelativo getTiempoRelativo(String tiempoRelativo){
        switch (tiempoRelativo){
            case "Minutos" -> {return TiempoRelativo.MINUTOS;}
            case "Días" -> {return TiempoRelativo.DIAS;}
            case "Horas" -> {return  TiempoRelativo.HORAS;}
            case "Semanas" -> {return TiempoRelativo.SEMANAS;}
            default -> {return null;}
        }
    }

    private void verActividad(){
        switch (vista.getEscuchaFrecuencia()){
            case "Semana" -> vista.registrarEscuchaVerActividadLabel(mouseEvent -> {
                try {
                    vista.abrirVistaDetalladaSemana();
                    actualizarActividad();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            default -> {
                vista.verActividadMenu();
                actualizarActividad();
        }}
    }
    public void actualizarActividad(){
        Actividad act = vista.getActividadActual();
        if (act == null){
            return;
        }
        guardarAlarma(act);
    }

    private void getAlarmas(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Set<Alarma> alarmasProximas =  calendario.getProximasAlarmas(LocalDateTime.now());
                for (Alarma alarma : alarmasProximas){
                    if(LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                                    LocalDateTime.now().getHour(), LocalDateTime.now().getMinute())
                            .equals(alarma.getFechaAlarma())){
                        if (alarma.getAviso().equals("Notificación")) {
                            vista.mostrarNotificacionAlarma(alarma);
                        }
                    }
                    /*if (timers.containsKey(alarma)){

                    }
                    continue;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(()-> {
                                if (alarma.getAviso().equals("Notificación")) {
                                    vista.mostrarNotificacionAlarma(alarma);
                                }
                            });
                        }
                    };
                    Date fecha = Date.from(alarma.getFechaAlarma().atZone(ZoneId.systemDefault()).toInstant());
                    if (!alarma.getFechaAlarma().isAfter(LocalDateTime.now())){
                        fecha = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                    }
                    Timer timer = new java.util.Timer();
                    timer.schedule(task, fecha);
                    timers.put(alarma, timer);*/
                }
            }
        };
        animationTimer.start();
    }

}