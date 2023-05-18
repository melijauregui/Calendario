package Calendario.Main.Adapters;

import Calendario.Duracion.Duracion;
import Calendario.Main.Constantes;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    public void write(JsonWriter writer, LocalDateTime fecha) throws IOException {
        writer.beginObject();
        writer.name(Constantes.ANIO).value(fecha.getYear());
        writer.name(Constantes.MES).value(fecha.getMonthValue());
        writer.name(Constantes.DIA).value(fecha.getDayOfMonth());
        writer.name(Constantes.HORA).value(fecha.getHour());
        writer.name(Constantes.MINUTO).value(fecha.getMinute());
        writer.endObject();
    }

    public LocalDateTime read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;
        int anio = 0;
        int mes = 0;
        int dia = 0;
        int hora = 0;
        int minuto = 0;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }

            if (Constantes.ANIO.equals(fieldname)) {
                anio = reader.nextInt();
            }

            if(Constantes.MES.equals(fieldname)) {
                mes = reader.nextInt();
            }

            if(Constantes.DIA.equals(fieldname)) {
                dia = reader.nextInt();
            }

            if(Constantes.HORA.equals(fieldname)) {
                hora = reader.nextInt();
            }

            if(Constantes.MINUTO.equals(fieldname)) {
                minuto = reader.nextInt();
            }
        }
        LocalDateTime fecha = LocalDateTime.of(anio, mes, dia, hora, minuto);
        reader.endObject();
        return fecha;
    }
}