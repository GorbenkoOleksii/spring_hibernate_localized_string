package o.horbenko.helpers.localizationstring;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum LocaleEnum {

    UK_UA {
        final Locale locale = new Locale("uk", "UA");

        @NotNull
        @Override
        public String toString() {
            return "uk_UA";
        }
    },

    EN_US {
        final Locale locale = new Locale("en", "US");

        @NotNull
        @Override
        public String toString() {
            return "en_US";
        }
    },

    RU_RU {
        final Locale locale = new Locale("ru", "RU");

        @NotNull
        @Override
        public String toString() {
            return "en_US";
        }
    };

    public static LocaleEnum getLocale(String localeString) {
        LocaleEnum localeEnum;

        switch (localeString) {
            case "uk_UA" :
                localeEnum = UK_UA;
                break;
            case "en_US" :
                localeEnum = EN_US;
                break;
            case "ru_RU" :
                localeEnum = RU_RU;
                break;

            default:
                localeEnum = EN_US;
        }

        return localeEnum;
    }
}
