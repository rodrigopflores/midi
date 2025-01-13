package io.github.rodrigopflores.midiparser;

import lombok.ToString;

@ToString
public class MetricTime extends DeltaTimeFormat {
    public final short ticksPerQuarterNote;
    public MetricTime(short division) {
        ticksPerQuarterNote = division;
    }
}
