package Calendario.Actividad;

public class ActividadMutable extends Actividad {

    private String titulo;
    private String descripcion;

    public ActividadMutable(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public ActividadMutable(){

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
     * Devuelve el título de la Actividad
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve la descripción de la Actividad
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
