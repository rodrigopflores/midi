package io.github.rodrigopflores.midi.file;

import java.util.Arrays;

public enum DeltaTimeType {

    METRIC_TIME(0x00, 0),
    SMPT_FILM( 0xE800, 24.0),
    SMPT_PAL( 0xE700, 25.0),
    SMTP_DROP_FRAME_NTSC( 0xE300, 20.97),
    SMTP_STANDARD_NTSC( 0xE200, 30.0);

    public final int code;
    public final double frameRate;

    DeltaTimeType(int code, double frameRate) {
        this.code = code;
        this.frameRate = frameRate;
    }

    public static DeltaTimeType fromDivision(int division) {

        if ((division & 0x8000) == 0) {
            return METRIC_TIME;
        }

        return Arrays.stream(DeltaTimeType.values())
                .filter(f -> f.code == (division & 0xFF00))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid division: " + division));
    }
}
