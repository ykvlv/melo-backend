package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.commons.response.ConcertResponse;
import dev.ykvlv.melo.domain.entity.Concert;
import dev.ykvlv.melo.domain.mapper.ConcertMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.ykvlv.melo.domain.repository.ConcertRepository;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertMapper concertMapper;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public ConcertResponse read(@NonNull Long id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new BEWrapper(BusinessException.CONCERT_NOT_FOUND, id));

        return concertMapper.map(concert);
    }

}
