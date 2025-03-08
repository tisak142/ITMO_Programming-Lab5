package org.example;

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
     * Точка входа в программу.
     * <p>
     * Запускает интерактивный режим работы с командами. Пользователь вводит команды,
     * которые затем передаются в Invoker для выполнения.
     * </p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Invoker invoker = new Invoker(in);
        while (true) {
            System.out.print("$ ");
            String line = in.nextLine();
            invoker.invoke(line);
        }
    }
}