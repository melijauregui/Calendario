package Calendario.Main;
/*
import Calendario.Duracion.Duracion;
import Calendario.Enums.TiempoRelativo;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Repeticiones.RepeticionDiaria;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
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
        var actividadesResultado = calendario.getActividadesEnElIntervalo(LocalDateTime.now(), LocalDateTime.now().plusYears(10));
        var actividadesEsperadas = new ArrayList<Actividad>();

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
        assertEquals(alarmasEsperadas, alarmasResultado);

    }

    @Test
    public void TestCrearYObtenerTareaConVencimiento() {
        //Arrange
        var calendario = new Calendario();


        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        //Act
        var tarea = calendario.crearTarea(titulo, descripcion, LocalDateTime.of(2023, 4, 22, 15, 40));
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadesEsperadas = new ArrayList<Actividad>();
        actividadesEsperadas.add(tarea);

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);

    }

    @Test
    public void TestCrearYObtenerEventoSinRepeticion() {
        //Arrange
        var calendario = new Calendario();

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracion.setDiaFin(LocalDate.of(2023, 4, 25));
        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        //Act
        var evento = calendario.crearEvento(titulo, descripcion, duracion);
        calendario.modificarFechaEvento(evento, primerEvento);
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadesEsperadas = new ArrayList<Actividad>();
        actividadesEsperadas.add(primerEvento);

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
    }

    @Test
    public void TestCrearYObtenerTareaDiaCompleto() {
        //Arrange
        var calendario = new Calendario();
        String titulo = "titulo";
        String descripcion = "descripción";
        LocalDate diaTarea = LocalDate.of(2023, 4, 22);

        var fechaDesde = LocalDateTime.of(2023, 4, 21, 23, 59);
        var fechaHasta = LocalDateTime.of(2023, 4, 23, 0, 1);

        //Act
        var tarea = calendario.crearTarea(titulo, descripcion, diaTarea);
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var tareaResultado = actividadesResultado.get(0);

        //Assert
        assertEquals(tarea, tareaResultado);
    }

    @Test
    public void TestCrearYObtenerEventoConRepeticion() {
        //Arrange
        var calendario = new Calendario();
        var duracion = new Duracion();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 22, 15, 31);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 25, 0, 0);
        duracion.setDiaInicio(fechaInicio.toLocalDate());
        duracion.setDiaFin(fechaFin.toLocalDate());
        duracion.setHoraInicio(fechaInicio.toLocalTime());
        duracion.setHoraFin(fechaFin.toLocalTime());
        String titulo = "titulo";
        String descripcion = "descripción";
        var repeticion = new RepeticionDiaria(3, 3);

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion, duracion, repeticion);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestObtenerNingunaActividad() {
        //Arrange
        var calendario = new Calendario();

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 23));
        duracion.setDiaFin(LocalDate.of(2023, 4, 25));
        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);



        var fechaDesde = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);
        var actividadesEsperadas = new ArrayList<Actividad>();

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        //Act
        var evento = calendario.crearEvento(titulo, descripcion, duracion);
        calendario.modificarFechaEvento(evento, primerEvento);
        var tarea = calendario.crearTarea(titulo, descripcion, LocalDate.of(2023, 4, 22));

        calendario.modificarHoraTarea(tarea,LocalTime.of(15, 40));
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);


        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);
    }
    @Test
    public void TestObtenerVariasActividades() {

        //Arrange

        var calendario = new Calendario();

        //evento sin repetición
        var diaInicio = LocalDate.of(2023, 4, 23);
        var diaFin = LocalDate.of(2023, 4, 25);

        var duracion1 = new Duracion();
        duracion1.setDiaInicio(diaInicio);
        duracion1.setDiaFin(diaFin);

        var duracion2 = new Duracion();
        duracion2.setDiaInicio(diaInicio);
        duracion2.setDiaFin(diaFin);

        var primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion1);

        //evento con repetición
        var repeticion = new RepeticionDiaria(1);

        //intervalo
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        //tarea
        var fechaTarea =  LocalDateTime.of(2023, 4, 23, 15, 40);

        //Act
        var evento1 = calendario.crearEvento(titulo, descripcion, duracion1);
        calendario.modificarFechaEvento(evento1, primerEvento);

        var evento2 = calendario.crearEvento(titulo, descripcion, duracion2, repeticion);

        var tarea = calendario.crearTarea(titulo, descripcion, fechaTarea);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);

        // actividades esperadas --> tarea 22/4, primerEvento 23/4
        // El primerEvento2 es el 21/4 y no entra en el intervalo. El segundoEvento2 comienza el 22/4, pero antes de las 15:30
        var tercerEvento2 = evento2.getProximaRepeticion(LocalDateTime.of(2023, 4, 22, 15, 30)); // 23/4
        var cuartoEvento2 = evento2.getProximaRepeticion(tercerEvento2); // 24/3
        var actEsperadas = 4;

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
        var calendario = new Calendario();


        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var tarea1 = calendario.crearTarea(titulo, descripcion, fecha1);

        var tarea2 = calendario.crearTarea(titulo, descripcion, fecha2);

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

        var calendario = new Calendario();

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var tamanioEsperado = 0;

        //Act
        calendario.completarTarea(tarea);
        var resultado = calendario.getActividadesEnElIntervalo(fecha.minusDays(1), fecha.plusDays(1)).size();

        //Assert
        assertEquals(tamanioEsperado, resultado);
    }

    @Test
    public void TestModificarTitulo() {
        //Arrange
        var calendario = new Calendario();
        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var tituloNuevo = "título nuevo";

        //Act
        calendario.modificarTitulo(tarea, tituloNuevo);
        var resultado = tarea.getTitulo();

        //Assert
        assertEquals(tituloNuevo, resultado);
    }

    @Test
    public void TestModificarDescripcion() {
        //Arrange
        var calendario = new Calendario();
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var diaInicio = LocalDate.of(2023, 4, 23);
        var diaFin = LocalDate.of(2023, 4, 25);

        var duracion = new Duracion();
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var evento = calendario.crearEvento(titulo, descripcion, duracion);

        var descripcionNueva = "descripción nueva";

        //Act
        calendario.modificarDescripcion(evento, descripcionNueva);
        var resultado = evento.getDescripcion();

        //Assert
        assertEquals(descripcionNueva, resultado);
    }

    @Test
    public void TestModificarDiaTarea() {
        //Arrange
        var calendario = new Calendario();

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        var diaviejo = LocalDate.of(2023, 4, 22);

        var tarea = calendario.crearTarea(titulo, descripcion, diaviejo);

        var fechaNueva = LocalDate.of(2023, 4, 23);

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

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fechaVieja = LocalDateTime.of(2100, 4, 22, 20, 0);

        var tarea = calendario.crearTarea(titulo, descripcion,fechaVieja);

        var horaNueva = LocalTime.of(20, 5);
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

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));

        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracionInicial);

        var evento = calendario.crearEvento(titulo, descripcion, duracionInicial);

        var fechaFinNueva = LocalDate.of(2023, 4, 22);
        var duracionNueva = new Duracion();
        duracionNueva.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionNueva.setDiaFin(LocalDate.of(2023, 4, 22));
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
    public void TestConfigurarAlarma() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma = new AlarmaConNotificacion(fecha);

        var calendario = new Calendario();

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var cantidadEsperadas = 1;

        //Act
        calendario.configurarAlarma(tarea, alarma);
        var resultado = tarea.getAlarmas();

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma));
    }

    @Test
    public void TestModificarAlarma() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha1 = LocalDateTime.of(2023, 4, 22, 15, 30);
        var alarma1 = new AlarmaConNotificacion(fecha1);

        var fecha2 = LocalDateTime.of(2023, 4, 22, 16, 50);
        var alarma2 = new AlarmaConNotificacion(fecha2);

        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));

        var calendario = new Calendario();
        var evento = calendario.crearEvento(titulo, descripcion, duracionInicial);


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
    public void TestEliminarAlarma() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma = new AlarmaConNotificacion(fecha);
        var calendario = new Calendario();

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

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
    public void TestModificarRepeticionEvento() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var duracion = new Duracion();
        duracion.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracion.setDiaFin(LocalDate.of(2023, 4, 22));
        duracion.setHoraInicio(LocalTime.of(20, 0));
        duracion.setHoraFin(LocalTime.of(20, 5));
        var eventoInicial = new InstanciaEvento();
        eventoInicial.setDuracion(duracion);
        var calendario = new Calendario();

        var evento = calendario.crearEvento(titulo, descripcion, duracion);
        calendario.modificarFechaEvento(evento, eventoInicial);
        var repeticion = new RepeticionDiaria(2, 2);

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
    public void TestEliminarTarea() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        var calendario = new Calendario();

        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var tamanioEsperado = 0;

        //Act
        calendario.eliminarTarea(tarea);
        var resultado = calendario.getActividadesEnElIntervalo(fecha.minusDays(1), fecha.plusDays(1)).size();

        //Assert
        assertEquals(tamanioEsperado, resultado);
    }

    @Test
    public void TestEliminarEvento() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var duracion = new Duracion();
        var fechaInicio = LocalDateTime.of(2023, 4, 22, 20, 0);
        var fechaFin = LocalDateTime.of(2023, 4, 22, 20, 5);
        duracion.setDiaInicio(fechaInicio.toLocalDate());
        duracion.setDiaFin(fechaFin.toLocalDate());
        duracion.setHoraInicio(fechaInicio.toLocalTime());
        duracion.setHoraFin(fechaFin.toLocalTime());


        var calendario = new Calendario();

        var evento = calendario.crearEvento(titulo, descripcion, duracion);

        var cantidadEsperadas = 0;

        //Act
        calendario.eliminarEvento(evento);
        var resultado = calendario.getActividadesEnElIntervalo(fechaInicio.minusDays(1), fechaFin.plusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
    }
}

 */