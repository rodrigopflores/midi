package io.github.rodrigopflores.midi.file;

import lombok.Data;

import java.util.HexFormat;

@Data
public class MetaEvent extends Event {

    private MetaEventType metaEventType;

    public MetaEvent(MidiMessageType messageType, byte[] data, MetaEventType metaEventType) {
        super(messageType, data);
        this.metaEventType = metaEventType;
    }

    @Override
    public String toString() {
        return "MetaEvent{" +
                "metaEventType=" + metaEventType +
                ", data=" + HexFormat.of().formatHex(getData()) +
                '}';
    }
}
