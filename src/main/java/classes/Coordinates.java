package classes;

/**
 * Класс Coordinates представляет координаты в двумерном пространстве.
 * <p>
 * Координаты состоят из двух значений:
 * - x: значение по оси X (не может быть null).
 * - y: значение по оси Y (максимальное значение: 860).
 * </p>
 */
public class Coordinates {
    private Double x; // Значение координаты по оси X. Поле не может быть null
    private long y; //Значение координаты по оси Y. Максимальное значение поля: 860

    /**
     * Создает новый объект Coordinates с указанными значениями.
     *
     * @param x значение по оси X (не может быть null)
     * @param y значение по оси Y (максимальное значение: 860)
     * @throws IllegalArgumentException если x равен null или y превышает 860
     */
    public Coordinates(Double x, long y) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        }
        this.x = x;
        if (y > 861) {
            throw new IllegalArgumentException("Y cannot be greater than 861");
        }
        this.y = y;
    }

    /**
     * Возвращает значение координаты по оси X.
     *
     * @return значение по оси X (не может быть null)
     */
    public Double getX() {
        return x;
    }

    /**
     * Возвращает значение координаты по оси Y.
     *
     * @return значение по оси Y
     */
    public long getY() {
        return y;
    }

    /**
     * Устанавливает значение координаты по оси X.
     *
     * @param x значение по оси X (не может быть null)
     * @throws IllegalArgumentException если x равен null
     */
    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        }
        this.x = x;
    }

    /**
     * Устанавливает значение координаты по оси Y.
     *
     * @param y значение по оси Y (максимальное значение: 860)
     * @throws IllegalArgumentException если y превышает 860
     */
    public void setY(long y) {
        if (y > 861) {
            throw new IllegalArgumentException("Y cannot be greater than 861");
        }
        this.y = y;
    }

    /**
     * Возвращает строковое представление объекта Coordinates.
     *
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
