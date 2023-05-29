package Calendario.Main;

import Calendario.Actividad.Actividad;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Duracion.Duracion;
import Calendario.Enums.Frecuencia;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import Calendario.Main.Builders.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Calendario.Alarmas.*;

import static org.junit.Assert.*;

public class CalendarioTest {

    @Test
    public void TestCalendarioVacio(){
        //Arrange
        var calendario = new Calendario();
        var fecha = LocalDateTime.of(2023, 11, 5, 2, 2, 4);
        var cantActividadesEsperadas = 0;
        var cantAlarmasEsperadas = 0;

        //Act
        var cantAlarmasResultado = calendario.getProximasAlarmas( fecha).size();
        var cantActividadesResultado = calendario.getActividadesEnElIntervalo(fecha, fecha.plusYears(10)).size();

        //Assert
        assertEquals(cantActividadesEsperadas, cantActividadesResultado);
        assertEquals(cantAlarmasEsperadas, cantAlarmasResultado);

    }

    @Test
    public void TestCrearYObtenerTareaConHora() {
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
    public void TestCrearYObtenerTareaDiaCompleto() {
        //Arrange
        var calendario = new Calendario();

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        //Act
        var tarea = calendario.crearTarea(titulo, descripcion, LocalDate.of(2023, 4, 22));
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadesEsperadas = new ArrayList<Actividad>();
        actividadesEsperadas.add(tarea);

        //Assert
        assertEquals(actividadesEsperadas, actividadesResultado);

    }

    @Test
    public void TestCrearYObtenerEventoSinRepeticionConHora() {
        //Arrange
        var calendario = new Calendario();

        var diaInicio = LocalDate.of(2023, 4, 22);
        var diaFin = LocalDate.of(2023, 4, 25);
        var horaInicio = LocalTime.of(15, 40);
        var horaFin = LocalTime.of(16,0);
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        //Act
        var evento = calendario.crearEvento(titulo, descripcion,diaInicio, diaFin, horaInicio, horaFin);
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadResultado = actividadesResultado.get(0);
        var tamanioEsperado = 1;

        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, actividadResultado.getTitulo());
        assertEquals(descripcion, actividadResultado.getDescripcion());
    }

