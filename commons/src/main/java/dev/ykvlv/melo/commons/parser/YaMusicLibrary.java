package dev.ykvlv.melo.commons.parser;

import lombok.Data;

import java.util.ArrayList;

@Data
public class YaMusicLibrary {
    private Boolean success;
    private ArrayList<String> trackIds;
}
