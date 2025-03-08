package commands;

import classes.MusicBand;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс UpdateIdCommand реализует команду для обновления элемента коллекции по его id.
 * <p>
 * Команда принимает один аргумент — id элемента, который нужно обновить.
 * Пользователь вводит новые данные для элемента, и он заменяет старый элемент с указанным id.
 * </p>
 */
public class UpdateIdCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Выполняет команду для обновления элемента коллекции по его id.
     * <p>
     * Команда принимает один аргумент — id элемента, который нужно обновить.
     * Пользователь вводит новые данные для элемента, и он заменяет старый элемент с указанным id.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (id элемента для обновления)
     */
    @Override
    public void execute(Scanner in, String... args) {
        try {
            int id = Integer.parseInt(args[0]);
            if (!receiver.containsId(id)) {
                System.err.println("Error: element with id " + id + " does not exist in collection.");
                return; // Прекращаем выполнение команды
            }
            Scanner scanner = new Scanner(System.in);
            MusicBand band = receiver.createMusicBand(scanner);
            receiver.updateId(id, band);
            System.out.println("The element with id " + id + " has successfully updated.");
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid id entered. It should be an integer");
        }
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды ("updateId")
     */
    @Override
    public String getName() {
        return "updateId";
    }
}