package commands;

import org.example.Invoker;
import org.example.Receiver;

import java.io.File;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private final Receiver receiver = Receiver.getInstance();
    private final Invoker invoker;

    public ExecuteScriptCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void execute(Scanner in, String... args) {
        if (args.length < 1) {
            System.out.println("Ошибка: не указано имя файла скрипта.");
            return;
        }

        String fileName = args[0];
        File scriptFile = new File(fileName);

        // Проверяем, существует ли файл
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Error file " + fileName +  " not found: ");
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
            System.out.println("Error while executing script " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}