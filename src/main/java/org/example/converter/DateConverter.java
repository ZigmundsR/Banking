package org.example.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<Date, String> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public String convertToDatabaseColumn(Date date) {
        return date != null ? sdf.format(date) : null;
    }

    @Override
    public Date convertToEntityAttribute(String dateString) {
        try {
            return dateString != null ? sdf.parse(dateString) : null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert String to Date", e);
        }
    }
}
