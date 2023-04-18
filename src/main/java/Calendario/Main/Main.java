package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaConNotificacion;
import Calendario.Alarmas.AlarmaConSonido;
import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Enums.Tiempo;
import Calendario.Eventos.EventoConRepeticion;
import Calendario.Eventos.EventoSinRepeticion;
import Calendario.Repeticiones.RepeticionMensual;
import Calendario.Repeticiones.RepeticionSemanal;
import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*LocalDateTime time = LocalDateTime.now();
        System.out.println(time.getHour());
        Mes abril = Mes.valueOf("abril".toUpperCase());
        System.out.println(abril.ordinal());
        LocalDateTime ej = LocalDateTime.of(2029, 5, 2, 0, 0); // usar para las pruebas
        System.out.println(Dia.values()[ej.getDayOfWeek().getValue()]);
        System.out.println(ej.getDayOfWeek().ordinal());
        System.out.println(time.toLocalTime());
        System.out.println(time.toLocalDate());
        //System.out.println(ej.until(time));
        System.out.println(ej.plusMonths(2));
        System.out.println(Math.abs(time.getDayOfYear()-ej.getDayOfYear())+
                365*(Math.abs(time.getYear()-ej.getYear())));*/

        // pruebo Calendario
        var calendario = new Calendario();

        var tarea = new Tarea("titulo1", "descripcion1", LocalDate.of(2023, 4, 11));
        var evInf = new EventoConRepeticion("titulo2", "descripcion2", new RepeticionMensual(5),
                LocalDateTime.of(2023, 4, 11, 23, 50),
                LocalDateTime.of(2023, 4, 11, 23, 55));
        var evSinRepe = new EventoSinRepeticion("titulo3", "descripcion3",
                LocalDate.of(2023, 4, 11),
                LocalDate.of(2023, 5, 11));
        List dias = new ArrayList<>();
        dias.add(Dia.MARTES);
        dias.add(Dia.JUEVES);
        var evSemanal = new EventoConRepeticion("titulo4", "descripcion4",
                new RepeticionSemanal(2, dias, 3),
                LocalDateTime.of(2023, 4, 11, 22, 50),
                LocalDateTime.of(2023, 4, 11, 23, 50));
        //calendario.agregarTarea(tarea);
        calendario.agregarEvento(evInf);
        //calendario.agregarEvento(evSemanal);
        //calendario.agregarEvento(evSinRepe);
        var alarma = new AlarmaConNotificacion(10, Tiempo.MINUTOS);
        evInf.configurarAlarma(alarma);
        var proxAct = calendario.getProximaActividad();
        //var proxAl = calendario.getProximaAlarma();
        var acts = calendario.getActividades(LocalDateTime.of(2023, 4, 1, 21, 0),
                LocalDateTime.of(2024, 4, 11, 21, 0));
        System.out.println(proxAct.getTitulo());
        //System.out.println(proxAl.getFechaAlarma());
        System.out.println("---------");
        for (Actividad act : acts){
            System.out.println(act.getAlarma().getFechaAlarma());
        }
    }
}