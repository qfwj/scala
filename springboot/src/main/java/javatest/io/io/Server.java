package javatest.io.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorCompletionService;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        // 注册 channel，并且指定感兴趣的事件是 Accept
        new Thread(()->{
            try{
                System.out.println(2222);
                selector.select();
                System.out.println(3333);
            } catch (Exception e){
            }
        }).start();
        Thread.sleep(10000);
        selector.wakeup();
        ssc.register(selector, SelectionKey.OP_ACCEPT, 12 );

        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(128);
        writeBuff.put("recsdfsdeived".getBytes());
        writeBuff.flip();

        while (true) {

            int nReady = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();


                if (key.isAcceptable()) {
                    it.remove();
                    // 创建新的连接，并且把连接注册到selector上，而且，
                    // 声明这个channel只对读操作感兴趣。
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ );
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE );

                }
                else if (key.isReadable()) {
                    it.remove();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuff.clear();
                    socketChannel.read(readBuff);

                    readBuff.flip();
                    System.out.println("received : " + new String(readBuff.array()));
                    key.interestOps(SelectionKey.OP_WRITE);


                }
                else if (key.isWritable()) {
                    writeBuff.rewind();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(writeBuff);
                   key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}
