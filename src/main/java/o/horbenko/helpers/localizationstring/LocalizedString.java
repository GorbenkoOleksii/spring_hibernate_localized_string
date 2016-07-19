package o.horbenko.helpers.localizationstring;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import o.horbenko.helpers.localizationstring.hibernate.LocalizationAttributeConverter;
import o.horbenko.helpers.localizationstring.jackson.LocalizedStringSerializer;

import javax.persistence.Convert;
import java.util.HashMap;
import java.util.Map;


@JsonSerialize(using = LocalizedStringSerializer.class)
@Convert(converter = LocalizationAttributeConverter.class)
public class LocalizedString {

    private Map<String, String> localizedValues;


    public LocalizedString() {
        localizedValues = new HashMap<>();
    }

    public String getLocalized(String locale) {
        return localizedValues.containsKey(locale) ? localizedValues.get(locale) : "";
    }

    public LocalizedString(Map<String, String> localizedValues) {
        this.localizedValues = localizedValues;
    }

    public Map<String, String> getLocalizedValues() {
        return localizedValues;
    }

    public void setLocalizedValues(Map<String, String> localizedValues) {
        this.localizedValues = localizedValues;
    }
}
