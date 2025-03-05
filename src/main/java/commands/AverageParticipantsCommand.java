package commands;

import org.example.Receiver;

import java.util.Scanner;

public class AverageParticipantsCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public String getName() {
        return "AverageParticipantsCommand";
    }

    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        System.out.println("Average number of participants in groups is " + receiver.getAverageNumber());
    }
}
