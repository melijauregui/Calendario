package Calendario.Eventos;

import Calendario.Duracion.Duracion;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionMensual;
import Calendario.Repeticiones.RepeticionSemanal;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventoTest {
    @Test
    public void TestEventoSetsAnsGetters(){
        //Act
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);
        var repeticion = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), 9);

        var duracion = new Duracion();

        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracion);
        evento.setEventoInicial(eventoInicial);


        evento.setRepeticion(repeticion);


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

        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();

        //Act
        var resObtenidoEventosIntervalo = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo.size());
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
    public void TestEventoSetsAnsGettersCambiarlos(){
        //Act
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescripcion(descripcion);

        var duracion = new Duracion();

        var diaInicio = LocalDate.of(2023, 1, 15);
        var diaFin = LocalDate.of(2023, 1, 15);
        var horaInicio = LocalTime.of(22, 00);
        var horaFin = LocalTime.of(23, 00);
        duracion.setDiaInicio(diaInicio);
        duracion.setHoraInicio(horaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraFin(horaFin);

        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracion);
        evento.setEventoInicial(eventoInicial);


        List<InstanciaEvento> resEsperadoEventosIntervalo = new ArrayList<>();
        resEsperadoEventosIntervalo.add(eventoInicial);

        var ocurrencias = 10;
        var repeticion1 = new RepeticionMensual(2, ocurrencias);;
        evento.setRepeticion(repeticion1);

        //Act
        for (int i = 0; i < ocurrencias-1; i++) {
            eventoInicial = eventoInicial.Clone(eventoInicial.getDiaInicio().plusMonths(2), eventoInicial.getDiaFin().plusMonths(2));
            resEsperadoEventosIntervalo.add(eventoInicial);
        }
        var resObtenidoEventosIntervalo1 = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoEventosIntervalo.size(), resObtenidoEventosIntervalo1.size());
        for (int i = 0; i< resEsperadoEventosIntervalo.size(); i++){
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaInicio(),resObtenidoEventosIntervalo1.get(i).getFechaInicio());
            assertEquals(resEsperadoEventosIntervalo.get(i).getFechaFin(),resObtenidoEventosIntervalo1.get(i).getFechaFin());
        }


        var repeticion2 = new RepeticionSemanal(1, List.of(DayOfWeek.SUNDAY), 9);
        evento.setRepeticion(repeticion2);


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

        var resObtenidoTitulo = evento.getTitulo();
        var resObtenidoDescripcion = evento.getDescripcion();

        //Act
        var resObtenidoEventosIntervalo2 = evento.getAlmacenamientoFechas();

        //Assert
        assertEquals(resEsperadoFechasInicioALmacenadas.size(), resObtenidoEventosIntervalo2.size());
        for (int i = 0; i < resEsperadoFechasInicioALmacenadas.size(); i++) {
            assertEquals(resEsperadoFechasInicioALmacenadas.get(i), resObtenidoEventosIntervalo2.get(i).getFechaInicio());
            assertEquals(resEsperadoFechasFinALmacenadas.get(i), resObtenidoEventosIntervalo2.get(i).getFechaFin());
        }
        assertEquals(resEsperadoTitulo, resObtenidoTitulo);
        assertEquals(resEsperadoDescripcion, resObtenidoDescripcion);
    }

}