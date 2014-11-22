package cc.billworks.tutorials.sharedinputstream;

import java.io.*;
import java.net.URL;

/**
 * Based on JDK 7u51
 *
 * Created by Bill Xiong on 11/22/14.
 */
public class BasicTest {

    public static void main(String[] args) throws IOException {
        URL res = BasicTest.class.getResource("/mail_samples/01.txt");

        // 使用原始 FileInputStream
        InputStream in = new FileInputStream(new File(res.getFile()));

        // 该方法取得的InputStream默认就是 BufferedInputStream
//        InputStream in = BasicTest.class.getResourceAsStream("/mail_samples/01.txt");

//        if (!in.markSupported()) {
//            in = new BufferedInputStream(in);
//        }

        printlnContentByBufferedRead(in, "BUF1");

        printlnContentByBufferedRead(in, "BUF2");

        in.close();
    }

    private static void printlnContentByBufferedRead(InputStream in, String tag) throws IOException {
        boolean isMarkSupport = in.markSupported();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in)); // allocate 8192

        println(tag, "input stream is [" + in.getClass().getName() + "]");
        println(tag, "mark support = " + isMarkSupport);

        if (isMarkSupport) {
            in.mark(5);
        }

        println(tag, "read : " + reader.readLine());

        if (isMarkSupport) {
            in.reset();
        }
    }

    private static void println(String s) {
        println("std", s);
    }

    private static void println(String tag, String s) {
        System.out.println("[" + tag + "] " + s);
    }
}
