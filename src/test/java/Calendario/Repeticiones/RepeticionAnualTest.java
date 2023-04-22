package Calendario.Repeticiones;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.InstanciaEvento;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class RepeticionAnualTest {

    @Test
    public void GetProximaInstanciaEvento() {
        //Arrange
        var repeticionOcurrencias = new RepeticionAnual(2, 15);
        var repeticionFechaHasta = new RepeticionAnual(2, LocalDate.of(2023,11,11));
        var repeticionInfinita = new RepeticionAnual(2);

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2023, 11, 11));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var resEsperadoFechaInicio = eventoInstancia.getFechaInicio().plusYears(2);
        var resEsperadoFechaFin = eventoInstancia.getFechaFin().plusYears(2);

        //Act
        var resObtenidoFechaInicioOcurrencias = repeticionOcurrencias.getProximaInstanciaEvento(eventoInstancia).getFechaInicio();
        var resObtenidoFechaFinOcurrencias = repeticionOcurrencias.getProximaInstanciaEvento(eventoInstancia).getFechaFin();
        var resObtenidoFechaInicioFechaHasta = repeticionFechaHasta.getProximaInstanciaEvento(eventoInstancia).getFechaInicio();
        var resObtenidoFechaFinFechaHasta = repeticionFechaHasta.getProximaInstanciaEvento(eventoInstancia).getFechaFin();
        var resObtenidoFechaInicioInfinita = repeticionInfinita.getProximaInstanciaEvento(eventoInstancia).getFechaInicio();
        var resObtenidoFechaFinInfinita = repeticionInfinita.getProximaInstanciaEvento(eventoInstancia).getFechaFin();

        //Assert
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioOcurrencias);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFinOcurrencias);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioFechaHasta);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFinFechaHasta);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioInfinita);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFinInfinita);
    }
}