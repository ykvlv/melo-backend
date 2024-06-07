package dev.ykvlv.melo.commons.parser;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AfishaEvents {
    private Paging paging;
    private ArrayList<AfishaEvent> data;

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
            private Image image;

            @Data
            public static class Image {
                private Sizes sizes;

                @Data
                public static class Sizes {
                    private EventCoverL2x eventCoverL2x;

                    @Data
                    public static class EventCoverL2x {
                        private String url;
                    }
                }
            }

        }

        @Data
        public static class ScheduleInfo {
            private String dateStarted;
            private OneOfPlaces oneOfPlaces;

            @Data
            public static class OneOfPlaces {
                private String title;
                private String address;
                private City city;
                private Coordinates coordinates;

                @Data
                public static class City {
                    private String name;
                }

                @Data
                public static class Coordinates {
                    private Double longitude;
                    private Double latitude;
                }

            }

        }

    }

}
