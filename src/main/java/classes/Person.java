package classes;

import java.util.Date;

/**
 * Класс Person представляет информацию о человеке.
 * <p>
 * Каждый человек имеет имя, дату рождения, цвет волос и национальность.
 * </p>
 * <p>
 * Все поля класса имеют определенные ограничения:
 * - name не может быть null или пустой строкой.
 * - birthday может быть null.
 * - hairColor не может быть null.
 * - nationality может быть null.
 * </p>
 */
public class Person {
    private String name; // Имя человека. Не может быть null или пустой строкой.

    private java.util.Date birthday; // Дата рождения человека. Может быть null.

    private Color hairColor; // Цвет волос человека. Не может быть null.

    private Country nationality; // Национальность человека. Может быть null.

    /**
     * Создает новый объект Person с указанными параметрами.
     *
     * @param name имя человека (не может быть null или пустой строкой)
     * @param birthday дата рождения (может быть null)
     * @param hairColor цвет волос (не может быть null)
     * @param nationality национальность (может быть null)
     * @throws IllegalArgumentException если name или hairColor не соответствуют ограничениям
     */
    public Person(String name, java.util.Date birthday, Color hairColor, Country nationality) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Error when creation musicBand. name cannot be null or empty");
            System.exit(1);
        }
        this.name = name;

        this.birthday = birthday;

        if (hairColor == null) {
            System.err.println("Error when creation musicBand. HairColor cannot be null");
            System.exit(1);
        }
        this.hairColor = hairColor;

        this.nationality = nationality;
    }

    /**
     * Возвращает имя человека.
     *
     * @return имя человека (не может быть null или пустой строкой)
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает национальность человека.
     *
     * @return национальность (может быть null)
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Возвращает цвет волос человека.
     *
     * @return цвет волос (не может быть null)
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * Возвращает дату рождения человека.
     *
     * @return дата рождения (может быть null)
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Устанавливает имя человека.
     *
     * @param name имя человека (не может быть null или пустой строкой)
     * @throws IllegalArgumentException если name не соответствует ограничениям
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Устанавливает дату рождения человека.
     *
     * @param birthday дата рождения (может быть null)
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Устанавливает цвет волос человека.
     *
     * @param hairColor цвет волос (не может быть null)
     * @throws IllegalArgumentException если hairColor равен null
     */
    public void setHairColor(Color hairColor) {
        if (hairColor == null) {
            throw new IllegalArgumentException("hairColor cannot be null");
        }
        this.hairColor = hairColor;
    }

    /**
     * Устанавливает национальность человека.
     *
     * @param nationality национальность (может быть null)
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Возвращает строковое представление объекта Person.
     *
     * @return строковое представление человека
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }
}