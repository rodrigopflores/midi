package io.github.rodrigopflores.midiparser;

import java.util.Arrays;

public enum MIDIMessageType {


    NOTE_OFF((byte) 0x80, 2, "Note Off event. This message is sent when a note is released (ended)."),
    NOTE_ON((byte) 0x90, 2, "Note On event. This message is sent when a note is depressed (start)."),
    POLYPHONIC_KEY_PRESSURE((byte) 0xA0, 2, "Polyphonic Key Pressure (Aftertouch). Sent by pressing down on the key after it 'bottoms out'."),
    CONTROL_CHANGE((byte) 0xB0, 2, "Control Change. Sent when a controller value changes (pedals, levers, etc.)."),
    PROGRAM_CHANGE((byte) 0xC0, 1, "Program Change. Sent when the patch number changes."),
    CHANNEL_PRESSURE((byte) 0xD0, 1, "Channel Pressure (After-touch). Sent when the single greatest pressure value of all depressed keys is sent."),
    PITCH_WHEEL_CHANGE((byte) 0xE0, 2, "Pitch Wheel Change. Indicates a change in pitch with a 14-bit value."),

    LOCAL_CONTROL_OFF((byte) 0xB0, 2, "Local Control Off. Stops devices on a channel from responding to data, only responding to incoming MIDI."),
    LOCAL_CONTROL_ON((byte) 0xB0, 2, "Local Control On. Restores normal controller functionality."),
    ALL_NOTES_OFF((byte) 0xB0, 2, "All Notes Off. Stops all oscillators."),
    OMNI_MODE_OFF((byte) 0xB0, 2, "Omni Mode Off. Disables omni mode."),
    OMNI_MODE_ON((byte) 0xB0, 2, "Omni Mode On. Enables omni mode."),
    MONO_MODE_ON((byte) 0xB0, 2, "Mono Mode On. Activates mono mode with a specified number of channels."),
    POLY_MODE_ON((byte) 0xB0, 2, "Poly Mode On. Activates polyphonic mode."),

    SYSTEM_EXCLUSIVE((byte) 0xF0, -1, "System Exclusive. Used for sending bulk dumps, patch parameters, or other non-spec data."),
    UNDEFINED_1((byte) 0xF1, 0, "Undefined."),
    SONG_POSITION_POINTER((byte) 0xF2, 2, "Song Position Pointer. Holds the number of MIDI beats since the start of the song."),
    SONG_SELECT((byte) 0xF3, 1, "Song Select. Specifies which sequence or song is to be played."),
    UNDEFINED_2((byte) 0xF4, 0, "Undefined."),
    UNDEFINED_3((byte) 0xF5, 0, "Undefined."),
    TUNE_REQUEST((byte) 0xF6, 0, "Tune Request. Requests a tuning from synthesizers."),
    END_OF_EXCLUSIVE((byte) 0xF7, 0, "End of Exclusive. Terminates a System Exclusive dump."),

    TIMING_CLOCK((byte) 0xF8, 0, "Timing Clock. Sent 24 times per quarter note for synchronization."),
    UNDEFINED_4((byte) 0xF9, 0, "Undefined."),
    START((byte) 0xFA, 0, "Start. Starts the current sequence playing."),
    CONTINUE((byte) 0xFB, 0, "Continue. Continues playback from where the sequence was stopped."),
    STOP((byte) 0xFC, 0, "Stop. Stops the current sequence."),
    UNDEFINED_5((byte) 0xFD, 0, "Undefined."),
    ACTIVE_SENSING((byte) 0xFE, 0, "Active Sensing. Ensures a connection is active; if not, receiver assumes termination."),
    SYSTEM_RESET((byte) 0xFF, -1, "System Reset. Resets all MIDI devices.");


    public final byte status;
    public final int dataLength;
    public final String description;

    MIDIMessageType(byte status, int dataLength, String description) {
        this.status = status;
        this.dataLength = dataLength;
        this.description = description;
    }

    public static MIDIMessageType getByStatus(byte status) {
        return Arrays.stream(MIDIMessageType.values())
                .filter(m -> status >= (byte) 0xF0 ? m.status == status : (m.status & 0xF0) == (status & 0xF0))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No MIDI message with status " + status));
    }
}
