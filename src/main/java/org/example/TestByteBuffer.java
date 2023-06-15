package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.example.ByteBufferUtil.debugAll;

public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);
                if (len == -1) {
                    break;
                }

                // 写->读切换
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                // 读->写切换
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer bbf1 = StandardCharsets.UTF_8.encode("hello");
        debugAll(bbf1);
        String str="hello";
        ByteBuffer bbf2 = ByteBuffer.wrap(str.getBytes());
        debugAll(bbf2);
    }
}
