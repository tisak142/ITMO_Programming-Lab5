package commands.concreteCommands;

import commands.Command;

import java.util.Scanner;

/**
 * Класс ExitCommand реализует команду для завершения работы программы.
 * <p>
 * Команда не принимает аргументов. После выполнения программа завершает свою работу.
 * </p>
 */
public class ExitCommand implements Command {

    /**
     * Выполняет команду для завершения работы программы.
     * <p>
     * Команда не принимает аргументов. После выполнения программа завершает свою работу.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды (пустая строка, так как команда не требует имени)
     */
    @Override
    public String getName() {
        return "";
    }
}