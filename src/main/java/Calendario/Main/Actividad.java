package Calendario.Main;

import Calendario.Alarmas.Alarma;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private Set<Alarma> alarmas;


    public Actividad(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.alarmas = new HashSet<>();
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

    public Alarma getProximaAlarma(LocalDateTime fecha){
        Alarma primerAlarma = null;
        for(Alarma alarma : getAlarmas()){
            if (!alarma.suenaAntes(fecha) && (primerAlarma == null || alarma.suenaAntes(primerAlarma))){
                primerAlarma = alarma;
            }
        }
        return primerAlarma;
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

}
