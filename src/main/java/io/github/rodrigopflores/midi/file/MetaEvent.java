package io.github.rodrigopflores.midi.file;

import lombok.Data;

@Data
public class MetaEvent extends Event {

    private MetaEventType metaEventType;

    public MetaEvent(MidiMessageType messageType, byte[] data, MetaEventType metaEventType) {
        super(messageType, data);
        this.metaEventType = metaEventType;
    }
}
