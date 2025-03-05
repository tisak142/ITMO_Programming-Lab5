package commands;

import classes.*;
import org.example.Receiver;

import java.util.Scanner;

public class AddCommand implements Command {
    private final Receiver receiver = Receiver.getInstance();



    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        MusicBand band = receiver.createMusicBand(in);
        receiver.add(band);
        System.out.println("Added Music Band - " + band.getName());
    }
}
