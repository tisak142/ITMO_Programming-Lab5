package commands;

import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс ShuffleCommand реализует команду для перемешивания элементов коллекции в случайном порядке.
 * <p>
 * Команда не принимает аргументов. После выполнения элементы коллекции располагаются в случайном порядке.
 * </p>
 */
public class ShuffleCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Выполняет команду для перемешивания элементов коллекции в случайном порядке.
     * <p>
     * Команда не принимает аргументов. После выполнения элементы коллекции располагаются в случайном порядке.
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
        receiver.shuffle();
        System.out.println("Collection has been shuffled");
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