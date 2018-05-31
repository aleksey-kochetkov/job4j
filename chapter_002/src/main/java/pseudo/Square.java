package pseudo;

/**
 * @author Aleksey Kochetkov
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+---+");
        pic.append(System.lineSeparator());
        pic.append("|   |");
        pic.append(System.lineSeparator());
        pic.append("+---+");
        return pic.toString();
    }
}
