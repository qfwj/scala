package javatest.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Optional;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/11/7 9:47
 */
public class TestFile {
    public static void main(String[] args)  throws Exception {

        System.out.println( Optional.empty() == null);


        File ff = new File("hhhh");
        // ff.createNewFile()才会调用系统创建磁盘文件
        //File f = new File("C:\\Users\\nefu_\\Desktop\\sparktest.txt");
        String path = "C:\\Users\\nefu_\\Desktop\\sparktest.txt";
        //String path = "C:\\Users\\nefu_\\Desktop\\1212.txt";
        OutputStream out = new FileOutputStream(ff,true);
       String url = "https://rmrbcmsonline.peopleapp.com/upload/voice/202003/202003141040028119.mp3";
        String re = null;
        long size = 0;
        long playTime = 0;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        URL url1 = new URL(url);
        conn = (HttpURLConnection) url1.openConnection();

        //设置超时时间
        conn.setConnectTimeout(5000);
        //设置请求方式
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection", "Keep-Alive");
        int code = conn.getResponseCode();
        inputStream = conn.getInputStream();
        FileOutputStream fos = new FileOutputStream(path, true);
        FileChannel channel = fos.getChannel();

        FileInputStream fosin = new FileInputStream(path);
        FileChannel channelin = fosin.getChannel();
        channelin.transferTo(1,2,channel);

        File file = new File("sfsdfdf");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byte[] tmp = new byte[2048];
        int len = 0;
        while ((len = inputStream.read(tmp)) != -1) {
            byteBuffer.put(tmp,0, len);
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        inputStream.close();
        size = outChannel.size();
        outChannel.close();
        //for (int i =0;i++ < 100000;) {
        for (int i =0;i++ < 10;) {
            InputStream in = new FileInputStream("C:\\Users\\nefu_\\Desktop\\sum_dics.txt");
            ByteBuffer buf = ByteBuffer.allocate(200000);
            while ((((FileInputStream) in).getChannel().read(buf)) > 0 ){
                buf.flip();
                channel.write(buf);
                buf.clear();

            }


            System.out.println(i);
        }
        System.out.println("");

    }
}
