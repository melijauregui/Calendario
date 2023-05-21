package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.AlarmaEvento;
import Calendario.Duracion.Duracion;
import Calendario.Actividad.ActividadMutable;
import Calendario.Repeticiones.Repeticion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Evento extends ActividadMutable {

    private Duracion duracion;
    private Repeticion repeticion;
    private Set<AlarmaEvento> alarmas = new HashSet<>();

    public Evento(){
    }


    /**
     * Cambia la duración del evento
     */
    public void setDuracion(Duracion duracion){
        this.duracion = duracion;
    }

    /**
     * Cambia la repetición del evento
     */
    public void setRepeticion(Repeticion repeticion){
        this.repeticion = repeticion;

    }

    /**
     * Crea una Instancia del evento con su información y una nueva duración que depende de la repetición
     * del mismo
     */
    public InstanciaEvento crearInstancia(LocalDate diaInicio, LocalDate diaFin){
        Duracion nuevaDuracion = this.duracion.Clone();
        nuevaDuracion.setDiaInicio(diaInicio);
        nuevaDuracion.setDiaFin(diaFin);
        InstanciaEvento instancia = new InstanciaEvento(getTitulo(), getDescripcion(), nuevaDuracion, alarmas);
        //instancia.configurarAlarmas(this.alarmas);
        return instancia;
    }

    /**
     * Devuelve todas las instancias del evento que se inician en el intervalo de fechas pasado
     */
    public List<InstanciaEvento> getProximasRepeticiones(LocalDateTime desde, LocalDateTime hasta){
        List<InstanciaEvento> eventos = new ArrayList<>();
        InstanciaEvento instancia = crearInstancia(duracion.getDiaInicio(), duracion.getDiaFin());
        while (instancia != null && !instancia.empiezaDespues(hasta)){
            if (instancia.empiezaDespues(desde) || instancia.empiezaIgual(desde)){
                eventos.add(instancia);
            }
            instancia = getProximaRepeticion(instancia.getDiaInicio(), instancia.getDiaFin());
        }
        return eventos;

    }

    /**
     * Devuelve la siguiente Instancia de Evento a la fechaInicio recibida.
     * Si el evento no tiene repetición, o la misma acabó devuelve null.
     */
    private InstanciaEvento getProximaRepeticion(LocalDate fechaInicio, LocalDate fechaFin){
        if (repeticion == null){
            return null;
        }
        fechaInicio = repeticion.getProximaFechaInicio(fechaInicio);
        fechaFin = repeticion.getProximaFechaFin(fechaFin);
        if (fechaInicio == null){
            return null;
        }
        return crearInstancia(fechaInicio, fechaFin);
    }

    /**
     * Agrega la alarma al evento
     */
    public void agregarAlarma(AlarmaEvento alarma){
        alarmas.add(alarma);
    }

    /**
     * Devuelve el conjunto de alarmas que suenan al mismo tiempo y son las más
     * próximas a la fecha pasada
     */
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        Set <Alarma> alarmasProximas = new HashSet<>();
        InstanciaEvento instancia = crearInstancia(duracion.getDiaInicio(), duracion.getDiaFin());
        while (instancia != null){
            if (instancia.empiezaDespues(fecha)){
                alarmasProximas = instancia.getProximasAlarmas(fecha);
                if (!estaVacia(alarmasProximas)){
                    return alarmasProximas;
                }
            }
            instancia = getProximaRepeticion(instancia.getDiaInicio(), instancia.getDiaFin());
        }
        return alarmasProximas;
    }


    /**
     * Elimina la alarma recibida del Evento
     */

    public void eliminarAlarma(AlarmaEvento alarma){
        alarmas.remove(alarma);
    }

    /**
     * Devuelve la fecha de inicio de la primera instancia del evento
     */
    public LocalDateTime getFechaInicio() {
        return duracion.getFechaInicio();
    }


    private boolean estaVacia(Collection collection){
        return collection.size() == 0;
    }

}


