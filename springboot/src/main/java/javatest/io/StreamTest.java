package javatest.io;

import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StreamTest {
        static Path pto = Paths.get("C:\\Users\\擎风\\Desktop\\pto.txt");
    static Path pfrom = Paths.get("C:\\Users\\擎风\\Desktop\\pfrom.txt");
   // static Path pto = Paths.get("C:\\Users\\nefu_\\Desktop\\to.txt");
   // static Path pfrom = Paths.get("C:\\Users\\nefu_\\Desktop\\from.txt");
    static long size = 0xffffffffL;

    public static void main(String[] args) throws Exception {

        long time = System.currentTimeMillis();
        transformto();
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void sendfile() {
        try (FileChannel channel = FileChannel.open(pfrom, StandardOpenOption.READ);
             FileChannel ptoChannel = FileChannel.open(pto, StandardOpenOption.WRITE);) {
            channel.transferTo(0, channel.size() ,ptoChannel );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void transformto() {
        try (FileChannel channel = FileChannel.open(pfrom, StandardOpenOption.READ);
             FileChannel ptoChannel = FileChannel.open(pto, StandardOpenOption.WRITE);) {
            channel.transferTo(0, channel.size() ,ptoChannel );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void RinitChannel() {
        try (FileChannel channel = FileChannel.open(pfrom, StandardOpenOption.READ);) {
          ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//128K：1539 64K：1594 256K:1474  512K: 1483
           while (channel.read(byteBuffer) > 0) {
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RinitChannelDirect() {
        try (FileChannel channel = FileChannel.open(pfrom, StandardOpenOption.READ);) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8192 * 32); //128K：1221 64K：1315 256K:1150  512K: 1075 1M:1092
            while (channel.read(byteBuffer) > 0) {
                ((DirectBuffer)byteBuffer).cleaner().clean();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void RinitBuffer() {
            try (BufferedInputStream outputStream = new BufferedInputStream(new FileInputStream(pfrom.toString()));) {
            byte[] bytes = new byte[1024]; //128K：1628 64K：1727 256K:1522  512K: 1483
            while (outputStream.read(bytes) > 0) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RinitNoBuffer() {
        try (InputStream outputStream = new FileInputStream(pfrom.toString());) {
            byte[] bytes = new byte[8192*16];//128K：1611 64K：1727 256K:1522  512K: 1483
            while ( outputStream.read(bytes) > 0) {

            }
            /**/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void WinitChannel() {
        try (FileChannel channel = FileChannel.open(pfrom, StandardOpenOption.WRITE);) {
            long mm = size / 8192;
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8192);
            while (--mm > 1) {
                channel.write(byteBuffer);
                byteBuffer.flip();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WinitBuffer() {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pfrom.toString()));) {
            long mm = size / 8192;
            byte[] bytes = new byte[8192];

            while (--mm > 1) {
                outputStream.write(bytes);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WinitNoBuffer() {
        try (OutputStream outputStream = new FileOutputStream(pfrom.toString());) {
            long mm = size / 8192;
            byte[] bytes = new byte[8192];

            while (--mm > 1) {
                outputStream.write(bytes, 0, 8192);
            }
            outputStream.flush();
            /*12900*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
