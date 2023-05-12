package Calendario.Main;

import Calendario.Alarmas.Alarma;
import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Enums.TiempoRelativo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class ActividadParticular {
    private String titulo;
    private String descripcion;
    private Set<Alarma> alarmas = new HashSet<>();


    public ActividadParticular(){
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
                proximasAlarmas.add(alarma);
            }else if (ambasSonProximas(primerAlarma, alarma)){
                proximasAlarmas.add(alarma);
            }
        }
        return proximasAlarmas;
    }

    /**
     * Setea el título
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Setea la descripción
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    /**
     * Agrega la alarma recibida al conjunto de alarmas de la actividad
     */

    public void agregarAlarma(Alarma alarma){
        this.alarmas.add(alarma);
    }


    /**
     * Devuelve el conjunto de alarmas de la actividad
     */
    private Set<Alarma> getAlarmas(){
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
