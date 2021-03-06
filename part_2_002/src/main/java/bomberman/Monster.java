package bomberman;

import java.util.Random;

public class Monster {
    private final Board board;

    Monster(Board board) {
        this.board = board;
    }

    void run() {
        Random random = new Random();
        Cell source;
        do {
            int x = random.nextInt(this.board.width);
            int y = random.nextInt(this.board.height);
            source = this.board.giveBirth(x, y);
        } while (source == null);
        Cell dest;
        int dx = 1;
        int dy = 0;
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                dest = new Cell(source.getX() + dx, source.getY() + dy);
                while (!this.board.move(source, dest)) {
                    if (dx != 0) {
                        dy = dx;
                        dx = 0;
                    } else {
                        dx = -dy;
                        dy = 0;
                    }
                    dest = new Cell(source.getX() + dx, source.getY() + dy);
                }
                source = dest;
            } catch (InterruptedException exception) {
                break;
            }
        }
    }
}
