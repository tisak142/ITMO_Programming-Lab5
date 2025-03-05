package commands;

import org.example.Receiver;

import java.util.Scanner;

public class MaxByGenreCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public String getName() {
        return "max_by_genre";
    }

    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        receiver.maxByGenre();
    }
}
