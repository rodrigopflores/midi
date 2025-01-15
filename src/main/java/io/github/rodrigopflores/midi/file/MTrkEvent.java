package io.github.rodrigopflores.midi.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MTrkEvent {

    private int deltaTime;
    private Event event;

    @Override
    public String toString() {
        return "MTrkEvent{" +
                "deltaTime=" + deltaTime +
                ", event=" + event +
                '}';
    }
}
