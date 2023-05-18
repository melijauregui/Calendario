package Calendario.Enums;

import Calendario.Alarmas.Aviso.Aviso;
import Calendario.Alarmas.Aviso.AvisoConSonido;
import Calendario.Alarmas.Aviso.AvisoEmail;
import Calendario.Alarmas.Aviso.AvisoNotificacion;

import java.time.LocalDateTime;

public enum TipoAviso {
    SONIDO{
        @Override
        public Aviso crearAviso() {
            return new AvisoConSonido();
        }
    },
    EMAIL{
        @Override
        public Aviso crearAviso() {
            return new AvisoEmail();
        }
    },
    NOTIFICACION{
        @Override
        public Aviso crearAviso() {
            return new AvisoNotificacion();
        }
    };
    TipoAviso(){}

    /**
     * Calcula una fecha relativa a otra
     */
    public abstract Aviso crearAviso();}

