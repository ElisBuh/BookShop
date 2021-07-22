package com.senla.ui.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз");
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз");
            }
        }
    }

    public static LocalDate readDate() {
        LocalDate date;
        while (true) {
            try {
                date = LocalDate.parse(ConsoleHelper.readString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                return date;
            } catch (DateTimeParseException e) {
                writeMessage("Произошла ошибка при попытке ввода даты. Попробуйте еще раз");
            }
        }
    }
}
