package Calendario.Enums;

import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoEmail;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import org.junit.Test;

import static org.junit.Assert.*;

public class TipoAvisoTest {

    @Test
    public void CrearAvisoNotificacion() {
        //Arrange
        var tipoAvisoEsperado = AvisoNotificacion.class;

        //Act
        var aviso = TipoAviso.NOTIFICACION.crearAviso();
        var tipoAvisoResultado = aviso.getClass();

        //Assert
        assertEquals(tipoAvisoEsperado, tipoAvisoResultado);

    }

    @Test
    public void CrearAvisoSonido() {
        //Arrange
        var tipoAvisoEsperado = AvisoConSonido.class;

        //Act
        var aviso = TipoAviso.SONIDO.crearAviso();
        var tipoAvisoResultado = aviso.getClass();

        //Assert
        assertEquals(tipoAvisoEsperado, tipoAvisoResultado);

    }

    @Test
    public void CrearAvisoEmail() {
        //Arrange
        var tipoAvisoEsperado = AvisoEmail.class;

        //Act
        var aviso = TipoAviso.EMAIL.crearAviso();
        var tipoAvisoResultado = aviso.getClass();

        //Assert
        assertEquals(tipoAvisoEsperado, tipoAvisoResultado);

    }
}