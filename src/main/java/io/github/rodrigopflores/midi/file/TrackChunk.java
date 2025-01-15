package io.github.rodrigopflores.midi.file;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackChunk {

    private List<MTrkEvent> mTrkEvents = new ArrayList<>();
}
