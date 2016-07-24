package o.horbenko.helpers.localizationstring;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Custom LocaleEnum for complexity of getting (in serialization/deserialization) value from Map = O(1)
 *
 * @author horbenko oleksii
 * */
public enum LocaleEnum {

    uk_UA {
        final Locale locale = new Locale("uk", "UA");

        @JsonValue
        @NotNull
        @Override
        public String toString() {
            return locale.toString();
        }
    },

    en_US {
        final Locale locale = new Locale("en", "US");

        @JsonValue
        @NotNull
        @Override
        public String toString() {
            return locale.toString();
        }
    },

    ru_RU {
        final Locale locale = new Locale("ru", "RU");

        @JsonValue
        @NotNull
        @Override
        public String toString() {
            return locale.toString();
        }
    };


    @JsonCreator
    public static LocaleEnum fromValue(String localeString) {
        try {

            return LocaleEnum.valueOf(localeString);    //get Enum by string

        } catch (IllegalArgumentException e) {

            return LocaleEnum.en_US;                    //or return default

        }
    }




}
