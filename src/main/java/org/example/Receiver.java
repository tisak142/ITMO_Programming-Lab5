package org.example;

import classes.*;
import xml.DOMReader;
import xml.DOMWriter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Receiver {
    private final ArrayList<MusicBand> bands = new ArrayList<>();
    private static Receiver instance;
    private final LocalDate date;

    {date = LocalDate.now();
    }


    private Receiver() {
    }

    public void shuffle() {
        Collections.shuffle(bands);
    }

    public void updateId(int idCounter, MusicBand band) {
        for (MusicBand b : bands) {
            if (b.getId() == idCounter) {
                int index = bands.indexOf(b);
                band.setId(idCounter);
                bands.set(index, band);
            }
        }
    }

    public void clear() {
        bands.clear();
    }

    public boolean containsId(int id) {
        for (MusicBand band : bands) {
            if (band.getId() == id) {
                return true;
            }
        }
        return false;
    }

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

    private void loadFromFile() {
        String fileName = System.getenv("FILE_NAME_READ");
        if (fileName == null) {
            System.out.println("Please set the environment variable FILE_NAME_READ");
            return;
        }

        ArrayList<MusicBand> loadedBands = DOMReader.readFromFile(fileName);
        bands.addAll(loadedBands);
        System.out.println("Successfully loaded " + loadedBands.size() + " music-band objects");
    }

    public void add(MusicBand band) {
        bands.add(band);
    }

    public LocalDate getDate() {
        return date;
    }

    public void showList() {
        for (MusicBand band : bands) {
            System.out.println(band);
        }
    }

    public String getType() {
        return bands.getClass().getSimpleName();
    }

    public int getLength() {
        return bands.size();
    }

    public static Receiver getInstance() {
        if (instance == null) {
            instance = new Receiver();
            instance.loadFromFile();
        }
        return instance;
    }

    public void save() {
        String fileName = System.getenv("FILE_NAME_WRITE");
        if (fileName == null) {
            System.out.println("Error to set the environment variable FILE_NAME_WRITE.");
            return;
        }
        DOMWriter.writeToFile(fileName, bands);
    }

    public MusicBand createMusicBand(Scanner scanner) {
        return new MusicBandBuilder()
                .setId(generateId()) // Генерация уникального ID
                .setName(readName(scanner))
                .setCoordinates(readCoordinates(scanner))
                .setCreationDate() // Дата создания генерируется автоматически
                .setNumberOfParticipants(readNumberOfParticipants(scanner))
                .setGenre(readGenre(scanner))
                .setFrontMan(readFrontMan(scanner))
                .build();
    }

    public float getAverageNumber() {
        float counter = 0;
        for (MusicBand band : bands) {
            counter += band.getNumberOfParticipants();
        }
        return counter / bands.size();
    }

    public static boolean isDateNotLaterThanToday(Date date) {
        if (date == null) {
            return true; // Или false, если null недопустимо
        }
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return !inputDate.isAfter(today);
    }

    public void maxByGenre() {
        MusicBand band = bands.getFirst();
        MusicGenre genre = band.getGenre();
        for (MusicBand b : bands) {
            if (b.getGenre().compareTo(genre) > 0) {
                band = b;
                genre = b.getGenre();
            }
        }
        System.out.println(band);
    }

    public HashMap<String, Integer> countByName() {
        HashMap<String, Integer> map = new HashMap<>();
        for (MusicBand band : bands) {
            map.put(band.getName(), map.getOrDefault(band.getName(), 0) + 1);
        }
        return map;
    }

    // Вспомогательные методы для ввода данных
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

    public void removeBand(int bandId) {
        Iterator<MusicBand> iterator = bands.iterator();
        while (iterator.hasNext()) {
            MusicBand band = iterator.next();
            if (band.getId() == bandId) {
                iterator.remove(); // Безопасное удаление
            }
        }
    }

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

    private long generateId() {
        return System.currentTimeMillis(); // Генерация уникального ID
    }
}
