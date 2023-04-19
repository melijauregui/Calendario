package Calendario.Enums;

import java.time.LocalDateTime;

public enum TiempoRelativo {
    MINUTOS {
        /* determinarFechaRelativa obtiene una fecha relativa a otra, dado un intervalo medido en
        MINUTOS */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusMinutes(intervalo);
        }
    },
    HORAS{
        /* determinarFechaRelativa obtiene una fecha relativa a otra, dado un intervalo medido en
        HORAS */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusHours(intervalo);
        }
    },
    DIAS{
        /* determinarFechaRelativa obtiene una fecha relativa a otra, dado un intervalo medido en
        DIAS */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusDays(intervalo);
        }
    },
    SEMANAS{
        /* determinarFechaRelativa obtiene una fecha relativa a otra, dado un intervalo medido en
        SEMANAS */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusWeeks(intervalo);
        }
    };

    TiempoRelativo(){}

    public abstract LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo);}
