package classes;

import java.time.ZonedDateTime;

/**
 * Класс MusicBand представляет музыкальную группу.
 * <p>
 * Каждая группа имеет уникальный идентификатор, название, координаты, дату создания,
 * количество участников, музыкальный жанр и фронтмена (лидера группы).
 * </p>
 * <p>
 * Все поля класса имеют определенные ограничения:
 * - id должно быть больше 0 и генерируется автоматически.
 * - name не может быть null или пустой строкой.
 * - coordinates не может быть null.
 * - creationDate генерируется автоматически и не может быть null.
 * - numberOfParticipants может быть null, но если указано, должно быть больше 0.
 * - genre может быть null.
 * - frontMan не может быть null.
 * </p>
 */
public class MusicBand implements Comparable<MusicBand> {
    private static long uniqueId = 1; // статическое поле для генерации уникального id
    private long id; // Идентификатор группы. Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Название группы. Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Координаты группы. Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Дата создания группы. Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Кол-во участников группы. Поле может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Жанр музыки группы. Поле может быть null
    private Person frontMan; //Фронтмен группы. Поле не может быть null

    /**
     * Создает новый объект MusicBand с указанными параметрами.
     *
     * @param name название группы (не может быть null или пустой строкой)
     * @param coordinates координаты группы (не может быть null)
     * @param numberOfParticipants количество участников (может быть null, но если указано, должно быть больше 0)
     * @param genre музыкальный жанр (может быть null)
     * @param frontMan фронтмен группы (не может быть null)
     * @throws IllegalArgumentException если параметры не соответствуют ограничениям
     */
    public MusicBand(String name, Coordinates coordinates, Long numberOfParticipants, MusicGenre genre, Person frontMan) {
        this.id = uniqueId++;

        if (name == null || name.trim().isEmpty()) {
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
    /**
     * Возвращает уникальный идентификатор группы.
     *
     * @return уникальный идентификатор группы
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает фронтмена группы.
     *
     * @return объект фронтмена (не может быть null)
     */
    public Person getFrontMan() {
        return frontMan;
    }

    /**
     * Возвращает музыкальный жанр группы.
     *
     * @return музыкальный жанр (может быть null)
     */
    public MusicGenre getGenre() {
        return genre;
    }

    /**
     * Возвращает количество участников группы.
     *
     * @return количество участников (может быть null)
     */
    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Возвращает дату создания группы.
     *
     * @return дата создания (не может быть null)
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает координаты группы.
     *
     * @return координаты (не может быть null)
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает название группы.
     *
     * @return название группы (не может быть null или пустой строкой)
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает уникальный идентификатор группы.
     *
     * @param id уникальный идентификатор (должен быть больше 0)
     * @throws IllegalArgumentException если id меньше или равен 0
     */
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
        this.id = id;
    }

    /**
     * Устанавливает название группы.
     *
     * @param name название группы (не может быть null или пустой строкой)
     * @throws IllegalArgumentException если name не соответствует ограничениям
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Устанавливает координаты группы.
     *
     * @param coordinates координаты (не может быть null)
     * @throws IllegalArgumentException если coordinates равен null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Обновляет дату создания группы на текущую дату и время.
     */
    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Устанавливает количество участников группы.
     *
     * @param numberOfParticipants количество участников (может быть null, но если указано, должно быть больше 0)
     * @throws IllegalArgumentException если numberOfParticipants меньше или равен 0
     */
    public void setNumberOfParticipants(Long numberOfParticipants) {
        if (numberOfParticipants <= 0 && numberOfParticipants != null) {
            throw new IllegalArgumentException("numberOfParticipants must be greater than 0");
        }
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Устанавливает музыкальный жанр группы.
     *
     * @param genre музыкальный жанр (может быть null)
     */
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    /**
     * Устанавливает фронтмена группы.
     *
     * @param frontMan фронтмен (не может быть null)
     * @throws IllegalArgumentException если frontMan равен null
     */
    public void setFrontMan(Person frontMan) {
        if (frontMan == null) {
            throw new IllegalArgumentException("frontMan cannot be null");
        }
        this.frontMan = frontMan;
    }

    /**
     * Сравнивает две группы по количеству участников.
     *
     * @param o другая группа для сравнения
     * @return результат сравнения (отрицательное число, если текущая группа меньше,
     *         положительное число, если больше, и 0, если группы равны)
     */
    @Override
    public int compareTo(MusicBand o) {
        Integer len1 = name.length();
        Integer len2 = o.name.length();
        return len1.compareTo(len2);
    }

    /**
     * Возвращает строковое представление объекта MusicBand.
     *
     * @return строковое представление группы
     */
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
