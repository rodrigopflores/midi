package io.github.rodrigopflores.midiparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HexFormat;

public class MidiFile {

    private String magic;

    public MidiFile(String path) {

        try (FileInputStream fis = new FileInputStream(path)) {
            parseFile(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFile(FileInputStream fis) throws IOException {
        magic = HexFormat.of().formatHex(fis.readNBytes(4));
        System.out.println(magic);
    }
}
