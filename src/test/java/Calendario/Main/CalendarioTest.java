package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaConEmail;
import Calendario.Alarmas.AlarmaConNotificacion;
import Calendario.Duracion.Duracion;
import Calendario.Enums.TiempoRelativo;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.RepeticionDiaria;
import Calendario.Tareas.Tarea;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CalendarioTest {

    @Test
    public void TestCalendarioVacio(){
        //Arrange
        var calendario = new Calendario();
        var alarmasResultado = calendario.getProximasAlarmas();
        var alarmasEsperadas = new HashSet<Alarma>();

        //Act
        var actividadesResultado = calendario.getActividades(LocalDateTime.now(), LocalDateTime.now().plusYears(10));
        var actividadesEsperadas = new ArrayList<Actividad>();

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
        assertEquals(alarmasEsperadas, alarmasResultado);

    }

    @Test
    public void TestAgregarYObtenerTarea() {
        //Arrange
        var calendario = new Calendario();
        var tarea = new Tarea(); //creada con los valores por default
        tarea.setDia(LocalDate.of(2023, 4, 22));
        tarea.setHora(LocalTime.of(15, 40));
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);
        var actividadesEsperadas = new ArrayList<Actividad>();
        actividadesEsperadas.add(tarea);

        //Act
        calendario.agregarTarea(tarea);
        var actividadesResultado = calendario.getActividades(fechaDesde, fechaHasta);

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);

    }

    @Test
    public void TestAgregarYObtenerEvento() {
        //Arrange
        var calendario = new Calendario();
        var evento = new Evento(); //creado con los valores por default
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracion.setDiaFin(LocalDate.of(2023, 4, 25));
        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        evento.setEventoInicial(primerEvento);
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);
        var actividadesEsperadas = new ArrayList<Actividad>();
        actividadesEsperadas.add(primerEvento);

        //Act
        calendario.agregarEvento(evento);
        var actividadesResultado = calendario.getActividades(fechaDesde, fechaHasta);


        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
    }

    @Test
    public void TestObtenerNingunaActividad() {
        //Arrange
        var calendario = new Calendario();
        var evento = new Evento(); //creado con los valores por default
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 23));
        duracion.setDiaFin(LocalDate.of(2023, 4, 25));
        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        evento.setEventoInicial(primerEvento);
        var tarea = new Tarea(); //creada con los valores por default
        tarea.setDia(LocalDate.of(2023, 4, 22));
        tarea.setHora(LocalTime.of(15, 40));
        var fechaDesde = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);
        var actividadesEsperadas = new ArrayList<Actividad>();

        //Act
        calendario.agregarEvento(evento);
        calendario.agregarTarea(tarea);
        var actividadesResultado = calendario.getActividades(fechaDesde, fechaHasta);


        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
    }
    @Test
    public void TestObtenerVariasActividades() {

        //Arrange

        var calendario = new Calendario();

        //evento sin repetición
        var evento1 = new Evento(); //creado con los valores por default
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 23));
        duracion.setDiaFin(LocalDate.of(2023, 4, 25));
        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        evento1.setEventoInicial(primerEvento);

        //evento con repetición
        var evento2 = new Evento(); //creado con los valores por default
        var repeticion = new RepeticionDiaria(1);
        var primerEvento2 = primerEvento.Clone(duracion.getDiaInicio().minusDays(2), duracion.getDiaFin());
        evento2.setEventoInicial(primerEvento2);
        evento2.setRepeticion(repeticion);

        //tarea
        var tarea = new Tarea(); //creada con los valores por default
        tarea.setDia(LocalDate.of(2023, 4, 23));
        tarea.setHora(LocalTime.of(15, 40));

        //intervalo
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);

        // actividades esperadas --> tarea 22/4, primerEvento 23/4
        // El primerEvento2 es el 21/4 y no entra en el intervalo. El segundoEvento2 comienza el 22/4, pero antes de las 15:30
        var tercerEvento2 = evento2.getProximaRepeticion(LocalDateTime.of(2023, 4, 22, 15, 30)); // 23/4
        var cuartoEvento2 = evento2.getProximaRepeticion(tercerEvento2); // 24/3
        var actEsperadas = 4;

        //Act
        calendario.agregarEvento(evento1);
        calendario.agregarEvento(evento2);
        calendario.agregarTarea(tarea);
        var actividadesResultado = calendario.getActividades(fechaDesde, fechaHasta);

        //Assert
        assertEquals(actEsperadas, actividadesResultado.size());
        assertTrue(actividadesResultado.contains(tarea));
        assertTrue(actividadesResultado.contains(primerEvento));
        assertTrue(actividadesResultado.contains(tercerEvento2));
        assertTrue(actividadesResultado.contains(cuartoEvento2));

    }

    @Test
    public void TestGetProximasAlarmas() {
        //Arrange
        var fecha1 = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma1 = new AlarmaConNotificacion(fecha1);
        var alarma2 = new AlarmaConNotificacion(10, TiempoRelativo.MINUTOS);
        var fecha2 = fecha1.minusMinutes(10);
        var alarma3 = new AlarmaConNotificacion(fecha2);
        var alarma4 = new AlarmaConEmail(fecha2);
        var tarea1 = new Tarea();
        tarea1.setDia(fecha1.toLocalDate());
        tarea1.setHora(fecha1.toLocalTime());
        var tarea2 = new Tarea();
        tarea2.setDia(fecha2.toLocalDate());
        tarea2.setHora(fecha2.toLocalTime());
        var calendario = new Calendario();
        calendario.agregarTarea(tarea1);
        calendario.agregarTarea(tarea2);
        var cantidadEsperadas = 3;

        //Act
        calendario.configurarAlarma(tarea1, alarma1);
        calendario.configurarAlarma(tarea1, alarma2);
        calendario.configurarAlarma(tarea2, alarma3);
        calendario.configurarAlarma(tarea2, alarma4);
        Set<Alarma> resultado = calendario.getProximasAlarmas();

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma2));
        assertTrue(resultado.contains(alarma3));
        assertTrue(resultado.contains(alarma4));

    }

    @Test
    public void TestCompletarTarea() {
        //Arrange
        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var tarea = new Tarea();
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var calendario = new Calendario();
        calendario.agregarTarea(tarea);
        var tamanioEsperado = 0;

        //Act
        calendario.completarTarea(tarea);
        var resultado = calendario.getActividades(fecha.minusDays(1), fecha.plusDays(1)).size();

        //Assert
        assertEquals(tamanioEsperado, resultado);
    }

    @Test
    public void TestModificarTitulo() {
        //Arrange
        var calendario = new Calendario();
        var tarea = new Tarea();
        tarea.setTitulo("título viejo");
        var tituloNuevo = "título nuevo";
        calendario.agregarTarea(tarea);

        //Act
        calendario.modificarTitulo(tarea, tituloNuevo);
        var resultado = tarea.getTitulo();

        //Assert
        assertEquals(tituloNuevo, resultado);
    }

    @Test
    public void TestModificarDescripcion() {
        //Arrange
        var evento = new Evento();
        evento.setDescripcion("descripción vieja");
        var descripcionNueva = "descripción nueva";
        var calendario = new Calendario();
        calendario.agregarEvento(evento);

        //Act
        calendario.modificarDescripcion(evento, descripcionNueva);
        var resultado = evento.getDescripcion();

        //Assert
        assertEquals(descripcionNueva, resultado);
    }

    @Test
    public void TestModificarDia() {
        //Arrange
        var calendario = new Calendario();
        var tarea = new Tarea();
        tarea.setDia(LocalDate.of(2023, 4, 22));
        var fechaNueva = LocalDate.of(2023, 4, 23);
        calendario.agregarTarea(tarea);

        //Act
        calendario.modificarDiaTarea(tarea, fechaNueva);
        var resultado = tarea.getFecha().toLocalDate();

        //Assert
        assertEquals(fechaNueva, resultado);
    }

    @Test
    public void TestModificarHoraTarea() {
        //Arrange
        var calendario = new Calendario();
        var tarea = new Tarea();
        tarea.setHora(LocalTime.of(20, 0));
        var horaNueva = LocalTime.of(20, 5);
        calendario.agregarTarea(tarea);

        //Act
        calendario.modificarHoraTarea(tarea, horaNueva);
        var resultado = tarea.getFecha().toLocalTime();

        //Assert
        assertEquals(horaNueva, resultado);
    }

    @Test
    public void TestModificarFechaEvento() {
        //Arrange
        var calendario = new Calendario();

        var evento = new Evento();
        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));
        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracionInicial);
        evento.setEventoInicial(eventoInicial);
        calendario.agregarEvento(evento);

        var duracionNueva = new Duracion();
        var fechaFinNueva = LocalDate.of(2023, 4, 22);
        duracionNueva.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionNueva.setDiaFin(fechaFinNueva);
        duracionNueva.setHoraInicio(LocalTime.of(20, 0));
        duracionNueva.setHoraFin(LocalTime.of(20, 5));
        var eventoNuevo = new InstanciaEvento();
        eventoNuevo.setDuracion(duracionNueva);

        //Act
        calendario.modificarFechaEvento(evento, eventoNuevo);
        var resultado = evento.getProximaRepeticion( LocalDateTime.of(2023, 4, 21, 0, 0)).getFechaFin().toLocalDate();

        //Assert
        assertEquals(fechaFinNueva, resultado);
    }

    @Test
    public void configurarAlarma() {
        //Arrange
        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma = new AlarmaConNotificacion(fecha);
        var tarea = new Tarea();
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var calendario = new Calendario();
        calendario.agregarTarea(tarea);
        var cantidadEsperadas = 1;

        //Act
        calendario.configurarAlarma(tarea, alarma);
        var resultado = tarea.getAlarmas();

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma));
    }

    @Test
    public void modificarAlarma() {
        //Arrange
        var fecha1 = LocalDateTime.of(2023, 4, 22, 15, 30);
        var alarma1 = new AlarmaConNotificacion(fecha1);
        var fecha2 = LocalDateTime.of(2023, 4, 22, 16, 50);
        var alarma2 = new AlarmaConNotificacion(fecha2);
        var evento= new Evento();
        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));
        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracionInicial);
        evento.setEventoInicial(eventoInicial);
        var calendario = new Calendario();
        calendario.agregarEvento(evento);
        var cantidadEsperadas = 1;
        calendario.configurarAlarma(evento, alarma1);

        //Act
        calendario.modificarAlarma(evento, alarma1, alarma2);
        var resultado = evento.getAlarmas();

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma2));
    }

    @Test
    public void eliminarAlarma() {
        //Arrange
        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma = new AlarmaConNotificacion(fecha);
        var tarea = new Tarea();
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var calendario = new Calendario();
        calendario.agregarTarea(tarea);
        var cantidadEsperadas = 0;
        calendario.configurarAlarma(tarea, alarma);

        //Act
        calendario.eliminarAlarma(tarea, alarma);
        var resultado = tarea.getAlarmas();

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertFalse(resultado.contains(alarma));
    }

    @Test
    public void modificarRepeticionEvento() {
        //Arrange
        var evento= new Evento();
        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracion.setDiaFin(LocalDate.of(2023, 4, 22));
        duracion.setHoraInicio(LocalTime.of(20, 0));
        duracion.setHoraFin(LocalTime.of(20, 5));
        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracion);
        evento.setEventoInicial(eventoInicial);
        var repeticion = new RepeticionDiaria(2, 2);
        var calendario = new Calendario();
        calendario.agregarEvento(evento);

        //Act
        calendario.modificarRepeticionEvento(evento, repeticion);
        var segundoEvento = evento.getProximaRepeticion(eventoInicial);
        var tercerEvento = evento.getProximaRepeticion(segundoEvento);
        var fechaEsperada = LocalDateTime.of(2023, 4, 24, 20, 0);
        var resultado = segundoEvento.getFechaInicio();

        //Assert
        assertNull(tercerEvento);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void eliminarTarea() {
        //Arrange
        var calendario = new Calendario();
        var tarea = new Tarea();
        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        calendario.agregarTarea(tarea);
        var tamanioEsperado = 0;

        //Act
        calendario.eliminarTarea(tarea);
        var resultado = calendario.getActividades(fecha.minusDays(1), fecha.plusDays(1)).size();

        //Assert
        assertEquals(tamanioEsperado, resultado);
    }

    @Test
    public void eliminarEvento() {
        //Arrange
        var evento= new Evento();
        var duracion = new Duracion();
        var fechaInicio = LocalDateTime.of(2023, 4, 22, 20, 0);
        var fechaFin = LocalDateTime.of(2023, 4, 22, 20, 5);
        duracion.setDiaInicio(fechaInicio.toLocalDate());
        duracion.setDiaFin(fechaFin.toLocalDate());
        duracion.setHoraInicio(fechaInicio.toLocalTime());
        duracion.setHoraFin(fechaFin.toLocalTime());
        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracion);
        evento.setEventoInicial(eventoInicial);
        var calendario = new Calendario();
        calendario.agregarEvento(evento);
        var cantidadEsperadas = 0;

        //Act
        calendario.eliminarEvento(evento);
        var resultado = calendario.getActividades(fechaInicio.minusDays(1), fechaFin.plusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
    }
}