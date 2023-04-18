package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepeticionMensual extends Repeticion {
    public RepeticionMensual(int intervaloMeses,  LocalDate fechaHasta){
        super(intervaloMeses, fechaHasta);
    }
    public RepeticionMensual(int intervaloMeses, int ocurrencias){
        super(intervaloMeses, ocurrencias);

    }
    public RepeticionMensual(int intervaloMeses){
        super(intervaloMeses);
    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de meses
    @Override
    public LocalDateTime getProximaFechaInicio(LocalDateTime fecha) {
        disminuirOcurrencias();
        return fecha.plusMonths(getIntervalo());
    }

    // getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición,
    // dependiendo del intervalo de meses
    public LocalDateTime getProximaFechaFin(LocalDateTime fecha) {
        return fecha.plusMonths(getIntervalo());
    }


    /*    public void RepetirEvento(ArrayList<Evento> almacenamientoFechas, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaHasta == null){
            fechaHasta = LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth());
        }
        while (this.fechaHasta.isAfter(LocalDate.of(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDayOfMonth())) ||
                this.ocurrencias > 0){
            almacenamientoFechas.add(new Evento(fechaInicio, fechaFin));
            fechaInicio = fechaInicio.plusMonths(this.intervalo);
            fechaFin = fechaFin.plusMonths(this.intervalo);
        }
    }*/
}