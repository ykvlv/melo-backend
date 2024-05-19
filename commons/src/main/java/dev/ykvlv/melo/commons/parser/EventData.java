package dev.ykvlv.melo.commons.parser;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class EventData {
    @NonNull
    private String artistName;
    @NonNull
    private LocalDate date;
    @NonNull
    private String url;
    @NonNull
    private String stageName;
    @NonNull
    private String cityName;
}
