package commands;

import classes.MusicBand;
import org.example.Receiver;

import java.util.Scanner;

public class AddIfMinCommand implements Command {
    Receiver receiver = Receiver.getInstance();


    @Override
    public void execute(Scanner in, String... args){
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        MusicBand band = receiver.createMusicBand(scanner);
        receiver.addIfMin(band);
    }

    @Override
    public String getName() {
        return "";
    }
}
