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

import java.io.IOException;
import java.util.List;

public class Controlador  {
    private Vista vista;
    private Calendario calendario;
    public Controlador(Calendario calendario, Vista vista){
        this.calendario = calendario;
        this.vista = vista;
    }
    public void start(){
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
        var infoAlarmas = vista.getInfoAlarmaCreada();
        if (tareaArgs != null){
            Tarea tarea = calendario.crearTarea(tareaArgs);
            for (List<String> infoAlarma: infoAlarmas){
                TipoAviso aviso = getTipoAviso(infoAlarma.get(0));
                TiempoRelativo tiempoRelativo = getTiempoRelativo(infoAlarma.get(2));
                if (tiempoRelativo == null){
                    calendario.agregarAlarmaTarea(tarea, tarea.getFecha(), aviso);
                }else{
                    int intervalo = Integer.parseInt(infoAlarma.get(1));
                    calendario.agregarAlarmaTarea(tarea, tarea.getFecha() , intervalo, tiempoRelativo, aviso);
                }
            }
            //vista.actualizarVistaActividades();
            vista.guardarTarea(tarea);
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
                TiempoRelativo tiempoRelativo = getTiempoRelativo(infoAlarma.get(2));
                if (tiempoRelativo == null){
                    calendario.agregarAlarmaEvento(evento, aviso);
                }else{
                    int intervalo = Integer.parseInt(infoAlarma.get(1));
                    calendario.agregarAlarmaEvento(evento, intervalo, tiempoRelativo, aviso);
                }
            }
            var repeticionArgs = vista.getInfoRepeticionEvento();
            if (repeticionArgs != null){
                calendario.modificarRepeticionEvento(evento, repeticionArgs);
            }
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            default -> {
                vista.registrarEscuchaGuardarItem();
                vista.registrarEscuchaVerActividadMenu(actionEvent -> {
                    try {
                        vista.abrirVistaDetalladaMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            });
        }}
    }
}