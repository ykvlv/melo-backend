package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.domain.dto.parser.AfishaEvents;
import dev.ykvlv.melo.domain.dto.parser.EventData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring")
public abstract class ParserMapper {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "artistName", source = "event.title")
    @Mapping(target = "date", source = "scheduleInfo.dateStarted", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "url", source = "event.url")
    @Mapping(target = "stageName", source = "scheduleInfo.oneOfPlaces.title")
    @Mapping(target = "cityName", source = "scheduleInfo.oneOfPlaces.city.name")
    public abstract EventData map(AfishaEvents.AfishaEvent entity);

    @Named("stringToLocalDate")
    public static LocalDate stringToLocalDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
