package com.cosight.ssk.constant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {

    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        return attribute.name();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbData) {
        return null;
    }

}
