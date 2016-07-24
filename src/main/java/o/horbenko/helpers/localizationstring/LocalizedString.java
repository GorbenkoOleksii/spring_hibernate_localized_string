package o.horbenko.helpers.localizationstring;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import o.horbenko.helpers.localizationstring.hibernate.LocalizedStringAttributeConverter;
import o.horbenko.helpers.localizationstring.jackson.LocalizedStringJsonSerializer;

import javax.persistence.Convert;
import java.util.EnumMap;

/**
 * Class, that contains Map LocaleEnum-String value
 * Need for hibernate entities that need to save in cache
 * */
@Convert(converter = LocalizedStringAttributeConverter.class)   //for hibernate
@JsonSerialize(using = LocalizedStringJsonSerializer.class)     //for jackson
public class LocalizedString {

    private EnumMap<LocaleEnum, String> localizedValues;


    public LocalizedString() {
        localizedValues = new EnumMap<>(LocaleEnum.class);
    }


    public LocalizedString(EnumMap<LocaleEnum, String> localizedValues) {
        this.localizedValues = localizedValues;
    }

    public String getLocalized(LocaleEnum key) {
        return localizedValues.containsKey(key) ? localizedValues.get(key) : "";
    }

    public EnumMap<LocaleEnum, String> getLocalizedValues() {
        return localizedValues;
    }


}
