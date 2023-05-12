package Calendario.Repeticiones;
/*
import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RepeticionMensualTest {

    int REPETICION = 100;
    @Test
    public void GetProximaInstanciaEventoMD() {
        //Comprueba que el método GetProximaInstanciaEvento() de Repeticion Mensual se comporte como lo esperado
        //Mismo Dia

        //Arrange
        //defino los diferentes tipos de repeticiones
        var repeticionOcurrencias = new RepeticionMensual(2, 15);
        var repeticionFechaHasta = new RepeticionMensual(2, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionMensual(2);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2023, 11, 11));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados
        var resEsperadoInfinitoOcurrencias = false;
        var resEsperadoInfinitoFechaHasta = false;
        var resEsperadoInfinitoInfinita = true;

        InstanciaEvento eventoOcurrencias = eventoInstancia;
        InstanciaEvento eventoFechaHasta = eventoInstancia;
        InstanciaEvento eventoInfinito = eventoInstancia;
        for (int i = 0; i < REPETICION; i++) {

            var resEsperadoFechaInicio = eventoInstancia.getFechaInicio().plusMonths(2);
            var resEsperadoFechaFin = eventoInstancia.getFechaFin().plusMonths(2);

            //Act Resultados Obetenidos
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

            //Assert compruebo que sea efectivamente el próximo evento en cada repeticion
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioOcurrencias);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinOcurrencias);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioFechaHasta);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinFechaHasta);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioInfinita);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinInfinita);
            assertEquals(resEsperadoInfinitoOcurrencias, resObtenidoEsInfinitaOcurrencias);
            assertEquals(resEsperadoInfinitoFechaHasta, resObtenidoEsInfinitaFechaHasta);
            assertEquals(resEsperadoInfinitoInfinita, resObtenidoEsInfinitaInfinita);

            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusMonths(2),eventoInstancia.getDiaFin().plusMonths(2));
        }
    }
    @Test
    public void GetProximaInstanciaEventoDD() {
        //Comprueba que el método GetProximaInstanciaEvento() de Repeticion mensual se comporte como lo esperado
        //Diferente Dia

        //Arrange
        //defino los diferentes tipos de repeticiones
        var repeticionOcurrencias = new RepeticionMensual(2, 15);
        var repeticionFechaHasta = new RepeticionMensual(2, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionMensual(2);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2024, 12, 9));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(12, 21));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados
        var resEsperadoInfinitoOcurrencias = false;
        var resEsperadoInfinitoFechaHasta = false;
        var resEsperadoInfinitoInfinita = true;

        InstanciaEvento eventoOcurrencias = eventoInstancia;
        InstanciaEvento eventoFechaHasta = eventoInstancia;
        InstanciaEvento eventoInfinito = eventoInstancia;
        for (int i = 0; i < REPETICION; i++) {

            var resEsperadoFechaInicio = eventoInstancia.getFechaInicio().plusMonths(2);
            var resEsperadoFechaFin = eventoInstancia.getFechaFin().plusMonths(2);

            //Act Resultados Obetenidos
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

            //Assert compruebo que sea efectivamente el próximo evento en cada repeticion
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioOcurrencias);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinOcurrencias);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioFechaHasta);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinFechaHasta);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioInfinita);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinInfinita);
            assertEquals(resEsperadoInfinitoOcurrencias, resObtenidoEsInfinitaOcurrencias);
            assertEquals(resEsperadoInfinitoFechaHasta, resObtenidoEsInfinitaFechaHasta);
            assertEquals(resEsperadoInfinitoInfinita, resObtenidoEsInfinitaInfinita);

            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusMonths(2), eventoInstancia.getDiaFin().plusMonths(2));
        }
    }

    @Test
    public void AlmacenarRepeticionesOcurrencias(){
        //Comprueba que el método GetAlmacenarRepeticiones() de Repeticion mensual se comporte como lo esperado

        int ocurrencias = 15;
        var repeticionOcurrencias = new RepeticionMensual(2, ocurrencias);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2024, 12, 9));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(12, 21));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //defino evento
        InstanciaEvento eventoOcurrencias = eventoInstancia;

        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);

        //Resultados Esperados
        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        resEsperadoEventosIntervalo.add(eventoInstancia);
        for (int i = 0; i < ocurrencias-1; i++) {
            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusMonths(2), eventoInstancia.getDiaFin().plusMonths(2));
            resEsperadoEventosIntervalo.add(eventoInstancia);
        }

        //Act Resultados Obetenidos
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoEventosIntervalo.size(), resObtenidoEventosIntervalo.size());
        for (int i = 0; i< resEsperadoEventosIntervalo.size(); i++){
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaInicio(),resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaFin(),resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }

    @Test
    public void AlmacenarRepeticionesFechaHasta(){
        //Comprueba que el método GetAlmacenarRepeticiones() de Repeticion Mensual se comporte como lo esperado

        //Assert
        //defino fechaHasta
        var fechaHasta = LocalDate.of(2027,10,11);
        var repeticionFechaHasta = new RepeticionMensual(2, fechaHasta);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2024, 12, 9));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(12, 21));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoFechaHasta = eventoInstancia;

        //defino evento
        var evento = new Evento();
        evento.setEventoInicial(eventoFechaHasta);
        evento.setRepeticion(repeticionFechaHasta);

        //Resultados Esperados
        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        do {
            resEsperadoEventosIntervalo.add(eventoInstancia);
            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusMonths(2), eventoInstancia.getDiaFin().plusMonths(2));
        } while (eventoInstancia.getFechaInicio().toLocalDate().isBefore(fechaHasta) || (eventoInstancia.getFechaInicio().toLocalDate().isEqual(fechaHasta)));

        //Act Resultados Obetenidos
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        for (int i = 0; i< resEsperadoEventosIntervalo.size(); i++){
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaInicio(),resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaFin(),resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }
}

 */