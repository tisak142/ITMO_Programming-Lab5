package commands;

import java.util.Scanner;

public class ExitCommand implements Command {

    @Override
    public void execute(Scanner in, String... args){
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "";
    }
}
