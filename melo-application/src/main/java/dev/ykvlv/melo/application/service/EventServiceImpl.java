package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.ExtendedEventResponse;
import dev.ykvlv.melo.domain.entity.Event;
import dev.ykvlv.melo.domain.mapper.EventMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.ykvlv.melo.domain.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public ExtendedEventResponse read(@NonNull Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new BEWrapper(BusinessException.EVENT_NOT_FOUND, id));

        return eventMapper.mapFull(event);
    }

}
