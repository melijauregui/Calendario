package Calendario.Actividad;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Actividad {
    private Set<Alarma> alarmas = new HashSet<>();

    public Actividad(){
    }

    public abstract String getTitulo();

    public abstract String getDescripcion();

    /**
     * Devuelve todas las alarmas con misma fecha y hora, que suenan
     * más próximas a la fecha pasada
     */
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        Set<Alarma> proximasAlarmas = new HashSet<>();
        Alarma primerAlarma = null;
        for(Alarma alarma : getAlarmas()){
            if (esMasProxima(primerAlarma, alarma, fecha)){
                primerAlarma = alarma;
                proximasAlarmas.clear();
                proximasAlarmas.add(alarma);
            }else if (ambasSonProximas(primerAlarma, alarma)){
                proximasAlarmas.add(alarma);
            }
        }
        return proximasAlarmas;
    }


    /**
     * Devuelve el conjunto de alarmas de la actividad
     */
    protected Set<Alarma> getAlarmas(){
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


}