    @Test
    public void TestCrearYObtenerEventoSinRepeticionDiaCompleto() {
        //Arrange
        var calendario = new Calendario();

        var diaInicio = LocalDate.of(2023, 4, 22);
        var diaFin = LocalDate.of(2023, 4, 25);
        var fechaDesde = LocalDateTime.of(2023, 4, 21, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 22, 15, 50);

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";
        //Act
        var evento = calendario.crearEvento(titulo, descripcion,diaInicio, diaFin);
        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var actividadResultado = actividadesResultado.get(0);
        var tamanioEsperado = 1;

        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, actividadResultado.getTitulo());
        assertEquals(descripcion, actividadResultado.getDescripcion());
    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionConOcurrencias() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 22, 15, 31);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 25, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),3, Frecuencia.DIARIA, 3);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionConOcurrencias() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 22);
        LocalDate fechaFin = LocalDate.of(2023, 4, 25);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,3, Frecuencia.DIARIA, 3);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionInfinita() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 22, 15, 31);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 25, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),3, Frecuencia.DIARIA);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionInfinita() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 22);
        LocalDate fechaFin = LocalDate.of(2023, 4, 25);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,3, Frecuencia.DIARIA);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionConFechaVencimiento() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 22, 15, 30);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 25, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaVencimiento = LocalDate.of(2023, 4, 23);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),2, Frecuencia.DIARIA, fechaVencimiento);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionFechaVencimiento() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 22);
        LocalDate fechaFin = LocalDate.of(2023, 4, 25);
        String titulo = "titulo";
        String descripcion = "descripción";
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaVencimiento = LocalDate.of(2023, 4, 23);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,2, Frecuencia.DIARIA, fechaVencimiento);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionSemanalConOcurrencias() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 20, 15, 31);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 21, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),3, diasSemanas, 3);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0); // repetición del domingo 23/4
        int tamanioEsperado = 1;

        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionSemanalConOcurrencias() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 20);
        LocalDate fechaFin = LocalDate.of(2023, 4, 21);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,3, diasSemanas, 3);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionSemanalInfinita() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 20, 15, 31);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 21, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),3, diasSemanas);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionSemanalInfinita() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 20);
        LocalDate fechaFin = LocalDate.of(2023, 4, 21);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,3, diasSemanas);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoConHoraYRepeticionSemanalConFechaVencimiento() {
        //Arrange
        var calendario = new Calendario();
        LocalDateTime fechaInicio = LocalDateTime.of(2023, 4, 20, 15, 30);
        LocalDateTime fechaFin = LocalDateTime.of(2023, 4, 21, 0, 0);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaVencimiento = LocalDate.of(2023, 4, 23);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio.toLocalDate(), fechaFin.toLocalDate(),
                fechaInicio.toLocalTime(), fechaFin.toLocalTime(),2,diasSemanas, fechaVencimiento);

        var actividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta);
        var eventoResultado = actividadesResultado.get(0);
        int tamanioEsperado = 1;


        //Assert
        assertEquals(tamanioEsperado, actividadesResultado.size());
        assertEquals(titulo, eventoResultado.getTitulo());
        assertEquals(descripcion, eventoResultado.getDescripcion());

    }

    @Test
    public void TestCrearYObtenerEventoDiaCompletoYRepeticionSemanalFechaVencimiento() {
        //Arrange
        var calendario = new Calendario();
        LocalDate fechaInicio = LocalDate.of(2023, 4, 20);
        LocalDate fechaFin = LocalDate.of(2023, 4, 21);
        String titulo = "titulo";
        String descripcion = "descripción";
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.SUNDAY);
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaVencimiento = LocalDate.of(2023, 4, 23);

        //Act
        calendario.crearEvento(titulo, descripcion,fechaInicio, fechaFin,2, diasSemanas, fechaVencimiento);

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

        var diaInicio = LocalDate.of(2023, 4, 23);
        var diaFin = LocalDate.of(2023, 4, 25);

        var fechaDesde = LocalDateTime.of(2023, 4, 24, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 4, 24, 15, 50);
        var cantActividadesEsperadas = 0;

        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        //Act
        var evento = calendario.crearEvento(titulo, descripcion, diaInicio, diaFin);
        var tarea = calendario.crearTarea(titulo, descripcion, LocalDateTime.of(2023, 4, 22, 15, 40));
        var cantActividadesResultado = calendario.getActividadesEnElIntervalo(fechaDesde, fechaHasta).size();


        //Assert
        assertEquals(cantActividadesEsperadas, cantActividadesResultado);
    }

    @Test
    public void TestObtenerVariasActividades() {

        //Arrange

        var calendario = new Calendario();

        var diaInicio = LocalDate.of(2023, 4, 23);
        var diaFin = LocalDate.of(2023, 4, 25);

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
        var evento1 = calendario.crearEvento(titulo, descripcion, diaInicio, diaFin);
        var evento2 = calendario.crearEvento(titulo2, descripcion, diaInicio, diaFin, 1, Frecuencia.DIARIA);
        var tarea = calendario.crearTarea(titulo3, descripcion, fechaTarea);

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


    // Falta -->
    // -corregir tests actuales
    // -agregar:
    //           modificarFechaEvento [diaCompleto/conHora] --> 2
    //           modificarRepeticion [Frecuenta/Semanal] [fecha/ocurrencias/infinita] --> 6
    //           agregarAlarmaTarea [absoluta/relativa] --> 2
    //           agregarAlarmaEvento [absoluta/relativa] --> 2
    //           modificarAlarmaTarea [absoluta/relativa] --> 2
    //           modificarAlarmaEvento [absoluta/relativa] --> 2


 /*
    @Test
    public void TestGetProximasAlarmas() {
        //Arrange
        var fecha1 = LocalDateTime.of(2023, 4, 22, 15, 30);
        var fecha2 = fecha1.plusMinutes(10);
        var calendario = new Calendario();
        var fechaAlarma = fecha1.minusMinutes(5);


        var titulo = "TP1";
        var descripcion = "Entrega limite del TP1";

        var tarea1 = calendario.crearTarea(titulo, descripcion, fecha1);

        var tarea2 = calendario.crearTarea(titulo, descripcion, fecha2);

        var cantidadEsperadas = 1; //suena 15:30

        //Act
        var alarma1 = calendario.agregarAlarmaTarea(tarea1, fecha1, TipoAviso.NOTIFICACION);
        var alarma2 = calendario.agregarAlarmaTarea(tarea1,fecha1, 10, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION);
        var alarma3 = calendario.agregarAlarmaTarea(tarea2, fecha2,  TipoAviso.NOTIFICACION);
        var alarma4 = calendario.agregarAlarmaTarea(tarea2, fecha2, TipoAviso.NOTIFICACION);
        Set<Alarma> resultado = calendario.getProximasAlarmas(fechaAlarma);

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
        assertFalse(resultado.contains(alarma2));
        assertTrue(resultado.contains(alarma1));
        assertFalse(resultado.contains(alarma3));
        assertFalse(resultado.contains(alarma4));

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

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var cantidadEsperadas = 1;

        //Act
        alarma = calendario.agregarAlarmaTarea(tarea, fecha, TipoAviso.NOTIFICACION);
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

        var calendario = new Calendario();

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var cantidadEsperadas = 1;
        var alarma = calendario.agregarAlarmaTarea(tarea, fecha, TipoAviso.NOTIFICACION);

        //Act
        var alarma2 = calendario.modificarAlarmaTarea(tarea, alarma, fecha, TipoAviso.SONIDO);
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
        calendario.agregarAlarmaEvento(evento, 30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION);
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
        calendario.agregarAlarmaEvento(evento,30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION);

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 45);
        var fechaEsperada = fechaDesde.plusMinutes(5);

        //Act
        calendario.modificarAlarmaEvento(evento, alarma1,10, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION);
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
        var calendario = new Calendario();

        var tarea = calendario.crearTarea(titulo, descripcion, fecha);

        var cantidadEsperadas = 0;
        var alarma = calendario.agregarAlarmaTarea(tarea,fecha, TipoAviso.NOTIFICACION);

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
        calendario.agregarAlarmaEvento(evento, 30, TiempoRelativo.MINUTOS, TipoAviso.NOTIFICACION);

        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 45);

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
        calendario.modificarRepeticionEvento(evento, 2, Frecuencia.DIARIA, 2);
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

        var evento = calendario.crearEvento(new BuilderEvento(titulo, descripcion, duracion));

        var cantidadEsperadas = 0;

        //Act
        calendario.eliminarEvento(evento);
        var resultado = calendario.getActividadesEnElIntervalo(fechaInicio.minusDays(1), fechaFin.plusDays(1));

        //Assert
        assertEquals(cantidadEsperadas, resultado.size());
    }
    @Test
    public void TestPersistencia2() throws IOException, ClassNotFoundException{
        // Arrange
        Calendario calendario = new Calendario();

        LocalDateTime desde = LocalDateTime.of(2023, 5, 10, 15, 31);
        LocalDateTime hasta = LocalDateTime.of(2023, 5, 13, 13, 30);


        //Evento1
        var tituloE1 = "E1";
        var descripcionE1 = "E1";
        var duracion1 = new Duracion();
        var fechaInicio = LocalDateTime.of(2023, 5, 10, 15, 35);
        var fechaFin = LocalDateTime.of(2023, 5, 13, 15, 35);
        duracion1.setDiaInicio(fechaInicio.toLocalDate());
        duracion1.setDiaFin(fechaFin.toLocalDate());
        duracion1.setHoraInicio(fechaInicio.toLocalTime());
        duracion1.setHoraFin(fechaFin.toLocalTime());
        var evento1 = calendario.crearEvento(new BuilderEvento(tituloE1, descripcionE1, duracion1));
        calendario.agregarAlarmaEvento(evento1, TipoAviso.NOTIFICACION);

        //Evento2
        var tituloE2 = "E2";
        var descripcionE2 = "E2";
        var duracion2 = new Duracion();
        var fechaDesde = LocalDateTime.of(2023, 5, 10, 15, 30);
        var fechaHasta = LocalDateTime.of(2023, 5, 10, 15, 50);
        duracion2.setDiaInicio(fechaDesde.toLocalDate());
        duracion2.setDiaFin(fechaHasta.toLocalDate());
        duracion2.setHoraInicio(fechaDesde.toLocalTime());
        duracion2.setHoraFin(fechaHasta.toLocalTime());
        calendario.crearEvento(new BuilderEvento(tituloE2, descripcionE2, duracion2, new BuilderRepeticion(1, Frecuencia.DIARIA, fechaHasta.plusMonths(1).toLocalDate())));


        //Tarea1
        var tituloT1 = "T1";
        var descripcionT1 = "T1";
        var fechaT1 =  LocalDateTime.of(2023, 5, 10, 15, 40);
        var tarea1 = calendario.crearTarea(tituloT1, descripcionT1, fechaT1);

        //ESPERADO
        var actividadesEsperadas = calendario.getActividadesEnElIntervalo(desde, hasta);
        var cantidadActEsperadas = actividadesEsperadas.size();
        var cantidadEvento1Esperada = 1;
        var cantidadEvento2Esperada = 2;
        int cantidadEsperadaAlarmas = 1;
        int cantidadTareasEsperadas = 1;


        int cantEvento1 = 0;
        int cantEvento2 = 0;
        int cantTareas = 0;

        //ACT

        // serializar
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        calendario.serializar(bytes);

        // deserializar
        Calendario calendarioDeserializado = Calendario.deserializar(new ByteArrayInputStream(bytes.toByteArray()));

        List<Actividad> actividadesResultado = calendarioDeserializado.getActividadesEnElIntervalo(desde, hasta);
        Set<Alarma> alarmasResultado = calendarioDeserializado.getProximasAlarmas(desde);


        // Assert
        assertEquals(cantidadActEsperadas, actividadesResultado.size());
        assertEquals(cantidadEsperadaAlarmas, alarmasResultado.size());

        for (Actividad actividad: actividadesResultado){
            int cantAlarmas = actividad.getAlarmas().size();
            if (actividad.getTitulo().equals(tituloT1)){
                assertEquals(descripcionT1, actividad.getDescripcion());
                assertEquals(0, cantAlarmas);
                cantTareas++;
            }
            if (actividad.getTitulo().equals(tituloE1)){
                assertEquals(descripcionE1,actividad.getDescripcion());
                assertEquals(cantidadEsperadaAlarmas, cantAlarmas);
                cantEvento1++;
            }
            if (actividad.getTitulo().equals(tituloE2)){
                assertEquals(descripcionE2,actividad.getDescripcion());
                assertEquals(0, cantAlarmas);
                cantEvento2++;
            }

        }

        assertEquals(cantidadTareasEsperadas, cantTareas);
        assertEquals(cantidadEvento1Esperada, cantEvento1);
        assertEquals(cantidadEvento2Esperada, cantEvento2);

    }*/

}

