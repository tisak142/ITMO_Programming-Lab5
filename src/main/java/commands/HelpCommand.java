package commands;

import java.util.Scanner;

/**
 * Класс HelpCommand реализует команду для вывода справки по доступным командам.
 * <p>
 * Команда не принимает аргументов. Выводит список всех доступных команд
 * с их кратким описанием.
 * </p>
 */
public class HelpCommand implements Command {

    /**
     * Выполняет команду для вывода справки по доступным командам.
     * <p>
     * Команда не принимает аргументов. Выводит список всех доступных команд
     * с их кратким описанием.
     * </p>
     *
     * @param in сканер для ввода данных от пользователя (не используется)
     * @param args аргументы команды (не используются)
     */
    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        System.out.println("""
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                shuffle : перемешать элементы коллекции в случайном порядке
                average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех элементов коллекции
                max_by_genre : вывести любой объект из коллекции, значение поля genre которого является максимальным
                group_counting_by_name : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе
                """);
    }

    /**
     * Возвращает название команды.
     *
     * @return название команды ("help")
     */
    @Override
    public String getName() {
        return "help";
    }
}