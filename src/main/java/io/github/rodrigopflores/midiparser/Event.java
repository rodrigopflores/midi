package io.github.rodrigopflores.midiparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HexFormat;

import static io.github.rodrigopflores.midiparser.FileManager.nBytes;

public abstract class Event {

    private static final byte EVENT_MASK = (byte) 0xF0;
    private static final byte META_STATUS = (byte) 0xFF;

    public final MIDIMessageType messageType;

    public Event(MIDIMessageType messageType) throws IOException {
        if (messageType == null) {
            throw new NullPointerException("messageType is null");
        }
        this.messageType = messageType;

    }

    public static Event getEvent(byte type) throws IOException {
        if ((type & EVENT_MASK) != EVENT_MASK) {
            return new MIDIEvent(type);
        }
        if (type == META_STATUS) {
            return new MetaEvent(type);
        }
        return new SysexEvent(type);
    }

}
