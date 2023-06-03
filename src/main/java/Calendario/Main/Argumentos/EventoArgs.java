package Calendario.Main.Argumentos;

public class EventoArgs {
    private String titulo;
    private String descripcion;
    private DuracionArgs duracion;
    private RepeticionArgs repeticion;
    public EventoArgs(String titulo, String descripcion, DuracionArgs duracion, RepeticionArgs repeticion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.repeticion = repeticion;
    }

    /**
     * Recibe la información de un Evento de día completo, y los datos de una Repetición con fecha de vencimiento.
     * Crea el Evento a partir de los Builders 'Duracion, Repeticion y Evento' correspondientes y lo devuelve
     */
    public EventoArgs(String titulo, String descripcion, DuracionArgs duracion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public DuracionArgs getDuracion() {
        return duracion;
    }

    public RepeticionArgs getRepeticion() {
        return repeticion;
    }
    public boolean hayRepeticion(){
        return repeticion!=null;
    }
}


