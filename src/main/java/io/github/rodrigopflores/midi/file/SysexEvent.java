package io.github.rodrigopflores.midi.file;

import lombok.Data;

@Data
public class SysexEvent extends Event {

    public SysexEvent(MidiMessageType messageType, byte[] data) {
        super(messageType, data);
    }
}
