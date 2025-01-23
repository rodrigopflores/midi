package io.github.rodrigopflores.midi.filereader;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileManager {

    private final DataInputStream data;
    private int bytesRead = 0;

    public FileManager(FileInputStream fis) {
        this.data = new DataInputStream(fis);
    }

    public byte aByte() throws IOException {
        bytesRead++;
        return data.readByte();
    }

    public byte[] nBytes(int n) throws IOException {
        bytesRead += n;
        return data.readNBytes(n);
    }

    public void skipBytes(int n) throws IOException {
        bytesRead += n;
        long skipped = data.skip(n);
        if (skipped != n) {
            throw new InvalidFileFormatException("Skipped " + skipped + " bytes of " + n + " bytes. There shouldn't be leftover bytes.");
        }
    }

    public String string(int n) throws IOException {
        bytesRead += n;
        return new String(data.readNBytes(n));
    }

    public int i32() throws IOException {
        bytesRead += 4;
        return data.readInt();
    }

    public short i16() throws IOException {
        bytesRead += 2;
        return data.readShort();
    }

    public int getBytesRead() throws IOException {
        return bytesRead;
    }

    public boolean bytesLeft() throws IOException {
        return data.available() > 0;
    }
}
