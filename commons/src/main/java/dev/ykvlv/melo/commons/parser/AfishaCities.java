package dev.ykvlv.melo.commons.parser;

import lombok.Data;
import java.util.ArrayList;

@Data
public class AfishaCities {

    private ArrayList<AfishaCity> data;

    @Data
    public static class AfishaCity {
        private String id;
        private String url;
        private String name;
    }
}
