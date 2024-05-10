package dev.ykvlv.melo.parser;

import dev.ykvlv.melo.parser.service.ParserStrategy;
import dev.ykvlv.melo.commons.parser.ParserType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventsParserTask<T extends ParserType> {

    private final List<ParserStrategy> parsers;

    @Scheduled(fixedRate = 3600000, initialDelay = 10000)
    public void runParsers() {
        for (ParserStrategy parser : parsers) {
            try {
                parser.parse();
            } catch (Exception e) {
                parser.handleError(e);
            }
        }
    }

}
