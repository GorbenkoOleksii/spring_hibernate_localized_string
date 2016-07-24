package o.horbenko.helpers.localizationstring.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import o.horbenko.helpers.localizationstring.LocaleEnum;
import o.horbenko.helpers.localizationstring.LocalizedString;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.EnumMap;


/**
 * Converter for HIBERNATE layer
 * converts LocalizedString entity from/into db string
 *
 * @author horbenko oleksii
 * */
@Converter(autoApply = true)
public class LocalizedStringAttributeConverter implements AttributeConverter<LocalizedString, String> {

    /**
     * Jackson mapper for conversion from db string into LocalizedString object
     * */
    private final static ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();

    /**
     * As default, method ObjectMapper.readValue(dbstring, Map.class) converts into Map<String, Object>
     * So, for casting into EnumMap, we need to create custom TypeReference
     * */
    private final static TypeReference TYPE_REFERENCE = new TypeReference<EnumMap<LocaleEnum, String>>(){};

    @Override
    public String convertToDatabaseColumn(LocalizedString attribute) {
        try {

            return JACKSON_OBJECT_MAPPER.writeValueAsString(attribute.getLocalizedValues());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public LocalizedString convertToEntityAttribute(String dbData) {
        try {

            EnumMap<LocaleEnum, String> localizedParams =
                    JACKSON_OBJECT_MAPPER.readValue(dbData, TYPE_REFERENCE);

            return new LocalizedString(localizedParams);

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return new LocalizedString();
        }
    }


}
