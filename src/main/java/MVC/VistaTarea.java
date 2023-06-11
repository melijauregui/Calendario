package MVC;

import Calendario.Actividad.ActividadMutable;
import Calendario.Eventos.InstanciaEvento;
import Calendario.Tareas.Tarea;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class VistaTarea {

    private Tarea tarea;
    public VistaTarea(Tarea tarea){
        this.tarea = tarea;
    }


    public void mostrarTareaSemana(Tarea tarea, Map<LocalDate, ListView<Label>> listasSemana,
                                   Map<Label,VistaTarea> vistaTareas){
        ListView<Label> lista = listasSemana.get(tarea.getFecha().toLocalDate());
        Label labelTarea =new Label(getMensajeTarea());
        labelTarea.setStyle("-fx-background-color: #adffc4;");
        lista.getItems().add(labelTarea);
        vistaTareas.put(labelTarea, this);
    }

    private String getMensajeTarea() {
        String mensaje = "Título: ";
        if (tarea.getTitulo().length() != 0) {
            mensaje += tarea.getTitulo();
        }
        mensaje += "\nFecha ";
        if (!tarea.esDiaCompleto()) {
            mensaje += tarea.getFecha().toString();
        } else {
            mensaje += tarea.getFecha().toLocalDate().toString();
        }
        return mensaje;
    }

    public  void mostrarTareaMes(Tarea tarea, Map<LocalDate, MenuButton> menuMes,
                                 Map<MenuItem,VistaTarea> vistaTareas){
        if (!menuMes.containsKey(tarea.getFecha().toLocalDate())){
            return;
        }
        MenuButton menu = menuMes.get(tarea.getFecha().toLocalDate());
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensajeTarea());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistaTareas.put(item, this);
    }

    public void mostrarTareaDia(Tarea tarea, Map<Integer, MenuButton> menuDia,
                                Map<MenuItem,VistaTarea> vistaTareas){
        int hora = tarea.getFecha().toLocalTime().getHour();
        if (tarea.esDiaCompleto()){
            hora = 0;
        }
        MenuButton menu = menuDia.get(hora);
        if(menu.getItems().isEmpty()){
            menu.setText("Ver más");
        }
        MenuItem item = new MenuItem(getMensajeTarea());
        item.setStyle("-fx-background-color: #adffc4;");
        menu.getItems().add(item);
        vistaTareas.put(item, this);
    }

}
