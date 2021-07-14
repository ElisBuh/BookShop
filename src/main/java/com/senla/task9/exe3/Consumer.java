package com.senla.task9.exe3;

public class Consumer implements Runnable
{
    private Store store;
    Consumer(Store store) {
        this.store=store;
    }
    @Override
    public void run(){
        for (int i = 1; i < 20; i++) {
            store.get();
        }
    }
}
