package javatest.io;


import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * @Description:  https://developer.ibm.com/zh/articles/j-zerocopy/
 *
 *
 * @author: zhbo
 * @date 2020/11/23 9:10
 */
public class FileChannelTest {
    private static int length = 0x4FFFFFFF;//1G

   static Path pto = Paths.get("C:\\Users\\nefu_\\Desktop\\to.txt") ;
   // static  Path  pfrom   = Paths.get("C:\\Users\\nefu_\\Desktop\\from.txt") ;
    //static Path   pto = Paths.get("C:\\Users\\擎风\\Desktop\\test.txt") ;
   static Path pfrom   = Paths.get("C:\\Users\\擎风\\Desktop\\pfrom.txt") ;
    public static void main(String[] args) throws Exception {
        FileChannel file = FileChannel.open(pto, StandardOpenOption.READ, StandardOpenOption.WRITE);
            MappedByteBuffer byteBuffer = file.map(FileChannel.MapMode.READ_WRITE, 0, file.size());
            file.close();
        ((DirectBuffer)byteBuffer).cleaner().clean();
        long start = System.currentTimeMillis();
        sfsdf();
        //fileChannel();
        //nioChannelBuffer();
        //nioBufferChannelBuffer();
        System.out.println(System.currentTimeMillis() - start);


    }

    public static void sfsdf() throws Exception {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606739827644&di=5e5cda8a9c9c39da59812ea46f608f2e&imgtype=0&src=http%3A%2F%2Fc11.eoemarket.com%2Fapp0%2F699%2F699128%2Fscreen%2F3445790.jpg";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            FileOutputStream fos1 = new FileOutputStream("./gxpgxpgxp.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1 ) {
                fos1.write(buffer,0,len);
            }
            fos1.flush();
            fos1.close();
        }
    }


    public static void fileChannel() {
        try(FileChannel file = FileChannel.open(pfrom, StandardOpenOption.READ, StandardOpenOption.WRITE);){
            MappedByteBuffer byteBuffer = file.map(FileChannel.MapMode.READ_WRITE, 0, file.size());
            long len = file.size();
            byte[] bytes = new byte[(int) len];
            byteBuffer.get(bytes);
            byteBuffer.load();
            byteBuffer.force();
            System.out.println(new String(bytes));
        } catch (IOException e) {

        }
    }


    public static void randomAccessFile() {
        try(RandomAccessFile file = new RandomAccessFile(pfrom.toString(),"rw");){
            /**
             * 直接使用mmap来替代
             */


        } catch (IOException e) {

        }
    }



    public static void nioBufferChannelBuffer() {

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(pfrom.toFile()));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(pto.toFile()))) {

            byte[] byteArray = new byte[8192];
            int bytesCount;
            while ((bytesCount = in.read(byteArray)) != -1) {
                out.write(byteArray, 0, bytesCount);
            }
            } catch (IOException ex) {
           ex.printStackTrace();
        }

    }


    public static void nioDirectChannelBuffer() {
        try(FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
            FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocateDirect(8);

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
        try(FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
            FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)){
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
        try(FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ);
            FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ)){
            MappedByteBuffer mapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0,length);
            outChannel.write(mapBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void ninit() throws Exception {
        try(FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE, StandardOpenOption.READ)){
            MappedByteBuffer mapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0,length);
            for(int i=0;i<length;i++) {
                mapBuffer.put((byte)0);
            }
            mapBuffer.flip();
            while(mapBuffer.hasRemaining()) {
                mapBuffer.get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void transferTo()  {
        try (FileChannel inChannel = FileChannel.open(pfrom, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(pto, StandardOpenOption.WRITE)){
            inChannel.transferTo(0,length,outChannel);
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
