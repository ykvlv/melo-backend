package dev.ykvlv.melo.commons.parser;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AfishaEvents implements ParserType {
    private Paging paging;
    private ArrayList<AfishaEvent> data;

    @Override
    public boolean hasNext() {
        return paging.getOffset() + paging.getLimit() < paging.getTotal();
    }

    @Data
    public static class Paging {
        private Integer limit;
        private Integer offset;
        private Integer total;
    }

    @Data
    public static class AfishaEvent {
        private Event event;
        private ScheduleInfo scheduleInfo;

        @Data
        public static class Event {
            private String url;
            private String title;
        }

        @Data
        public static class ScheduleInfo {
            private String dateStarted;
            private Place oneOfPlaces;

            @Data
            public static class Place {
                private String title;
                private String address;
                private City city;

                @Data
                public static class City {
                    private String name;
                }

            }

        }

    }

}
