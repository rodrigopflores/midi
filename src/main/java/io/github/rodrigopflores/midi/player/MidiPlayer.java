package io.github.rodrigopflores.midi.player;

import io.github.rodrigopflores.midi.file.*;

import java.math.BigInteger;
import java.util.List;

public class MidiPlayer {

    private MidiFile midiFile;
    List<MTrkEvent> events;

    public MidiPlayer(MidiFile midiFile) throws InterruptedException {
        this.midiFile = midiFile;
        int tPQN = midiFile.getHeaderChunk().getTicksPerUnit();
        events = midiFile.getTrackChunks().stream()
                .flatMap(trackChunk -> trackChunk.getMTrkEvents().stream())
                .toList();

        int tempo = 500000;
        for (MTrkEvent mTrkEvent : events) {
            int deltaTime = mTrkEvent.getDeltaTime();
            if (mTrkEvent.getEvent() instanceof MetaEvent metaEvent) {
                if (metaEvent.getMetaEventType() == MetaEventType.SET_TEMPO) {
                    tempo = new BigInteger(metaEvent.getData()).intValue();
                }
            } else if (mTrkEvent.getEvent() instanceof MidiEvent midiEvent) {
                if (midiEvent.getMessageType() == MidiMessageType.NOTE_ON && midiEvent.getChannel() == 0) {

                }
            }
            if (deltaTime > 0) {
                Thread.sleep(calculateTime(deltaTime, tempo, tPQN));
            }
        }

    }

    public static long calculateTime(int deltaTime, int tempo, int tPQN) {
        return (long) deltaTime * tempo / (tPQN * 1000L);
    }
}
