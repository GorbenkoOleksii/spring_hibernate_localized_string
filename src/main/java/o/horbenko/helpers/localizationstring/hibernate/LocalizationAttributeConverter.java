package o.horbenko.helpers.localizationstring.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import o.horbenko.helpers.localizationstring.LocalizedString;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
public class LocalizationAttributeConverter implements AttributeConverter<LocalizedString, String> {

    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(LocalizedString attribute) {
        try {

            return mapper.writeValueAsString(attribute.getLocalizedValues());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public LocalizedString convertToEntityAttribute(String dbData) {
        try {

            Map<String, String> localizedParams = mapper.readValue(dbData, HashMap.class);
            return new LocalizedString(localizedParams);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return new LocalizedString();
        }
    }


}
