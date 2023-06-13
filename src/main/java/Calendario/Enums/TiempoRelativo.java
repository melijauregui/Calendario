package Calendario.Enums;

import java.io.Serializable;
import java.time.LocalDateTime;

public enum TiempoRelativo implements Serializable {
    MINUTOS {
        /**
         * Obtiene una fecha relativa a otra, dado un intervalo medido en
         * MINUTOS
         */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusMinutes(intervalo);
        }
        @Override
        public String getTiempoRelativoToString() {
            return "Minutos";
        }
    },
    HORAS{
        /**
         * Obtiene una fecha relativa a otra, dado un intervalo medido en
         * HORAS
         */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusHours(intervalo);
        }
        @Override
        public String getTiempoRelativoToString() {
            return "Horas";
        }
    },
    DIAS{
        /**
         * Obtiene una fecha relativa a otra, dado un intervalo medido en
         * DIAS
         */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusDays(intervalo);
        }
        @Override
        public String getTiempoRelativoToString() {
            return "Días";
        }
    },
    SEMANAS{
        /**
         * Obtiene una fecha relativa a otra, dado un intervalo medido en
         * SEMANAS
         */
        @Override
        public LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo) {
            return fecha.minusWeeks(intervalo);
        }

        @Override
        public String getTiempoRelativoToString() {
            return "Semanas";
        }
    };

    TiempoRelativo(){}

    /**
     * Calcula una fecha relativa a otra
     */
    public abstract LocalDateTime determinarFechaRelativa(LocalDateTime fecha, int intervalo);
    public abstract String getTiempoRelativoToString();
}