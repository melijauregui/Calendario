package Calendario.Duracion;

import Calendario.Main.Calendario;
import Calendario.Main.Constantes;
import com.google.gson.Gson;

import javax.json.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class Duracion implements Serializable {
    private LocalDate diaInicio;
    private LocalDate diaFin;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Devuelve la fecha de inicio
     */
    public void setDiaInicio(LocalDate diaInicio){
        this.diaInicio = diaInicio;
    }

    /**
     * Devuelve la fecha de fin
     */
    public void setDiaFin(LocalDate diaFin){
        this.diaFin = diaFin;
    }

    /**
     * Devuelve la hora de inicio
     */
    public void setHoraInicio(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }

    /**
     * Devuelve la hora de fin
     */
    public void setHoraFin(LocalTime horaFin){
        this.horaFin = horaFin;
    }

    /**
     * Devuelve la fecha y hora de inicio
     */
    public LocalDateTime getFechaInicio() {
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(getDiaInicio(), horaInicio);
        }
        return LocalDateTime.of(getDiaInicio(), LocalTime.of(Constantes.horaInicioDiaCompleto, Constantes.minutoInicioDiaCompleto));
    }

    /**
     * Devuelve la fecha y hora de fin
     */
    public LocalDateTime getFechaFin() {
        if (!this.esDiaCompleto()) {
            return LocalDateTime.of(getDiaFin(), horaFin);
        }
        return LocalDateTime.of(getDiaFin(), LocalTime.of(Constantes.horaFinDiaCompleto, Constantes.minutoFinDiaCompleto));
    }

    /**
     * Devuelve la fecha de inicio de una Duración de día completo
     */
    public LocalDate getDiaInicio(){
        return this.diaInicio;
    }

    /**
     * Devuelve la fecha de finalización de una Duración de día completo
     */
    public LocalDate getDiaFin(){
        return this.diaFin;
    }

    /**
     * Devuelve una copia de la duración
     */{}
    public Duracion Clone(){
        Duracion nuevaDuracion = new Duracion();
        nuevaDuracion.setDiaInicio(this.diaInicio);
        nuevaDuracion.setDiaFin(this.diaFin);
        if (!this.esDiaCompleto()) {
            nuevaDuracion.setHoraInicio(this.horaInicio);
            nuevaDuracion.setHoraFin(this.horaFin);
        }
        return nuevaDuracion;
    }

    /**
     * Devuelve true si la duración es de día completo
     */
    public boolean esDiaCompleto(){
        return (horaInicio == null && horaFin == null);
    }

}
