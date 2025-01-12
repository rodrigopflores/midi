package io.github.rodrigopflores.midiparser;

import java.io.FileInputStream;
import java.io.IOException;

public class MidiFile {

    private HeaderChunk headerChunk;

    public MidiFile(String path) {

        try (FileInputStream fis = new FileInputStream(path)) {
            FileManager.setFileInputStream(fis);
            parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFile() throws IOException {
        headerChunk = new HeaderChunk();
        System.out.println(headerChunk);
    }
}
