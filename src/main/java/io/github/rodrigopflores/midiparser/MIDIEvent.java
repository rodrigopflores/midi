package io.github.rodrigopflores.midiparser;

import java.io.IOException;
import java.util.Arrays;
import java.util.HexFormat;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class MIDIEvent extends Event {

    private static final byte MSB_MASK = (byte) 0x80;

    public static MIDIMessageType lastEventType = null;

    public final int channel;
    public final byte[] data;

    public MIDIEvent(byte type) throws IOException {
        super((type & MSB_MASK) == 0 ? lastEventType : MIDIMessageType.getByStatus(type));
        channel = type & (byte) 0x0F;
        lastEventType = super.messageType;
        if ((type & MSB_MASK) == 0) {
            data = new byte[super.messageType.dataLength];
            data[0] = type;
            byte[] restOfData = nBytes(super.messageType.dataLength-1);
            System.arraycopy(restOfData, 0, data, 1, restOfData.length);
        } else {
            data = nBytes(super.messageType.dataLength);
        }
    }

    @Override
    public String toString() {
        return "MIDIEvent{" +
                "channel=" + channel +
                ", messageType=" + messageType +
                ", data=" + HexFormat.of().formatHex(data) +
                '}';
    }
}
