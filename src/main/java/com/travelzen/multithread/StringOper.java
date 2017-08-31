package com.travelzen.multithread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author andrew
 * @createtime 2017-08-30
 * @IDE INTELLIJ IDEA
 **/
public class StringOper {

    String s;
    StringBuffer s1;
    StringBuilder s2;
    CountDownLatch c = new CountDownLatch(1);
    public StringOper() {
        s = new String();
        s1 = new StringBuffer();
        s2 = new StringBuilder();
    }

    public void strAppend() {

        Random r = new Random();
        for (int i = 0; i < 10 ; i ++) {
            Thread t = new Thread(new Param("-" + r.nextInt() + "-"));
            t.start();
        }
        c.countDown();

    }

    public static void main(String[] args) {

        StringOper s = new StringOper();
        s.strAppend();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(s.s);
        System.out.println(s.s1.toString());
        System.out.println(s.s2.toString());
    }

    class Param implements Runnable {
        private String str;

        public Param(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            s += str;
            s1.append(str);
            s2.append(str);
        }
    }
}
