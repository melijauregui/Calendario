package Calendario.Eventos;
/*
import Calendario.Duracion.Duracion;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.*;

public class InstanciaEventoTest {

    @Test
    public void TestDiaIntervaloMDSettersAndGetters(){
        //Comprueba que los setters y getters de la instancia evento se comporte como lo esperado
        //MismoDía

        //Arrange
        //defino fecha
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

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act Resultados Obtenidos
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
        //Comprueba que los setters y getters de la instancia evento se comporte como lo esperado
        //Mismo día y completo

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act Resultados Obtenidos
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
        //Comprueba que los setters y getters de la instancia evento se comporte como lo esperado
        //Diferente día

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Act Resultados Obtenidos
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
        //Comprueba que los setters y getters de la instancia evento se comporte como lo esperado
        //Diferente Día

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);

        //Act Resultados Obtenidos
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

/*    public void TestDiaIntervaloMDClone(){
        //Comprueba que el metodo clone de instancia evento se comporte como lo esperado
        //MismoDía

        //Arrange
        //defino fecha
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

        //fecha para instancia clon
        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);

        //obtengo instancia clone
        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);

        //Act Resultados Obtenidos
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
        //Comprueba que el metodo clone de instancia evento se comporte como lo esperado
        //Mismo día

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //fecha para instancia clon
        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);

        //obtengo instancia clone
        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);


        //Act Resultados Obtenidos
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
        //Comprueba que el metodo clone de instancia evento se comporte como lo esperado
        //Diferente día

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(18, 30);
        var horaFin = LocalTime.of(19, 30);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        duracion.setHoraInicio(horaInicio);
        duracion.setHoraFin(horaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //fecha para instancia clon
        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);

        //obtengo instancia clone
        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);

        //Act Resultados Obtenidos
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
        //Comprueba que el metodo clone de instancia evento se comporte como lo esperado
        //DiaCompleto

        //Arrange
        //defino fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 12, 7);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //fecha para instancia clon
        var diaInicioDespues = diaInicio.plusDays(3);
        var diaFinDespues = diaFin.plusDays(3);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioDespues;
        var resEsperadoDiaFin = diaFinDespues;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioDespues, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinDespues, horaFin);

        //obtengo instancia clone
        var eventoClon = eventoInstancia.Clone(diaInicioDespues, diaFinDespues);

        //Act Resultados Obtenidos
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
    }*/
