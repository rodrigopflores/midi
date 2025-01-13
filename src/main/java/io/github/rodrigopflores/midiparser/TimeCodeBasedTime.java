package io.github.rodrigopflores.midiparser;

public class TimeCodeBasedTime extends DeltaTimeFormat {
    public final SMPTEformat smpteFormat;
    public final byte ticksPerFrame;

    public TimeCodeBasedTime(short division) {
        smpteFormat = SMPTEformat.fromNegative((byte) (division >> 8));
        ticksPerFrame = (byte) (division & 0xFF);
    }
}
