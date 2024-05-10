package dev.ykvlv.melo.parser.service;

import lombok.NonNull;

public interface ParserStrategy {

    /**
     * Парсит данные из URL.
     */
    void parse();

    /**
     * Обрабатывает ошибку, возникшую во время парсинга.
     *
     * @param e Исключение, возникшее во время парсинга
     */
    void handleError(@NonNull Exception e);

}
