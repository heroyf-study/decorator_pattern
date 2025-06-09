package com.heroyf.tech.insight.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author frankfyang
 * @date 2025/6/9 10:21
 */
public class BufferFileInputStream extends InputStream {

    // 8KB buffer size
    private static final int BUFFER_SIZE = 8192;
    private static final byte[] buffer = new byte[BUFFER_SIZE];
    private final FileInputStream fileInputStream;
    private int capacity = -1;
    private int position = -1;

    public BufferFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        if (bufferCanRead()) {
            return readFromBuffer();
        }
        refreshBuffer();
        if (!bufferCanRead()) {
            return -1; // End of stream
        }
        return readFromBuffer();
    }

    private void refreshBuffer() throws IOException {
        capacity = this.fileInputStream.read(buffer);
        position = 0;
    }

    private int readFromBuffer() {
        return buffer[position++] & 0xFF; // Read byte from buffer
    }

    private boolean bufferCanRead() {
        if (capacity == -1) {
            return false;
        }
        return position != capacity;
    }

    @Override
    public void close() throws IOException {
        this.fileInputStream.close();
    }
}
