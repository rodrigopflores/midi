package io.github.rodrigopflores.midi.file;

import lombok.Data;

@Data
public class MidiEvent extends Event {

    private int channel;

    public MidiEvent(MidiMessageType messageType, byte[] data, int channel) {
        super(messageType, data);
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MidiEvent{" +
                "channel=" + channel +
                "type=" + getMessageType() +
                '}';
    }
}
