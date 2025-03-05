package commands;

import org.example.Receiver;

import java.util.Scanner;

public class ShowCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public void execute(Scanner in, String... args){
        System.out.println("Information about collection:");
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        receiver.showList();
    }
}
