package io.github.rodrigopflores.midiparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;

import static io.github.rodrigopflores.midiparser.FileManager.*;

public class SysexEvent extends Event {

    private static final byte SYSEX_END = (byte) 0xF7;

    // TODO company
    public final byte[] data;

    public SysexEvent(byte type) throws IOException {
        super(MIDIMessageType.getByStatus(type));
        var list = new ArrayList<Byte>();
        byte currentByte = 0x00;
        while (currentByte != SYSEX_END) {
            currentByte = aByte();
            list.add(currentByte);
        }
        data = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
    }

    @Override
    public String toString() {
        return "SysexEvent{" +
                "data=" + HexFormat.of().formatHex(data) +
                ", messageType=" + messageType +
                '}';
    }
}
