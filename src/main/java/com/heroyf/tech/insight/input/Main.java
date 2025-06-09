package com.heroyf.tech.insight.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

/**
 * @author frankfyang
 * @date 2025/6/9 10:03
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("");
        long time = Instant.now().toEpochMilli();
        try (InputStream countFileInputStream = new CountFileInputStream(
                new BufferFileInputStream(new FileInputStream(file)))) {
            while (true) {
                int countRead = countFileInputStream.read();
                if (countRead == -1) {
                    break;
                }
            }
            System.out.println("用时:" + (Instant.now().toEpochMilli() - time) + "毫秒");
            System.out.println("调用:" + ((CountFileInputStream) countFileInputStream).getReadCount() + "次");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
