package com.senla.task9.exe1;

public class Thread2 implements Runnable {

    public static Object getA() {
        return a;
    }

    private static Object a = new Object();
    @Override
    public void run() {
        try {

            synchronized (a) {
                a.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
