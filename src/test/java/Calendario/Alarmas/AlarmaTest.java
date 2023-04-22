package Calendario.Alarmas;

import Calendario.Enums.TiempoRelativo;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AlarmaTest {
    @Test
    public void TestAlarmaSuenaAntesDeUnaFecha() {
        // Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
        Alarma alarma = new AlarmaConEmail(fecha);
        LocalDateTime fechaAntes = LocalDateTime.of(2023, 4, 22, 16, 14);
        LocalDateTime fechaDespues = LocalDateTime.of(2023, 4, 22, 16, 16);

        //Act
        boolean suenaAntesFechaAntes = alarma.suenaAntes(fechaAntes);
        boolean suenaAntesFechaDespues = alarma.suenaAntes(fechaDespues);

        //Assert
        assertFalse(suenaAntesFechaAntes);
        assertTrue(suenaAntesFechaDespues);
    }

    @Test
    public void TestDeterminarFechaRelativa() {
        //Arrange
        Alarma alarma = new AlarmaConSonido(10, TiempoRelativo.MINUTOS);
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
        LocalDateTime fechaAntes = LocalDateTime.of(2023, 4, 22, 16, 4);
        LocalDateTime fechaDespues = LocalDateTime.of(2023, 4, 22, 16, 6);

        // Act
        alarma.determinarFecha(fecha);

        //Assert
        assertFalse(alarma.suenaAntes(fechaAntes));
        assertTrue(alarma.suenaAntes(fechaDespues));


    }
    @Test
    public void TestSuenaAntesDeOtraAlarma() {
         //Arrange
         Alarma alarma = new AlarmaConEmail(10, TiempoRelativo.MINUTOS);
         LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
         Alarma otra = new AlarmaConNotificacion(fecha);
         alarma.determinarFecha(fecha);

         //Act
         boolean suenaAntes = alarma.suenaAntes(otra);

         //Assert
         assertTrue(suenaAntes);
    }

    @Test
    public void TestAlarmasSuenanAlMismoTiempo() {
        //Arrange
        Alarma alarma = new AlarmaConNotificacion(10, TiempoRelativo.MINUTOS);
        LocalDateTime fechaAlarma = LocalDateTime.of(2023, 4, 22, 16, 25);
        LocalDateTime fechaOtra = LocalDateTime.of(2023, 4, 22, 16, 15);
        Alarma otra = new AlarmaConNotificacion(fechaOtra);
        alarma.determinarFecha(fechaAlarma);

        //Act
        boolean suenaIgual = alarma.suenaIgual(otra);

        //Assert
        assertTrue(suenaIgual);
    }


}