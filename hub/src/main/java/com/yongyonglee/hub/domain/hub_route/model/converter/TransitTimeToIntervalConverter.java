package com.yongyonglee.hub.domain.hub_route.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Converter(autoApply = true)
public class TransitTimeToIntervalConverter implements AttributeConverter<Duration, String> {

    // PostgreSQL INTERVAL 표현식을 위한 보다 유연한 정규식 패턴
    private static final Pattern INTERVAL_PATTERN = Pattern.compile(
                                "(?:(-?\\d+)\\s+days?\\s*)?" +       // days 부분 (선택적)
                                      "(?:(-?\\d+)\\s+hours?\\s*)?" +      // hours 부분 (선택적)
                                      "(?:(-?\\d+)\\s+minutes?\\s*)?");    // minutes 부분 (선택적)


    @Override
    public String convertToDatabaseColumn(Duration duration) {

        if (duration == null) {
            return null;
        }

        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        // PostgreSQL INTERVAL 표현식으로 변환
        return String.format("%d days %d hours %d minutes", days, hours, minutes);
    }

    @Override
    public Duration convertToEntityAttribute(String interval) {

        if (interval == null) {
            return null;
        }

        // PostgreSQL에서 반환된 'XX days YY hours ZZ minutes' 형식에서 각 값을 추출
        Matcher matcher = INTERVAL_PATTERN.matcher(interval);

        if (matcher.matches()) {
            long days = parseGroup(matcher.group(1));
            long hours = parseGroup(matcher.group(2));
            long minutes = parseGroup(matcher.group(3));

            return Duration.ofDays(days).plusHours(hours).plusMinutes(minutes);
        } else {
            throw new IllegalArgumentException("Invalid interval format: " + interval);
        }
    }

    private long parseGroup(String group) {
        return group != null ? Long.parseLong(group) : 0;
    }
}
