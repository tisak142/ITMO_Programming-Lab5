package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс AverageParticipantsCommand реализует команду для вычисления среднего значения
 * количества участников (numberOfParticipants) для всех элементов коллекции.
 * <p>
 * Команда не принимает аргументов. Результат выводится в консоль.
 * </p>
 */
public class AverageParticipantsCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("AverageParticipantsCommand")
     */
    @Override
    public String getName() {
        return "AverageParticipantsCommand";
    }

    /**
     * Выполняет команду для вычисления среднего значения количества участников
     * (numberOfParticipants) для всех элементов коллекции.
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
        System.out.println("Average number of participants in groups is " + receiver.getAverageNumber());
    }
}