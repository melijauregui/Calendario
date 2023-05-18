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
import java.time.LocalTime;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    public void write(JsonWriter writer, LocalTime hora) throws IOException {
        writer.beginObject();
        writer.name(Constantes.HORA).value(hora.getHour());
        writer.name(Constantes.MINUTO).value(hora.getMinute());
        writer.endObject();
    }

    public LocalTime read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;
        int hora = 0;
        int minuto = 0;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }

            if(Constantes.HORA.equals(fieldname)) {
                hora = reader.nextInt();
            }

            if(Constantes.MINUTO.equals(fieldname)) {
                minuto = reader.nextInt();
            }
        }
        LocalTime fecha = LocalTime.of(hora, minuto);
        reader.endObject();
        return fecha;
    }
}
