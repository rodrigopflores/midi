package io.github.rodrigopflores.midiparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class MidiFile {

    private HeaderChunk headerChunk;
    private List<TrackChunk> trackChunks;

    public MidiFile(String path) {

        try (FileInputStream fis = new FileInputStream(path)) {
            setFileInputStream(fis);
            parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFile() throws IOException {
        headerChunk = new HeaderChunk();
        System.out.println(headerChunk);
        trackChunks = new ArrayList<TrackChunk>();
        while (bytesLeft()) {
            System.out.println("Chunk # " + trackChunks.size());
            String chunkType = string(4);
            if (!chunkType.equals("MTrk")) {
                throw new IOException(chunkType + " is not a valid MIDI file");
            }
            trackChunks.add(new TrackChunk());
            System.out.println("End of chunk # " + (trackChunks.size()-1));
            System.out.println("Bytes read: " + getBytesRead());
        }
    }
}
