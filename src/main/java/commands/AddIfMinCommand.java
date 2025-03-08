package commands;

import classes.MusicBand;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс AddIfMinCommand реализует команду добавления нового элемента (MusicBand) в коллекцию,
 * если его значение меньше значения наименьшего элемента в коллекции.
 * <p>
 * Команда не принимает аргументов. Пользователь вводит данные для создания нового объекта MusicBand
 * через консольный ввод.
 * </p>
 */
public class AddIfMinCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Выполняет команду добавления нового элемента (MusicBand) в коллекцию,
     * если его значение меньше значения наименьшего элемента в коллекции.
     * <p>
     * Команда не принимает аргументов. Пользователь вводит данные для создания нового объекта MusicBand
     * через консольный ввод.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        MusicBand band = receiver.createMusicBand(scanner);
        receiver.addIfMin(band);
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