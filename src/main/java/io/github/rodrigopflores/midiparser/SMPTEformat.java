package io.github.rodrigopflores.midiparser;

import java.util.Arrays;

public enum SMPTEformat {

    FILM((short) 24, (byte) -24, 24.0),
    PAL((short) 25, (byte) -25, 25.0),
    DROP_FRAME_NTSC((short) 29, (byte) -29, 20.97),
    STANDARD_NTSC((short) 30, (byte) -30, 30.0);

    public final short code;
    public final byte negativeCode;
    public final double frameRate;

    SMPTEformat(short code, byte negativeCode, double frameRate) {
        this.code = code;
        this.negativeCode = negativeCode;
        this.frameRate = frameRate;
    }

    public static SMPTEformat fromNegative(byte negativeCode) {
        return Arrays.stream(SMPTEformat.values())
                .filter(f -> (f.negativeCode & 0x7f) == (negativeCode & 0x7f))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid negative code: " + negativeCode));
    }
}
