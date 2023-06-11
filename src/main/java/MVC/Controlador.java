package MVC;

import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Eventos.Evento;
import Calendario.Main.Argumentos.EventoArgs;
import Calendario.Main.Argumentos.TareaArgs;
import Calendario.Main.Calendario;
import Calendario.Tareas.Tarea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.List;

public class Controlador  {
    private Vista vista;
    private Calendario calendario;
    public Controlador(Calendario calendario, Vista vista){
        this.calendario = calendario;
        this.vista = vista;
    }
    public void start(){
        vista.registrarEscuchaFrecuencia(actionEvent -> {vista.tipoRango(vista.getEscuchaFrecuencia());});
        vista.registrarEscuchaSiguiente(actionEvent -> {vista.getEscuchaSiguiente();});
        vista.registrarEscuchaAnterior(actionEvent -> {vista.getEscuchaAnterior();});
        vista.registrarEscuchaCrearTarea(actionEvent -> {
            try {
                vista.abrirVentanaCrearTarea();
                guardarTarea();
                vista.actualizarVistaActividades();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        vista.registrarEscuchaCrearEvento(actionEvent -> {
            try {
                vista.abrirVentanaCrearEvento();
                guardarEvento();
                vista.actualizarVistaActividades();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void guardarTarea() {
        TareaArgs tareaArgs = vista.getInfoTarea();
        var infoAlarmas = vista.getInfoAlarmaCreada();
        if (tareaArgs != null){
            Tarea tarea = calendario.crearTarea(tareaArgs);
            for (List<String> infoAlarma: infoAlarmas){
                TipoAviso aviso = getTipoAviso(infoAlarma.get(0));
                int intervalo = Integer.parseInt(infoAlarma.get(1));
                TiempoRelativo tiempoRelativo = getTiempoRelativo(infoAlarma.get(2));
                if (tiempoRelativo == null){
                    calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), aviso);
                }else{
                    calendario.agregarAlarmaTarea(tarea, tarea.getFecha() , intervalo, tiempoRelativo, aviso);
                }
            }
            //vista.actualizarVistaActividades();
            vista.eliminarTareaActual();
        }
    }

    private void guardarEvento() {
        EventoArgs eventoArgs = vista.getInfoEvento();
        var infoAlarmas = vista.getInfoAlarmaCreada();
        if (eventoArgs != null){
            Evento evento = calendario.crearEvento(eventoArgs);
            for (List<String> infoAlarma: infoAlarmas){
                TipoAviso aviso = getTipoAviso(infoAlarma.get(0));
                int intervalo = Integer.parseInt(infoAlarma.get(1));
                TiempoRelativo tiempoRelativo = getTiempoRelativo(infoAlarma.get(2));
                if (tiempoRelativo == null){
                    calendario.agregarAlarmaEvento(evento, aviso);
                }else{
                    calendario.agregarAlarmaEvento(evento, intervalo, tiempoRelativo, aviso);
                }
            }
            var repeticionArgs = vista.getInfoRepeticionEvento();
            if (repeticionArgs != null){
                calendario.modificarRepeticionEvento(evento, repeticionArgs);
            }
        }
    }

    private TipoAviso getTipoAviso(String aviso){
        switch (aviso){
            case "Notificación" -> {return TipoAviso.NOTIFICACION;}
            case "Sonido" -> {return TipoAviso.SONIDO;}
            case "Email" -> {return  TipoAviso.EMAIL;}
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
}