package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Tareas.Tarea;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BuilderTareaTest {

    @Test
    public void CrearTareaConHora() {
        // Arrange
        var titulo = "Builder con hora";
        var descripcion = "Crea una tarea con hora";
        var fecha = LocalDateTime.of(2023,2,2, 2, 2);
        var tipoEsperado = Tarea.class;

        // Act
        var builder = new BuilderTarea(titulo, descripcion, fecha);
        var tarea = builder.crearTarea();
        var tipoResultado = tarea.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(titulo, tarea.getTitulo());
        assertEquals(descripcion, tarea.getDescripcion());
        assertEquals(fecha, tarea.getFecha());
    }

    @Test
    public void CrearTareaDiaCompleto() {
        // Arrange
        var titulo = "Builder Día completo";
        var descripcion = "Crea una tarea de día completo";
        var fecha = LocalDateTime.of(2023,2,2, 23, 59);
        var tipoEsperado = Tarea.class;

        // Act
        var builder = new BuilderTarea(titulo, descripcion, fecha.toLocalDate());
        var tarea = builder.crearTarea();
        var tipoResultado = tarea.getClass();

        // Assert
        assertEquals(tipoEsperado, tipoResultado);
        assertEquals(titulo, tarea.getTitulo());
        assertEquals(descripcion, tarea.getDescripcion());
        assertEquals(fecha, tarea.getFecha());
    }
}