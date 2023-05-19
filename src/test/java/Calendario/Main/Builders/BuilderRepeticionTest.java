package Calendario.Main.Builders;

import Calendario.Enums.Frecuencia;
import Calendario.Repeticiones.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BuilderRepeticionTest {
    @Test
    public void CrearRepeticionDiaria() {
        //Arrange
        var tipoRepeticionEsperada = RepeticionDiaria.class;

        //Act
        var builderRepeticionConFecha = new BuilderRepeticion(3, Frecuencia.DIARIA,LocalDate.of(2023, 4, 4));
        var repeticionConFecha = builderRepeticionConFecha.crearRepeticion();
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var builderRepeticionConOcurrencias = new BuilderRepeticion(3, Frecuencia.DIARIA, 3);
        var repeticionConOcurrencias = builderRepeticionConOcurrencias.crearRepeticion();
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var builderRepeticionInfinita = new BuilderRepeticion(3, Frecuencia.DIARIA);
        var repeticionInfinita = builderRepeticionInfinita.crearRepeticion();
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
        var builderRepeticionConFecha = new BuilderRepeticion(3, Frecuencia.ANUAL,LocalDate.of(2023, 4, 4));
        var repeticionConFecha = builderRepeticionConFecha.crearRepeticion();
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var builderRepeticionConOcurrencias = new BuilderRepeticion(3, Frecuencia.ANUAL, 3);
        var repeticionConOcurrencias = builderRepeticionConOcurrencias.crearRepeticion();
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var builderRepeticionInfinita = new BuilderRepeticion(3, Frecuencia.ANUAL);
        var repeticionInfinita = builderRepeticionInfinita.crearRepeticion();
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
        var builderRepeticionConFecha = new BuilderRepeticion(3, Frecuencia.MENSUAL,LocalDate.of(2023, 4, 4));
        var repeticionConFecha = builderRepeticionConFecha.crearRepeticion();
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var builderRepeticionConOcurrencias = new BuilderRepeticion(3, Frecuencia.MENSUAL, 3);
        var repeticionConOcurrencias = builderRepeticionConOcurrencias.crearRepeticion();
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var builderRepeticionInfinita = new BuilderRepeticion(3, Frecuencia.MENSUAL);
        var repeticionInfinita = builderRepeticionInfinita.crearRepeticion();
        var tipoRepeticionInfinita = repeticionInfinita.getClass();

        //Assert
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConFecha);
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConOcurrencias);
        assertEquals(tipoRepeticionEsperada, tipoRepeticionInfinita);
    }

    @Test
    public void CrearRepeticionSemanal() {
        //Arrange
        var tipoRepeticionEsperada = RepeticionSemanal.class;
        var diasSemanas = new ArrayList<DayOfWeek>();
        diasSemanas.add(DayOfWeek.THURSDAY);
        diasSemanas.add(DayOfWeek.MONDAY);

        //Act
        var builderRepeticionConFecha = new BuilderRepeticion(3, diasSemanas,LocalDate.of(2023, 4, 4));
        var repeticionConFecha = builderRepeticionConFecha.crearRepeticion();
        var tipoRepeticionConFecha = repeticionConFecha.getClass();
        var builderRepeticionConOcurrencias = new BuilderRepeticion(3, diasSemanas, 3);
        var repeticionConOcurrencias = builderRepeticionConOcurrencias.crearRepeticion();
        var tipoRepeticionConOcurrencias = repeticionConOcurrencias.getClass();
        var builderRepeticionInfinita = new BuilderRepeticion(3, diasSemanas);
        var repeticionInfinita = builderRepeticionInfinita.crearRepeticion();
        var tipoRepeticionInfinita = repeticionInfinita.getClass();

        //Assert
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConFecha);
        assertEquals(tipoRepeticionEsperada,tipoRepeticionConOcurrencias);
        assertEquals(tipoRepeticionEsperada, tipoRepeticionInfinita);
    }

}