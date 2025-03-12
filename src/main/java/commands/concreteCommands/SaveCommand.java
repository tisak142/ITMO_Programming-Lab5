package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс SaveCommand реализует команду для сохранения коллекции в файл.
 * <p>
 * Команда не принимает аргументов. Коллекция сохраняется в файл, указанный
 * в настройках программы.
 * </p>
 */
public class SaveCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("save")
     */
    @Override
    public String getName() {
        return "save";
    }

    /**
     * Выполняет команду для сохранения коллекции в файл.
     * <p>
     * Команда не принимает аргументов. Коллекция сохраняется в файл, указанный
     * в настройках программы.
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
        receiver.save();
    }
}