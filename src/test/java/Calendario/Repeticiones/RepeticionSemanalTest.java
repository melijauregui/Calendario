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
        //Comprueba que el método GetProximaInstanciaEvento() de Repeticion Anual se comporte como lo esperado
        //Mismo Dia

        //Arrange
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        //defino los diferentes tipos de repeticiones
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, 15);
        var repeticionFechaHasta = new RepeticionSemanal(2, diasSemanas, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionSemanal(2, diasSemanas);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion);
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados
        var resEsperadoInfinitoOcurrencias = false;
        var resEsperadoInfinitoFechaHasta = false;
        var resEsperadoInfinitoInfinita = true;

        var resEsperadoFechaInicio = new ArrayList<LocalDate>();
        var resEsperadoFechaFin = new ArrayList<LocalDate>();

        resEsperadoFechaInicio.add(LocalDate.of(2023, 4, 27));
        resEsperadoFechaFin.add(LocalDate.of(2023, 4, 27));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 4, 30));
        resEsperadoFechaFin.add(LocalDate.of(2023, 4, 30));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 8));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 8));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 11));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 11));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 14));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 14));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 22));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 22));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 25));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 25));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 5, 28));
        resEsperadoFechaFin.add(LocalDate.of(2023, 5, 28));

        resEsperadoFechaInicio.add(LocalDate.of(2023, 6, 5));
        resEsperadoFechaFin.add(LocalDate.of(2023, 6, 5));


        //Act Resultados Obetenidos
        for (int i = 0; i < 9; i++) {
            InstanciaEvento eventoOcurrencias = eventoInstancia;
            InstanciaEvento eventoFechaHasta = eventoInstancia;
            InstanciaEvento eventoInfinito = eventoInstancia;
            var resObtenidoFechaInicioOcurrencias = repeticionOcurrencias.getProximaFecha(eventoOcurrencias.getDiaInicio());
            var resObtenidoFechaFinOcurrencias = repeticionOcurrencias.getProximaFechaFin(eventoOcurrencias.getDiaFin());
            var resObtenidoFechaInicioFechaHasta = repeticionFechaHasta.getProximaFecha(eventoFechaHasta.getDiaInicio());
            var resObtenidoFechaFinFechaHasta = repeticionFechaHasta.getProximaFechaFin(eventoFechaHasta.getDiaFin());
            var resObtenidoFechaInicioInfinita = repeticionInfinita.getProximaFecha(eventoInfinito.getDiaInicio());
            var resObtenidoFechaFinInfinita = repeticionInfinita.getProximaFechaFin(eventoInfinito.getDiaFin());


            //Assert
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioOcurrencias);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinOcurrencias);
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioFechaHasta);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinFechaHasta);
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoFechaInicioInfinita);
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoFechaFinInfinita);

            var duracion2 = new Duracion();
            duracion2.setDiaInicio(resEsperadoFechaInicio.get(i));
            duracion2.setDiaFin(resEsperadoFechaFin.get(i));

            eventoInstancia = new InstanciaEvento(titulo, descripcion);
            eventoInstancia.setDuracion(duracion2);
        }
    }

}/*

    @Test
    public void AlmacenarRepeticionesOcurrencias() {
        //Comprueba que el método AlmacenarRepeticiones() de Repeticion Anual se comporte como lo esperado

        //Arrange
        int ocurrencias = 10;
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, ocurrencias);

        //defino dia Inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoOcurrencias = eventoInstancia;

        //defino evento
        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);

        //Resultados Esperados
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


        //Act Resultados Obetenidos
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
        //Comprueba que el método AlmacenarRepeticiones() de Repeticion Anual se comporte como lo esperado

        //Arrange
        //defino fecha hasta
        LocalDate fechaHasta = LocalDate.of(2023, 6, 6);

        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);

        var repeticionOcurrencias = new RepeticionSemanal(2, diasSemanas, fechaHasta);

        //defino dia Inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 24));
        duracion.setDiaFin(LocalDate.of(2023, 4, 24));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        //defino evento
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);
        InstanciaEvento eventoOcurrencias = eventoInstancia;
        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);

        //Resultados Esperados
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


        //Act Resultados Obetenidos
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoFechaInicio.size(), resObtenidoEventosIntervalo.size());
        for (int i = 0; i < resEsperadoFechaInicio.size(); i++) {
            assertEquals(resEsperadoFechaInicio.get(i), resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoFechaFin.get(i), resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }
}

 */