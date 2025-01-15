package io.github.rodrigopflores.midi;

import io.github.rodrigopflores.midi.file.MidiFile;
import io.github.rodrigopflores.midi.filereader.MidiFileReader;

public class Main {
    public static void main(String[] args) {

        String path = new Main()
                .getClass()
                .getResource("/flourish.mid")
                .getPath();

        MidiFileReader reader = new MidiFileReader(path);
        MidiFile midi = reader.read();
        System.out.println(midi);

    }
}