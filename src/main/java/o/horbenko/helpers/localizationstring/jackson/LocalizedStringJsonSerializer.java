package o.horbenko.helpers.localizationstring.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import o.horbenko.helpers.localizationstring.LocaleEnum;
import o.horbenko.helpers.localizationstring.LocalizedString;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

/**
 * Jackson serializer from LocalizedString object
 *
 * LocalizedString object contains EnumMap<LocaleEnum, String>
 * We want to return to FRONT just localized string (not object with map inside)
 * LocaleContextHolder contains ThreadLocal value of Locale(sets from cookie by CookieLocaleResolver)
 *
 * We use this ThreadLocal Locale value for getting String value from map, localized by Locale and return ONLY STRING
 *
 * @author horbenko oleksii
 * */
@Component
public class LocalizedStringJsonSerializer extends JsonSerializer<LocalizedString> {

    @Override
    public void serialize(LocalizedString value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Locale locale = LocaleContextHolder.getLocale();
        LocaleEnum key = LocaleEnum.fromValue(locale.toString());

        gen.writeString(value.getLocalized(key));
    }
}
