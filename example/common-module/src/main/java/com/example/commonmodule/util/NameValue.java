package com.example.commonmodule.util;

import com.example.commonmodule.annonation.PreventModify;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NameValue {
    private String name;
    private String value;

    public static <T> void modifyValues(T object, List<NameValue> nameValues) {
        nameValues.forEach(it -> NameValue.modifyValue(object, it));
    }
    public static <T> void modifyValue(T object, NameValue nameValue) {
        Field field = null;
        try {
            List<String> fields = Arrays.stream(object.getClass().getDeclaredFields()).map(it -> it.getName()).collect(Collectors.toList());
            if (fields.contains(nameValue.getName())) {
                field = object.getClass().getDeclaredField(nameValue.getName());
                if (field.isAnnotationPresent(PreventModify.class)) return ;
                if (field != null) {
                    field.setAccessible(true);
                    try {
                        field.set(object, nameValue.getValue());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}