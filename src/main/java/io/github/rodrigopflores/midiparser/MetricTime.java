package io.github.rodrigopflores.midiparser;

public class MetricTime extends DeltaTime {
    public final short ticksPerQuarterNote;
    public MetricTime(short division) {
        ticksPerQuarterNote = division;
    }
}
