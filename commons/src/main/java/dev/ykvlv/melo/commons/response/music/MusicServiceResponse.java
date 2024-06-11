package dev.ykvlv.melo.commons.response.music;

import dev.ykvlv.melo.commons.response.ArtistResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class MusicServiceResponse {

    @NonNull
    private Boolean connected;

    private String login;

    private List<ArtistResponse> artists;
}
