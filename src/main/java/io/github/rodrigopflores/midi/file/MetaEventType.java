package io.github.rodrigopflores.midi.file;

import java.util.Arrays;

public enum MetaEventType {
    SEQUENCE_NUMBER((byte) 0x00, "Sequence Number"),
    TEXT_EVENT((byte) 0x01, "Text Event"),
    COPYRIGHT_NOTICE((byte) 0x02, "Copyright Notice"),
    TRACK_NAME((byte) 0x03, "Track Name"),
    INSTRUMENT_NAME((byte) 0x04, "Instrument Name"),
    LYRIC((byte) 0x05, "Lyric"),
    MARKER((byte) 0x06, "Marker"),
    CUE_POINT((byte) 0x07, "Cue Point"),
    PROGRAM_NAME((byte) 0x08, "Program Name"),
    DEVICE_NAME((byte) 0x09, "Device Name"),
    MIDI_CHANNEL_PREFIX((byte) 0x20, "MIDI Channel Prefix"),
    END_OF_TRACK((byte) 0x2F, "End of Track"),
    SET_TEMPO((byte) 0x51, "Set Tempo"),
    SMPTE_OFFSET((byte) 0x54, "SMPTE Offset"),
    TIME_SIGNATURE((byte) 0x58, "Time Signature"),
    KEY_SIGNATURE((byte) 0x59, "Key Signature"),
    SEQUENCER_SPECIFIC((byte) 0x7F, "Sequencer-Specific");


    public final byte code;
    public final String description;

    MetaEventType(byte code, String description) {
        this.code = code;
        this.description = description;
    }

    public static MetaEventType getByCode(byte code) {
        return Arrays.stream(MetaEventType.values())
                .filter(e -> e.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No Meta Event with code " + code));
    }
}
