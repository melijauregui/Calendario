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
import java.util.List;

import static org.junit.Assert.*;

public class RepeticionSemanalTest {

    int REPETICION = 100;

    @Test
    public void GetProximaInstanciaEventoMD() {
        //Mismo Dia
        //Arrange
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, 15);
        var repeticionFechaHasta = new RepeticionSemanal(2, diasSemanas, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionSemanal(2, diasSemanas);

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoOcurrencias = eventoInstancia;
        InstanciaEvento eventoFechaHasta = eventoInstancia;
        InstanciaEvento eventoInfinito = eventoInstancia;
        var resEsperadoInfinitoOcurrencias = false;
        var resEsperadoInfinitoFechaHasta = false;
        var resEsperadoInfinitoInfinita = true;

        var resEsperadoFechaInicio = new ArrayList<LocalDateTime>();
        var resEsperadoFechaFin = new ArrayList<LocalDateTime>();

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 27, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 27, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 30, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 30, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 8, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 8, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 11, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 11, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 14, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 14, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 22, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 22, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 25, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 25, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 28, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 28, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 6, 5, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 6, 5, 19, 30));

        //Act

        for (int i = 0; i < 9; i++) {
            eventoOcurrencias = repeticionOcurrencias.getProximaInstanciaEvento(eventoOcurrencias);
            eventoFechaHasta = repeticionOcurrencias.getProximaInstanciaEvento(eventoFechaHasta);
            eventoInfinito = repeticionOcurrencias.getProximaInstanciaEvento(eventoInfinito);

            var resObtenidoFechaInicioOcurrencias = eventoOcurrencias.getFechaInicio();
            var resObtenidoFechaFinOcurrencias = eventoOcurrencias.getFechaFin();
            var resObtenidoFechaInicioFechaHasta = eventoFechaHasta.getFechaInicio();
            var resObtenidoFechaFinFechaHasta = eventoFechaHasta.getFechaFin();
            var resObtenidoFechaInicioInfinita = eventoInfinito.getFechaInicio();
            var resObtenidoFechaFinInfinita = eventoInfinito.getFechaFin();

            var resObtenidoEsInfinitaOcurrencias = repeticionOcurrencias.esInfinita();
            var resObtenidoEsInfinitaFechaHasta = repeticionFechaHasta.esInfinita();
            var resObtenidoEsInfinitaInfinita = repeticionInfinita.esInfinita();

            //Assert
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioOcurrencias);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinOcurrencias);
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioFechaHasta);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinFechaHasta);
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioInfinita);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinInfinita);
            assertEquals(resEsperadoInfinitoOcurrencias, resObtenidoEsInfinitaOcurrencias);
            assertEquals(resEsperadoInfinitoFechaHasta, resObtenidoEsInfinitaFechaHasta);
            assertEquals(resEsperadoInfinitoInfinita, resObtenidoEsInfinitaInfinita);
        }
    }

    @Test
    public void AlmacenarRepeticionesOcurrencias() {
        int ocurrencias = 10;

        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, ocurrencias);

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoOcurrencias = eventoInstancia;

        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);


        var resEsperadoFechaInicio = new ArrayList<LocalDateTime>();
        var resEsperadoFechaFin = new ArrayList<LocalDateTime>();

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 24, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 24, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 27, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 27, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 30, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 30, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 8, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 8, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 11, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 11, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 14, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 14, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 22, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 22, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 25, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 25, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 28, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 28, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 6, 5, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 6, 5, 19, 30));


        //Act
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoFechaInicio.size(), resObtenidoEventosIntervalo.size());
        for (int i = 0; i < resEsperadoFechaInicio.size(); i++) {
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }


    @Test
    public void AlmacenarRepeticionesFechaHasta() {
        LocalDate fechaHasta = LocalDate.of(2023, 6, 6);

        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, fechaHasta);

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoOcurrencias = eventoInstancia;

        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);

        var resEsperadoFechaInicio = new ArrayList<LocalDateTime>();
        var resEsperadoFechaFin = new ArrayList<LocalDateTime>();

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 24, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 24, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 27, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 27, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 4, 30, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 4, 30, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 8, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 8, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 11, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 11, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 14, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 14, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 22, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 22, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 25, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 25, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 5, 28, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 5, 28, 19, 30));

        resEsperadoFechaInicio.add(LocalDateTime.of(2023, 6, 5, 18, 30));
        resEsperadoFechaFin.add(LocalDateTime.of(2023, 6, 5, 19, 30));


        //Act
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoFechaInicio.size(), resObtenidoEventosIntervalo.size());
        for (int i = 0; i < resEsperadoFechaInicio.size(); i++) {
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }
}