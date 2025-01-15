package io.github.rodrigopflores.midi.filereader;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class FileManager {

    private final FileInputStream fis;
    private int bytesRead = 0;

    public FileManager(FileInputStream fis) {
        this.fis = fis;
    }

    public byte aByte() throws IOException {
        bytesRead++;
        return fis.readNBytes(1)[0];
    }

    public byte[] nBytes(int n) throws IOException {
        bytesRead += n;
        return fis.readNBytes(n);
    }

    public void skipBytes(int n) throws IOException {
        bytesRead += n;
        long skipped = fis.skip(n);
        if (skipped != n) {
            throw new InvalidaFileFormatException("Skipped " + skipped + " bytes of " + n + " bytes. There shouldn't be leftover bytes.");
        }
    }

    public String string(int n) throws IOException {
        bytesRead += n;
        return new String(fis.readNBytes(n));
    }

    public int i32() throws IOException {
        bytesRead += 4;
        return new BigInteger(fis.readNBytes(4)).intValue();
    }

    public short i16() throws IOException {
        bytesRead += 2;
        return new BigInteger(fis.readNBytes(2)).shortValue();
    }

    public int getBytesRead() throws IOException {
        return bytesRead;
    }

    public boolean bytesLeft() throws IOException {
        return fis.available() > 0;
    }
}
