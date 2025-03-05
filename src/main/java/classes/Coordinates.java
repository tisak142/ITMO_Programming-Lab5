package classes;

public class Coordinates {
    private Double x; //Поле не может быть null
    private long y; //Максимальное значение поля: 860

    public Coordinates(Double x, long y) {
        if (x == null) {
            throw new IllegalArgumentException("x cannot be null");
        }
        this.x = x;

        if (y > 861) {
            throw new IllegalArgumentException("y cannot be greater than 861");
        }
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("x cannot be null");
        }
        this.x = x;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setY(long y) {
        if (y > 861) {
            throw new IllegalArgumentException("y cannot be greater than 861");
        }
        this.y = y;
    }
}
