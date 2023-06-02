package Calendario.Repeticiones;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class RepeticionMensualTest {

    int REPETICION = 100;

    @Test
    public void RepeticionConOcurrencias() {

        //Arrange
        int ocurrencias = 15;
        var repeticionOcurrencias = new RepeticionMensual(2, null, ocurrencias);

        var fechaInicio = LocalDate.of(2023, 11, 11);
        var fechaFin = LocalDate.of(2023, 11, 11);


        for (int i = 1; i < ocurrencias; i++) {


            //Act
            var resObtenidoFechaInicio = repeticionOcurrencias.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticionOcurrencias.getProximaFechaFin(fechaFin);

            fechaInicio = fechaInicio.plusMonths(repeticionOcurrencias.getIntervalo());
            fechaFin = fechaFin.plusMonths(repeticionOcurrencias.getIntervalo());

            //Assert
            assertEquals(fechaInicio, resObtenidoFechaInicio);
            assertEquals(fechaFin, resObtenidoFechaFin);

        }
        assertTrue(repeticionOcurrencias.terminoRepeticion(fechaInicio));
        assertNull(repeticionOcurrencias.getProximaFechaInicio(fechaInicio));
    }

    @Test
    public void RepeticionConFecha() {

        //Arrange
        var fechaHasta = LocalDate.of(2023, 10, 11);
        var repeticion = new RepeticionMensual(1, fechaHasta, 0);

        var fechaInicio = LocalDate.of(2023, 5, 11);
        var fechaFin = LocalDate.of(2023, 6, 11);


        for (int i = 0; i < 5; i++) {


            //Act
            var resObtenidoFechaInicio = repeticion.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticion.getProximaFechaFin(fechaFin);

            fechaInicio = fechaInicio.plusMonths(repeticion.getIntervalo());
            fechaFin = fechaFin.plusMonths(repeticion.getIntervalo());

            //Assert
            assertEquals(fechaInicio, resObtenidoFechaInicio);
            assertEquals(fechaFin, resObtenidoFechaFin);

        }
        assertNull(repeticion.getProximaFechaInicio(fechaInicio));
        fechaInicio = fechaInicio.plusMonths(repeticion.getIntervalo());
        assertTrue(repeticion.terminoRepeticion(fechaInicio));

    }

    @Test
    public void RepeticionInfinita() {

        //Arrange
        var repeticion = new RepeticionMensual(1, null, 0);

        var fechaInicio = LocalDate.of(2023, 5, 11);
        var fechaFin = LocalDate.of(2023, 6, 11);


        for (int i = 0; i < REPETICION; i++) {


            //Act
            var resObtenidoFechaInicio = repeticion.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticion.getProximaFechaFin(fechaFin);

            fechaInicio = fechaInicio.plusMonths(repeticion.getIntervalo());
            fechaFin = fechaFin.plusMonths(repeticion.getIntervalo());

            //Assert
            assertEquals(fechaInicio, resObtenidoFechaInicio);
            assertEquals(fechaFin, resObtenidoFechaFin);

        }
        assertNotNull(repeticion.getProximaFechaInicio(fechaInicio));
        assertFalse(repeticion.terminoRepeticion(fechaInicio));

    }
}