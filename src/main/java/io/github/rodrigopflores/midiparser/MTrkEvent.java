package io.github.rodrigopflores.midiparser;

import java.io.IOException;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class MTrkEvent {

    public final int deltaTime;
    public final Event event;

    public MTrkEvent() throws IOException {
        deltaTime = BinaryUtils.getNextVLQ();
        event = Event.getEvent(aByte());
    }

    @Override
    public String toString() {
        return "MTrkEvent{" +
                "deltaTime=" + deltaTime +
                ", event=" + event +
                '}';
    }
}
