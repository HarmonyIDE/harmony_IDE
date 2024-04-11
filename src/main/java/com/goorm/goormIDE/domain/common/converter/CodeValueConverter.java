//package com.goorm.goormIDE.domain.common.converter;
//
//import com.example.muk_table.domain.common.CodeValue;
//import jakarta.persistence.AttributeConverter;
//
//import java.util.EnumSet;
//import java.util.NoSuchElementException;
//
//public class CodeValueConverter<E extends Enum<E> & CodeValue> implements AttributeConverter<E, String> {
//
//    private final Class<E> clz;
//
//    CodeValueConverter(Class<E> enumClass) { this.clz = enumClass;}
//
//    @Override
//    public String convertToDatabaseColumn(E attribute) {
//        return attribute.getCode();
//    }
//
//    @Override
//    public E convertToEntityAttribute(String dbData) {
//        return EnumSet.allOf(clz).stream()
//                .filter(e-> e.getCode().equals(dbData))
//                .findAny()
//                .orElseThrow(NoSuchElementException::new);
//    }
//}
