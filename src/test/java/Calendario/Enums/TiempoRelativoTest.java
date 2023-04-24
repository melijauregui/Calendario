package Calendario.Enums;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TiempoRelativoTest {

    @Test
    public void TestDeterminarFechaRelativaMinutos() {
        //Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 50);
        int intervalo = 10;
        TiempoRelativo tiempoRelativo = TiempoRelativo.MINUTOS;

        //Act
        LocalDateTime fechaEsperada = LocalDateTime.of(2023, 4, 22, 16, 40);
        LocalDateTime fechaDeterminada = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);

        //Assert
        assertEquals(fechaEsperada, fechaDeterminada);

    }

    @Test
    public void TestDeterminarFechaRelativaHoras() {
        //Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 50);
        int intervalo = 4;
        TiempoRelativo tiempoRelativo = TiempoRelativo.HORAS;

        //Act
        LocalDateTime fechaEsperada = LocalDateTime.of(2023, 4, 22, 12, 50);
        LocalDateTime fechaDeterminada = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);

        //Assert
        assertEquals(fechaEsperada, fechaDeterminada);

    }

    @Test
    public void TestDeterminarFechaRelativaDias() {
        //Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 50);
        int intervalo = 5;
        TiempoRelativo tiempoRelativo = TiempoRelativo.DIAS;

        //Act
        LocalDateTime fechaEsperada = LocalDateTime.of(2023, 4, 17, 16, 50);
        LocalDateTime fechaDeterminada = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);

        //Assert
        assertEquals(fechaEsperada, fechaDeterminada);

    }

    @Test
    public void TestDeterminarFechaRelativaSemanas() {
        //Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 50);
        int intervalo = 2;
        TiempoRelativo tiempoRelativo = TiempoRelativo.SEMANAS;

        //Act
        LocalDateTime fechaEsperada = LocalDateTime.of(2023, 4, 8, 16, 50);
        LocalDateTime fechaDeterminada = tiempoRelativo.determinarFechaRelativa(fecha, intervalo);

        //Assert
        assertEquals(fechaEsperada, fechaDeterminada);

    }

}