package Calendario.Main.Adapters;

import Calendario.Duracion.Duracion;
import Calendario.Main.Constantes;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    public void write(JsonWriter writer, LocalDate fecha) throws IOException {
        writer.beginObject();
        writer.name(Constantes.ANIO).value(fecha.getYear());
        writer.name(Constantes.MES).value(fecha.getMonthValue());
        writer.name(Constantes.DIA).value(fecha.getDayOfMonth());
        writer.endObject();
    }

    @Override
    public LocalDate read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;
        int anio = 0;
        int mes = 0;
        int dia = 0;

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
        }
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        reader.endObject();
        return fecha;
    }
}


