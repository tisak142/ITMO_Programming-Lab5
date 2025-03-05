package org.example;

import classes.Coordinates;
import classes.MusicBand;
import classes.MusicGenre;
import classes.Person;

import java.time.ZonedDateTime;

public class MusicBandBuilder {
    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Long numberOfParticipants;
    private MusicGenre genre;
    private Person frontMan;

    public MusicBandBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public MusicBandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MusicBandBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public MusicBandBuilder setCreationDate() {
        this.creationDate = ZonedDateTime.now();
        return this;
    }

    public MusicBandBuilder setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
        return this;
    }

    public MusicBandBuilder setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    public MusicBandBuilder setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
        return this;
    }

    public MusicBand build() {
        return new MusicBand(id, name, coordinates, numberOfParticipants, genre, frontMan);
    }
}