package MVC;

import Calendario.Main.Calendario;
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

    }

}
