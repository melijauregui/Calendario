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

        vista.registrarEscuchaMes(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vista.tipoRango(vista.getEscuchaMes());
            }
        });
        vista.registrarEscuchaSemana(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vista.tipoRango(vista.getEscuchaSemana());
            }
        });
        vista.registrarEscuchaDia(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vista.tipoRango(vista.getEscuchaDia());
            }
        });

    }

}