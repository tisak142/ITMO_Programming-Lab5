package commands;

import classes.MusicBand;
import org.example.Receiver;

import java.util.Scanner;

public class UpdateIdCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public void execute(Scanner in, String... args){
        try {
            int id = Integer.parseInt(args[0]);
            if (!receiver.containsId(id)) {
                System.out.println("Error: element with id " + id + " does not exist in collection.");
                return; // Прекращаем выполнение команды
            }
            Scanner scanner = new Scanner(System.in);
            MusicBand band = receiver.createMusicBand(scanner);
            receiver.updateId(id, band);
            System.out.println("The element with id " + id + " has succesfully updated.");
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid id entered. It should be an integer");
        }
    }

    @Override
    public String getName() {
        return "updateId";
    }
}
