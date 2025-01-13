package io.github.rodrigopflores.midiparser;

import java.io.IOException;

import static io.github.rodrigopflores.midiparser.FileManager.aByte;

public class BinaryUtils {

    public static final int ALL_BYTE_BITS = 0xFF;
    public static final int DATA_BITS_OFFSET = 7;
    public static final int DATA_BITS_MAKS = 0x7F;
    public static final int CONTINUATION_BIT_MASK = 0x80;

    private BinaryUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static int getNextVLQ() throws IOException {
        int dt = 0;
        for (int i = 0; i < 4; i++) {
            int b = aByte() & ALL_BYTE_BITS;
            dt <<= DATA_BITS_OFFSET;
            dt |= (b & DATA_BITS_MAKS);
            if ((b & CONTINUATION_BIT_MASK) == 0) {
                break;
            }
            if (i == 3) {
                throw new IOException("Invalid VLQ encoding: exceeds 4 bytes");
            }
        }
        return dt;
    }
}
