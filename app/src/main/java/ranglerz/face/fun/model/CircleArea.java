package ranglerz.face.fun.model;

/** Stores data about single circle */
public class CircleArea {

    public int radius;
    public int centerX;
    public int centerY;

    public CircleArea(int centerX, int centerY, int radius) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public String toString() {
        return "Circle[" + centerX + ", " + centerY + ", " + radius + "]";
    }
}
