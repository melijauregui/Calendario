package Calendario.Eventos;

import Calendario.Duracion.Duracion;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class InstanciaEventoTest {

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

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act
        var resObtenidoDiaInicio = eventoInstancia.getDiaInicio();
        var resObtenidoDiaFin = eventoInstancia.getDiaFin();
        var resObtenidoFechaInicio = eventoInstancia.getFechaInicio();
        var resObtenidoFechaFin = eventoInstancia.getFechaFin();

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

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);


        //Act
        var resObtenidoDiaInicio = eventoInstancia.getDiaInicio();
        var resObtenidoDiaFin = eventoInstancia.getDiaFin();
        var resObtenidoFechaInicio = eventoInstancia.getFechaInicio();
        var resObtenidoFechaFin = eventoInstancia.getFechaFin();

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

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Act
        var resObtenidoDiaInicio = eventoInstancia.getDiaInicio();
        var resObtenidoDiaFin = eventoInstancia.getDiaFin();
        var resObtenidoFechaInicio = eventoInstancia.getFechaInicio();
        var resObtenidoFechaFin = eventoInstancia.getFechaFin();

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

        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);


        //Act
        var resObtenidoDiaInicio = eventoInstancia.getDiaInicio();
        var resObtenidoDiaFin = eventoInstancia.getDiaFin();
        var resObtenidoFechaInicio = eventoInstancia.getFechaInicio();
        var resObtenidoFechaFin = eventoInstancia.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }


    // ----------------------------------------------------------------------------
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

        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);


        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);


        //Act
        var resObtenidoDiaInicio = eventoClon.getDiaInicio();
        var resObtenidoDiaFin = eventoClon.getDiaFin();
        var resObtenidoFechaInicio = eventoClon.getFechaInicio();
        var resObtenidoFechaFin = eventoClon.getFechaFin();

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

        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);


        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);


        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = eventoClon.getDiaInicio();
        var resObtenidoDiaFin = eventoClon.getDiaFin();
        var resObtenidoFechaInicio = eventoClon.getFechaInicio();
        var resObtenidoFechaFin = eventoClon.getFechaFin();

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

        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);


        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);


        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = eventoClon.getDiaInicio();
        var resObtenidoDiaFin = eventoClon.getDiaFin();
        var resObtenidoFechaInicio = eventoClon.getFechaInicio();
        var resObtenidoFechaFin = eventoClon.getFechaFin();

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

        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);


        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);


        //Act
        var duracionClon = duracion.Clone();
        var resObtenidoDiaInicio = eventoClon.getDiaInicio();
        var resObtenidoDiaFin = eventoClon.getDiaFin();
        var resObtenidoFechaInicio = eventoClon.getFechaInicio();
        var resObtenidoFechaFin = eventoClon.getFechaFin();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
    }


}