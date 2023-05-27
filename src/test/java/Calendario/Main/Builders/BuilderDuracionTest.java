package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class BuilderDuracionTest {

    @Test
    public void crearDuracionConHora() {
        // Arrange
        LocalDate diaInicio = LocalDate.of(2023, 5, 5);
        LocalDate diaFin = LocalDate.of(2023, 5, 6);
        LocalTime horaInicio = LocalTime.of(20, 0);
        LocalTime horaFin = LocalTime.of(20, 5);

        // Act
        BuilderDuracion builderDuracion = new BuilderDuracion(diaInicio, diaFin, horaInicio, horaFin);
        Duracion duracion = builderDuracion.crearDuracion();
        var fechaInicioResultado = duracion.getFechaInicio();
        var fechaFinResultado = duracion.getFechaFin();
        var diaInicioResultado = fechaInicioResultado.toLocalDate();
        var diaFinResultado = fechaFinResultado.toLocalDate();
        var horaInicioResultado = fechaInicioResultado.toLocalTime();
        var horaFinResultado = fechaFinResultado.toLocalTime();

        // Assert
        assertEquals(diaInicio, diaInicioResultado);
        assertEquals(diaFin, diaFinResultado);
        assertEquals(horaInicio, horaInicioResultado);
        assertEquals(horaFin, horaFinResultado);
    }

    public void crearDuracionDiaCompletp() {
        // Arrange
        LocalDate diaInicio = LocalDate.of(2023, 5, 5);
        LocalDate diaFin = LocalDate.of(2023, 5, 6);
        LocalTime horaInicio = LocalTime.of(0, 0);
        LocalTime horaFin = LocalTime.of(23, 59);

        // Act
        BuilderDuracion builderDuracion = new BuilderDuracion(diaInicio, diaFin);
        Duracion duracion = builderDuracion.crearDuracion();
        var fechaInicioResultado = duracion.getFechaInicio();
        var fechaFinResultado = duracion.getFechaFin();
        var diaInicioResultado = fechaInicioResultado.toLocalDate();
        var diaFinResultado = fechaFinResultado.toLocalDate();
        var horaInicioResultado = fechaInicioResultado.toLocalTime();
        var horaFinResultado = fechaFinResultado.toLocalTime();

        // Assert
        assertEquals(diaInicio, diaInicioResultado);
        assertEquals(diaFin, diaFinResultado);
        assertEquals(horaInicio, horaInicioResultado);
        assertEquals(horaFin, horaFinResultado);
    }

}