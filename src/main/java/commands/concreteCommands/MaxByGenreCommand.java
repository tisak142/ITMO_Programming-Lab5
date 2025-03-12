package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс MaxByGenreCommand реализует команду для вывода элемента коллекции
 * с максимальным значением поля genre.
 * <p>
 * Команда не принимает аргументов. Результат выводится в консоль.
 * </p>
 */
public class MaxByGenreCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("max_by_genre")
     */
    @Override
    public String getName() {
        return "max_by_genre";
    }

    /**
     * Выполняет команду для вывода элемента коллекции с максимальным значением поля genre.
     * <p>
     * Команда не принимает аргументов. Результат выводится в консоль.
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
        receiver.maxByGenre();
    }
}