package Calendario.Main.Builders;

import Calendario.Duracion.Duracion;
import Calendario.Eventos.Evento;
import Calendario.Repeticiones.Repeticion;
import Calendario.Repeticiones.RepeticionSemanal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class BuilderRepeticion {
    Repeticion repeticion;
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia, LocalDate fechaHasta){
        repeticion = frecuencia.crearRepeticion(intervalo, fechaHasta);
    }
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia, int ocurrencias){
        repeticion = frecuencia.crearRepeticion(intervalo, ocurrencias);
    }
    public BuilderRepeticion(int intervalo, Frecuencia frecuencia){
        repeticion = frecuencia.crearRepeticion(intervalo);
    }
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana, int ocurrencias){
        repeticion = new RepeticionSemanal(intervalo, diasSemana, ocurrencias);
    }
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana, LocalDate fechaHasta){
        repeticion = new RepeticionSemanal(intervalo, diasSemana, fechaHasta);
    }
    public BuilderRepeticion(int intervalo, List<DayOfWeek> diasSemana){
        repeticion = new RepeticionSemanal(intervalo, diasSemana);
    }
    public Repeticion crearRepeticion(){
        return repeticion;
    }
}


