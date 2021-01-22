package javatest.file;


import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/11/23 9:10
 */
public class ReadWriteFileTest {
    private static int length = 0x4FFFFFFF;//1G

    //    static Path   pto = Paths.get("C:\\Users\\nefu_\\Desktop\\to.txt") ;
//    static  Path  pfrom   = Paths.get("C:\\Users\\nefu_\\Desktop\\from.txt") ;
    static Path pto = Paths.get("C:\\Users\\擎风\\Desktop\\test.txt");
    static Path pfrom = Paths.get("C:\\Users\\擎风\\Desktop\\tstss.txt");

    public static void main(String[] args) throws Exception {


        long start = System.currentTimeMillis();
        readerTestBuffer();
        System.out.println(System.currentTimeMillis() - start);


    }


    public static void readerTestBuffer() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pto.toString() )); //默认是UTF-8
                //  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pto.toString()),StandardCharsets.UTF_8 )); //默认是UTF-8

                BufferedWriter outputStreamWriter = new BufferedWriter(new FileWriter(pfrom.toString()))) {
            bufferedReader.lines().forEach(f-> System.out.println(f));
              //  stringBuffer.append(bufferedReader.readLine()., 0,bytesCount);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void readerTest() {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(pto.toString()), StandardCharsets.UTF_8);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(pfrom.toString()), StandardCharsets.UTF_8)) {
            char[] byteArray = new char[8];
            StringBuffer stringBuffer = new StringBuffer();
            int count = 0;
            while ( (count = isr.read(byteArray)) != -1) {
                stringBuffer.append(byteArray, 0, count);
            }
            outputStreamWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public static void nioDirectChannelBuffer() {
        try (FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
             FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
                //outChannel.force(true);
                outChannel.force(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void nioChannelBuffer() {
        try (FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
             FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void mapBuffer() {
        try (FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
             FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)) {
            MappedByteBuffer mapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            outChannel.write(mapBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void ninit() throws Exception {
        try (FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ)) {
            MappedByteBuffer mapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i = 0; i < length; i++) {
                mapBuffer.put((byte) 0);
            }
            mapBuffer.flip();
            while (mapBuffer.hasRemaining()) {
                mapBuffer.get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void transferTo() {
        try (FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE)) {
            inChannel.transferTo(0, length, outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void fileCopy() throws Exception {

        /**
         * file copy test
         */
        Files.copy(pfrom, pto, StandardCopyOption.REPLACE_EXISTING);
    }
}
