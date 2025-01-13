package io.github.rodrigopflores.midiparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class FileManager {

    private static FileInputStream fis;
    private static int bytesRead = 0;

    public static void setFileInputStream(FileInputStream fis) {
        FileManager.fis = fis;
    }

    public static byte aByte() throws IOException {
        bytesRead++;
        return fis.readNBytes(1)[0];
    }

    public static byte[] nBytes(int n) throws IOException {
        bytesRead += n;
        return fis.readNBytes(n);
    }

    public static String string(int n) throws IOException {
        bytesRead += n;
        return new String(fis.readNBytes(n));
    }

    public static int i32() throws IOException {
        bytesRead += 4;
        return new BigInteger(fis.readNBytes(4)).intValue();
    }

    public static short i16() throws IOException {
        bytesRead += 2;
        return new BigInteger(fis.readNBytes(2)).shortValue();
    }

    public static int getBytesRead() throws IOException {
        return bytesRead;
    }

    public static boolean bytesLeft() throws IOException {
        return fis.available() > 0;
    }
}
