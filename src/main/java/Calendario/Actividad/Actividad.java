package Calendario.Actividad;

import Calendario.Alarmas.Alarma;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Actividad implements Serializable {
    private Set<Alarma> alarmas = new HashSet<>();

    public Actividad(){
    }


    /**
     * Devuelve el título de la Actividad
     */
    public abstract String getTitulo();

    /**
     * Devuelve la descripción de la Actividad
     */
    public abstract String getDescripcion();

    /**
     * Devuelve todas las alarmas con misma fecha y hora, que suenan
     * más próximas a la fecha pasada
     */
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        Set<Alarma> proximasAlarmas = new HashSet<>();
        Alarma primerAlarma = null;
        for(Alarma alarma : getAlarmas()){
            if (debeIncluirAlarma(proximasAlarmas, primerAlarma, alarma, fecha)){
                proximasAlarmas.add(alarma);
                primerAlarma = alarma;
            }
        }
        return proximasAlarmas;
    }

    /**
     * Devuelve el conjunto de alarmas de la actividad
     */
    public Set<Alarma> getAlarmas(){
        return alarmas;
    }

    /**
     * Devuelve true si 'otra' suena antes que 'primerAlarma' y es más próxima a la fecha pasada por parámetro
     */
    private boolean esMasProxima(Alarma primerAlarma, Alarma otra, LocalDateTime fecha){
        return otra.suenaDespues(fecha) && (primerAlarma == null || otra.suenaAntes(primerAlarma));
    }

    /**
     * Devuelve true si dos alarmas suenan al mismo tiempo
     */
    private boolean ambasSonProximas(Alarma primerAlarma, Alarma otra){
        return primerAlarma != null && otra.suenaIgual(primerAlarma);
    }

    /**
     * Devuelve true si 'alarma' es más próxima que 'primerAlarma' o suenan al mismo tiempo
     */
    private boolean debeIncluirAlarma(Set<Alarma> proximasAlarmas, Alarma primerAlarma, Alarma alarma, LocalDateTime fecha){
        if (esMasProxima(primerAlarma, alarma, fecha)){
            proximasAlarmas.clear();
            return true;
        }
        return (ambasSonProximas(primerAlarma, alarma));
    }

    /**
     * Acepta un Visitor
     */
    public abstract void aceptarVisitor(ActividadVisitor actividadVisitor);
}
