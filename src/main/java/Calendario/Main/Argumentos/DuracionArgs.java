package Calendario.Main.Argumentos;

import java.time.LocalDate;
import java.time.LocalTime;

public class DuracionArgs {
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Guarda la información de una Duración con hora
     */
    public DuracionArgs(LocalDate diaInicio, LocalDate diaFin, LocalTime horaInicio, LocalTime horaFin){
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     * Guarda la información de una Duración de día completo
     */
    public DuracionArgs(LocalDate diaInicio, LocalDate diaFin){
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
    }

    /**
     * Devuelve el día de inicio
     */
    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    /**
     * Devuelve el día de finalización
     */
    public LocalDate getDiaFin() {
        return diaFin;
    }

    /**
     * Devuelve la hora de inicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Devuelve la hora de finalización
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }
}
