package pseudo;

/**
 * @author Aleksey Kochetkov
 */
public class Paint {

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        System.out.println();
        paint.draw(new Square());
        System.out.println();
    }

    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
