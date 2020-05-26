package com.shaunmccready.orderupapi.config;

import com.shaunmccready.orderupapi.domain.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {


    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getName();
    }

    @Override
    public Status convertToEntityAttribute(String name) {
        return Status.getFromName(name);
    }
}
