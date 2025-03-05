package commands;

import org.example.Receiver;

import java.util.Scanner;

public class ShuffleCommand implements Command {
    Receiver receiver = Receiver.getInstance();
    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        receiver.shuffle();
        System.out.println("Collection has been shuffled");
    }

    @Override
    public String getName() {
        return "";
    }
}
