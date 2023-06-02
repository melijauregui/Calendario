package Calendario.Enums;

import Calendario.Enums.Frecuencia;
import Calendario.Repeticiones.RepeticionAnual;
import Calendario.Repeticiones.RepeticionDiaria;
import Calendario.Repeticiones.RepeticionMensual;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FrecuenciaTest {
    @Test
    public void CrearRepeticionDiaria() {
        //Arrange
        var tipoRepeticionEsperada = RepeticionDiaria.class;

        //Act
        var repeticionConFecha = Frecuencia.DIARIA.crearRepeticion(3, LocalDate.of(2023, 4, 4), 0);
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var repeticionConOcurrencias = Frecuencia.DIARIA.crearRepeticion(3, null,3);
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var repeticionInfinita = Frecuencia.DIARIA.crearRepeticion(3, null, 0);
        var tipoRepeticionInfinita = repeticionInfinita.getClass();

        //Assert
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConFecha);
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConOcurrencias);
        assertEquals(tipoRepeticionEsperada, tipoRepeticionInfinita);
    }

    @Test
    public void CrearRepeticionAnual() {
        //Arrange
        var tipoRepeticionEsperada = RepeticionAnual.class;

        //Act
        var repeticionConFecha = Frecuencia.ANUAL.crearRepeticion(3, LocalDate.of(2023, 4, 4), 0);
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var repeticionConOcurrencias = Frecuencia.ANUAL.crearRepeticion(3, null,3);
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var repeticionInfinita = Frecuencia.ANUAL.crearRepeticion(3, null, 0);
        var tipoRepeticionInfinita = repeticionInfinita.getClass();

        //Assert
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConFecha);
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConOcurrencias);
        assertEquals(tipoRepeticionEsperada, tipoRepeticionInfinita);
    }

    @Test
    public void CrearRepeticionMensual() {
        //Arrange
        var tipoRepeticionEsperada = RepeticionMensual.class;

        //Act
        var repeticionConFecha = Frecuencia.MENSUAL.crearRepeticion(3, LocalDate.of(2023, 4, 4), 0);
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var repeticionConOcurrencias = Frecuencia.MENSUAL.crearRepeticion(3, null,3);
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var repeticionInfinita = Frecuencia.MENSUAL.crearRepeticion(3, null, 0);
        var tipoRepeticionInfinita = repeticionInfinita.getClass();

        //Assert
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConFecha);
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConOcurrencias);
        assertEquals(tipoRepeticionEsperada, tipoRepeticionInfinita);
    }
}