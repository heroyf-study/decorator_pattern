package com.heroyf.tech.insight.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author frankfyang
 * @date 2025/6/9 14:17
 */
public class CountFileInputStream extends InputStream {

    private InputStream delegate;
    private AtomicInteger readCount = new AtomicInteger(0);

    public CountFileInputStream(InputStream inputStream) {
        this.delegate = inputStream;
    }

    @Override
    public int read() throws IOException {
        int readIdx = this.delegate.read();
        if (readIdx != -1) {
            return readCount.incrementAndGet();
        }
        return readIdx;
    }

    public int getReadCount() {
        return this.readCount.get();
    }
}
