package dev.ykvlv.melo.commons.parser;

import dev.ykvlv.melo.commons.type.MusicService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ArtistData {
    @NonNull
    MusicService musicService;
    @NonNull
    String artistName;
    String photoUrl;
}
