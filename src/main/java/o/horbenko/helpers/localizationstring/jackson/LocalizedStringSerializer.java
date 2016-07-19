package o.horbenko.helpers.localizationstring.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import o.horbenko.helpers.localizationstring.LocalizedString;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@Component
public class LocalizedStringSerializer extends JsonSerializer<LocalizedString> {

    @Override
    public void serialize(LocalizedString value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Locale locale = LocaleContextHolder.getLocale();
        String key = locale.toString();

        gen.writeString(value.getLocalized(key));
    }
}
