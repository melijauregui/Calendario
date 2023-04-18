package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Evento extends Actividad {
    private LocalDateTime fechaFin;
    private static final int horaFinDiaCompleto = 23;

    private static final int minutoFinDiaCompleto = 59;

    public Evento(String titulo, String descripcion, LocalDate fechaInicio,
                  LocalDate fechaFin) {
        super(titulo, descripcion, LocalDateTime.of(fechaInicio, LocalTime.of(horaInicialDiaCompleto, minutoInicialDiaCompleto)));
        this.fechaFin = LocalDateTime.of(fechaFin, LocalTime.of(horaFinDiaCompleto, minutoFinDiaCompleto));
    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        super(titulo, descripcion, fechaHoraInicio);
        this.fechaFin = fechaHoraFin;
    }

    // getFechaFin devuelve la fecha y hora de finalización del Evento
    public LocalDateTime getFechaFin() {
        return this.fechaFin;
    }


    // getProximaRepeticion es un método abstracto que, dada una una fecha,
    // //devuelve la Instancia de la repetición que más cercana
    public abstract InstanciaEvento getProximaRepeticion(LocalDateTime fecha);

    public abstract void configurarAlarma(Alarma alarma);

}