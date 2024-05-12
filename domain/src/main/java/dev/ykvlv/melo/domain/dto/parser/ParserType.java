package dev.ykvlv.melo.domain.dto.parser;

public interface ParserType {

    /**
     * Есть ли следующая страница.
     *
     * @return true, если есть следующая страница, иначе false
     */
    boolean hasNext();

}
