package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс ClearCommand реализует команду для очистки коллекции.
 * <p>
 * Команда не принимает аргументов. После выполнения коллекция становится пустой.
 * </p>
 */
public class ClearCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("clear")
     */
    @Override
    public String getName() {
        return "clear";
    }

    /**
     * Выполняет команду для очистки коллекции.
     * <p>
     * Команда не принимает аргументов. После выполнения коллекция становится пустой.
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
        receiver.clear();
        System.out.println("Collection successfully cleaned");
    }
}