package commands.concreteCommands;

import commands.Command;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс RemoveCommand реализует команду для удаления элемента коллекции по его id.
 * <p>
 * Команда принимает один аргумент — id элемента, который нужно удалить.
 * Если элемент с указанным id существует, он удаляется из коллекции.
 * </p>
 */
public class RemoveCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("remove")
     */
    @Override
    public String getName() {
        return "remove";
    }

    /**
     * Выполняет команду для удаления элемента коллекции по его id.
     * <p>
     * Команда принимает один аргумент — id элемента, который нужно удалить.
     * Если элемент с указанным id существует, он удаляется из коллекции.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (id элемента для удаления)
     */
    @Override
    public void execute(Scanner in, String... args) {
        try {
            int id = Integer.parseInt(args[0]);
            if (!receiver.containsId(id)) {
                System.err.println("Error: the specified id does not exist.");
                return; // Прекращаем выполнение команды
            }
            receiver.removeBand(id);
            System.out.println("Band with " + id + " successfully removed");
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid id entered. It should be an integer");
        }
    }
}