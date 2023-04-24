package Calendario.Eventos;

import Calendario.Alarmas.Alarma;
import Calendario.Duracion.Duracion;
import Calendario.Main.Actividad;
import Calendario.Repeticiones.Repeticion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Evento extends Actividad{
    private InstanciaEvento eventoInicial;
    private ArrayList<InstanciaEvento> almacenamientoFechas = new ArrayList<>();
    private Repeticion repeticion = null;

    public Evento(){
        setPrimerEventoDefault();
    }

    /**
     * Recibe la primera instancia del Evento y guarda su información
     */
    public void setEventoInicial(InstanciaEvento eventoInicial){
        this.eventoInicial = eventoInicial;
        eventoInicial.setTitulo(getTitulo());
        eventoInicial.setDescripcion(getDescripcion());
        actualizarAlmacenamientoFechas();
    }


    /**
     * Cambia el título del evento y de las instancias del mismo
     */
    @Override
    public void setTitulo(String titulo){
        super.setTitulo(titulo);
        setTituloInstancias(titulo);
    }

    /**
     * Cambia la descripción del evento y de las instancias del mismo
     */
    @Override
    public void setDescripcion(String descripcion){
        super.setDescripcion(descripcion);
        setDescripcionInstancias(descripcion);
    }


    /**
     * Cambia la repetición del evento, y actualiza las instancias del mismo
     */
    public void setRepeticion(Repeticion repeticion){
        this.repeticion = repeticion;
        actualizarAlmacenamientoFechas();
    }

    /**
     * Devuelve la primera Instancia que se encuentra a partir de la fecha dada
     */
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


    /**
     * Devuelve la primera Instancia que se encuentra a partir del evento dado
     */
    public InstanciaEvento getProximaRepeticion(InstanciaEvento evento){
        return getProximaRepeticion(evento.getFechaInicio());
    }


    /**
     * Agrega la alarma al evento, y a las instancias del mismo
     */
    public void configurarAlarma(Alarma alarma){
        alarma.determinarFecha(getFechaInicio());
        agregarAlarma(alarma);
        setAlarmaInstancias(alarma);
    }

    /**
     * Devuelve el conjunto de alarmas que suenan al mismo tiempo y son las más
     * próximas a la fecha pasada
     */
    @Override
    public Set<Alarma> getProximasAlarmas(LocalDateTime fecha){
        InstanciaEvento evento = getProximaRepeticion(fecha);
        Set<Alarma> alarmasProximas = evento.getProximasAlarmas(fecha);
        while (!estaVacia(getAlarmas()) && estaVacia(alarmasProximas)){
            alarmasProximas = getProximaRepeticion(evento).getProximasAlarmas(fecha);
        }
        return alarmasProximas;
    }


    /**
     * Elimina la alarma recibida del Evento y sus instancias
     */
    @Override
    public void eliminarAlarma(Alarma alarma){
        super.eliminarAlarma(alarma);
        eliminarAlarmaInstancias(alarma);
    }

    /**
     * Devuelve todas las instancias del evento, o algunas si el mismo
     * es infinito.
     */
    public List<InstanciaEvento> getAlmacenamientoFechas(){
        return almacenamientoFechas;
    }

    /**
     * Agrega al Almacenamiento las sucesivas instancias del evento, o solamente
     * la primera si es un evento sin repetición
     */
    private void almacenarFechas(InstanciaEvento primerEvento) {
        if (!this.esEventoConRepeticion()) {
            almacenamientoFechas.add(primerEvento);
        } else {
            this.repeticion.almacenarRepeticiones(this.almacenamientoFechas, primerEvento);
        }
    }

    /**
     * Agrega al Almacenamiento la Instancia de la repetición que le sigue a la última guardada
     */
    private void almacenarUnaFecha(){
        var instancia = this.repeticion.getProximaInstanciaEvento(getUltimoEventoAlmacenado());
        almacenamientoFechas.add(instancia);
    }

    /**
     * Elimina las instancias del evento y, a partir de la primer instancia,
     * vuelve a cargarlas
     */
    private void actualizarAlmacenamientoFechas(){
        almacenamientoFechas.clear();
        almacenarFechas(this.eventoInicial);
    }

    /**
     * Devuelve la última Instancia de Repetición almacenada
     */
    private InstanciaEvento getUltimoEventoAlmacenado(){
        return almacenamientoFechas.get(almacenamientoFechas.size()-1);
    }

    /**
     * Devuelve la fecha de inicio de la primer instancia del evento
     */
    private LocalDateTime getFechaInicio() {
        return almacenamientoFechas.get(0).getFechaInicio();
    }

    /**
     * Cambia el título de las instancias del evento
     */
    private void setTituloInstancias(String titulo){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.setTitulo(titulo);
        }
    }

    /**
     * Cambia la descripción de las instancias del evento
     */
    private void setDescripcionInstancias(String descipcion){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.setDescripcion(descipcion);
        }
    }

    /**
     * Elimina la alarma recibida de las instancias del evento
     */
    private void eliminarAlarmaInstancias(Alarma alarma){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.eliminarAlarma(alarma);
        }
    }

    /**
     * Le configura la alarma a cada instancia del evento
     */
    private void setAlarmaInstancias(Alarma alarma){
        for (InstanciaEvento instancia : almacenamientoFechas){
            instancia.configurarAlarma(alarma);
        }
    }

    /**
     * Crea una primera instancia por default del evento. Esta es de día completo
     */
    private void setPrimerEventoDefault(){
        var duracion = new Duracion(); // por default, es un evento de día completo
        duracion.setDiaInicio(LocalDate.now());
        duracion.setDiaFin(LocalDate.now());
        InstanciaEvento primerEvento = new InstanciaEvento();
        primerEvento.setDuracion(duracion);
        this.setEventoInicial(primerEvento);
    }

    /**
     * Devuelve true si el evento se repite indefinidamente
     */
    private boolean esInfinito(){
        return esEventoConRepeticion() && this.repeticion.esInfinita();
    }

    /**
     * Devuelve true si el evento se repite más de 1 vez
     */
    private boolean esEventoConRepeticion(){
        return this.repeticion != null;
    }

    private boolean estaVacia(Collection collection){
        return collection.size() == 0;
    }

}



