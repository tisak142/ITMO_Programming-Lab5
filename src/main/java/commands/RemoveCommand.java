package commands;

import org.example.Receiver;

import java.util.Scanner;

public class RemoveCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public void execute(Scanner in, String... args) {
        try {
            int id = Integer.parseInt(args[0]);
            if (!receiver.containsId(id)) {
                System.out.println("Error: the specified id does not exist.");
                return; // Прекращаем выполнение команды
            }
            receiver.removeBand(id);
            System.out.println("Band with " + id + " successfully removed");
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid id entered. It should be an integer");
        }
    }
}
