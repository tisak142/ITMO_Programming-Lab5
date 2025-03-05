package org.example;

import commands.*;

import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    private static final HashMap<String, Command> commands = new HashMap<>();
    private final Scanner in;

    {
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("add", new AddCommand());
        commands.put("show", new ShowCommand());
        commands.put("update id", new UpdateIdCommand());
        commands.put("remove_by_id", new RemoveCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("shuffle", new ShuffleCommand());
        commands.put("average_of_number_of_participants", new AverageParticipantsCommand());
        commands.put("max_by_genre", new MaxByGenreCommand());
        commands.put("group_counting_by_name", new CountingByNameCommand());
    }

    public Invoker(Scanner in) {
        this.in = in;
    }

    public void invoke(String line) {
        String[] args = line.trim().split("\\s+");
        if (args.length == 0) {
            System.out.println("Error: the command is not specified.");
            return;
        }

        Command command = null;
        String[] commandArgs = new String[0];

        // Определяем команду и аргументы
        if (args.length == 1) {
            // Команда без аргументов
            command = commands.get(args[0]);
        } else if (args.length == 2) {
            // Команда с одним аргументом
            command = commands.get(args[0]);
            if (command == null) {
                // Проверяем, может быть это команда из двух слов (например, "update id")
                command = commands.get(args[0] + " " + args[1]);
                if (command != null) {
                    System.out.println("Error: please enter ai id");
                    return;
                }
            } else {
                commandArgs = new String[]{args[1]};
            }
        } else if (args.length == 3) {
            // Команда из двух слов (например, "update id") с аргументом
            command = commands.get(args[0] + " " + args[1]);
            if (command != null) {
                commandArgs = new String[]{args[2]};
            }
        }

        // Если команда не найдена
        if (command == null) {
            System.out.println("Error: unknown command. Enter 'help' for help.");
            return;
        }

        // Выполняем команду
        try {
            if (commandArgs.length == 0) {
                command.execute(in);
            } else {
                command.execute(in, commandArgs);
            }
        } catch (Exception e) {
            System.out.println("error when executing the command " + e.getMessage());
        }
    }
}
