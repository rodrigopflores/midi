package io.github.rodrigopflores.midiparser;

import java.io.IOException;
import java.util.Arrays;
import java.util.HexFormat;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class MetaEvent extends Event {

    public final MetaEventType metaEventType;
    public final int length;
    public final byte[] data;

    public MetaEvent(byte type) throws IOException {
        super(MIDIMessageType.getByStatus(type));
        metaEventType = MetaEventType.getByCode(aByte());
        length = BinaryUtils.getNextVLQ();
        data = nBytes(length);
    }

    @Override
    public String toString() {
        return "MetaEvent{" +
                "metaEventType=" + metaEventType +
                ", length=" + length +
                ", data=" + HexFormat.of().formatHex(data) +
                ", messageType=" + messageType +
                '}';
    }
}
