package Calendario.Main.Argumentos;

import java.time.LocalDate;
import java.time.LocalTime;

public class DuracionArgs {
    private LocalDate diaInicio;
    private LocalDate diaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    public DuracionArgs(LocalDate diaInicio, LocalDate diaFin, LocalTime horaInicio, LocalTime horaFin){
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    public DuracionArgs(LocalDate diaInicio, LocalDate diaFin){
        this.diaInicio = diaInicio;
        this.diaFin = diaFin;
    }

    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    public LocalDate getDiaFin() {
        return diaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
