package javatest.video;

import java.util.List;

/**
 * description:  <br>
 * date: 2020/12/3 10:13 <br>
 * author: zhbo <br>
 */
public class TestVideo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(main2());
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end:" + (System.currentTimeMillis() - start));
    }

    public static List<String> main2() {
        List<String> commend = new java.util.ArrayList<>();
        commend.add("D:\\迅雷下载\\ffmpeg_v4.2.2\\ffmpeg-20200315-c467328-win64-static\\bin\\ffmpeg.exe");

        // commend.add("-fflags");commend.add("flush_packets");
        // commend.add("-c");commend.add("copy");
        commend.add("-ss");
        // -ss 30 -c copy -to 40 output.wmv
        commend.add("" +2);
        commend.add("-to");commend.add("" + 22);
        commend.add("-accurate_seek");
        commend.add("-i");
        commend.add("D:\\迅雷下载\\ffmpeg_v4.2.2\\ffmpeg-20200315-c467328-win64-static\\bin\\test.mp4");

        //  commend.add("-copyts");
        commend.add("-codec");
        commend.add("copy");
        commend.add("-avoid_negative_ts");
        commend.add("1");
        commend.add("-y");
        commend.add("output.mp4");
        return commend;
    }
    public static List<String> main1() {
        List<String> commend = new java.util.ArrayList<>();
        commend.add("D:\\迅雷下载\\ffmpeg_v4.2.2\\ffmpeg-20200315-c467328-win64-static\\bin\\ffmpeg.exe");
        commend.add("-ss");
        // -ss 30 -c copy -to 40 output.wmv
        commend.add("5");
        commend.add("-i");
        commend.add("D:\\迅雷下载\\ffmpeg_v4.2.2\\ffmpeg-20200315-c467328-win64-static\\bin\\test.mp4");
        //commend.add("-fflags");commend.add("flush_packets");
        // ffmpeg -ss 3 -i test.mp4 -ss 3 -to 50  -copyts -codec copy -avoid_negative_ts 1 -y output.mp4
//https://blog.csdn.net/yueliang2100/article/details/104292258
        //  commend.add("-c");
        //   commend.add("copy");

        commend.add("-ss");
        // -ss 30 -c copy -to 40 output.wmv
        commend.add("5");

        commend.add("-copyts");
        commend.add("-codec");
        commend.add("copy");
        commend.add("-avoid_negative_ts");
        commend.add("1");
        commend.add("-y");
        commend.add("-to");
        commend.add("25.0");

        commend.add("output.mp4");
        return commend;
    }
}
