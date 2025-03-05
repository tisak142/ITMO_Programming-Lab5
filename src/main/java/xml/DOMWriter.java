package xml;

import classes.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class DOMWriter {

    public static void writeToFile(String filename, ArrayList<MusicBand> musicBands) {
        try {
            // Создаем новый XML-документ
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Создаем корневой элемент
            Element rootElement = document.createElement("musicBands");
            document.appendChild(rootElement);

            // Добавляем каждый элемент коллекции в XML
            for (MusicBand band : musicBands) {
                Element bandElement = createMusicBandElement(document, band);
                rootElement.appendChild(bandElement);
            }

            // Записываем документ в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Форматирование с отступами
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Размер отступа

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("The collection has been successfully saved to a file: " + filename);
        } catch (Exception e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    private static Element createMusicBandElement(Document document, MusicBand band) {
        // Создаем элемент для MusicBand
        Element bandElement = document.createElement("musicBand");

        // Добавляем id
        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(String.valueOf(band.getId())));
        bandElement.appendChild(idElement);

        // Добавляем name
        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(band.getName()));
        bandElement.appendChild(nameElement);

        // Добавляем coordinates
        Element coordinatesElement = document.createElement("coordinates");
        Element xElement = document.createElement("x");
        xElement.appendChild(document.createTextNode(String.valueOf(band.getCoordinates().getX())));
        coordinatesElement.appendChild(xElement);

        Element yElement = document.createElement("y");
        yElement.appendChild(document.createTextNode(String.valueOf(band.getCoordinates().getY())));
        coordinatesElement.appendChild(yElement);

        bandElement.appendChild(coordinatesElement);

        // Добавляем numberOfParticipants (если не null)
        if (band.getNumberOfParticipants() != null) {
            Element numberOfParticipantsElement = document.createElement("numberOfParticipants");
            numberOfParticipantsElement.appendChild(document.createTextNode(String.valueOf(band.getNumberOfParticipants())));
            bandElement.appendChild(numberOfParticipantsElement);
        }

        // Добавляем genre (если не null)
        if (band.getGenre() != null) {
            Element genreElement = document.createElement("genre");
            genreElement.appendChild(document.createTextNode(band.getGenre().name()));
            bandElement.appendChild(genreElement);
        }

        // Добавляем frontMan
        Element frontManElement = document.createElement("frontMan");
        Element frontManNameElement = document.createElement("name");
        frontManNameElement.appendChild(document.createTextNode(band.getFrontMan().getName()));
        frontManElement.appendChild(frontManNameElement);

        // Добавляем birthday (если не null)
        if (band.getFrontMan().getBirthday() != null) {
            Element birthdayElement = document.createElement("birthday");
            birthdayElement.appendChild(document.createTextNode(band.getFrontMan().getBirthday().toString()));
            frontManElement.appendChild(birthdayElement);
        }

        // Добавляем hairColor
        Element hairColorElement = document.createElement("hairColor");
        hairColorElement.appendChild(document.createTextNode(band.getFrontMan().getHairColor().name()));
        frontManElement.appendChild(hairColorElement);

        // Добавляем nationality (если не null)
        if (band.getFrontMan().getNationality() != null) {
            Element nationalityElement = document.createElement("nationality");
            nationalityElement.appendChild(document.createTextNode(band.getFrontMan().getNationality().name()));
            frontManElement.appendChild(nationalityElement);
        }

        bandElement.appendChild(frontManElement);

        return bandElement;
    }
}