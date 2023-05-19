package Calendario.Main.Builders;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BuilderAlarmaTest {

    @Test
    public void CrearAlarmaAbsoluta() {
        // Arrange
        var tipoEsperado = Alarma.class;
        var aviso = TipoAviso.NOTIFICACION;
        var fecha = LocalDateTime.of(2023, 2, 2, 2, 2);

        // Act
        var builder = new BuilderAlarma(fecha, aviso);
        var alarma = builder.crearAlarma();
        var tipoResultado = alarma.getClass();
        var fechaResultado = alarma.getFechaAlarma();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(fecha, fechaResultado);
    }

    @Test
    public void CrearAlarmaRelativa() {
        // Arrange
        var tipoEsperado = Alarma.class;
        var aviso = TipoAviso.EMAIL;
        var tiempoRelativo = TiempoRelativo.DIAS;
        var cantTiempoRelativo = 10;
        var fecha = LocalDateTime.of(2023, 2, 2, 2, 2);
        var fechaEsperada = fecha.minusDays(10);

        // Act
        var builder = new BuilderAlarma(fecha, cantTiempoRelativo, tiempoRelativo, aviso);
        var alarma = builder.crearAlarma();
        var tipoResultado = alarma.getClass();
        var fechaResultado = alarma.getFechaAlarma();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(fechaEsperada, fechaResultado);
    }
}