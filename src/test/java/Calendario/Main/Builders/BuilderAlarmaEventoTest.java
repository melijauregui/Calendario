package Calendario.Main.Builders;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Enums.TiempoRelativo;
import Calendario.Enums.TipoAviso;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BuilderAlarmaEventoTest {

    @Test
    public void CrearAlarmaEventoAbsoluta() {
        // Arrange
        var tipoEsperado = AlarmaEvento.class;
        var aviso = TipoAviso.NOTIFICACION;

        // Act
        var builder = new BuilderAlarmaEvento(aviso);
        var alarma = builder.crearAlarmaEvento();
        var tipoResultado = alarma.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
    }

    @Test
    public void CrearAlarmaEventoRelativa() {
        // Arrange
        var tipoEsperado = AlarmaEvento.class;
        var aviso = TipoAviso.EMAIL;
        var tiempoRelativo = TiempoRelativo.DIAS;
        var cantTiempoRelativo = 10;

        // Act
        var builder = new BuilderAlarmaEvento(cantTiempoRelativo, tiempoRelativo, aviso);
        var alarma = builder.crearAlarmaEvento();
        var tipoResultado = alarma.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
    }

}