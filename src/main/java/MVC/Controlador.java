package MVC;

import Calendario.Main.Calendario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Controlador  {
    private Vista vista;
    private Calendario calendario;
    public Controlador(Calendario calendario, Vista vista){
        this.calendario = calendario;
        this.vista = vista;
    }
    public void start(){
        vista.registrarEscuchaFrecuencia(actionEvent -> {vista.tipoRango(vista.getEscuchaFrecuencia());});
        vista.registrarEscuchaSiguiente(actionEvent -> {vista.getEscuchaSiguiente();});
        vista.registrarEscuchaAnterior(actionEvent -> {vista.getEscuchaAnterior();});
        vista.registrarEscuchaCrearTarea(actionEvent -> {
            try {
                vista.abrirVentanaCrearTarea();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

}