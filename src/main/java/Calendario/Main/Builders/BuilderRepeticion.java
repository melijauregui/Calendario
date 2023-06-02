package Calendario.Main.Builders;

import Calendario.Enums.Frecuencia;
import Calendario.Main.Argumentos.RepeticionArgs;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class BuilderRepeticion {
    Repeticion repeticion;

    /**
     * Recibe la información de una Repetición con fecha de vencimiento y la crea según el tipo de Frecuencia pasado
     */
    public BuilderRepeticion(RepeticionArgs args){
        if (args == null){
            return;
        }
        if (args.getFrecuencia() != null){
            repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo(), args.getFechaHasta(), args.getOcurrencias());
        } else{
            repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana(), args.getFechaHasta(), args.getOcurrencias());
        }
    }

    /**
     * Devuelve la Repetición creada
     */
    public Repeticion crearRepeticion(){
        return repeticion;
    }
}


