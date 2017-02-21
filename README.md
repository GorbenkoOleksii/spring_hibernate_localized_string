# Spring+Hivernate and Jackson Localization string from DB via user locale settings
1. Imagine that you use Hibernate, Spring and Jackson in own WebApp
2. Imagine that u have database (postgres, for example) in that you must save String/JSON fields in format like

```java
{"uk_UA" : "Ukraine", "en_US" : "USA"}
```
3. You need to cache some entities with all of this pairs locale-String value
4. You hate create transformers entity2DTO just for localization fields for FRONT-part of app and you don`t want to worry about localization of JSON-like fields
5. Locale you get from cookie in custom param, like ```... lang=en_US; ...```
6. You hate this in your hibernate entities
```java
@Column(name = "value")
private Map<String, Object> localizedParam;
```

and you want just to replace this with this and let spring and hibernate to localize parameter for you
```java
 @Column(name = "value")
 private LocalizedString localizedParam;
  ```
  WITHOUT entity2DTOTransformers!
  
  
# MAIN IDEA:
Create own type, something like `LocalizedString` that contains EnumMap with LocaleEnum-String key-value pairs
```java
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
```
and annotate this class with two annotations:


```java
@Convert(converter = LocalizedStringAttributeConverter.class)   //for hibernate
```
AND
```java
@JsonSerialize(using = LocalizedStringJsonSerializer.class)     //for jackson
```

There are Custom realizations of converters for Hibernate and Jackson.
First - for creating LocalizedString object from String in DB 

-WHY WE NEED IT?
-for caching. 

In Cache we need to save all Locale-String value pairs
Than we using it application until we returned them via Jackson.

For jackson, we create custom JsonSerializer
```java
@Component
public class LocalizedStringJsonSerializer extends JsonSerializer<LocalizedString> {

    @Override
    public void serialize(LocalizedString value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Locale locale = LocaleContextHolder.getLocale();
        LocaleEnum key = LocaleEnum.fromValue(locale.toString());

        gen.writeString(value.getLocalized(key));
    }

```

WHAT ``` LocaleContextHolder.getLocale(); ``` is it?
LocaleContextHolder holds ThreadLocal value of Locale of current thread

for setting Locale in runtime we just configure beans:

```java
<bean id="localeResolver"
          class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
        <!--Cookie has value named {lang} -->
        <property name="cookieName" value="lang"/>
    </bean>

    <bean id="localeChangeInterceptor"
          class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
        <property name="paramName" value="language" />
    </bean>
```

you can replace ``` org.springframework.web.servlet.i18n.CookieLocaleResolver ``` with Class ```org.springframework.web.servlet.i18n.SessionLocaleResolver```
if you want to get locale from header request param, like Accept-Language..

What we got?

``` java
@Entity
@Table(name = "test_table")
public class TestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private LocalizedString localizedParam;
...  
```

### that's all!!!
all things Hibernate, Spring with Jackson  will do for you!
You don`t need to worry about transformers, DTO or another annotations!

## Author: Horbenko Oleksii
