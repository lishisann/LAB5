package LAB5;

import java.util.Arrays;

public class Polyline {

    private Point[] points;

    public Polyline(Point[] points) {
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException("Массив точек не может быть пустым.");
        }
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Линия " + Arrays.toString(points);
    }
}
