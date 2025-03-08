package commands;

import org.example.Receiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс CountingByNameCommand реализует команду для подсчета количества элементов коллекции,
 * сгруппированных по их именам.
 * <p>
 * Команда не принимает аргументов. Результат выводится в консоль в виде таблицы,
 * где для каждого имени указано количество элементов с таким именем.
 * </p>
 */
public class CountingByNameCommand implements Command {
    Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команды.

    /**
     * Возвращает название команды.
     *
     * @return название команды ("countingByName")
     */
    @Override
    public String getName() {
        return "countingByName";
    }

    /**
     * Выполняет команду для подсчета количества элементов коллекции,
     * сгруппированных по их именам.
     * <p>
     * Команда не принимает аргументов. Результат выводится в консоль в виде таблицы,
     * где для каждого имени указано количество элементов с таким именем.
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
        HashMap<String, Integer> map = receiver.countByName();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Group " + entry.getKey() + " : " + value);
        }
    }
}