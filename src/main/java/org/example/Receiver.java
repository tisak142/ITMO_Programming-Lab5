package org.example;

import classes.*;
import xml.DOMReader;
import xml.DOMWriter;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Класс Receiver отвечает за управление коллекцией объектов MusicBand.
 * <p>
 * Этот класс предоставляет методы для выполнения операций с коллекцией,
 * таких как добавление, удаление, обновление, поиск и другие.
 * Также он загружает данные из файла и сохраняет их обратно.
 * </p>
 */
public class Receiver {
    private final ArrayList<MusicBand> bands = new ArrayList<>(); // Коллекция для хранения объектов MusicBand.
    private static ArrayList<String> scriptsHistory = new ArrayList<>();
    private static Receiver instance; // Единственный экземпляр класса Receiver (реализация паттерна Singleton).

    private final LocalDate date; // Дата инициализации коллекции.

    {
        date = LocalDate.now(); // Устанавливаем текущую дату при создании объекта.
    }

    /**
     * Приватный конструктор для реализации паттерна Singleton.
     */
    private Receiver() {
    }

    /**
     * Перемешивает элементы коллекции в случайном порядке.
     */
    public void shuffle() {
        Collections.shuffle(bands);
    }

    /**
     * Обновляет элемент коллекции по-указанному id.
     *
     * @param idCounter id элемента, который нужно обновить
     * @param band новый объект MusicBand для замены
     */
    public void updateId(int idCounter, MusicBand band) {
        for (MusicBand b : bands) {
            if (b.getId() == idCounter) {
                int index = bands.indexOf(b);
                band.setId(idCounter);
                bands.set(index, band);
            }
        }
    }

    /**
     * Очищает коллекцию, удаляя все элементы.
     */
    public void clear() {
        bands.clear();
    }

    /**
     * Проверяет, существует ли элемент с указанным id в коллекции.
     *
     * @param id id элемента для проверки
     * @return true, если элемент существует, иначе false
     */
    public boolean containsId(int id) {
        for (MusicBand band : bands) {
            if (band.getId() == id) {
                return true;
            }
        }
        return false;
    }


