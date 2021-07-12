package com.senla.task9.exe3;

import java.util.LinkedList;
import java.util.Queue;

public class Store {

    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized void get() {
        while (queue.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Потребитель забрал число: " + queue.poll());
        System.out.println("Кол. товаров на складе: " + queue.size());
        notify();
    }

    public synchronized void put() {
        while (queue.size() >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int i = (int) (Math.random() * 100);
        System.out.println("Производитель добавили число: " + i);
        queue.add(i);
        System.out.println("Кол. товаров на складе: " + queue.size());
        notify();
    }
}
