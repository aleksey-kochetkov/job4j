package pingpong;

import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * В задаче была установлена исходная скорость по x = 1, по y = 0.
 * Я её оставил как есть, но при столкновении увеличиваю скорость по модулю
 * на случайную величину не более 10 процентов. Таким образом, объект через
 * некоторое время сильно разгоняется, ради забавы.
 * @author Aleksey Kochetkov
 */
public class RectangleMove implements Runnable {
    private static final Random RANDOM = new Random();
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        double x = this.rect.getX();
        double y = this.rect.getY();
        double dx = 1;
        double dy = 0;
        while (!Thread.interrupted()) {
            x += dx;
            y += dy;
            double speed = 0.0;
            if (x >= PingPong.LIMIT_X || x <= 0.0) {
                speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                dx = -increaseCoordinateByRandom(dx, speed);
                dy = increaseCoordinateByRandom(dy, speed);

            }
            if (y >= PingPong.LIMIT_Y || y <= 0.0) {
                if (speed == 0.0) {
                    speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                }
                dx = increaseCoordinateByRandom(dx, speed);
                dy = -increaseCoordinateByRandom(dy, speed);
            }
            this.rect.setX((int) x);
            this.rect.setY((int) y);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private double increaseCoordinateByRandom(double coord, double speed) {
        return coord + (coord < 0 ? -1 : 1) * speed * RANDOM.nextDouble() / 10;
    }
}