package Calendario.Main.Argumentos;

public class EventoArgs {
    private String titulo;
    private String descripcion;
    private DuracionArgs duracion;
    private RepeticionArgs repeticion;

    /**
     * Recibe la información de un Evento con repetición y la guarda
     */
    public EventoArgs(String titulo, String descripcion, DuracionArgs duracion, RepeticionArgs repeticion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.repeticion = repeticion;
    }

    /**
     * Recibe la información de un Evento sin repetición y la guarda
     */
    public EventoArgs(String titulo, String descripcion, DuracionArgs duracion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
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
     * Devuelve la información sobre la Duración
     */
    public DuracionArgs getDuracion() {
        return duracion;
    }

    /**
     * Devuelve la información sobre la Repetición
     */
    public RepeticionArgs getRepeticion() {
        return repeticion;
    }

    /**
     * Devuelve true si tiene información sobre la Repetición
     */
    public boolean hayRepeticion(){
        return repeticion!=null;
    }
}


