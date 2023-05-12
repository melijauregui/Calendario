package Calendario.Alarmas;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Enums.TiempoRelativo;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AlarmaTest {
    @Test
    public void TestAlarmaSuenaAntesDeUnaFecha() {
        // Arrange
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
        Alarma alarma = new Alarma(fecha, new AvisoConSonido());
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
        AlarmaEvento alarma = new AlarmaEvento(10, TiempoRelativo.MINUTOS, new AvisoConSonido());
        LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
        LocalDateTime fechaAntes = LocalDateTime.of(2023, 4, 22, 16, 4);
        LocalDateTime fechaDespues = LocalDateTime.of(2023, 4, 22, 16, 6);

        // Act
        var alarmaFecha = alarma.crearAlarmaInstaciaEvento(fecha);

        //Assert
        assertFalse(alarmaFecha.suenaAntes(fechaAntes));
        assertTrue(alarmaFecha.suenaAntes(fechaDespues));


    }
    @Test
    public void TestSuenaAntesDeOtraAlarma() {
         //Arrange
         AlarmaEvento alarma = new AlarmaEvento(10, TiempoRelativo.MINUTOS, new AvisoConSonido());
         LocalDateTime fecha = LocalDateTime.of(2023, 4, 22, 16, 15);
         Alarma otra = new Alarma(fecha, new AvisoNotificacion());
         var alarmaFecha = alarma.crearAlarmaInstaciaEvento(fecha);

         //Act
         boolean suenaAntes = alarmaFecha.suenaAntes(otra);

         //Assert
         assertTrue(suenaAntes);
    }

    @Test
    public void TestAlarmasSuenanAlMismoTiempo() {
        //Arrange
        AlarmaEvento alarma = new AlarmaEvento(10, TiempoRelativo.MINUTOS, new AvisoConSonido());
        LocalDateTime fechaAlarma = LocalDateTime.of(2023, 4, 22, 16, 25);
        LocalDateTime fechaOtra = LocalDateTime.of(2023, 4, 22, 16, 15);
        Alarma otra = new Alarma(fechaOtra, new AvisoNotificacion());
        var alarmaFecha = alarma.crearAlarmaInstaciaEvento(fechaAlarma);

        //Act
        boolean suenaIgual = alarmaFecha.suenaIgual(otra);

        //Assert
        assertTrue(suenaIgual);
    }


}