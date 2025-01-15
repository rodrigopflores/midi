package io.github.rodrigopflores.midi.file;

import lombok.Data;

@Data
public class HeaderChunk {

    private int format;
    private int ntrks;
    private int ticksPerUnit;
    private DeltaTimeType deltaTimeType;

    public HeaderChunk(int format, int ntrks, int ticksPerUnit, DeltaTimeType deltaTimeType) {
        if (format < 0 || format > 2) {
            throw new IllegalArgumentException("Format must 0, 1 or 2");
        }
        this.format = format;
        this.ntrks = ntrks;
        this.ticksPerUnit = ticksPerUnit;
        this.deltaTimeType = deltaTimeType;
    }
}
