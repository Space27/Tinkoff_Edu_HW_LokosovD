package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class Task4 {

    private Task4() {
    }

    public static void createFileWithStreams(Path path) {
        final CRC32 crc = new CRC32();
        final CharsetEncoder utf8 = StandardCharsets.UTF_8.newEncoder();

        try (OutputStream newFile = Files.newOutputStream(path);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(newFile, crc);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, utf8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.print("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
