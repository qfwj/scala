package javatest.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/11/27 17:25
 */
public class ProcessMain {

    public static void main(String[] args) {
        BufferedReader br = null;
        Process process = null;
        try {
             process = Runtime.getRuntime().exec("tasklist");
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            System.out.println("列出所有正在运行的进程信息：");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(process!=null){
                process.destroy();
            }
        }





    }
}
