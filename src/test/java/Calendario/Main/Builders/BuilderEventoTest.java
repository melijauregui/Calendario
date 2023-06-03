package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;

import Calendario.Eventos.Evento;
import Calendario.Main.Argumentos.DuracionArgs;
import Calendario.Main.Argumentos.Frecuencia;
import Calendario.Main.Argumentos.RepeticionArgs;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BuilderEventoTest {

    @Test
    public void CrearEventoConRepeticion() {
        // Arrange
        var titulo = "Builder con repetición";
        var descripcion = "Crea un evento con repetición";
        var fecha = LocalDate.of(2023,2,2);
        var tipoEsperado = Evento.class;

        // Act
        var builder = new BuilderEvento(titulo, descripcion, new BuilderDuracion(new DuracionArgs(fecha, fecha)), new BuilderRepeticion(new RepeticionArgs(5, Frecuencia.DIARIA)));
        var evento = builder.crearEvento();
        var tipoResultado = evento.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(titulo, evento.getTitulo());
        assertEquals(descripcion, evento.getDescripcion());
        assertEquals(fecha, evento.getFechaInicio().toLocalDate());
    }

    @Test
    public void CrearEventoSinRepeticion() {
        // Arrange
        var titulo = "Builder sinn repetición";
        var descripcion = "Crea un evento sin repetición";
        var fecha = LocalDate.of(2023,2,2);
        var tipoEsperado = Evento.class;

        // Act
        var builder = new BuilderEvento(titulo, descripcion, new BuilderDuracion(new DuracionArgs(fecha, fecha)));
        var evento = builder.crearEvento();
        var tipoResultado = evento.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(titulo, evento.getTitulo());
        assertEquals(descripcion, evento.getDescripcion());
        assertEquals(fecha, evento.getFechaInicio().toLocalDate());
    }

}