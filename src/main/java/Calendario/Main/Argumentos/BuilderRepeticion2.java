package Calendario.Main.Argumentos;

import Calendario.Enums.Frecuencia;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;

public class BuilderRepeticion2 {
    Repeticion repeticion;

    /**
     * Recibe la información de una Repetición y la crea
     */
    public BuilderRepeticion2(RepeticionArgs2 args){
        switch (args.getTipo()){
            case FECHA_LIMITE -> crearRepeticionFecha(args);
            case OCURRENCIAS ->  crearRepeticionOcurrencias(args);
            case INFINITA ->  crearRepeticionInfinita(args);
        }
    }

    /**
     * Devuelve la Repetición creada
     */
    public Repeticion crearRepeticion(){
        return repeticion;
    }

    /**
     * Recibe los argumentos de una Repetición con límite de ocurrencias y la crea según el tipo de Frecuencia pasado. Si
     * no tiene Frecuencia, crea una RepeticionSemanal
     */
    private void crearRepeticionOcurrencias(RepeticionArgs2 args){
        if (args.getFrecuencia() == null){
            //repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana(), args.getOcurrencias());
        }
        repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo(), args.getOcurrencias());
    }

    /**
     * Recibe los argumentos de una Repetición con fecha de vencimiento y la crea según el tipo de Frecuencia pasado. Si
     * no tiene Frecuencia, crea una RepeticionSemanal
     */
    private void crearRepeticionFecha(RepeticionArgs2 args){
        if (args.getFrecuencia() == null){
            //repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana(), args.getFechaHasta());
        }
        repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo(), args.getFechaHasta());
    }

    /**
     * Recibe los argumentos de una Repetición infinita y la crea según el tipo de Frecuencia pasado. Si
     * no tiene Frecuencia, crea una RepeticionSemanal
     */
    private void crearRepeticionInfinita(RepeticionArgs2 args){
        if (args.getFrecuencia() == null){
            //repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana());
        }
        repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo());
    }

}
