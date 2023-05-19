package Calendario.Enums;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoEmail;
import Calendario.Alarmas.Aviso.AvisoNotificacion;

import java.io.Serializable;
import java.time.LocalDateTime;

public enum TipoAviso implements Serializable {
    SONIDO{

        /**
         * Crea un Aviso con Sonido y lo devuelve
         */
        @Override
        public Aviso crearAviso() {
            return new AvisoConSonido();
        }
    },
    EMAIL{

        /**
         * Crea un Aviso por Email y lo devuelve
         */
        @Override
        public Aviso crearAviso() {
            return new AvisoEmail();
        }
    },
    NOTIFICACION{

        /**
         * Crea un Aviso con Notificación y lo devuelve
         */
        @Override
        public Aviso crearAviso() {
            return new AvisoNotificacion();
        }
    };
    TipoAviso(){}

    /**
     * Crea un Aviso y lo devuelve
     */
    public abstract Aviso crearAviso();}

