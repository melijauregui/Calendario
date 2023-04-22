package Calendario.Main;

import Calendario.Alarmas.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private Set<Alarma> alarmas;


    public Actividad(){
    }

    // getTitulo devuelve el título de la Actividad
    public String getTitulo() {
        return titulo;
    }

    // getDescripción devuelve la descripción de la Actividad
    public String getDescripcion() {
        return descripcion;
    }

    // configurarAlarma recibe una Alarma y se la agrega a la Actividad
    public abstract void configurarAlarma(Alarma alarma);

    public void eliminarAlarma(Alarma alarma){
        alarmas.remove(alarma);
    }

    // getProximasAlarmas devuelve todas las alarmas con misma fecha y hora de la actividad, que suenan
    // más próximas a la fecha pasada
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

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setDescripcion(String descripcion){
        this.titulo = descripcion;
    }

    protected void agregarAlarma(Alarma alarma){
        this.alarmas.add(alarma);
    }
    protected Set<Alarma> getAlarmas(){
        return alarmas;
    }

    private boolean esMasProxima(Alarma primerAlarma, Alarma otra, LocalDateTime fecha){
        return !otra.suenaAntes(fecha) && (primerAlarma == null || otra.suenaAntes(primerAlarma));
    }

    private boolean ambasSonProximas(Alarma primerAlarma, Alarma otra){
        return primerAlarma != null && otra.suenaIgual(primerAlarma);
    }
}
