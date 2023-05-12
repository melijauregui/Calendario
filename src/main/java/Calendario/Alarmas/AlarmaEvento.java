package Calendario.Alarmas;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class AlarmaEvento {
    private int intervalo;
    private TiempoRelativo tiempoRelativo;
    private Aviso aviso;

    public AlarmaEvento(int intervalo, TiempoRelativo tiempoRelativo, Aviso aviso){
        this.intervalo = intervalo;
        this.tiempoRelativo = tiempoRelativo;
        this.aviso = aviso;
    }

    public Alarma crearAlarmaInstaciaEvento(LocalDateTime fecha){
        return new Alarma(intervalo, tiempoRelativo, fecha, aviso);
    }
}
