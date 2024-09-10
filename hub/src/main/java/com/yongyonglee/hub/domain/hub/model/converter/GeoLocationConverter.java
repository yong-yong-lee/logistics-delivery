package com.yongyonglee.hub.domain.hub.model.converter;

import com.yongyonglee.hub.domain.hub.model.value_object.GeoLocation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GeoLocationConverter implements AttributeConverter<GeoLocation, String> {

    @Override
    public String convertToDatabaseColumn(GeoLocation geoLocation) {

        if (geoLocation == null) {
            return null;
        }
        return geoLocation.getValue();
    }

    @Override
    public GeoLocation convertToEntityAttribute(String dbData) {

        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] pointComponents = dbData.replace("POINT(", "").replace(")", "").split(" ");
        Double longitude = Double.valueOf(pointComponents[0]);
        Double latitude = Double.valueOf(pointComponents[1]);

        return new GeoLocation(latitude, longitude);
    }
}
