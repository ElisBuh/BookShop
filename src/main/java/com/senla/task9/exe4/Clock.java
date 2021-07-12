package com.senla.task9.exe4;

import java.time.LocalDateTime;

public class Clock implements Runnable {

    private int i;

    public Clock(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(LocalDateTime.now());
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
