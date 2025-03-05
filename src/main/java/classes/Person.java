package classes;

import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }

    public Person(String name, java.util.Date birthday, Color hairColor, Country nationality) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;

        this.birthday = birthday;

        if (hairColor == null) {
            throw new IllegalArgumentException("hairColor cannot be null");
        }
        this.hairColor = hairColor;

        this.nationality = nationality;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setHairColor(Color hairColor) {
        if (hairColor == null) {
            throw new IllegalArgumentException("hairColor cannot be null");
        }
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Country getNationality() {
        return nationality;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Date getBirthday() {
        return birthday;
    }

}