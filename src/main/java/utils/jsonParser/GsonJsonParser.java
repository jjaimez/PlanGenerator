package utils.jsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.text.DateFormat;

public class GsonJsonParser implements JsonParser {

    final Gson parser = new GsonBuilder()
            .disableHtmlEscaping()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat(DateFormat.LONG)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            //  .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .setVersion(1.0)
            .create();

    public <T> T fromJson(String json, Type typeOfT) {
        return parser.fromJson(json, typeOfT);
    }

    public String toJson(Object object) {
        return parser.toJson(object);
    }


}
