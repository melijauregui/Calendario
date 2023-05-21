package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoNotificacion;
import Calendario.Duracion.Duracion;
import Calendario.Enums.TiempoRelativo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.*;

public class InstanciaEventoTest {

    @Test
    public void TestDiaIntervaloMDSettersAndGetters() {
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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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
        var tituloResultado = eventoInstancia.getTitulo();
        var descripcionEsperada = eventoInstancia.getDescripcion();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
        assertEquals(titulo, tituloResultado);
        assertEquals(descripcion, descripcionEsperada);
    }

    @Test
    public void TestDiaCompletoMDSettersAndGetters() {
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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

        //Resultados Esperados
        var resEsperadoDiaInicio = diaInicio;
        var resEsperadoDiaFin = diaFin;
        var resEsperadoFechaInicio = LocalDateTime.of(diaInicio, horaInicio);
        var resEsperadoFechaFin = LocalDateTime.of(diaFin, horaFin);
        var tituloResultado = eventoInstancia.getTitulo();
        var descripcionEsperada = eventoInstancia.getDescripcion();

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
        assertEquals(titulo, tituloResultado);
        assertEquals(descripcion, descripcionEsperada);
    }

    @Test
    public void TestDiaIntervaloDDSettersAndGetters() {
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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

        //Act Resultados Obtenidos
        var resObtenidoDiaInicio = eventoInstancia.getDiaInicio();
        var resObtenidoDiaFin = eventoInstancia.getDiaFin();
        var resObtenidoFechaInicio = eventoInstancia.getFechaInicio();
        var resObtenidoFechaFin = eventoInstancia.getFechaFin();
        var tituloResultado = eventoInstancia.getTitulo();
        var descripcionEsperada = eventoInstancia.getDescripcion();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
        assertEquals(titulo, tituloResultado);
        assertEquals(descripcion, descripcionEsperada);
    }

    @Test
    public void TestDiaCompletoDDSettersAndGetters() {
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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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
        var tituloResultado = eventoInstancia.getTitulo();
        var descripcionEsperada = eventoInstancia.getDescripcion();

        //Assert
        assertEquals(resEsperadoDiaInicio, resObtenidoDiaInicio);
        assertEquals(resEsperadoDiaFin, resObtenidoDiaFin);
        assertEquals(resEsperadoFechaInicio, resObtenidoFechaInicio);
        assertEquals(resEsperadoFechaFin, resObtenidoFechaFin);
        assertEquals(titulo, tituloResultado);
        assertEquals(descripcion, descripcionEsperada);
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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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
        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, new HashSet<>());

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


        var alarma1 = new AlarmaEvento(30, TiempoRelativo.MINUTOS, new AvisoNotificacion());
        var alarma2 = new AlarmaEvento(40, TiempoRelativo.MINUTOS, new AvisoConSonido());

        var titulo = "LastOfUs";
        var descripcion = "Capítulos nuevos de la serie The Last of Us";
        var alarmas = new HashSet<AlarmaEvento>();
        alarmas.add(alarma1);
        alarmas.add(alarma2);
        var eventoInstancia = new InstanciaEvento(titulo, descripcion, duracion, alarmas);

        var resEsperado = alarma2.crearAlarmaInstaciaEvento(eventoInstancia.getFechaInicio());
        int tamanioEsperado = 1;

        //Act
        var alarmasObtenidas = eventoInstancia.getProximasAlarmas(eventoInstancia.getFechaInicio().minusDays(1));
        var alarmaObtenida = alarmasObtenidas.iterator().next();

        //Assert
        assertEquals(tamanioEsperado, alarmasObtenidas.size());
        assertEquals(resEsperado.getFechaAlarma(), alarmaObtenida.getFechaAlarma());
    }

}