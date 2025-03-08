package commands;

import org.example.Invoker;
import org.example.Receiver;

import java.io.File;
import java.util.Scanner;

/**
 * Класс ExecuteScriptCommand реализует команду для выполнения скрипта из файла.
 * <p>
 * Команда принимает один аргумент — имя файла со скриптом. Скрипт содержит
 * последовательность команд, которые выполняются построчно.
 * </p>
 */
public class ExecuteScriptCommand implements Command {
    private final Receiver receiver = Receiver.getInstance(); // Экземпляр Receiver для выполнения команд.

    private final Invoker invoker; // Экземпляр Invoker для вызова команд.

    /**
     * Создает новый объект ExecuteScriptCommand.
     *
     * @param invoker экземпляр Invoker для вызова команд
     */
    public ExecuteScriptCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    /**
     * Выполняет команду для выполнения скрипта из файла.
     * <p>
     * Команда принимает один аргумент — имя файла со скриптом. Скрипт содержит
     * последовательность команд, которые выполняются построчно.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (имя файла со скриптом)
     */
    @Override
    public void execute(Scanner in, String... args) {
        if (args.length < 1) {
            System.err.println("Mistake: the script file name is not specified");
            return;
        }

        String fileName = args[0];
        File scriptFile = new File(fileName);

        // Проверяем, существует ли файл
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.err.println("Error file " + fileName +  " not found: ");
            return;
        }

        try (Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                String commandLine = fileScanner.nextLine().trim();

                // Пропускаем пустые строки и комментарии
                if (commandLine.isEmpty() || commandLine.startsWith("#")) {
                    continue;
                }

                System.out.println("executing command  " + commandLine);

                // Выполняем команду
                invoker.invoke(commandLine);
            }
        } catch (Exception e) {
            System.err.println("Error while executing script " + e.getMessage());
        }
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды ("execute_script")
     */
    @Override
    public String getName() {
        return "execute_script";
    }
}