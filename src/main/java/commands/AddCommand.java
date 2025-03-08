package commands;

import classes.*;
import org.example.Receiver;

import java.util.Scanner;

/**
 * Класс AddCommand реализует команду добавления нового элемента (MusicBand) в коллекцию.
 * <p>
 * Команда не принимает аргументов. Пользователь вводит данные для создания нового объекта MusicBand
 * через консольный ввод.
 * </p>
 */
public class AddCommand implements Command {
    private final Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("add")
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * Выполняет команду добавления нового элемента (MusicBand) в коллекцию.
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
        MusicBand band = receiver.createMusicBand(in);
        receiver.add(band);
        System.out.println("Added Music Band - " + band.getName());
    }
}