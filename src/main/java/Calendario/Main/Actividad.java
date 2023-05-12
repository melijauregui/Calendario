package Calendario.Main;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;
import java.util.Set;

public interface Actividad {
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha);
    public void setTitulo(String titulo);
    public void setDescripcion(String descripcion);
}
