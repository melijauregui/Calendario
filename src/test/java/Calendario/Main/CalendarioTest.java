package Calendario.Main;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.ActividadMutable;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoEmail;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Duracion.Duracion;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Main.Builders.*;
import Calendario.Repeticiones.RepeticionDiaria;
import Calendario.Tareas.Tarea;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Calendario.Alarmas.*;

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
        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, LocalDateTime.of(2023, 4, 22, 15, 40)));
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
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        var primerEvento = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());
        //Act
        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));
        calendario.modificarFechaEvento(evento, duracion);
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadResultado = actividadesResultado.get(0);
        var tamanioEsperado = 1;

        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(primerEvento.getTitulo(), actividadResultado.getTitulo());
        assertEquals(primerEvento.getDescripcion(), actividadResultado.getDescripcion());
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
        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, diaTarea));
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

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion, new BuilderRepeticion(3, Frecuencia.DIARIA, 3)));

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

        var fechaDesde = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);
        var actividadesEsperadas = new ArrayList<Actividad>();

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        var primerEvento = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());


        //Act
        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));
        calendario.modificarFechaEvento(evento, duracion);
        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, LocalDate.of(2023, 4, 22)));

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

        //evento con repetición

        //intervalo
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);

        var titulo = "TP1";
        var titulo2 = "TP1 2.0";
        var titulo3 = "TP1 3.0";
        var descripcion = "Entrega limite del TP1";

        //tarea
        var fechaTarea =  LocalDateTime.of(2023, 4, 23, 15, 40);

        var actEsperadas = 4;
        int cantRepeticionesEvento1 = 1;
        int cantRepeticionesEvento2 = 2;
        int cantEvento1 = 0;
        int cantEvento2 = 0;

        //Act
        var evento1 = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion1));

        var evento2 = calendario.crearEvento(new BuilderEvento(titulo2, descripcion, duracion1, new BuilderRepeticion(1, Frecuencia.DIARIA)));

        var tarea = calendario.crearTarea(new BuilderTarea(titulo3, descripcion, fechaTarea));

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);

        //Assert
        assertEquals(actEsperadas, actividadesResultado.size());
        for (Actividad actividad: actividadesResultado){
            if (actividad.getTitulo().equals(titulo3)){
                assertEquals(tarea, actividad);
            }
            if (actividad.getTitulo().equals(titulo)){
                cantEvento1++;
            }
            if (actividad.getTitulo().equals(titulo2)){
                cantEvento2++;
            }
        }
        assertEquals(cantRepeticionesEvento1, cantEvento1);
        assertEquals(cantRepeticionesEvento2, cantEvento2);

    }

    @Test
    public void TestGetProximasAlarmas() {
        //Arrange
        var fecha1 = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma1 = new Alarma(fecha1, new AvisoNotificacion());
        var alarma2 = new Alarma(10, TiempoRelativo.MINUTOS, fecha1, new AvisoNotificacion());
        var fecha2 = fecha1.minusMinutes(10);
        var alarma3 = new Alarma(fecha2, new AvisoNotificacion());
        var alarma4 = new Alarma(fecha2, new AvisoEmail());
        var calendario = new Calendario();


        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var tarea1 = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha1));

        var tarea2 = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha2));

        var cantidadEsperadas = 3;

        //Act
        alarma1 = calendario.agregarAlarmaTarea(tarea1, new BuilderAlarma(fecha1, TipoAviso.NOTIFICACION));
        alarma2 = calendario.agregarAlarmaTarea(tarea1, new BuilderAlarma(fecha1, 10, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION));
        alarma3 = calendario.agregarAlarmaTarea(tarea2, new BuilderAlarma(fecha2,  TipoAviso.NOTIFICACION));
        alarma4 = calendario.agregarAlarmaTarea(tarea2, new BuilderAlarma(fecha2, TipoAviso.NOTIFICACION));
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

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

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

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

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

        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));

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

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, diaviejo));

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

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion,fechaVieja));

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

        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracionInicial));

        var fechaFinNueva = LocalDate.of(2023, 4, 22);
        var duracionNueva = new Duracion();
        duracionNueva.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionNueva.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionNueva.setHoraInicio(LocalTime.of(20, 0));
        duracionNueva.setHoraFin(LocalTime.of(20, 5));
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 0, 0);
        var fechaHasta = LocalDateTime.of(2023, 4, 23, 0, 5);

        //Act
        calendario.modificarFechaEvento(evento, duracionNueva);
        var resultado = evento.getRepeticionesEnIntervalo(fechaDesde, fechaHasta).get(0).getFechaFin().toLocalDate();
        //Assert
        assertEquals(fechaFinNueva, resultado);
    }

    @Test
    public void TestConfigurarAlarmaTarea() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        var alarma = new Alarma(fecha, new AvisoNotificacion());

        var calendario = new Calendario();

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

        var cantidadEsperadas = 1;

        //Act
        alarma = calendario.agregarAlarmaTarea(tarea, new BuilderAlarma(fecha, TipoAviso.NOTIFICACION));
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma));
    }

    @Test
    public void TestModificarAlarmaTarea() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        //var alarma = new Alarma(fecha, new AvisoNotificacion());
        //var alarma2 = new Alarma(fecha, new AvisoConSonido());

        var calendario = new Calendario();

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

        var cantidadEsperadas = 1;
        var alarma = calendario.agregarAlarmaTarea(tarea, new BuilderAlarma(fecha, TipoAviso.NOTIFICACION));

        //Act
        var alarma2 = calendario.modificarAlarmaTarea(tarea, alarma, new BuilderAlarma(fecha, TipoAviso.SONIDO));
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertTrue(resultado.contains(alarma2));
        assertFalse(resultado.contains(alarma));
    }

    @Test
    public void TestConfigurarAlarmaEvento() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var alarma1 = new AlarmaEvento(30, TiempoRelativo.MINUTOS, new AvisoNotificacion());

        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));

        var calendario = new Calendario();
        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracionInicial));

        var cantidadEsperadas = 1;

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 25);
        var fechaEsperada = fechaDesde.plusMinutes(5);

        //Act
        calendario.agregarAlarmaEvento(evento, new BuilderAlarmaEvento(30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION));
        var alarmasRes = evento.getProximasAlarmas(fechaDesde);
        var resultado = alarmasRes.iterator().next().getFechaAlarma();

        //Assert
        assertEquals(cantidadEsperadas, alarmasRes.size());
        assertEquals(fechaEsperada,resultado);
    }

    @Test
    public void TestModificarAlarmaEvento() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var alarma1 = new AlarmaEvento(30, TiempoRelativo.MINUTOS, new AvisoNotificacion());

        var alarma2 = new AlarmaEvento(10, TiempoRelativo.MINUTOS, new AvisoNotificacion());

        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));

        var calendario = new Calendario();
        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracionInicial));

        var cantidadEsperadas = 1;
        calendario.agregarAlarmaEvento(evento, new BuilderAlarmaEvento(30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION));

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 45);
        var fechaEsperada = fechaDesde.plusMinutes(5);

        //Act
        calendario.modificarAlarmaEvento(evento, alarma1, new BuilderAlarmaEvento(10, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION));
        var alarmasRes = evento.getProximasAlarmas(fechaDesde);
        var resultado = alarmasRes.iterator().next().getFechaAlarma();

        //Assert
        assertEquals(cantidadEsperadas, alarmasRes.size());
        assertEquals(fechaEsperada,resultado);
    }

    @Test
    public void TestEliminarAlarmaTarea() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var fecha = LocalDateTime.of(2100, 4, 22, 15, 30);
        //var alarma = new Alarma(fecha, new AvisoNotificacion());
        var calendario = new Calendario();

        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

        var cantidadEsperadas = 0;
        var alarma = calendario.agregarAlarmaTarea(tarea, new BuilderAlarma(fecha, TipoAviso.NOTIFICACION));

        //Act
        calendario.eliminarAlarmaTarea(tarea, alarma);
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertFalse(resultado.contains(alarma));
    }

    @Test
    public void TestEliminrAlarmaEvento() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var alarma1 = new AlarmaEvento(30, TiempoRelativo.MINUTOS, new AvisoNotificacion());

        var duracionInicial = new Duracion();
        duracionInicial.setDiaInicio(LocalDate.of(2023, 4, 22));
        duracionInicial.setDiaFin(LocalDate.of(2023, 4, 22));
        duracionInicial.setHoraInicio(LocalTime.of(20, 0));
        duracionInicial.setHoraFin(LocalTime.of(20, 5));

        var calendario = new Calendario();
        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracionInicial));

        var cantidadEsperadas = 0;
        calendario.agregarAlarmaEvento(evento, new BuilderAlarmaEvento(30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION));

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 45);
        var fechaEsperada = fechaDesde.plusMinutes(5);

        //Act
        calendario.eliminarAlarmaEvento(evento, alarma1);
        var alarmasRes = evento.getProximasAlarmas(fechaDesde);

        //Assert
        assertEquals(cantidadEsperadas, alarmasRes.size());
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
        var calendario = new Calendario();

        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));
        calendario.modificarFechaEvento(evento, duracion);

        int tamanioEsperado = 1;

        //Act
        calendario.modificarRepeticionEvento(evento, new BuilderRepeticion(2, Frecuencia.DIARIA, 2));
        var eventos = evento.getRepeticionesEnIntervalo(duracion.getFechaInicio().minusDays(1),
                duracion.getFechaInicio().plusDays(1));
        var fechaEsperada = LocalDateTime.of(2023, 4, 22, 20, 0);
        var resultadoFecha = eventos.iterator().next().getFechaInicio();


        //Assert
        assertEquals(tamanioEsperado, eventos.size());
        assertEquals(fechaEsperada, resultadoFecha);
    }

    @Test
    public void TestEliminarTarea() {
        //Arrange
        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        var calendario = new Calendario();

        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        var tarea = calendario.crearTarea(new BuilderTarea(titulo, descripcion, fecha));

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

        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));

        var cantidadEsperadas = 0;

        //Act
        calendario.eliminarEvento(evento);
        var resultado = calendario.getActividadesEnElIntervalo(fechaInicio.minusDays(1), fechaFin.plusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
    }

    @Test
    public void TestPersistencia(){
        // Arrange
        Calendario calendario = new Calendario();

        var titulo = "TP";
        var titulo2 = "TP 2.0";
        var titulo3 = "TP 3.0";
        var descripcion = "Etapa 2";

        var duracion1 = new Duracion();
        var fechaInicio = LocalDateTime.of(2023, 5, 10, 15, 35);
        var fechaFin = LocalDateTime.of(2023, 5, 13, 15, 35);
        duracion1.setDiaInicio(fechaInicio.toLocalDate());
        duracion1.setDiaFin(fechaFin.toLocalDate());
        duracion1.setHoraInicio(fechaInicio.toLocalTime());
        duracion1.setHoraFin(fechaFin.toLocalTime());
        calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion1));

        var duracion2 = new Duracion();
        var fechaDesde = LocalDateTime.of(2023, 5, 10, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 5, 10, 15, 50);
        duracion2.setDiaInicio(fechaDesde.toLocalDate());
        duracion2.setDiaFin(fechaHasta.toLocalDate());
        duracion2.setHoraInicio(fechaDesde.toLocalTime());
        duracion2.setHoraFin(fechaHasta.toLocalTime());
        calendario.crearEvento(new BuilderEvento(titulo2, descripcion, duracion2, new BuilderRepeticion(1, Frecuencia.DIARIA)));

        var fechaTarea =  LocalDateTime.of(2023, 5, 10, 15, 40);
        Tarea tarea = calendario.crearTarea(new BuilderTarea(titulo3, descripcion, fechaTarea));

        var cantidadActEsperadas = 4;
        int cantRepeticionesEvento1 = 1;
        int cantRepeticionesEvento2 = 2;
        int cantEvento1 = 0;
        int cantEvento2 = 0;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        int tamanioEsperado = 0;
        LocalDateTime desde = LocalDateTime.of(2023, 5, 10, 15, 30);
        LocalDateTime hasta = LocalDateTime.of(2023, 5, 13, 13, 30);

        // Act
        calendario.serializar(bytes);
        Calendario calendarioDeserializado = Calendario.deserializar(new ByteArrayInputStream(bytes.toByteArray()));


        // Assert
        assertNotNull(calendarioDeserializado);

        List<Actividad> actividadesResultado = calendarioDeserializado.getActividadesEnElIntervalo(desde, hasta);
        Set<Alarma> alarmasResultado = calendarioDeserializado.getProximasAlarmas();

        // calendario y calendarioDeserializado deben tener la misma info, pero son instancias distintas en memoria
        assertEquals(tamanioEsperado, alarmasResultado.size());

        assertEquals(cantidadActEsperadas, actividadesResultado.size());
        for (Actividad actividad: actividadesResultado){
            if (actividad.getTitulo().equals(titulo3)){
                //assertEquals(tarea, actividad);
            }
            if (actividad.getTitulo().equals(titulo)){
                cantEvento1++;
            }
            if (actividad.getTitulo().equals(titulo2)){
                cantEvento2++;
            }
        }
        assertEquals(cantRepeticionesEvento1, cantEvento1);
        assertEquals(cantRepeticionesEvento2, cantEvento2);
        //Falta --> crear alarmas y comparar
    }

}

