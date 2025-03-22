package org.example;

import xml.DOMReaderScript;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс Client представляет клиентскую часть программы.
 * <p>
 * Этот класс запускает интерактивный режим работы с командами.
 * Пользователь вводит команды, которые затем выполняются Invoker'ом.
 * </p>
 */
public class Client {
    /**
     * Точка входа в программу.ы
     * <p>
     * Запускает интерактивный режим работы с командами. Пользователь вводит команды,
     * которые затем передаются в Invoker для выполнения.
     * </p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Invoker invoker = new Invoker(in);
            while (true) {
                System.out.print("$ ");
                String line = in.nextLine();
                invoker.invoke(line);
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}