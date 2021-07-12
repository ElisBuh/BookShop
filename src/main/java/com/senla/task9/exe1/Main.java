package com.senla.task9.exe1;

public class Main {
    public static void main(String[] args) {
        try {
            Thread thread1 = new Thread(new Thread1());
            Thread thread2 = new Thread(new Thread2());
            System.out.println(thread1.getState() + " new");
            thread1.start();
            thread2.start();
            Thread.sleep(500);
            System.out.println(thread1.getState() + " timed_waiting");
            System.out.println(thread2.getState() + " waiting");
            Thread.sleep(2000);
            System.out.println(thread1.getState() + " terminated");
            System.out.println(thread2.getState() + " waiting");

            Object a = Thread2.getA();
            synchronized (a){
                a.notify();
            }

            System.out.println(thread2.getState() + " blocked");
            Thread.sleep(3000);
            System.out.println(thread2.getState() + " terminated thread 2");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
