package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;

import java.time.LocalDate;
import java.time.LocalTime;

public class BuilderDuracion {
    private Duracion duracion = new Duracion();

    public BuilderDuracion(LocalDate diaInicio, LocalDate diaFin,LocalTime horaInicio, LocalTime horaFin){
        setFechas(diaInicio, diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);
    }

    public BuilderDuracion(LocalDate diaInicio, LocalDate diaFin){
        setFechas(diaInicio, diaFin);
    }

    public Duracion crearDuracion(){
        return duracion;
    }

    private void setFechas(LocalDate diaInicio, LocalDate diaFin){
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
    }


}
