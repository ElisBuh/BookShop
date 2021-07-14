package com.senla.task9.exe2;

public class Main {
    private static int x = 0;

    public static void main(String[] args) {
        Main main = new Main();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                while (x < 1) {
                    main.waits();
                }
                System.out.println(Thread.currentThread().getName());
                main.notifys();
                x = 0;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                while (x > 0) {
                    main.waits();
                }
                System.out.println(Thread.currentThread().getName());
                main.notifys();
                x = 1;
            }
        });
        thread2.start();
        thread1.start();
    }

    private synchronized void waits() {
        try {
            wait();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void notifys() {
        notify();
    }
}
