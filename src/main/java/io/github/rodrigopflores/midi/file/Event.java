package io.github.rodrigopflores.midi.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Event {

    private MidiMessageType messageType;
    private byte[] data;
}
