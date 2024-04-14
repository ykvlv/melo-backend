package dev.ykvlv.melo.application.exception;

import lombok.Getter;

@Getter
public final class BEWrapper extends RuntimeException {
    private final BusinessException businessException;

    public BEWrapper(BusinessException businessException, Object... args) {
        super(String.format(businessException.getFormat(), args));

        this.businessException = businessException;
    }
}
