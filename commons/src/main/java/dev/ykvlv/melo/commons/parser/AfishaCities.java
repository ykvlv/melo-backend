package dev.ykvlv.melo.commons.parser;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class AfishaCities {

    @NonNull
    private ArrayList<AfishaCity> data;

    @Data
    public static class AfishaCity {
        private String id;
        private String url;
        private String name;
    }
}
