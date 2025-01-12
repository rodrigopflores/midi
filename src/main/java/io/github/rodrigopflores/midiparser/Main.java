package io.github.rodrigopflores.midiparser;

public class Main {
    public static void main(String[] args) {

        String path = new Main()
                .getClass()
                .getResource("/flourish.mid")
                .getPath();

        MidiFile midi = new MidiFile(path);

    }
}