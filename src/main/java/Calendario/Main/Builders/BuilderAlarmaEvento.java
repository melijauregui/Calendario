package Calendario.Main.Builders;

import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Tareas.Tarea;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BuilderAlarmaEvento {
    private AlarmaEvento alarmaEvento;

    public BuilderAlarmaEvento(int intervalo, TiempoRelativo tiempoRelativo, TipoAviso aviso){
        alarmaEvento = new AlarmaEvento(intervalo, tiempoRelativo, aviso.crearAviso());
    }
    public BuilderAlarmaEvento(TipoAviso aviso){
        alarmaEvento = new AlarmaEvento(aviso.crearAviso());
    }
    public AlarmaEvento CrearAlarmaEvento(){
        return alarmaEvento;
    }
}
