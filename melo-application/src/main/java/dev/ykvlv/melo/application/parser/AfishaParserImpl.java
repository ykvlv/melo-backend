package dev.ykvlv.melo.application.parser;

import dev.ykvlv.melo.application.MeloParserProperties;
import dev.ykvlv.melo.commons.parser.AfishaCities;
import dev.ykvlv.melo.commons.parser.AfishaEvents;
import dev.ykvlv.melo.commons.parser.EventData;
import dev.ykvlv.melo.domain.mapper.ParserMapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;


@Service
public class AfishaParserImpl extends AbstractParser<AfishaEvents> {

    private final ParserMapper parserMapper;

    public AfishaParserImpl(MeloParserProperties meloParserProperties,
                            EventFilterService eventFilterService,
                            ParserMapper parserMapper) {
        super(meloParserProperties.getAfisha(), eventFilterService);
        this.parserMapper = parserMapper;
    }

    @Override
    public void parse() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("city", "saint-petersburg"); // без этого параметра запрос не проходит

        // Парсим данные городов
        AfishaCities cities = parseWithParams(parserProperties.getCitiesUrl(), params, AfishaCities.class);

        for (AfishaCities.AfishaCity city : cities.getData()) {
            int offset = 0;
            int limit = 20;

            params.set("city", city.getId());
            params.set("hasMixed", String.valueOf(0));
            params.set("limit", String.valueOf(limit));
            params.set("offset", String.valueOf(offset));

            AfishaEvents eventsData;
            do {
                eventsData = parseWithParams(parserProperties.getEventsUrl(), params, AfishaEvents.class);

                if (eventsData == null) {
                    break;
                }

                List<EventData> data = map(eventsData);

                eventFilterService.saveData(data);

                params.set("offset", String.valueOf(offset += limit));
            } while (eventsData.hasNext());
        }
    }

    @Override
    protected List<EventData> map(@NonNull AfishaEvents entity) {
        return entity.getData().stream().map(parserMapper::map).toList();
    }
}
