package io.github.rodrigopflores.midiparser;

public abstract class DeltaTime {
    public static DeltaTime getDeltaTime(short division) {
        if (division < 0) {
            return new TimeCodeBasedTime(division);
        } else {
            return new MetricTime(division);
        }
    }
}
