package Calendario.Main.Builders;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;

import java.time.LocalDateTime;

public class BuilderAlarma {
    private Alarma alarma;

    /**
     * Recibe la información de una Alarma con Tiempo Relativo y se la setea
     */
    public BuilderAlarma(LocalDateTime fecha ,int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        alarma = new Alarma(intervalo, tiempoRelativo, fecha, aviso);
    }

    /**
     * Recibe la información de una Alarma con fecha y hora absoluta y se la setea
     */
    public BuilderAlarma(LocalDateTime fecha, TipoAviso aviso){
        alarma = new Alarma(fecha, aviso);
    }

    /**
     * Devuelve la Alarma creada
     */
    public Alarma crearAlarma(){
        return alarma;
    }
}
