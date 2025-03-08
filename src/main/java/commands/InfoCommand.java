package commands;

import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс InfoCommand реализует команду для вывода информации о коллекции.
 * <p>
 * Команда не принимает аргументов. Выводит тип коллекции, её размер и дату инициализации.
 * </p>
 */
public class InfoCommand implements Command {
    private final Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для получения информации о коллекции.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("info")
     */
    @Override
    public String getName() {
        return "info";
    }

    /**
     * Выполняет команду для вывода информации о коллекции.
     * <p>
     * Команда не принимает аргументов. Выводит тип коллекции, её размер и дату инициализации.
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
        String type = "Тип коллекции: " + receiver.getType();
        String length = "Размер коллекции: " + receiver.getLength();
        String time = "Дата инициализации коллекции: " + receiver.getDate();
        System.out.println(type);
        System.out.println(length);
        System.out.println(time);
    }
}