package Calendario.Main.Builders;

import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BuilderAlarmaEvento {
    private AlarmaEvento alarmaEvento;

    /**
     * Recibe la información de una AlarmaEvento con Tiempo Relativo y se la setea
     */
    public BuilderAlarmaEvento(int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        alarmaEvento = new AlarmaEvento(intervalo, tiempoRelativo, aviso);
    }

    /**
     * Recibe la información de una AlarmaEvento sin Tiempo Relativo y se la setea
     */
    public BuilderAlarmaEvento(TipoAviso aviso){
        alarmaEvento = new AlarmaEvento(aviso);
    }

    /**
     * Devuelve la AlarmaEvento creada
     */
    public AlarmaEvento crearAlarmaEvento(){
        return alarmaEvento;
    }
}
