package mihee.com.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class ListTypeConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLITE_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(SPLITE_CHAR, list) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLITE_CHAR)) : Collections.emptyList();
    }
}
