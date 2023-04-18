package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento extends Actividad{
    private ArrayList<InstanciaEvento> almacenamientoFechas = new ArrayList<>();
    private Repeticion repeticion;

    private LocalDateTime fechaInicio;

    public Evento(String titulo, String descripcion, Repeticion repeticion,
                               LocalDateTime fechaInicio,LocalDateTime fechaFin) {
        super(titulo, descripcion);
        var eventoInicial = new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin);
        this.repeticion = repeticion;
        almacenarFechas(eventoInicial);
        this.fechaInicio = eventoInicial.getFechaInicio();
    }

    public Evento(String titulo, String descripcion, Repeticion repeticion,
                  LocalDate fechaInicio, LocalDate fechaFin) {
        super(titulo, descripcion);
        var eventoInicial = new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin);
        this.repeticion = repeticion;
        almacenarFechas(eventoInicial);
        this.fechaInicio = eventoInicial.getFechaInicio();
    }

    public Evento(String titulo, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        super(titulo, descripcion);
        var eventoInicial = new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin);
        almacenamientoFechas.add(eventoInicial);
        this.fechaInicio = eventoInicial.getFechaInicio();
    }

    // es de dia completo
    public Evento(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin) {
        super(titulo, descripcion);
        var eventoInicial = new InstanciaEvento(titulo, descripcion, fechaInicio, fechaFin);
        almacenamientoFechas.add(eventoInicial);
        this.fechaInicio = eventoInicial.getFechaInicio();
    }

    @Override
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    // getProximaRepeticion devuelve la primera Instancia que se encuentra a partir de la fecha dada
    public InstanciaEvento getProximaRepeticion(LocalDateTime fecha){

        for(InstanciaEvento instancia : almacenamientoFechas){
            if (instancia.empiezaDespues(fecha)){
                return instancia;
            }
        }
        if (!repeticion.esInfinita()) {
            return null;
        }

        InstanciaEvento instancia;
        do {
            instancia = almacenarUnaFecha();
        } while (!instancia.empiezaDespues(fecha));

        return instancia;
    }

    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(almacenamientoFechas.get(0).getFechaInicio());
        setAlarma(alarma);
        setAlarmaInstancias();
    }

    private void setAlarmaInstancias(){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.configurarAlarma(getAlarma());
        }
    }

    /*almacenarFechas agrega al Almacenamiento la Instancia de la primera repetición del Evento.
    Si la repetición no es infinita, agrega las sucesivas Instancias de las repeticiones*/
    private void almacenarFechas(InstanciaEvento primerEvento) {
        almacenamientoFechas.add(primerEvento);
        repeticion.disminuirOcurrencias();
        if (!repeticion.esInfinita()){
            while (!repeticion.terminoRepeticion(getFechaInicioUltimoEvento().toLocalDate())){
                repeticion.disminuirOcurrencias();
                almacenarUnaFecha();
            }
        }
    }

    // almacenarUnaFecha agrega al Almacenamiento la Instancia de la repetición que le sigue a la última guardada
    private InstanciaEvento almacenarUnaFecha(){
        var instancia = this.repeticion.getProximaInstanciaEvento(getUltimoEventoAlmacenado());
        almacenamientoFechas.add(instancia);
        return instancia;
    }


    // getRepeticionActualizada devuelve la última Instancia de Repetición almacenada
    private InstanciaEvento getUltimoEventoAlmacenado(){
        return almacenamientoFechas.get(almacenamientoFechas.size()-1);
    }

    // getFechaInicioActualizada devuelve la fecha de inicio de la última Instancia de Repetición almacenada
    private LocalDateTime getFechaInicioUltimoEvento(){
        return getUltimoEventoAlmacenado().getFechaInicio();
    }

    public Alarma getProximaAlarma(LocalDateTime fecha){
        InstanciaEvento evento = getProximaRepeticion(fecha);
        Alarma alarmaProxima = evento.getAlarma();
        if (alarmaProxima.getFechaAlarma().isBefore(fecha)){
            alarmaProxima = getProximaRepeticion(evento).getAlarma();
        }
        return alarmaProxima;
    }

    public InstanciaEvento getProximaRepeticion(InstanciaEvento evento){
        return getProximaRepeticion(evento.getFechaInicio());
    }
}




