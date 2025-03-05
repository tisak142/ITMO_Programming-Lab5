package commands;

import org.example.Receiver;

import java.util.Scanner;

public class ClearCommand implements Command {
    Receiver receiver = Receiver.getInstance();
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public void execute(Scanner in, String... args){
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        receiver.clear();
        System.out.println("Collection succesfully cleaned");
    }
}
