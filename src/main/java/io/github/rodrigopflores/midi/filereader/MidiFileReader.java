package io.github.rodrigopflores.midi.filereader;

import io.github.rodrigopflores.midi.file.*;
import io.github.rodrigopflores.midi.file.TrackChunk;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MidiFileReader {

    private String path;
    private FileManager fm;

    public MidiFileReader(String path) {
        this.path = path;
    }

    public MidiFile read() {

        try (FileInputStream fis = new FileInputStream(path)) {
            if (path == null) {
                throw new IllegalArgumentException("Path cannot be null");
            }
            fm = new FileManager(fis);
            return readAndParseFile();
        } catch (IOException | IllegalArgumentException e) {
            // TODO handle
            e.printStackTrace();
            return null;
        }
    }

    private MidiFile readAndParseFile() throws IOException {

        HeaderChunk headerChunk = readHeaderChunk();
        List<TrackChunk> trackChunks = new ArrayList<>();
        while (fm.bytesLeft()) {

            if (!"MTrk".equals(fm.string(4))) {
                throw new InvalidaFileFormatException("Bytes in this position must read 'MTrk'");
            }

            trackChunks.add(readTrackChunk());
        }

        return new MidiFile(headerChunk, trackChunks);
    }

    private HeaderChunk readHeaderChunk() throws IOException {

        if (!"MThd".equals(fm.string(4))) {
            throw new InvalidaFileFormatException("First bytes must be 'MThd'");
        }

        fm.skipBytes(4); // The length of the header is always the same
        int format = fm.i16();
        int ntrks = fm.i16();
        int division = fm.i16();
        var deltaTimeType = DeltaTimeType.fromDivision(division);

        int ticksPerUnit;
        if (deltaTimeType == DeltaTimeType.METRIC_TIME) {
            ticksPerUnit = division & 0x7FFF;
        } else {
            ticksPerUnit = division & 0xFF;
        }


        return new HeaderChunk(format, ntrks, ticksPerUnit, deltaTimeType);
    }

    private TrackChunk readTrackChunk() throws IOException {

        int length = fm.i32();
        int initialByteCount = fm.getBytesRead();
        var trackChunk = new TrackChunk();
        var mTrkEvents = trackChunk.getMTrkEvents();
        MidiMessageType runningStatus = null;
        while (fm.getBytesRead() - initialByteCount < length) {
            mTrkEvents.add(readMTrkEvent(runningStatus));
            runningStatus = Optional.of(mTrkEvents)
                    .map(List::getLast)
                    .map(MTrkEvent::getEvent)
                    .map(Event::getMessageType)
                    .orElseThrow(() -> new InvalidaFileFormatException("MTrkEvents must contain at least one event"));
        }

        return trackChunk;
    }

    private MTrkEvent readMTrkEvent(MidiMessageType runningStatus) throws IOException {

        int deltaTime = readVariableLengthQuantity();
        byte type = fm.aByte();
        Event event;
        if (isMidiEventType(type)) {
            event = readMidiEvent(runningStatus, type);
        } else if (isMetaEventType(type)) {
            event = reandMetaEvent(type);
        } else {
            event = readSysexEvent(type);
        }

        return new MTrkEvent(deltaTime, event);
    }

    private static boolean isMidiEventType(byte type) {
        return (type & (byte) 0xF0) != (byte) 0xF0;
    }

    private static boolean isMetaEventType(byte type) {
        return type == (byte) 0xFF;
    }

    private Event readMidiEvent(MidiMessageType runningStatus, byte type) throws IOException {

        MidiMessageType messageType;
        byte[] data;
        if (usesRunningStatus(type)) {
            messageType = runningStatus;
            data = new byte[runningStatus.dataLength];
            data[0] = type; // Type is actually first byte of data because of running status
            byte[] restOfData = fm.nBytes(runningStatus.dataLength-1);
            System.arraycopy(restOfData, 0, data, 1, restOfData.length);
        } else {
            messageType = MidiMessageType.getByStatus(type);
            data = fm.nBytes(messageType.dataLength);
        }
        int channel = type & 0x0F;

        return new MidiEvent(messageType, data, channel);
    }

    private static boolean usesRunningStatus(byte type) {
        return (type & 0x80) == 0;
    }

    private Event reandMetaEvent(byte type) throws IOException {
        var messageType = MidiMessageType.getByStatus(type);
        var metaEventType = MetaEventType.getByCode(fm.aByte());
        int length = readVariableLengthQuantity();
        return new MetaEvent(messageType, fm.nBytes(length), metaEventType);
    }

    private Event readSysexEvent(byte type) throws IOException {

        var messageType = MidiMessageType.getByStatus(type);
        var dataList = new ArrayList<Byte>();
        byte currentByte = 0x00;
        while (currentByte != (byte) 0xF7) {
            currentByte = fm.aByte();
            dataList.add(currentByte);
        }
        byte[] data = new byte[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return new SysexEvent(messageType, data);
    }

    // TODO add javadoc style description
    private int readVariableLengthQuantity() throws IOException {

        int dt = 0;
        for (int i = 0; i < 4; i++) {
            int b = fm.aByte() & 0xFF;
            dt <<= 7;
            dt |= (b & 0x7F);
            if ((b & 0x80) == 0) {
                break;
            }
            if (i == 3) {
                throw new IOException("Invalid VLQ encoding: exceeds 4 bytes");
            }
        }
        return dt;
    }
}
