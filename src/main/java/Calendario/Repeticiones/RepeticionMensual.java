package Calendario.Repeticiones;


import Calendario.Enums.Dia;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

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

    /* getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición, dependiendo del
     intervalo de meses*/
    public LocalDateTime getProximaFechaInicio(LocalDateTime fecha) {
        disminuirOcurrencias();
        return fecha.plusMonths(getIntervalo());
    }

    /* getProximaFechaInicio devuelve la fecha de inicio de la siguiente repetición, dependiendo del
     intervalo de meses */
    public LocalDateTime getProximaFechaFin(LocalDateTime fecha) {
        return fecha.plusMonths(getIntervalo());
    }

    public InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento) {
        return new InstanciaEvento(evento.getTitulo(), evento.getDescripcion(), evento.getFechaInicio().plusYears(getIntervalo()), evento.getFechaFin().plusYears(getIntervalo()));
    }

}