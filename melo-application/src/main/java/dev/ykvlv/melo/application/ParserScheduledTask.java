package dev.ykvlv.melo.application;

import dev.ykvlv.melo.application.parser.ParserStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ParserScheduledTask {

    private final List<ParserStrategy> parsers;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void parseDaily() {
        for (ParserStrategy parser : parsers) {
            try {
                parser.parse();
            } catch (Exception e) {
                parser.handleError(e);
            }
        }
    }
}

