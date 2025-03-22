package xml;

import classes.*;
import org.example.Receiver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

import static org.example.Receiver.isDateNotLaterThanToday;

public class DOMReaderScript {
    private static Receiver receiver = Receiver.getInstance();


    public static MusicBand parseScriptAddCommand(String filePath) {
        StringBuilder xmlContent = new StringBuilder();
        boolean musicBandFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim(); // Убираем пробелы в начале и конце строки

                if (trimmedLine.indexOf("<musicBand") == 0) {
                    musicBandFound = true;
                    xmlContent.append(line).append("\n");
                } else if (musicBandFound) {
                    xmlContent.append(line).append("\n");
                    if (trimmedLine.indexOf("</musicBand") == 0) {
                        break; // Прекращаем чтение после закрывающего тега </musicBand>
                    }
                }
            }

            if (!musicBandFound) {
                System.out.println("<musicBand> start tag not found.");
                return null;
            }

            if (xmlContent.length() == 0) {
                System.out.println("No musicBand XML content found.");
                return null;
            }

            // Парсим XML из строки
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xmlContent.toString())));
            doc.getDocumentElement().normalize();

            Element band = (Element) doc.getElementsByTagName("musicBand").item(0);
            MusicBand parsedBand = parseMusicBand(band);
            if (parsedBand == null) {
                return null;
            } else {
                return parsedBand;
            }

        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
            return null;
        } catch (javax.xml.parsers.ParserConfigurationException e) {
            System.err.println("Parser Configuration Exception: " + e.getMessage());
            return null;
        } catch (org.xml.sax.SAXException e) {
            System.err.println("SAX Exception (XML parsing error): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    private static MusicBand parseMusicBand(Element element) {
        // Парсинг названия группы
        String name = getElementValue(element, "name");
        if (name == null) {
            System.err.println("Error when loading the musicBands to collection. Invalid name. It can't be null");
            return null;
        }
        if (name.trim().isEmpty()) {
            System.err.println("Error when loading the musicBands to collection. Invalid name of band. It can't be empty");
            return null;
        }

        // Парсинг координат
        Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
        String coordinateX = getElementValue(coordinatesElement, "x");
        String coordinateY = getElementValue(coordinatesElement, "y");
        Coordinates coordinates = null;
        if (coordinateX == null || coordinateY == null) {
            System.err.println("Error when loading the coordinates. Invalid coordinates. It can't be null");
            return null;
        } else {
            if (coordinateX.trim().isEmpty() || coordinateY.trim().isEmpty()) {
                System.err.println("Error when loading the musicBands to collection. Invalid coordinates. It can't be empty");
                return null;
            } else {
                try {
                    if (Integer.parseInt(coordinateY) > 860) {
                        System.err.println("Error when loading the musicBands to collection. Invalid coordinates. Y can't be greater than 860");
                        return null;
                    } else {
                        double x = Double.parseDouble(coordinateX);
                        long y = Long.parseLong(coordinateY);
                        coordinates = new Coordinates(x, y);
                    }
                }  catch (NumberFormatException e) {
                    System.err.println("Error when loading the musicBands to collection. Invalid coordinates. It has to be a number");
                    return null;
                }
            }
        }

        // Парсинг количества участников
        Long numberOfParticipants = null;
        String numbOfPart = getElementValue(element, "numberOfParticipants");
        if (numbOfPart != null && !numbOfPart.trim().isEmpty()) {
            try {
                numberOfParticipants = Long.parseLong(numbOfPart);
            } catch (NumberFormatException e) {
                System.err.println("Error when loading the number. field 'numberOfParticipants' is invalid. It has to be number'");
                return null;
            }
        }

        // Парсинг музыкального жанра
        MusicGenre genre = null;
        String testGenre = getElementValue(element, "genre");
        Set<String> genres = Set.of("PROGRESSIVE_ROCK", "JAZZ", "BLUES", "PUNK_ROCK");
        if (testGenre != null && !testGenre.isEmpty()) {
            if (!genres.contains(testGenre)) {
                System.err.println("Error when loading the musicBands to collection. Invalid genre. It has to be one of PROGRESSIVE_ROCK, JAZZ, BLUES, PUNK_ROCK");
                return null;
            } else {
                genre = MusicGenre.valueOf(testGenre);
            }
        }

        // Парсинг данных о фронтмене
        Element frontManElement = (Element) element.getElementsByTagName("frontMan").item(0);
        // Парсинг имени фронтмена
        String frontManName = getElementValue(frontManElement, "name");
        if (frontManName == null) {
            System.err.println("Error when loading the musicBands to collection. Invalid name of person. It can't be null");
            return null;
        } else {
            if (frontManName.trim().isEmpty()) {
                System.err.println("Error when loading the musicBands to collection. Invalid name of person. It can't be empty");
                return null;
            } else {
                frontManName = frontManName.trim();
            }
        }

        // Парсинг цвета волос
        Color hairColor = null;
        String testHairColor = getElementValue(frontManElement, "hairColor");
        Set<String> colors = Set.of("GREEN", "BLUE", "ORANGE", "WHITE");
        if (testHairColor == null || testHairColor.isEmpty()) {
            System.err.println("Error when loading the musicBands to collection. Color of hair cannot be null or empty");
            return null;
        } else {
            if (!colors.contains(testHairColor)) {
                System.err.println("Error when loading the musicBands to collection. Invalid color of hair. It has to be one of GREEN, BLUE, ORANGE, WHITE");
                return null;
            } else {
                hairColor = Color.valueOf(testHairColor);
            }
        }

        // Парсинг даты рождения
        java.util.Date birthday = null;
        String birthdayString = getElementValue(frontManElement, "birthday");
        if (birthdayString != null && !birthdayString.isEmpty()) {
            try {
                LocalDate localDate = LocalDate.parse(birthdayString);
                birthday = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (!isDateNotLaterThanToday(birthday)) {
                    System.err.println("Error when loading the musicBands to collection. An incorrect date was entered. The date of birth cannot be later than today.");
                    return null;
                }
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error when loading the musicBands to collection. An incorrect date was entered. Please use the YYYY-MM-DD format.");
                return null;
            }
        }

        // Парсинг национальности
        Country nationality = null;
        String testNationality = getElementValue(frontManElement, "nationality");
        Set<String> countries = Set.of("SPAIN", "CHINA", "VATICAN", "NORTH_COREA");
        if (testNationality != null && !testNationality.isEmpty()) {
            if (!countries.contains(testNationality)) {
                System.err.println("Error when loading the musicBands to collection. Invalid nationality. It has to be one of SPAIN, CHINA, VATICAN, NORTH_COREA");
                return null;
            } else {
                nationality = Country.valueOf(testNationality);
            }
        }

        // Создание объекта Person
        Person frontMan = new Person(frontManName, birthday, hairColor, nationality);

        // Создание объекта MusicBand
        return new MusicBand(name, coordinates, numberOfParticipants, genre, frontMan);
    }
    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}
