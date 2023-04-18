package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Enums.Mes;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoConRepeticion extends Evento {
    private ArrayList<InstanciaEvento> almacenamientoFechas;
    private Repeticion repeticion;

    //no es de dia completo, tiene fecha limite
    public EventoConRepeticion(String titulo, String descripcion, Repeticion repeticion,
                               LocalDateTime fechaHoraInicio,LocalDateTime fechaHoraFin) {
        super(titulo, descripcion, fechaHoraInicio, fechaHoraFin);
        this.almacenamientoFechas = new ArrayList<>();
        this.repeticion = repeticion;
        almacenarFechas();
        //repeticion.RepetirEvento(almacenamientoFechas, fechaInicio, fechaFin);
    }

    // es de dia completo
    public EventoConRepeticion(String titulo, String descripcion, Repeticion repeticion,
                  LocalDate fechaInicio, LocalDate fechaFin,
                  int diaFin, Mes mesFin, int anioFin) {
        super(titulo, descripcion, fechaInicio, fechaFin);
        this.repeticion = repeticion;
        this.almacenamientoFechas = new ArrayList<>();
        this.almacenamientoFechas = new ArrayList<>();
        almacenarFechas();
        //repeticion.RepetirEvento(almacenamientoFechas, fechaInicio, fechaFin);

    }

    // getProximaRepeticion devuelve la primera Instancia que se encuentra a partir de la fecha dada
    public InstanciaEvento getProximaRepeticion(LocalDateTime fechaActual){
        var instancia = buscarProximaRepeticion(fechaActual);
        if (repeticion.esInfinita()){
            while (instancia == null || !instancia.empiezaDespues(fechaActual)){
                almacenarUnaFecha();
                instancia = getRepeticionActualizada();
            }
        }
        return instancia;
    }

    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(getFecha());
        setAlarma(alarma);
        setAlarmaInstancias();
    }

    private void setAlarmaInstancias(){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.configurarAlarma(getAlarma());
        }
    }

    // almacenarFechas agrega al Almacenamiento la Instancia de la primera repetición del Evento.
    // Si la repetición no es infinita, agrega las sucesivas Instancias de las repeticiones
    private void almacenarFechas() {
        almacenamientoFechas.add(new InstanciaEvento(getTitulo(), getDescripcion(), getFecha(), getFechaFin()));
        if (!repeticion.esInfinita()){
            repeticion.disminuirOcurrencias();
            while (!repeticion.terminoRepeticion(getFechaInicioActualizada().toLocalDate())){
                almacenarUnaFecha();
            }
        }
    }

    // almacenarUnaFecha agrega al Almacenamiento la Instancia de la repetición que le sigue a la última guargada
    private void almacenarUnaFecha(){
        LocalDateTime nuevaFechaInicio = repeticion.getProximaFechaInicio(getFechaInicioActualizada());
        LocalDateTime nuevaFechaFin = repeticion.getProximaFechaFin(getFechaFinActualizada());
        var instancia = new InstanciaEvento(getTitulo(), getDescripcion(), nuevaFechaInicio, nuevaFechaFin);
        instancia.configurarAlarma(getAlarma());
        almacenamientoFechas.add(instancia);

    }


    // getRepeticionActualizada devuelve la última Instancia de Repetición almacenada
    private InstanciaEvento getRepeticionActualizada(){
        return almacenamientoFechas.get(almacenamientoFechas.size()-1);
    }

    // getFechaInicioActualizada devuelve la fecha de inicio de la última Instancia de Repetición almacenada
    private LocalDateTime getFechaInicioActualizada(){
        return getRepeticionActualizada().getFecha();
    }

    // getFechaFinActualizada devuelve la fecha de finalización de la última Instancia de Repetición almacenada
    private LocalDateTime getFechaFinActualizada(){
        return getRepeticionActualizada().getFechaFin();
    }

    // buscarProximaRepeticion recorre las Instancias almacenadas y devuelve la primera que se encuentra
    // a partir de la fecha dada
    private InstanciaEvento buscarProximaRepeticion(LocalDateTime fecha){
        for(InstanciaEvento instancia : almacenamientoFechas){
            if (instancia.empiezaDespues(fecha)){
                return instancia;
            }
        }
        return null;
    }





}




