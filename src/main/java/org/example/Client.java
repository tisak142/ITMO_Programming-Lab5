package org.example;

import java.util.Scanner;


public class Client {
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