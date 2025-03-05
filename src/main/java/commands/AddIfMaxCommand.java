package commands;

import classes.MusicBand;
import org.example.Receiver;

import java.util.Scanner;

public class AddIfMaxCommand implements Command {
    private final Receiver receiver = Receiver.getInstance();


    @Override
    public void execute(Scanner in, String... args){
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        MusicBand band = receiver.createMusicBand(scanner);
        receiver.addIfMax(band);
    }

    @Override
    public String getName() {
        return "";
    }
}
