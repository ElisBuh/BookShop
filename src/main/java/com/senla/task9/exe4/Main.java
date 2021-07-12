package com.senla.task9.exe4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Clock(1000));

        thread.setDaemon(true);
        thread.start();

        int i = -1;
        while (i != 0) {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                i = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


