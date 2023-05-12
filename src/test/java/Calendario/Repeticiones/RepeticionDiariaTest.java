package Calendario.Repeticiones;

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

public class RepeticionDiariaTest {

    int REPETICION = 100;

    @Test
    public void GetProximaInstanciaEventoMD() {
        //Comprueba que el método GetProximaInstanciaEvento() de Repeticion Diaria se comporte como lo esperado
        //Mismo Dia

        //Arrange
        //defino los diferentes tipos de repeticiones
        var repeticionOcurrencias = new RepeticionDiaria(2, 15);
        var repeticionFechaHasta = new RepeticionDiaria(2, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionDiaria(2);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2023, 11, 11));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(19, 30));

        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion);
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados


        for (int i = 0; i < REPETICION; i++) {
            InstanciaEvento eventoOcurrencias = eventoInstancia;
            InstanciaEvento eventoFechaHasta = eventoInstancia;
            InstanciaEvento eventoInfinito = eventoInstancia;

            var resEsperadoFechaInicio = eventoInstancia.getDiaInicio().plusDays(2);
            var resEsperadoFechaFin = eventoInstancia.getDiaFin().plusDays(2);

            //Act Resultados Obetenidos

            var resObtenidoFechaInicioOcurrencias = repeticionOcurrencias.getProximaFecha(eventoOcurrencias.getDiaInicio());
            var resObtenidoFechaFinOcurrencias = repeticionOcurrencias.getProximaFechaFin(eventoOcurrencias.getDiaFin());
            var resObtenidoFechaInicioFechaHasta = repeticionFechaHasta.getProximaFecha(eventoFechaHasta.getDiaInicio());
            var resObtenidoFechaFinFechaHasta = repeticionFechaHasta.getProximaFechaFin(eventoFechaHasta.getDiaFin());
            var resObtenidoFechaInicioInfinita = repeticionInfinita.getProximaFecha(eventoInfinito.getDiaInicio());
            var resObtenidoFechaFinInfinita = repeticionInfinita.getProximaFechaFin(eventoInfinito.getDiaFin());


            //Assert compruebo que sea efectivamente el próximo evento en cada repeticion
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioOcurrencias);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinOcurrencias);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioFechaHasta);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinFechaHasta);
            assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicioInfinita);
            assertEquals(resEsperadoFechaFin, resObtenidoFechaFinInfinita);

            var duracion2 = new Duracion();
            duracion2.setDiaInicio(eventoInstancia.getDiaInicio().plusDays(2));
            duracion2.setDiaFin(eventoInstancia.getDiaFin().plusDays(2));

            eventoInstancia = new InstanciaEvento(titulo, descripcion);
            eventoInstancia.setDuracion(duracion2);
        }
    }

}
    /*
    @Test
    public void GetProximaInstanciaEventoDD() {
        //Comprueba que el método GetProximaInstanciaEvento() de Repeticion Diaria se comporte como lo esperado
        //Diferente Dia

        //Arrange
        //defino los diferentes tipos de repeticiones
        var repeticionOcurrencias = new RepeticionDiaria(2, 15);
        var repeticionFechaHasta = new RepeticionDiaria(2, LocalDate.of(2023, 11, 11));
        var repeticionInfinita = new RepeticionDiaria(2);

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

            var resEsperadoFechaInicio = eventoInstancia.getFechaInicio().plusDays(2);
            var resEsperadoFechaFin = eventoInstancia.getFechaFin().plusDays(2);

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

            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusDays(2), eventoInstancia.getDiaFin().plusDays(2));
        }
    }

    @Test
    public void AlmacenarRepeticionesOcurrencias(){
        //Comprueba que el método GetAlmacenarRepeticiones() de Repeticion Diaria se comporte como lo esperado
        int ocurrencias = 15;
        var repeticionOcurrencias = new RepeticionDiaria(2, ocurrencias);

        //defino instancia inicio
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 11, 11));
        duracion.setDiaFin(LocalDate.of(2024, 12, 9));
        duracion.setHoraInicio(LocalTime.of(18, 30));
        duracion.setHoraFin(LocalTime.of(12, 21));
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        InstanciaEvento eventoOcurrencias = eventoInstancia;

        //defino evento
        var evento = new Evento();
        evento.setEventoInicial(eventoOcurrencias);
        evento.setRepeticion(repeticionOcurrencias);

        //Resultados Esperados
        var resEsperadoInfinitoOcurrencias = false;
        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        resEsperadoEventosIntervalo.add(eventoInstancia);

        //Act Resultados Obetenidos
        for (int i = 0; i < ocurrencias-1; i++) {
            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusDays(2), eventoInstancia.getDiaFin().plusDays(2));
            resEsperadoEventosIntervalo.add(eventoInstancia);
        }
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
        //Comprueba que el método GetAlmacenarRepeticiones() de Repeticion Anual se comporte como lo esperado

        //Assert
        //defino fechaHasta
        var fechaHasta = LocalDate.of(2027,10,11);
        var repeticionFechaHasta = new RepeticionDiaria(2, fechaHasta);

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


        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();

        //Resultados Esperados
        do {
            resEsperadoEventosIntervalo.add(eventoInstancia);
            eventoInstancia = eventoInstancia.Clone(eventoInstancia.getDiaInicio().plusDays(2), eventoInstancia.getDiaFin().plusDays(2));
        } while (eventoInstancia.getFechaInicio().toLocalDate().isBefore(fechaHasta) || (eventoInstancia.getFechaInicio().toLocalDate().isEqual(fechaHasta)));

        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        for (int i = 0; i< resEsperadoEventosIntervalo.size(); i++){
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaInicio(),resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaFin(),resObtenidoEventosIntervalo.get(i).getFechaFin());
        }
    }
}

 */