package io.github.rodrigopflores.midiparser;

public abstract class DeltaTimeFormat {
    public static DeltaTimeFormat getDeltaTimeFormat(short division) {
        if (division < 0) {
            return new TimeCodeBasedTime(division);
        } else {
            return new MetricTime(division);
        }
    }
}
