package io.github.rodrigopflores.midiparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class TrackChunk {
    public final String chunkType;
    public final int length;
    public final List<MTrkEvent> mTrkEvents;

    public TrackChunk() throws IOException {
        chunkType = "MTrk";
        length = i32();
        int initialByteCount = getBytesRead();
        System.out.println("Chunk length: " + length);
        mTrkEvents = new ArrayList<MTrkEvent>();
        while (getBytesRead()-initialByteCount < length) {
            var mTrkEvent = new MTrkEvent();
            mTrkEvents.add(mTrkEvent);
            System.out.println(mTrkEvent);
            System.out.println("Loop end bytes: " +(getBytesRead()-initialByteCount) + "/" + length);
        }
    }
}
