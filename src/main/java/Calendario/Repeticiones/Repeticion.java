package Calendario.Repeticiones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Calendario.Enums.Frecuencias;
import Calendario.Enums.Mes;
import Calendario.Eventos.Evento;
import Calendario.Eventos.InstanciaEvento;

public abstract class Repeticion {

    private LocalDate fechaHasta;
    private int intervalo;
    private int ocurrencias;
    private int ocurrenciasRelativas = 0;
    private static final int sinRepeticion = 0;
    private static final int repeticionInfinita = -1;

    // hasta determinda fecha
    public Repeticion(int intervalo, LocalDate fechaHasta){
        this.intervalo = intervalo;
        this.fechaHasta = fechaHasta;
    }

    //hasta n ocurrencias
    public Repeticion(int intervalo, int ocurrencias){
        this.intervalo = intervalo;
        this.ocurrencias = ocurrencias;
        this.ocurrenciasRelativas = ocurrencias;
    }
    // infinita
    public Repeticion(int intervalo){
        this.intervalo = intervalo;
        this.ocurrencias = repeticionInfinita;
    }

<<<<<<< Updated upstream
    // esInfinita devuelve true si la repetición nunca acaba
=======
    //---------------------------------------------------------------------

    private void disminuirOcurrencias(){
        if (this.ocurrenciasRelativas > sinRepeticion){
            this.ocurrenciasRelativas-=1;
        }
    }

>>>>>>> Stashed changes
    public boolean esInfinita(){
        return (this.ocurrencias == repeticionInfinita);
    }

    //getProximaInstanciaEvento devuelve el siguiente evento del pasado por parámetro
    public abstract InstanciaEvento getProximaInstanciaEvento(InstanciaEvento evento);

<<<<<<< Updated upstream
    // almacenarRepeticiones guarda en una lista las sucesivas repeticiones del evento
=======
    // esUltimaRepeticion devuelve true si la instancia pasada por parámetro es la última del
    // evento no hay más repeticiones del Evento, false en caso contrario
    private boolean esUltimaInstanciaEvento(InstanciaEvento evento){
        LocalDate fechaProximoEvento = getProximaInstanciaEvento(evento).getDiaInicio();
        if (fechaHasta == null){
            return ocurrenciasRelativas == sinRepeticion;
        }
        return fechaProximoEvento.isAfter(fechaHasta);
    }


>>>>>>> Stashed changes
    public void almacenarRepeticiones(List<InstanciaEvento> almacenamiento, InstanciaEvento primerEvento) {
        this.ocurrenciasRelativas = ocurrencias;
        almacenamiento.add(primerEvento);
        InstanciaEvento utlimoEventoAlmacenado = primerEvento;
        this.disminuirOcurrencias();
        if (!esInfinita()) {
            while (!esUltimaInstanciaEvento(utlimoEventoAlmacenado)) {
                this.disminuirOcurrencias();
                var instancia = this.getProximaInstanciaEvento(utlimoEventoAlmacenado);
                almacenamiento.add(instancia);
                utlimoEventoAlmacenado = instancia;
            }
        }
    }

    // getter del intervalo
    public int getIntervalo(){
        return intervalo;
    }

    // esUltimaRepeticion devuelve true si la instancia pasada por parámetro es la última del
    // evento
    private boolean esUltimaInstanciaEvento(InstanciaEvento evento){
        LocalDate fechaProximoEvento = getProximaInstanciaEvento(evento).getDiaInicio();
        if (fechaHasta == null){
            return ocurrencias == sinRepeticion;
        }
        return fechaProximoEvento.isAfter(fechaHasta);
    }

    // disminuirOcurrencias resta 1 a la cantidad de ocurrencias del evento
    private void disminuirOcurrencias(){
        if (this.ocurrencias > sinRepeticion){
            this.ocurrencias-=1;
        }
    }
}