    public void executeScript(Scanner in, String fileName) {
        File scriptFile = new File(fileName);
        // Проверяем, существует ли файл
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.err.println("Error file " + fileName +  " not found: ");
            return;
        } else if (scriptsHistory.contains(fileName)) {
            System.err.println("Error file " + fileName +  " is already executing");
            return;
        }
        scriptsHistory.add(fileName);
        try (Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                String commandLine = fileScanner.nextLine().trim();
                // Пропускаем пустые строки и комментарии
                if (commandLine.isEmpty() || commandLine.startsWith("#")) {
                    continue;
                }
                System.out.println("executing command  " + commandLine);
                // Выполняем команду
                new Invoker(in).invoke(commandLine);
            }
        } catch (Exception e) {
            System.err.println("Error while executing script " + e.getMessage());
        }
    }


    /**
     * Добавляет элемент в коллекцию, если его значение превышает значение наибольшего элемента.
     *
     * @param band объект MusicBand для добавления
     */
    public void addIfMax(MusicBand band) {
        boolean flag = true;
        for (MusicBand b : bands) {
            if (band.compareTo(b) <= 0) {
                flag = false;
                System.out.println("The band " + band.getName() + " wasn't added to collection");
                break;
            }
        }
        if (flag) {
            bands.add(band);
            System.out.println("Added Music Band - " + band.getName());
        }
    }

    /**
     * Добавляет элемент в коллекцию, если его значение меньше значения наименьшего элемента.
     *
     * @param band объект MusicBand для добавления
     */
    public void addIfMin(MusicBand band) {
        boolean flag = true;
        for (MusicBand b : bands) {
            if (band.compareTo(b) >= 0) {
                flag = false;
                System.out.println("The band " + band.getName() + " wasn't added to collection");
                break;
            }
        }
        if (flag) {
            bands.add(band);
            System.out.println("Added Music Band - " + band.getName());
        }
    }

    /**
     * Загружает данные из файла в коллекцию.
     */
    private void loadFromFile() {
        String fileName = System.getenv("FILE_NAME_READ");
        if (fileName == null) {
            System.out.println("Please set the environment variable FILE_NAME_READ");
            return;
        }

        ArrayList<MusicBand> loadedBands = DOMReader.readFromFile(fileName);
        if (loadedBands == null) {
            System.err.println("Could not read the file " + fileName);
        } else {
            bands.addAll(loadedBands);
            System.out.println("Successfully loaded " + loadedBands.size() + " music-band objects");
        }
    }

    /**
     * Добавляет объект MusicBand в коллекцию.
     *
     * @param band объект MusicBand для добавления
     */
    public void add(MusicBand band) {
        bands.add(band);
    }

    /**
     * Возвращает дату инициализации коллекции.
     *
     * @return дата инициализации коллекции
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Выводит все элементы коллекции в строковом представлении.
     */
    public void showList() {
        for (MusicBand band : bands) {
            System.out.println(band);
        }
        if (bands.isEmpty()) {
            System.out.println("The collection is empty");
        }
    }

    /**
     * Возвращает тип коллекции.
     *
     * @return тип коллекции
     */
    public String getType() {
        return bands.getClass().getSimpleName();
    }

    /**
     * Возвращает количество элементов в коллекции.
     *
     * @return количество элементов в коллекции
     */
    public int getLength() {
        return bands.size();
    }

    /**
     * Возвращает единственный экземпляр класса Receiver (реализация паттерна Singleton).
     *
     * @return экземпляр Receiver
     */
    public static Receiver getInstance() {
        if (instance == null) {
            instance = new Receiver();
            instance.loadFromFile();
        }
        return instance;
    }

    /**
     * Сохраняет коллекцию в файл.
     */
    public void save() {
        String fileName = System.getenv("FILE_NAME_WRITE");
        if (fileName == null) {
            System.out.println("Error to set the environment variable FILE_NAME_WRITE.");
            return;
        }
        DOMWriter.writeToFile(fileName, bands);
    }

    /**
     * Создает объект MusicBand на основе введенных пользователем данных.
     *
     * @param scanner сканер для ввода данных
     * @return новый объект MusicBand
     */
    public MusicBand createMusicBand(Scanner scanner) {
        return new MusicBandBuilder()
                .setName(readName(scanner))
                .setCoordinates(readCoordinates(scanner))
                .setCreationDate() // Дата создания генерируется автоматически
                .setNumberOfParticipants(readNumberOfParticipants(scanner))
                .setGenre(readGenre(scanner))
                .setFrontMan(readFrontMan(scanner))
                .build();
    }

    /**
     * Возвращает среднее значение поля numberOfParticipants для всех элементов коллекции.
     *
     * @return среднее значение numberOfParticipants
     */
    public float getAverageNumber() {
        float counter = 0;
        for (MusicBand band : bands) {
            counter += band.getNumberOfParticipants();
        }
        return counter / bands.size();
    }

    /**
     * Проверяет, что указанная дата не позже текущей даты.
     *
     * @param date дата для проверки
     * @return true, если дата не позже текущей, иначе false
     */
    public static boolean isDateNotLaterThanToday(Date date) {
        if (date == null) {
            return true; // Или false, если null недопустимо
        }
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return !inputDate.isAfter(today);
    }

    /**
     * Выводит элемент коллекции с максимальным значением поля genre.
     */
    public void maxByGenre() {
        MusicBand band = bands.get(0);
        MusicGenre genre = band.getGenre();
        for (MusicBand b : bands) {
            if (b.getGenre().toString().compareTo(genre.toString()) > 0) {
                band = b;
                genre = b.getGenre();
            }
        }
        System.out.println(band);
    }

    /**
     * Группирует элементы коллекции по значению поля name и возвращает количество элементов в каждой группе.
     *
     * @return HashMap, где ключ — имя группы, значение — количество элементов
     */
    public HashMap<String, Integer> countByName() {
        HashMap<String, Integer> map = new HashMap<>();
        for (MusicBand band : bands) {
            map.put(band.getName(), map.getOrDefault(band.getName(), 0) + 1);
        }
        return map;
    }

    /**
     * Читает название группы из ввода пользователя.
     *
     * @param scanner сканер для ввода данных
     * @return название группы
     */
    private String readName(Scanner scanner) {
        while (true) {
            System.out.print("Type name of the band: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Error, the group must have at least one word in name, please try again.");
            }
        }
    }

    /**
     * Читает координаты из ввода пользователя.
     *
     * @param scanner сканер для ввода данных
     * @return объект Coordinates
     */
    private Coordinates readCoordinates(Scanner scanner) {
        Double x = null;
        while (x == null) {
            System.out.print("enter the X coordinate: ");
            try {
                x = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено некорректное число. Пожалуйста, повторите ввод.");
            }
        }

        long y = 1000;
        while (y > 860) {
            System.out.print("enter the Y coordinate (max value is 860): ");
            try {
                y = Long.parseLong(scanner.nextLine().trim());
                if (y > 860) {
                    System.out.println("Error: the value must be greater than 0 and not exceed 860. Please,repeat the input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }

        return new Coordinates(x, y);
    }

    /**
     * Читает количество участников из ввода пользователя.
     *
     * @param scanner сканер для ввода данных
     * @return количество участников
     */
    private Long readNumberOfParticipants(Scanner scanner) {
        Long numberOfParticipants = null;
        while (numberOfParticipants == null || numberOfParticipants <= 0) {
            System.out.print("Enter the number of participants (or leave it blank): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                numberOfParticipants = Long.parseLong(input);
                if (numberOfParticipants <= 0) {
                    System.out.println("Error: The number of participants must be greater than 0. Please repeat the input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }
        return numberOfParticipants;
    }

    /**
     * Удаляет элемент коллекции по указанному id.
     *
     * @param bandId id элемента для удаления
     */
    public void removeBand(int bandId) {
        Iterator<MusicBand> iterator = bands.iterator();
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.getId() == bandId) {
                iterator.remove(); // Безопасное удаление
            }
        }
    }

    /**
     * Читает музыкальный жанр из ввода пользователя.
     *
     * @param scanner сканер для ввода данных
     * @return объект MusicGenre
     */
    private MusicGenre readGenre(Scanner scanner) {
        MusicGenre genre = null;
        while (genre == null) {
            System.out.println("Available genres: " + Arrays.toString(MusicGenre.values()));
            System.out.print("Enter the genre (or leave it blank): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                genre = MusicGenre.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect number entered. Please,repeat the input.");
            }
        }
        return genre;
    }

    /**
     * Читает данные о фронтмене из ввода пользователя.
     *
     * @param scanner сканер для ввода данных
     * @return объект Person
     */
    private Person readFrontMan(Scanner scanner) {
        String name = null;
        while (name == null || name.isEmpty()) {
            System.out.print("Enter the name of the Frontman: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: The name cannot be empty. Please repeat the input.");
            }
        }

        java.util.Date birthday = null;
        while (true) {
            System.out.print("Enter the date of birth (YYYY-MM-DD, or leave it blank): ");
            String birthdayInput = scanner.nextLine().trim();
            if (birthdayInput.isEmpty()) {
                break; // Если строка пустая, оставляем birthday = null
            }
            try {
                LocalDate localDate = LocalDate.parse(birthdayInput);
                birthday = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (!isDateNotLaterThanToday(birthday)) {
                    System.out.println("Mistake: The date of birth cannot be later than today.");
                    birthday = null; // Сбрасываем значение, чтобы попросить ввод заново
                    continue;  // Возвращаемся к началу цикла (переходим к следующей итерации)
                }
                break; // Если парсинг успешен, выходим из цикла
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: An incorrect date was entered. Please use the YYYY-MM-DD format.");
            }
        }

        Color hairColor = null;
        while (hairColor == null) {
            System.out.println("Available colors of hair " + Arrays.toString(Color.values()));
            System.out.print("Enter the color of hair: ");
            String hairColorInput = scanner.nextLine().trim();
            try {
                hairColor = Color.valueOf(hairColorInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect value entered. Please repeat the input.");
            }
        }

        Country nationality = null;
        while (true) {
            System.out.println("Available nationalities: " + Arrays.toString(Country.values()));
            System.out.print("Enter the nationality (or leave it blank): ");
            String nationalityInput = scanner.nextLine().trim();
            if (nationalityInput.isEmpty()) {
                break;
            }
            try {
                nationality = Country.valueOf(nationalityInput.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: incorrect value entered. Please repeat the input.");
            }
        }

        return new Person(name, birthday, hairColor, nationality);
    }

    /**
     * Генерирует уникальный идентификатор на основе текущего времени.
     *
     * @return уникальный идентификатор
     */
    private long generateId() {
        return System.currentTimeMillis(); // Генерация уникального ID
    }
}