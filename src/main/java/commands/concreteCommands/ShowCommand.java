package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс ShowCommand реализует команду для вывода всех элементов коллекции.
 * <p>
 * Команда не принимает аргументов. Выводит информацию о каждом элементе коллекции
 * в строковом представлении.
 * </p>
 */
public class ShowCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("show")
     */
    @Override
    public String getName() {
        return "show";
    }

    /**
     * Выполняет команду для вывода всех элементов коллекции.
     * <p>
     * Команда не принимает аргументов. Выводит информацию о каждом элементе коллекции
     * в строковом представлении.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(Scanner in, String... args) {
        System.out.println("Information about collection:");
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        receiver.showList();
    }
}