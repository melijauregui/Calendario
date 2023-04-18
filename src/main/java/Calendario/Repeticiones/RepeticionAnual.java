package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionAnual extends Repeticion{
    public RepeticionAnual(int intervaloAnios,  LocalDate fechaHasta){
        super(intervaloAnios, fechaHasta);
    }
    public RepeticionAnual(int intervaloAnios, int ocurrencias){
        super(intervaloAnios, ocurrencias);

    }
    public RepeticionAnual(int intervaloAnios){
        super(intervaloAnios);

    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de años
    @Override
    public LocalDateTime getProximaFechaInicio(LocalDateTime fecha) {
        disminuirOcurrencias();
        return fecha.plusYears(getIntervalo());
    }

    // getProximaFechaInicio devuelve la fecha de finalización de la siguiente repetición,
    // dependiendo del intervalo de años
    public LocalDateTime getProximaFechaFin(LocalDateTime fecha) {
        return fecha.plusYears(getIntervalo());
    }

    /*public void RepetirEvento(ArrayList<Evento> almacenamientoFechas, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if (fechaHasta == null){
            fechaHasta = LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth());
        }
        while (this.fechaHasta.isAfter(LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth())) ||
                this.ocurrencias > 0){
            almacenamientoFechas.add(new Evento(fechaInicio, fechaFin));
            fechaInicio = fechaInicio.plusYears(this.intervalo);
            fechaFin = fechaFin.plusYears(this.intervalo);
        }
    }*/
}
