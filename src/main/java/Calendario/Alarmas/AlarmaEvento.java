package Calendario.Alarmas;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AlarmaEvento implements Serializable {
    private int intervalo;
    private TiempoRelativo tiempoRelativo;
    private TipoAviso aviso;
    private static final int sinIntervalo = 0;

    /**
     * Crea una AlarmaEvento con tiempo relativo
     */
    public AlarmaEvento(int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
        this.aviso = aviso;
    }

    /**
     * Crea una AlarmaEvento sin tiempo relativo
     */
    public AlarmaEvento(TipoAviso aviso){
        this.intervalo = sinIntervalo;
        this.aviso = aviso;
    }

    /**
     * Devuelve una Alarma con la fecha absoluta o relativa a la pasada por parámetro
     */
    public Alarma crearAlarmaInstaciaEvento(LocalDateTime fecha){
        if (esFechaRelativa()){
            return new Alarma(intervalo, tiempoRelativo, fecha, aviso);
        }
        return new Alarma(fecha, aviso);
    }

    /**
     * Devuelve true si la AlarmaEvento tiene intervalo relativo
     */
    private boolean esFechaRelativa(){
        return intervalo != sinIntervalo;
    }

}