/*
    //----------------------------------------------------------------------------------
    @Test
    public void TestDiaIntervaloCambiarFecha(){
        //Comprueba que al cambiar la fecha de la instancia evento se comporte como lo esperado
        //MismoDía

        //Arrange
        //defino primer fecha
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

        //defino segunda fecha
        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 10, 7);
        var horaActualizadaInicio = LocalTime.of(20, 35);
        var horaActualizadaFin = LocalTime.of(23, 23);
        var duracionActualizada = new Duracion();
        duracionActualizada.setDiaInicio(diaInicioActualizado);
        duracionActualizada.setDiaFin(diaFinActualizado);
        duracionActualizada.setHoraInicio(horaActualizadaInicio);
        duracionActualizada.setHoraFin(horaActualizadaFin);
        eventoInstancia.setDuracion(duracionActualizada);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaActualizadaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaActualizadaFin);

        //Act Resultados Obtenidos
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
    public void TestDiaCompletoCambiarFecha(){
        //Comprueba que al cambiar la fecha de la instancia evento se comporte como lo esperado
        //Mismo día

        //Arrange
        //defino primer fecha
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        var horaInicio = LocalTime.of(00, 00);
        var horaFin = LocalTime.of(23, 59);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //defino segunda fecha
        var diaInicioActualizado = LocalDate.of(2024, 10, 7);
        var diaFinActualizado = LocalDate.of(2024, 10, 7);
        duracion.setDiaInicio(diaInicioActualizado);
        duracion.setDiaFin(diaFinActualizado);
        var duracionActualizada = new Duracion();
        duracionActualizada.setDiaInicio(diaInicioActualizado);
        duracionActualizada.setDiaFin(diaFinActualizado);
        eventoInstancia.setDuracion(duracionActualizada);

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicioActualizado;
        var resEsperadoDiaFin = diaFinActualizado;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicioActualizado, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFinActualizado, horaFin);

        //Act Resultados Obtenidos
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

    //----------------------------------------------------------------------------------
    @Test
    public void TestDiaIntervaloEmpiezaDespuesFalse() {
        //Comprueba que el metodo EmpiezaDespues(fecha) de la instancia evento se comporte como lo esperado
        //MismoDía

        //Arrange
        //defino instancia evento
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

        //defino fecha
        var fecha = LocalDateTime.of(2024, 11, 11, 18, 30);

        //Resultados Esperados
        var resEsperado = false;

        //Act Resultados Obtenidos
        var resObtenido = eventoInstancia.empiezaDespues(fecha);

        //Assert
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void TestDiaIntervaloEmpiezaDespuesTrue() {
        //Comprueba que el metodo EmpiezaDespues(fecha) de la instancia evento se comporte como lo esperado
        //MismoDía

        //Arrange
        //defino instancia evento
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

        //defino fecha
        var fecha = LocalDateTime.of(2023, 11, 11, 18, 23);

        //Resultados Esperados
        var resEsperado = true;

        //Act Resultados Obtenidos
        var resObtenido = eventoInstancia.empiezaDespues(fecha);

        //Assert
        assertEquals(resEsperado, resObtenido);

    }

    @Test
    public void TestDiaCompletoEmpiezaDespuesFalse() {
        //Comprueba que el metodo EmpiezaDespues(fecha) de la instancia evento se comporte como lo esperado
        //MismoDía completo

        //Arrange
        //defino instancia evento
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //defino fecha
        var fecha = LocalDateTime.of(2023, 11, 11, 00, 30);

        //Resultados Esperados
        var resEsperado = false;

        //Act Resultados Obtenidos
        var resObtenido = eventoInstancia.empiezaDespues(fecha);

        //Assert
        assertEquals(resEsperado, resObtenido);
    }

    @Test
    public void TestDiaCompletoEmpiezaDespuesTrue() {
        //Comprueba que el metodo EmpiezaDespues(fecha) de la instancia evento se comporte como lo esperado
        //MismoDía completo

        //Arrange
        //defino instancia evento
        var duracion = new Duracion();
        var diaInicio = LocalDate.of(2023, 11, 11);
        var diaFin = LocalDate.of(2023, 11, 11);
        duracion.setDiaInicio(diaInicio);
        duracion.setDiaFin(diaFin);
        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);

        //defino fecha
        var fecha = LocalDateTime.of(2023, 11, 10, 18, 23);

        //Resultados Esperados
        var resEsperado = true;

        //Act Resultados Obtenidos
        var resObtenido = eventoInstancia.empiezaDespues(fecha);

        //Assert
        assertEquals(resEsperado, resObtenido);

    }

    @Test
    public void TestConfigurarAlarmaProxima(){
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


        var alarma1 = new AlarmaConNotificacion(LocalDateTime.of(diaInicio, horaInicio.minusMinutes(30)));
        var alarma2 = new AlarmaConEmail(LocalDateTime.of(diaInicio, horaInicio.plusMinutes(30)));
        var alarmas = new HashSet<Alarma>();
        alarmas.add(alarma1);
        alarmas.add(alarma2);


        var eventoInstancia = new InstanciaEvento();
        eventoInstancia.setDuracion(duracion);
        eventoInstancia.configurarAlarmas(alarmas);

        var resEsperado = new HashSet<Alarma>();
        resEsperado.add(alarma2);

        //Act
        var alarmasObtenidas = eventoInstancia.getProximasAlarmas(LocalDateTime.of(diaInicio, horaInicio));

        //Assert
        assertEquals(resEsperado, alarmasObtenidas);
    }

}*/