package Calendario.Main;

import Calendario.Alarmas.Alarma;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private Set<Alarma> alarmas = new HashSet<>();


    public Actividad(){
    }

    /**
     * Devuelve el título de la Actividad
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la descripción de la Actividad
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Recibe una Alarma y se la agrega a la Actividad
     */
    public abstract void configurarAlarma(Alarma alarma);

    /**
     * Elimina la alarma recibida por parámetro
     */
    public void eliminarAlarma(Alarma alarma){
        alarmas.remove(alarma);
    }

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
            }
            if (esMasProxima(primerAlarma, alarma, fecha) || ambasSonProximas(primerAlarma, alarma)){
                proximasAlarmas.add(alarma);
            }
        }
        return proximasAlarmas;
    }

    /**
     * Devuelve el título
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Devuelve la descripción
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    /**
     * Agrega la alarma recibida al conjunto de alarmas de la actividad
     */
    protected void agregarAlarma(Alarma alarma){
        this.alarmas.add(alarma);
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
        return !otra.suenaAntes(fecha) && (primerAlarma == null || otra.suenaAntes(primerAlarma));
    }

    /**
     * Devuelve true si dos alarmas suenan al mismo tiempo
     */
    private boolean ambasSonProximas(Alarma primerAlarma, Alarma otra){
        return primerAlarma != null && otra.suenaIgual(primerAlarma);
    }
}
