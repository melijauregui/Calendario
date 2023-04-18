package Calendario.Repeticiones;


import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionDiaria extends Repeticion {

    // cada "intervaloDias" dias
    public RepeticionDiaria(int intervaloDias, LocalDate fechaHasta) {
        super(intervaloDias, fechaHasta);
    }
    public RepeticionDiaria(int intervaloDias, int ocurrencias){

        super(intervaloDias, ocurrencias);
    }

    public RepeticionDiaria(int intervaloDias){
        super(intervaloDias);
    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de días
    public LocalDateTime getProximaFechaInicio(LocalDateTime fecha){
        disminuirOcurrencias();
        return fecha.plusDays(getIntervalo());
    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de días
    public LocalDateTime getProximaFechaFin(LocalDateTime fecha){
        return fecha.plusDays(getIntervalo());
    }


/*    public void RepetirEvento(ArrayList<Evento> almacenamientoFechas, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaHasta == null){
            fechaHasta = LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth());
        }
        while (this.fechaHasta.isAfter(LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth())) ||
                this.ocurrencias > 0){
            almacenamientoFechas.add(new Evento(fechaInicio, fechaFin));
            fechaInicio = fechaInicio.plusDays(this.intervalo);
            fechaFin = fechaFin.plusDays(this.intervalo);
        }
    }*/
}