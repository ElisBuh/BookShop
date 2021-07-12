package com.senla.task9.exe2;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> { });
        Thread thread2 = new Thread(() -> { });
        thread1.start();
        thread2.start();
        int i = 0;
        while (i < 20) {
            try {
                System.out.println(thread1.getName());
                Thread.sleep(500);
                System.out.println(thread2.getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
