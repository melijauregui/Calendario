package Calendario.Main.Builders;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;

import java.time.LocalDateTime;

public class BuilderAlarma {
    private Alarma alarma;

    public BuilderAlarma(LocalDateTime fecha ,int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        alarma = new Alarma(intervalo, tiempoRelativo, fecha, aviso.crearAviso());
    }
    public BuilderAlarma(LocalDateTime fecha, TipoAviso aviso){
        alarma = new Alarma(fecha, aviso.crearAviso());
    }
    public Alarma CrearAlarma(){
        return alarma;
    }
}
