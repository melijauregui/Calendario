package Calendario.Tareas;
/*
import Calendario.Enums.TiempoRelativo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class TareaTest {

    @Test
    public void TestEsDiaCompleto() {
        //Arrange
        var tarea = new Tarea();
        var dia = LocalDate.of(2023, 4, 22);
        tarea.setDia(dia);

        //Act
        var resultado = tarea.esDiaCompleto();

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void TestEstaEnElIntervalo() {
        //Arrange
        var tarea1 = new Tarea();
        tarea1.setDia(LocalDate.of(2023, 4, 22));
        var tarea2 = new Tarea();
        tarea2.setDia(LocalDate.of(2023, 4, 22));
        tarea2.setHora(LocalTime.of(20, 0));
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 0);
        var fechaHasta = LocalDateTime.of(2023, 4, 23, 19, 0);

        //Act
        var resultadoTarea1 = tarea1.estaEnElIntervalo(fechaDesde, fechaHasta);
        var resultadoTarea2 = tarea2.estaEnElIntervalo(fechaDesde, fechaHasta);

        //Assert
        assertTrue(resultadoTarea1);
        assertTrue(resultadoTarea2);
    }

    @Test
    public void TestConfigurarAlarma() {
        //Arrange
        var tarea = new Tarea();
        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var alarma = new AlarmaConSonido(10, TiempoRelativo.MINUTOS);

        //Act
        tarea.configurarAlarma(alarma);
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1));

        //Assert
        assertTrue(resultado.contains(alarma));


    }

    @Test
    public void TestGetProximasAlarma() {
        //Arrange
        var tarea = new Tarea();
        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var alarma1 = new AlarmaConSonido(10, TiempoRelativo.MINUTOS);
        var alarma2 = new AlarmaConNotificacion(10, TiempoRelativo.MINUTOS);
        var alarma3 = new AlarmaConEmail(fecha.plusDays(1));
        tarea.configurarAlarma(alarma1);
        tarea.configurarAlarma(alarma2);
        tarea.configurarAlarma(alarma3);

        //Act
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1));

        //Assert
        assertTrue(resultado.contains(alarma1));
        assertTrue(resultado.contains(alarma2));
        assertFalse(resultado.contains(alarma3));

    }

    @Test
    public void TestEliminarAlarma() {
        //Arrange
        var tarea = new Tarea();
        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());
        var alarma = new AlarmaConSonido(10, TiempoRelativo.MINUTOS);
        tarea.configurarAlarma(alarma);

        //Act
        tarea.eliminarAlarma(alarma);
        var resultado = tarea.getProximasAlarmas(fecha.minusDays(1)).size();
        var tamanioEsperado = 0;

        //Assert
        assertEquals(tamanioEsperado, resultado);


    }
    @Test
    public void TestCompletar() {
        //Arrange
        var tarea = new Tarea();
        tarea.setDia(LocalDate.of(2023, 4, 22));
        tarea.setHora(LocalTime.of(20, 0));
        var fechaDesde = LocalDateTime.of(2023, 4, 22, 19, 0);
        var fechaHasta = LocalDateTime.of(2023, 4, 23, 19, 0);

        //Act
        tarea.completar();
        var resultado = tarea.estaEnElIntervalo(fechaDesde, fechaHasta);

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void TestGetFecha() {
        //Arrange
        var tarea = new Tarea();
        var fecha = LocalDateTime.of(2023, 4, 22, 20, 0);
        tarea.setDia(fecha.toLocalDate());
        tarea.setHora(fecha.toLocalTime());

        //Act
        var resultado = tarea.getFecha();

        //Assert
        assertEquals(fecha, resultado);
    }

    @Test
    public void TestGetTitulo() {
        //Arrange
        var tarea = new Tarea();
        var titulo = "título";
        tarea.setTitulo(titulo);

        //Act
        var resultado = tarea.getTitulo();

        //Assert
        assertEquals(titulo, resultado);
    }

    @Test
    public void TestGetDescripcion() {
        //Arrange
        var tarea = new Tarea();
        var descripcion = "descripcion";
        tarea.setDescripcion(descripcion);

        //Act
        var resultado = tarea.getDescripcion();

        //Assert
        assertEquals(descripcion, resultado);
    }
    @Test
    public void TestSetDia() {
        //Arrange
        var tarea = new Tarea();
        var dia = LocalDate.of(2023, 4, 22);

        //Act
        tarea.setDia(dia);
        var resultado = tarea.getFecha().toLocalDate();

        //Assert
        assertEquals(dia, resultado);

    }

    @Test
    public void TestSetHora() {
        //Arrange
        var tarea = new Tarea();
        var dia = LocalDate.of(2023, 4, 22);
        var hora = LocalTime.of(20, 0);
        tarea.setDia(dia);

        //Act
        tarea.setHora(hora);
        var resultado = tarea.getFecha().toLocalTime();

        //Assert
        assertEquals(hora, resultado);
    }



}

 */