package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Duracion.Duracion;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.*;

public class Evento extends Actividad{
    private InstanciaEvento eventoInicial;
    private ArrayList<InstanciaEvento> almacenamientoFechas = new ArrayList<>();
    private Repeticion repeticion = null;

    public Evento(){
        setPrimerEventoDefault();
    }

    public void setEventoInicial(InstanciaEvento eventoInicial){
        this.eventoInicial = eventoInicial;
        eventoInicial.setTitulo(getTitulo());
        eventoInicial.setDescripcion(getDescripcion());
        actualizarAlmacenamientoFechas();
    }


    // setTitulo cambia el título del evento y de las instancias del mismo
    @Override
    public void setTitulo(String titulo){
        super.setTitulo(titulo);
        setTituloInstancias(titulo);
    }

    // setDescripcion cambia la descripción del evento y de las instancias del mismo
    @Override
    public void setDescripcion(String descripcion){
        super.setDescripcion(descripcion);
        setDescripcionInstancias(descripcion);
    }


    // setRepeticion cambia la repetición del evento, y la agrega si no tenía una
    public void setRepeticion(Repeticion repeticion){
        this.repeticion = repeticion;
        actualizarAlmacenamientoFechas();
    }

    // getProximaRepeticion devuelve la primera Instancia que se encuentra a partir de la fecha dada
    public InstanciaEvento getProximaRepeticion(LocalDateTime fecha){
        for(InstanciaEvento instancia : almacenamientoFechas){
            if (instancia.empiezaDespues(fecha)){
                return instancia;
            }
        }
        if (!esInfinito()) {
            return null;
        }
        InstanciaEvento instancia;
        do {
            almacenarUnaFecha();
            instancia = getUltimoEventoAlmacenado();
        } while (!instancia.empiezaDespues(fecha));

        return instancia;
    }


    // getProximaRepeticion devuelve la primera Instancia que se encuentra a partir del evento dado
    public InstanciaEvento getProximaRepeticion(InstanciaEvento evento){
        return getProximaRepeticion(evento.getFechaInicio());
    }


    // configurarAlarma le agrega la alarma al evento, y a las instancias del mismo
    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(getFechaInicio());
        agregarAlarma(alarma);
        setAlarmaInstancias(alarma);
    }

    // eliminarAlarma elimina la alarma recibida del Evento y sus instancias
    @Override
    public void eliminarAlarma(Alarma alarma){
        super.eliminarAlarma(alarma);
        eliminarAlarmaInstancias(alarma);
    }

    /*almacenarFechas agrega al Almacenamiento la Instancia de la primera repetición del Evento.
    Si tiene repetición y no es infinita, agrega las sucesivas Instancias de las repeticiones*/
    private void almacenarFechas(InstanciaEvento primerEvento) {
        if (!this.esEventoConRepeticion()) {
            almacenamientoFechas.add(primerEvento);
        } else {
            this.repeticion.almacenarRepeticiones(this.almacenamientoFechas, primerEvento);
        }
    }

    // almacenarUnaFecha agrega al Almacenamiento la Instancia de la repetición que le sigue a la última guardada
    private void almacenarUnaFecha(){
        var instancia = this.repeticion.getProximaInstanciaEvento(getUltimoEventoAlmacenado());
        almacenamientoFechas.add(instancia);
    }

    // getProximaAlarma devuelve la siguiente alarma del evento
    @Override
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        InstanciaEvento evento = getProximaRepeticion(fecha);
        Set<Alarma> alarmasProximas = evento.getProximasAlarmas(fecha);
        while (alarmasProximas.size() == 0){
            alarmasProximas = getProximaRepeticion(evento).getProximasAlarmas(fecha);
        }
        return alarmasProximas;
    }

    // actualizarAlmacenamientoFechas elimina las instancias del evento y, a partir de la primer instancia,
    // vuelve a cargarlas
    private void actualizarAlmacenamientoFechas(){
        almacenamientoFechas.clear();
        almacenarFechas(this.eventoInicial);
    }

    // getRepeticionActualizada devuelve la última Instancia de Repetición almacenada
    private InstanciaEvento getUltimoEventoAlmacenado(){
        return almacenamientoFechas.get(almacenamientoFechas.size()-1);
    }

    // getFechaInicioActualizada devuelve la fecha de inicio de la última Instancia de Repetición almacenada
    private LocalDateTime getFechaInicioUltimoEvento(){
        return getUltimoEventoAlmacenado().getFechaInicio();
    }

    // getFechaInicio devuelve la fecha de inicio de la primer instancia del evento
    private LocalDateTime getFechaInicio() {
        return almacenamientoFechas.get(0).getFechaInicio();
    }

    // setTituloInstancias cambia el título de las instancias del evento
    private void setTituloInstancias(String titulo){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.setTitulo(titulo);
        }
    }

    // setDescripcionInstancias cambia la descripción de las instancias del evento
    private void setDescripcionInstancias(String descipcion){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.setDescripcion(descipcion);
        }
    }

    // eliminarAlarmaInstancias elimina la alarma recibida de las instancias del evento
    private void eliminarAlarmaInstancias(Alarma alarma){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.eliminarAlarma(alarma);
        }
    }

    //setAlarmaInstancias le configura la alarma a cada instancia del evento
    private void setAlarmaInstancias(Alarma alarma){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.configurarAlarma(alarma);
        }
    }

    private void setPrimerEventoDefault(){
        var duracion = new Duracion();
        duracion.setHoraInicio(LocalTime.now());
        duracion.setHoraFin(LocalTime.now());
        duracion.setDiaInicio(LocalDate.now());
        duracion.setDiaFin(LocalDate.now());
        InstanciaEvento primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        this.setEventoInicial(primerEvento);
    }

    // esInfinito devuelve true si el evento se repite indefinidamente
    private boolean esInfinito(){
        return esEventoConRepeticion() && this.repeticion.esInfinita();
    }

    // esEventoConRepeticion devuelve true si el evento se repite más de 1 vez
    private boolean esEventoConRepeticion(){
        return this.repeticion != null;
    }

    public ArrayList<InstanciaEvento> getAlmacenamientoFechas(){
        return almacenamientoFechas;
    }
}



