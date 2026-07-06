package Calendario.Main.Builders;

import Calendario.Main.Argumentos.RepeticionArgs;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;


public class BuilderRepeticion {
    Repeticion repeticion;

    /**
     * Recibe la información de una Repetición y la crea
     */
    public BuilderRepeticion(RepeticionArgs args){
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
    private void crearRepeticionOcurrencias(RepeticionArgs args){
        if (args.getFrecuencia() == null){
            repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana(), args.getOcurrencias());
        } else {
            repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo(), args.getOcurrencias());
        }
    }

    /**
     * Recibe los argumentos de una Repetición con fecha de vencimiento y la crea según el tipo de Frecuencia pasado. Si
     * no tiene Frecuencia, crea una RepeticionSemanal
     */
    private void crearRepeticionFecha(RepeticionArgs args){
        if (args.getFrecuencia() == null){
            repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana(), args.getFechaHasta());
        } else {
            repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo(), args.getFechaHasta());
        }
    }

    /**
     * Recibe los argumentos de una Repetición infinita y la crea según el tipo de Frecuencia pasado. Si
     * no tiene Frecuencia, crea una RepeticionSemanal
     */
    private void crearRepeticionInfinita(RepeticionArgs args){
        if (args.getFrecuencia() == null){
            repeticion = new RepeticionSemanal(args.getIntervalo(), args.getDiasSemana());
        }else {
            repeticion = args.getFrecuencia().crearRepeticion(args.getIntervalo());
        }
    }

}
