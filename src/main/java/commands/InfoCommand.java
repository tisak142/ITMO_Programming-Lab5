package commands;

import org.example.Receiver;

import java.util.Scanner;

public class InfoCommand implements Command {
    private final Receiver receiver = Receiver.getInstance();
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void execute(Scanner in, String... args){
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        String type = "Тип коллекции: " + receiver.getType();
        String length = "Размер коллекции: " + receiver.getLength();
        String time = "Дата инициализации коллекции: " + receiver.getDate();
        System.out.println(type);
        System.out.println(length);
        System.out.println(time);
    }
}
