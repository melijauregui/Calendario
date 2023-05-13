package Calendario.Repeticiones;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class RepeticionSemanalTest {

    @Test
    public void RepeticionConOcurrencias() {

        //Arrange
        int ocurrencias = 5;
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, ocurrencias);

        var fechaInicio = LocalDate.of(2023, 5, 12);
        var fechaFin = LocalDate.of(2023, 5, 13);

        List<LocalDate> resEsperadoFechaInicio = new ArrayList<>();
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 22));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 25));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 5));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 8));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 19));


        for (int i = 0; i < ocurrencias - 1; i++) {


            //Act
            var resObtenidoFechaInicio = repeticionOcurrencias.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticionOcurrencias.getProximaFechaFin(fechaFin);

            fechaInicio = resEsperadoFechaInicio.get(i);
            fechaFin = fechaInicio.plusDays(1);

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
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        var fechaHasta = LocalDate.of(2023, 6, 19);
        var repeticion = new RepeticionSemanal(2, diasSemanas, fechaHasta);


        var fechaInicio = LocalDate.of(2023, 5, 12);
        var fechaFin = LocalDate.of(2023, 5, 13);

        List<LocalDate> resEsperadoFechaInicio = new ArrayList<>();
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 22));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 25));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 5));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 8));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 19));


        for (int i = 0; i < 5; i++) {


            //Act
            var resObtenidoFechaInicio = repeticion.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticion.getProximaFechaFin(fechaFin);

            fechaInicio = resEsperadoFechaInicio.get(i);
            fechaFin = fechaInicio.plusDays(1);


            //Assert
            assertEquals(fechaInicio, resObtenidoFechaInicio);
            assertEquals(fechaFin, resObtenidoFechaFin);

        }
        assertNull(repeticion.getProximaFechaInicio(fechaInicio));
        fechaInicio = fechaInicio.plusYears(repeticion.getIntervalo());
        assertTrue(repeticion.terminoRepeticion(fechaInicio));

    }

    @Test
    public void RepeticionInfinita() {

        //Arrange
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        var repeticion = new RepeticionSemanal(2, diasSemanas);


        var fechaInicio = LocalDate.of(2023, 5, 12);
        var fechaFin = LocalDate.of(2023, 5, 13);

        List<LocalDate> resEsperadoFechaInicio = new ArrayList<>();
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 22));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 25));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 5));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 8));
        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 19));


        for (int i = 0; i < 5; i++) {


            //Act
            var resObtenidoFechaInicio = repeticion.getProximaFechaInicio(fechaInicio);
            var resObtenidoFechaFin = repeticion.getProximaFechaFin(fechaFin);

            fechaInicio = resEsperadoFechaInicio.get(i);
            fechaFin = fechaInicio.plusDays(1);

            //Assert
            assertEquals(fechaInicio, resObtenidoFechaInicio);
            assertEquals(fechaFin, resObtenidoFechaFin);

        }
        assertNotNull(repeticion.getProximaFechaInicio(fechaInicio));
        assertFalse(repeticion.terminoRepeticion(fechaInicio));

    }

}