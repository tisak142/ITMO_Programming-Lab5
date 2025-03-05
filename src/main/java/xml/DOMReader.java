package xml;

import classes.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class DOMReader {

    public static ArrayList<MusicBand> readFromFile(String filename) {
        ArrayList<MusicBand> musicBands = new ArrayList<>();
        try {
            File file = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodelist = document.getElementsByTagName("musicBand");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    MusicBand band = parseMusicBand(element);
                    musicBands.add(band);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicBands;
    }

    private static MusicBand parseMusicBand(Element element) {
        long id = Long.parseLong(getElementValue(element, "id"));
        String name = getElementValue(element, "name");

        // Парсинг Coordinates
        Element coordinatesElement = (Element) element.getElementsByTagName("coordinates").item(0);
        double x = Double.parseDouble(getElementValue(coordinatesElement, "x"));
        long y = Long.parseLong(getElementValue(coordinatesElement, "y"));
        Coordinates coordinates = new Coordinates(x, y);

        // Парсинг numberOfParticipants
        Long numberOfParticipants = null;
        if (element.getElementsByTagName("numberOfParticipants").getLength() > 0) {
            numberOfParticipants = Long.parseLong(getElementValue(element, "numberOfParticipants"));
        }

        // Парсинг MusicGenre
        MusicGenre genre = null;
        if (element.getElementsByTagName("genre").getLength() > 0) {
            genre = MusicGenre.valueOf(getElementValue(element, "genre"));
        }

        // Парсинг Person
        Element frontManElement = (Element) element.getElementsByTagName("frontMan").item(0);
        String frontManName = getElementValue(frontManElement, "name");
        String birthdayString = getElementValue(frontManElement, "birthday");
        java.util.Date birthday = null;
        if (birthdayString != null && !birthdayString.isEmpty()) {
            birthday = java.sql.Date.valueOf(birthdayString); // Пример для простоты
        }
        Color hairColor = Color.valueOf(getElementValue(frontManElement, "hairColor"));
        Country nationality = null;
        if (frontManElement.getElementsByTagName("nationality").getLength() > 0) {
            nationality = Country.valueOf(getElementValue(frontManElement, "nationality"));
        }
        Person frontMan = new Person(frontManName, birthday, hairColor, nationality);

        // Создание объекта MusicBand
        return new MusicBand(id, name, coordinates, numberOfParticipants, genre, frontMan);
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}
