package io.github.rodrigopflores.midi.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MidiFile {

    private HeaderChunk headerChunk;
    private List<TrackChunk> trackChunks;

}
