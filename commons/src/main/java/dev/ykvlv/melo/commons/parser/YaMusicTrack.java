package dev.ykvlv.melo.commons.parser;

import lombok.Data;

import java.util.ArrayList;

@Data
public class YaMusicTrack {

    private ArrayList<Artist> artists;

    @Data
    public static class Artist {
        private String name;
        private Cover cover;

        @Data
        public static class Cover {
            private String uri;
        }
    }
}
