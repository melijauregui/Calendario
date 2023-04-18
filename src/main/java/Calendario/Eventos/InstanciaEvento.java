package Calendario.Eventos;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;

public class InstanciaEvento extends Evento{
    public InstanciaEvento(String titulo, String descripcion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        super(titulo, descripcion, fechaHoraInicio, fechaHoraFin);
    }

    // getProximaRepeticion devuelve null porque una Instancia no tiene repetición
    public InstanciaEvento getProximaRepeticion(LocalDateTime fechaActual){
        return null;
    }

    // empiezaDespues devuelve true si la instancia del evento comienza luego de la fecha recibida como argumento
    public boolean empiezaDespues(LocalDateTime fecha){
        return this.getFecha().isAfter(fecha);
    }

    // estaEnElIntervalo devuelve true si la instancia del evento se encuentra dentro del intervalo dado
    public boolean estaEnElIntervalo(LocalDateTime desde, LocalDateTime hasta){
        return this.getFecha().isAfter(desde) && getFechaFin().isBefore(hasta);
    }

    public void configurarAlarma(Alarma alarma){
        if (alarma != null){
            setAlarma(alarma);
        }
    }


}
