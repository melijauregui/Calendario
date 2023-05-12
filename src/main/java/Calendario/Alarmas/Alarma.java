package Calendario.Alarmas;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Enums.TiempoRelativo;

import java.time.LocalDateTime;

public class Alarma {
    private LocalDateTime fechaAlarma;
    private Aviso aviso;

    /**
     * Crea una Alarma con fecha y hora absolutas
     */
    public Alarma(LocalDateTime fechaAlarma,  Aviso aviso){
        this.aviso = aviso;
        this.fechaAlarma = fechaAlarma;
    }

    /**
     * Crea una Alarma con un intervalo de tiempo relativo
     */
    public Alarma(int intervalo, TiempoRelativo tiempoRelativo, LocalDateTime fecha, Aviso aviso){
        fechaAlarma = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);
        this.aviso = aviso;
    }

    /**
     * Devuelve true si la alarma suena antes de la alarma recibida por parámetro
     */
    public boolean suenaAntes(Alarma otra){
        return this.fechaAlarma.isBefore(otra.getFechaAlarma());
    }

    /**
     * Devuelve true si la alarma suena antes de la fecha recibida por parámetro
     */
    public boolean suenaAntes(LocalDateTime fecha){
        return this.fechaAlarma.isBefore(fecha);
    }

    /**
     * Devuelve true si la alarma suena al mismo tiempo que la recibida por parámetro
     */
    public boolean suenaIgual(Alarma otra){
        return getFechaAlarma().isEqual(otra.getFechaAlarma());
    }


    /**
     * Devuelve la fecha de la Alarma
     */
    private LocalDateTime getFechaAlarma() {
        return fechaAlarma;
    }

    public void avisar(){
        aviso.avisar();
    }
}
