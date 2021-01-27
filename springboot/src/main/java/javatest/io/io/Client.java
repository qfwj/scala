package javatest.io.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        ByteBuffer readBuffer = ByteBuffer.allocate(32);

        writeBuffer.put("hello".getBytes());
        writeBuffer.flip();

        while (true) {
            readBuffer.clear();
            socketChannel.read(readBuffer);
            System.out.println("received : " + new String(readBuffer.array()));
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);


        }

    }
}
