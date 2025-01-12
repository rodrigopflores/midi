package io.github.rodrigopflores.midiparser;

import lombok.ToString;

import java.io.IOException;

import static io.github.rodrigopflores.midiparser.FileManager.*;

@ToString
public class HeaderChunk {

    public final String chunkType;
    public final int length;
    public final short format;
    public final short ntrks;
    public final short division;
    public final DeltaTime deltaTime;

    public HeaderChunk() throws IOException {
        chunkType = string(4);
        length = i32();
        format = i16();
        ntrks = i16();
        division = i16();
        deltaTime = DeltaTime.getDeltaTime(division);
    }
}
