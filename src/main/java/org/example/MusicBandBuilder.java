package org.example;

import classes.Coordinates;
import classes.MusicBand;
import classes.MusicGenre;
import classes.Person;

import java.time.ZonedDateTime;

/**
 * Класс MusicBandBuilder реализует паттерн "Строитель" для создания объектов класса MusicBand.
 * <p>
 * Позволяет поэтапно устанавливать значения полей объекта MusicBand и затем создавать его.
 * </p>
 */
public class MusicBandBuilder {
    private long id; // Уникальный идентификатор музыкальной группы.

    private String name; // Название музыкальной группы.

    private Coordinates coordinates; // Координаты музыкальной группы.

    private ZonedDateTime creationDate; // Дата создания музыкальной группы.

    private Long numberOfParticipants; // Количество участников музыкальной группы.

    private MusicGenre genre; // Музыкальный жанр группы.

    private Person frontMan; // Фронтмен группы.

    /**
     * Устанавливает название музыкальной группы.
     *
     * @param name название группы
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Устанавливает координаты музыкальной группы.
     *
     * @param coordinates координаты группы
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    /**
     * Устанавливает дату создания музыкальной группы на текущее время.
     *
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setCreationDate() {
        this.creationDate = ZonedDateTime.now();
        return this;
    }

    /**
     * Устанавливает количество участников музыкальной группы.
     *
     * @param numberOfParticipants количество участников
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
        return this;
    }

    /**
     * Устанавливает музыкальный жанр группы.
     *
     * @param genre музыкальный жанр
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    /**
     * Устанавливает фронтмена группы.
     *
     * @param frontMan фронтмен группы
     * @return текущий объект MusicBandBuilder (для цепочки вызовов)
     */
    public MusicBandBuilder setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
        return this;
    }

    /**
     * Создает объект MusicBand на основе установленных значений.
     *
     * @return новый объект MusicBand
     */
    public MusicBand build() {
        return new MusicBand(name, coordinates, numberOfParticipants, genre, frontMan);
    }
}