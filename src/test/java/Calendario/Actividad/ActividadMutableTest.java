package Calendario.Actividad;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ActividadMutableTest {

    @Test
    public void actividadVacia(){
        //Arrange
        var actividad = new ActividadMutable();
        String cadenaEsperada = null;
        int tamanioEsperado = 0;

        //Act
        var alarmasResultado = actividad.getAlarmas();
        var tituloResultado = actividad.getTitulo();
        var descripcionResultado = actividad.getDescripcion();

        //Assert
        assertEquals(tamanioEsperado, alarmasResultado.size());
        assertEquals(cadenaEsperada, tituloResultado);
        assertEquals(cadenaEsperada, descripcionResultado);
    }

    @Test
    public void getTitulo() {
        //Arrange
        var titulo = "Título";
        var actividad = new ActividadMutable(titulo, "");

        //Act
        var tituloResultado = actividad.getTitulo();

        //Assert
        assertEquals(titulo, tituloResultado);

    }

    @Test
    public void getDescripcion() {
        //Arrange
        var descripcion = "Descripción";
        var actividad = new ActividadMutable("", descripcion);

        //Act
        var descripcionResultado = actividad.getDescripcion();

        //Assert
        assertEquals(descripcion, descripcionResultado);
    }

    @Test
    public void setTitulo() {
        //Arrange
        var actividad = new ActividadMutable();
        String titulo = "Título";

        //Act
        actividad.setTitulo(titulo);
        var tituloResultado = actividad.getTitulo();

        //Assert
        assertEquals(titulo, tituloResultado);
    }

    @Test
    public void setDescripcion() {
        //Arrange
        var actividad = new ActividadMutable();
        String descripcion = "Descripción";

        //Act
        actividad.setDescripcion(descripcion);
        var descripcionResultado = actividad.getDescripcion();

        //Assert
        assertEquals(descripcion, descripcionResultado);
    }

}