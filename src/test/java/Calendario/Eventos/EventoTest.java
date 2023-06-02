package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Duracion.Duracion;
import Calendario.Enums.TiempoRelativo;
import Calendario.Repeticiones.RepeticionMensual;
import Calendario.Repeticiones.RepeticionSemanal;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class EventoTest {
    @Test
    public void TestEventoSetsAnsGetters() {
        //Comprueba que los setters y getters de Evento se comporte como lo esperado

        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";

        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        evento.setDuracion(duracion);

        //Act Resultados Obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();

        //Assert
        assertEquals(titulo, resObtenidoTitulo);
        assertEquals(descripcion, resObtenidoDescripcion);
    }

    @Test
    public void TestEventoGetRepeticiones() {
        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";

        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);

        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        //seteo la duración y su repeticion
        evento.setDuracion(duracion);
        evento.setRepeticion(repeticion);

        //Resueltados esperados
        var resEsperadoTitulo = titulo;
        var resEsperadoDescripcion = descripcion;
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados Obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();
        var resObtenidoEventosIntervalo = evento.getRepeticionesEnIntervalo(duracion.getFechaInicio(), LocalDateTime.of(2024, 3, 12, 23, 00));

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo.size());
        //Compruebo que los eventos alamacenados sean los correctos
        for (int i = 0; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenidoEventosIntervalo.get(i).getFechaFin());
            assertEquals(resEsperadoTitulo, resObtenidoEventosIntervalo.get(i).getTitulo());
            assertEquals(resEsperadoDescripcion, resObtenidoEventosIntervalo.get(i).getDescripcion());
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }

    @Test
    public void TestEventoCambiarRepeticion() {
        //Comprueba que al cambiar la repeticion de un evento se comporte como lo esperado

        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";

        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);
        evento.setDuracion(duracion);

        //defino repeticion1
        var ocurrencias = 10;
        var repeticion1 = new RepeticionMensual(2, null, ocurrencias);
        evento.setRepeticion(repeticion1);

        //Resultados esperados
        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        var eventoInicial = evento.crearInstancia(diaInicio, diaFin);
        resEsperadoEventosIntervalo.add(eventoInicial);
        for (int i = 0; i < ocurrencias - 1; i++) {
            eventoInicial = evento.crearInstancia(eventoInicial.getDiaInicio().plusMonths(2), eventoInicial.getDiaFin().plusMonths(2));
            resEsperadoEventosIntervalo.add(eventoInicial);
        }

        //Act Resultados Obtenidos
        var resObtenidoEventosIntervalo1 = evento.getRepeticionesEnIntervalo(LocalDateTime.of(diaInicio, horaInicio), eventoInicial.getFechaFin());

        //Assert
        assertEquals(resEsperadoEventosIntervalo.size(), resObtenidoEventosIntervalo1.size());
        //Compruebo que los eventos alamacenados sean los correctos
        for (int i = 0; i < resEsperadoEventosIntervalo.size(); i++) {
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaInicio(), resObtenidoEventosIntervalo1.get(i).getFechaInicio());
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaFin(), resObtenidoEventosIntervalo1.get(i).getFechaFin());
        }

        //Modifico repeticion
        var repeticion2 = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);
        evento.setRepeticion(repeticion2);

        //Resultados Esperados nueva repeticion
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados obtenidos luego de cambiar repeticion
        var resObtenidoEventosIntervalo2 = evento.getRepeticionesEnIntervalo(LocalDateTime.of(2023, 1, 15, 22, 00), LocalDateTime.of(2023, 3, 12, 23, 00));

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo2.size());
        //Compruebo que los eventos alamacenados sean los correctos
        for (int i = 0; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenidoEventosIntervalo2.get(i).getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenidoEventosIntervalo2.get(i).getFechaFin());
        }
    }

    @Test
    public void TestEventoGetProximoEvento() {
        //Comprueba que el método getProximoEvento(fecha) de Evento se comporte como lo esperado
        //El evento es finito

        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        //seteo en evento su duracion y repeticion
        evento.setDuracion(duracion);
        evento.setRepeticion(repeticion);

        //Resultados Esperados
        var resEsperadoTitulo = titulo;
        var resEsperadoDescripcion = descripcion;
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();

        //Número para comprobar que den los resultados esperados
        var diaAnteriorPrueba = 2; //debe estar entre [1,6] para que las pruebas sigan dando como lo esperado
        //Assert

        var proximasRepeticiones = evento.getRepeticionesEnIntervalo(
                resEsperadoFechasInicioALmacenadas.get(0).minusDays(diaAnteriorPrueba),
                resEsperadoFechasFinALmacenadas.get(resEsperadoFechasFinALmacenadas.size()-1)
                );

        for (int i = 0; i < proximasRepeticiones.size(); i++) {
            //Compruebo que ante la fecha dada de efectivamente el próximo evento
            var diaFechaInicio = resEsperadoFechasInicioALmacenadas.get(i).minusDays(diaAnteriorPrueba);
            var diaFechaFin = resEsperadoFechasFinALmacenadas.get(i);
            var resObtenido = proximasRepeticiones.get(i);
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenido.getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenido.getFechaFin());
            assertEquals(resEsperadoTitulo, resObtenido.getTitulo());
            assertEquals(resEsperadoDescripcion, resObtenido.getDescripcion());
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }



    @Test
    public void TestEventoGetProximoEventoInfinito() {
        //Comprueba que el método getProximoEvento() de Evento se comporte como lo esperado
        //El evento es finito

        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null, 0);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);


        //seteo en evento el dia inicial y su repeticion
        evento.setDuracion(duracion);
        evento.setRepeticion(repeticion);

        //Resultados Esperados
        var resEsperadoTitulo = titulo;
        var resEsperadoDescripcion = descripcion;
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();
        var proximasRepeticiones = evento.getRepeticionesEnIntervalo(
                resEsperadoFechasInicioALmacenadas.get(0).minusDays(1),
                resEsperadoFechasFinALmacenadas.get(resEsperadoFechasFinALmacenadas.size()-1)
        );

        //Número para comprobar que den los resultados esperados
        var diaAnteriorPrueba = 2; //debe estar entre [1,6] para que las pruebas sigan dando como lo esperado
        //Assert
        for (int i = 0; i < proximasRepeticiones.size(); i++) {
            //Compruebo que ante la fecha dada de efectivamente el próximo evento
            var diaFechaInicio = resEsperadoFechasInicioALmacenadas.get(i).minusDays(diaAnteriorPrueba);
            var diaFechaFin = resEsperadoFechasFinALmacenadas.get(i).minusDays(diaAnteriorPrueba);
            var resObtenido = proximasRepeticiones.get(i);
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenido.getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenido.getFechaFin());
            assertEquals(resEsperadoTitulo, resObtenido.getTitulo());
            assertEquals(resEsperadoDescripcion, resObtenido.getDescripcion());
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }


    @Test
    public void TestEventoGetProximoEvento2() {
        //Comprueba que el método getProximoEvento(evento) de Evento se comporte como lo esperado
        //El evento es finito

        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);


        //seteo en evento el dia inicial y su repeticion
        evento.setDuracion(duracion);
        evento.setRepeticion(repeticion);

        //Resultados Esperados
        var resEsperadoTitulo = titulo;
        var resEsperadoDescripcion = descripcion;
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();
        var proximasRepeticiones = evento.getRepeticionesEnIntervalo(
                resEsperadoFechasInicioALmacenadas.get(0).minusDays(1),
                resEsperadoFechasFinALmacenadas.get(resEsperadoFechasFinALmacenadas.size()-1)
        );

        //Assert
        var eventoInicial = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());
        for (int i = 1; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            //Compruebo que ante el evento dado de efectivamente el próximo evento
            var resObtenido = proximasRepeticiones.get(i);
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenido.getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenido.getFechaFin());
            assertEquals(resEsperadoTitulo, resObtenido.getTitulo());
            assertEquals(resEsperadoDescripcion, resObtenido.getDescripcion());
            eventoInicial = resObtenido;
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }

    @Test
    public void TestEventoGetProximoEventoInfinito2() {
        //Comprueba que el método getProximoEvento(evento) de Evento se comporte como lo esperado
        //El evento es infinito

        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null, 0);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        //seteo en evento el dia inicial y su repeticion
        evento.setDuracion(duracion);
        evento.setRepeticion(repeticion);

        //Resultados Esperados
        var resEsperadoTitulo = titulo;
        var resEsperadoDescripcion = descripcion;
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados obtenidos
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();
        var proximasRepeticiones = evento.getRepeticionesEnIntervalo(
                resEsperadoFechasInicioALmacenadas.get(0).minusDays(1),
                resEsperadoFechasFinALmacenadas.get(resEsperadoFechasFinALmacenadas.size()-1)
        );

        //Assert
        var eventoInicial = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());
        for (int i = 1; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            var resObtenido = proximasRepeticiones.get(i);
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenido.getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenido.getFechaFin());
            assertEquals(resEsperadoTitulo, resObtenido.getTitulo());
            assertEquals(resEsperadoDescripcion, resObtenido.getDescripcion());
            eventoInicial = resObtenido;
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }

    @Test
    public void TestEventoSetsAnsGettersCambiarDiaInicio() {
        //Comprueba que al cambiar la fecha de inicio del evento se comporte como lo esperado

        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        //defino dia Inicio
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        //seteo en evento el dia inicial y su repeticion
        evento.setDuracion(duracion);

        //defino repeticion
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);
        evento.setRepeticion(repeticion);

        //Resultados Esperados
        var eventoInicial = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());
        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        resEsperadoEventosIntervalo.add(eventoInicial);
        var resEsperadoFechasInicioALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 22, 00),
                LocalDateTime.of(2023, 1, 22, 22, 00),
                LocalDateTime.of(2023, 1, 29, 22, 00),
                LocalDateTime.of(2023, 2, 5, 22, 00),
                LocalDateTime.of(2023, 2, 12, 22, 00),
                LocalDateTime.of(2023, 2, 19, 22, 00),
                LocalDateTime.of(2023, 2, 26, 22, 00),
                LocalDateTime.of(2023, 3, 5, 22, 00),
                LocalDateTime.of(2023, 3, 12, 22, 00)
        );
        var resEsperadoFechasFinALmacenadas = List.of(
                LocalDateTime.of(2023, 1, 15, 23, 00),
                LocalDateTime.of(2023, 1, 22, 23, 00),
                LocalDateTime.of(2023, 1, 29, 23, 00),
                LocalDateTime.of(2023, 2, 5, 23, 00),
                LocalDateTime.of(2023, 2, 12, 23, 00),
                LocalDateTime.of(2023, 2, 19, 23, 00),
                LocalDateTime.of(2023, 2, 26, 23, 00),
                LocalDateTime.of(2023, 3, 5, 23, 00),
                LocalDateTime.of(2023, 3, 12, 23, 00)
        );

        //Act Resultados Obtenidos con el primer evento Inicial
        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();
        var resObtenidoEventosIntervalo = evento.getRepeticionesEnIntervalo(LocalDateTime.of(2022, 1, 15, 22, 00), LocalDateTime.of(2024, 3, 12, 23, 00));

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo.size());
        for (int i = 0; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenidoEventosIntervalo.get(i).getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenidoEventosIntervalo.get(i).getFechaFin());
        }

        //defino segundo evento inicial
        var duracion2 = new Duracion();
        var diaInicio2 = LocalDate.of(2023, 1, 22);
        var diaFin2 = LocalDate.of(2023, 1, 22);
        var horaInicio2 = LocalTime.of(22, 00);
        var horaFin2 = LocalTime.of(23, 00);
        duracion2.setDiaInicio(diaInicio2);
        duracion2.setHoraInicio(horaInicio2);
        duracion2.setDiaFin(diaFin2);
        duracion2.setHoraFin(horaFin2);

        //seteo el nuevo dia Inicio
        evento.setDuracion(duracion2);

        //Act 2
        var resObtenidoEventosIntervalo2 = evento.getRepeticionesEnIntervalo(LocalDateTime.of(2022, 1, 15, 22, 00), LocalDateTime.of(2024, 3, 12, 23, 00));
        ;

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo2.size());
        for (int i = 0; i < resObtenidoEventosIntervalo2.size() - 1; i++) {
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i + 1), resObtenidoEventosIntervalo2.get(i).getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i + 1), resObtenidoEventosIntervalo2.get(i).getFechaFin());
        }

    }

    @Test
    public void TestEventoConfigurarAlarmas() {
        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY),null, 9);

        var duracion = new Duracion();

        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        evento.setDuracion(duracion);

        evento.setRepeticion(repeticion);

        AlarmaEvento alarma1 = new AlarmaEvento(1, TiempoRelativo.HORAS, new AvisoNotificacion());
        AlarmaEvento alarma2 = new AlarmaEvento(10, TiempoRelativo.HORAS, new AvisoNotificacion());


        //Act
        evento.agregarAlarma(alarma1);
        evento.agregarAlarma(alarma2);

        var instancia = evento.crearInstancia(duracion.getDiaInicio(), duracion.getDiaFin());


        // alarmas próximas a 'fechaDesde' --> corresponden a la segunda alarma del evento inicial, porque es la primera
        // que suena
        var fechaDesde = LocalDateTime.of(diaInicio.minusDays(1), horaInicio);
        var j = instancia.getProximasAlarmas(fechaDesde);
        var alarmasProximas = evento.getProximasAlarmas(fechaDesde);

        var alarmasEsperadas = new ArrayList<LocalDateTime>();
        alarmasEsperadas.add(LocalDateTime.of(diaInicio, horaInicio).minusHours(10));
        var tamanioEsperado = 1;
        List<Alarma> alarmasFechas = alarmasProximas.stream().toList();

        //Assert
        assertEquals(tamanioEsperado, alarmasProximas.size());

        for (Alarma f : alarmasFechas) {
           alarmasEsperadas.get(0).isEqual(f.getFechaAlarma());
        }
    }

    @Test
    public void TestEventoEliminarAlarmas() {
        //Arrange
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), null,9);

        var duracion = new Duracion();

        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        var eventoInicial = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());
        evento.setDuracion(duracion);

        evento.setRepeticion(repeticion);

        var alarma1 = new AlarmaEvento(2, TiempoRelativo.HORAS, new AvisoNotificacion());
        evento.agregarAlarma(alarma1);

        //Act
        evento.eliminarAlarma(alarma1);
        var fechaDesde = LocalDateTime.of(diaInicio.minusDays(1), horaInicio);
        var alarmasProximas = evento.getProximasAlarmas(fechaDesde);
        var tamanioEsperado = 0;

        //Assert
        assertEquals(tamanioEsperado, alarmasProximas.size());
    }

}
