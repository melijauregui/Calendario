package Calendario.Duracion;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class DuracionTest {

    @Test
    public void TestDiaIntervaloMDSettersAndGetters(){
        //MismoDía
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    @Test
    public void TestDiaCompletoMDSettersAndGetters(){
        //Mismo día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }

    @Test
    public void TestDiaIntervaloDDSettersAndGetters(){
        //Diferente día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    @Test
    public void TestDiaCompletoDDSettersAndGetters(){
        //Diferente Día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }


   // ----------------------------------------------------------------------------
    @Test
   public void TestDiaIntervaloMDClone(){
       //MismoDía
       //Arrange
       var duracion = new Duracion();
       var diaInicio = LocalDate.of(2023, 11, 11);
       var diaFin = LocalDate.of(2023, 11, 11);
       var horaInicio = LocalTime.of(18, 30);
       var horaFin = LocalTime.of(19, 30);
       duracion.setDiaInicio(diaInicio);
       duracion.setDiaFin(diaFin);
       duracion.setHoraInicio(horaInicio);
       duracion.setHoraFin(horaFin);

       var resEsperadoDiaInicio = diaInicio;
       var resEsperadoDiaFin = diaFin;
       var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
       var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

       //Act
       var duracionClon = duracion.Clone();
       var resObtenidoDiaInicio = duracionClon.getDiaInicio();
       var resObtenidoDiaFin = duracionClon.getDiaFin();
       var resObtenidoFechaInicio = duracionClon.getFechaInicio();
       var resObtenidoFechaFin = duracionClon.getFechaFin();

       //Assert
       assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
       assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
       assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
       assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
   }
    @Test
    public void TestDiaCompletoMDClon(){
        //Mismo día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = duracionClon.getDiaInicio();
        var resObtenidoDiaFin = duracionClon.getDiaFin();
        var resObtenidoFechaInicio = duracionClon.getFechaInicio();
        var resObtenidoFechaFin = duracionClon.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }

    @Test
    public void TestDiaIntervaloDDClon(){
        //Diferente día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = duracionClon.getDiaInicio();
        var resObtenidoDiaFin = duracionClon.getDiaFin();
        var resObtenidoFechaInicio = duracionClon.getFechaInicio();
        var resObtenidoFechaFin = duracionClon.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    @Test
    public void TestDiaCompletoDDClon(){
        //Diferente Día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = duracionClon.getDiaInicio();
        var resObtenidoDiaFin = duracionClon.getDiaFin();
        var resObtenidoFechaInicio = duracionClon.getFechaInicio();
        var resObtenidoFechaFin = duracionClon.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    //------------------------------------------------------------------------------------------------
    @Test
    public void TestDiaIntervaloMDCambiarFecha(){
        //MismoDía
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);


        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 10, 7);
        var horaActualizadaInicio = LocalTime.of(20, 35);
        var horaActualizadaFin = LocalTime.of(23, 23);

        duracion.setDiaInicio(diaInicioActualizado);
        duracion.setDiaFin(diaFinActualizado);
        duracion.setHoraInicio(horaActualizadaInicio);
        duracion.setHoraFin(horaActualizadaFin);

        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaActualizadaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaActualizadaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    @Test
    public void TestDiaCompletoMDCambiarFecha(){
        //Mismo día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 10, 7);

        duracion.setDiaInicio(diaInicioActualizado);
        duracion.setDiaFin(diaFinActualizado);

        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }

    @Test
    public void TestDiaIntervaloDDCambiarFecha(){
        //Diferente día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);

        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 11, 14);
        var horaActualizadaInicio = LocalTime.of(20, 35);
        var horaActualizadaFin = LocalTime.of(23, 23);

        duracion.setDiaInicio(diaInicioActualizado);
        duracion.setDiaFin(diaFinActualizado);
        duracion.setHoraInicio(horaActualizadaInicio);
        duracion.setHoraFin(horaActualizadaFin);

        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaActualizadaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaActualizadaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }
    @Test
    public void TestDiaCompletoDDCambiarFecha(){
        //Diferente Día
        //Arrange
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);

        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 11, 14);


        duracion.setDiaInicio(diaInicioActualizado);
        duracion.setDiaFin(diaFinActualizado);


        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaFin);

        //Act
        var resObtenidoDiaInicio = duracion.getDiaInicio();
        var resObtenidoDiaFin = duracion.getDiaFin();
        var resObtenidoFechaInicio = duracion.getFechaInicio();
        var resObtenidoFechaFin = duracion.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }


}

