package io.github.rodrigopflores.midiparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class FileManager {

    private static FileInputStream fis;

    public static void setFileInputStream(FileInputStream fis) {
        FileManager.fis = fis;
    }

    public static byte[] nBytes(int n) throws IOException {
        return fis.readNBytes(n);
    }

    public static String string(int n) throws IOException {
        return new String(fis.readNBytes(n));
    }

    public static int i32() throws IOException {
        return new BigInteger(fis.readNBytes(4)).intValue();
    }

    public static short i16() throws IOException {
        return new BigInteger(fis.readNBytes(2)).shortValue();
    }
}
