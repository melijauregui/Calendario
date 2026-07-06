package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Main.Argumentos.DuracionArgs;

import java.time.LocalDate;
import java.time.LocalTime;

public class BuilderDuracion {
    private Duracion duracion = new Duracion();

    public BuilderDuracion(DuracionArgs args){
        duracion.setDiaInicio(args.getDiaInicio());
        duracion.setDiaFin(args.getDiaFin());
        duracion.setHoraInicio(args.getHoraInicio());
        duracion.setHoraFin(args.getHoraFin());
    }

    public Duracion crearDuracion(){
        return duracion;
    }

}
