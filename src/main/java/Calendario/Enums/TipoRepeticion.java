package Calendario.Enums;

public enum TipoRepeticion {
    DIARIA{
        public String getTipo(){
            return "Diaria";
        }
    },
    MENSUAL{
        public String getTipo(){
            return "Mensual";
        }
    },
    ANUAL{
        public String getTipo(){
            return "Anual";
        }
    },
    SEMANAL{
        public String getTipo(){
            return "Semanal";
        }
    };
    public abstract  String getTipo();
}
