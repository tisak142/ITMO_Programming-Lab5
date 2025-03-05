package classes;

import java.time.ZonedDateTime;

public class MusicBand implements Comparable<MusicBand> {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле не может быть null

    public MusicBand(long id, String name, Coordinates coordinates, Long numberOfParticipants, MusicGenre genre, Person frontMan) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
        this.id = id;

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;

        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates cannot be null");
        }
        this.coordinates = coordinates;

        this.creationDate = ZonedDateTime.now();

        if (numberOfParticipants != null) {
            if (numberOfParticipants <= 0) {
                throw new IllegalArgumentException("numberOfParticipants must be greater than 0");
            }
        }
        this.numberOfParticipants = numberOfParticipants;

        this.genre = genre;
        if (frontMan == null) {
            throw new IllegalArgumentException("frontMan cannot be null");
        }
        this.frontMan = frontMan;
    }

    public long getId() {
        return id;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        if (numberOfParticipants <= 0 && numberOfParticipants != null) {
            throw new IllegalArgumentException("numberOfParticipants must be greater than 0");
        }
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setFrontMan(Person frontMan) {
        if (frontMan == null) {
            throw new IllegalArgumentException("frontMan cannot be null");
        }
        this.frontMan = frontMan;
    }

    @Override
    public int compareTo(MusicBand o) {
        return numberOfParticipants.compareTo(o.numberOfParticipants);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", frontMan=" + frontMan +
                '}';
    }
}
