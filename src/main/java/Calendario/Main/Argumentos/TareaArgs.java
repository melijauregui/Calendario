package Calendario.Main.Argumentos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TareaArgs {
    private String titulo;
    private String descripcion;
    private LocalDate dia;

    private LocalTime hora;

    /**
     * Recibe la información de una Tarea de día completo y se la setea
     */
    public TareaArgs(String titulo, String descripcion, LocalDate dia){
        setInformacion(titulo, descripcion, dia);
    }

    /**
     * Recibe la información de una Tarea con fecha y hora, y se la setea
     */
    public TareaArgs(String titulo, String descripcion, LocalDateTime diaHora){
        setInformacion(titulo, descripcion, diaHora.toLocalDate());
        hora = diaHora.toLocalTime();
    }
    /**
     * Modifica el título, la descripción y la fecha de la Tarea
     */
    private void setInformacion(String titulo, String descripcion, LocalDate dia){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dia = dia;
    }

    /**
     * Devuelve el título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el día de vencimiento
     */
    public LocalDate getDia() {
        return dia;
    }

    /**
     * Devuelve la hora de vencimiento
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Devuelve true si tiene información de una Tarea de día completo
     */
    public boolean esDiaCompleto(){
        return hora == null;
    }
}
