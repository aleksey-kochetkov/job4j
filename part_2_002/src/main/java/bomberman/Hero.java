package bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Hero {
    private final Board board;
    @GuardedBy("this")
    private int dx = 1;
    @GuardedBy("this")
    private int dy = 0;

    Hero(Board board) {
        this.board = board;
    }

    void run() {
        Cell source = null;
        for (int y = 0; y < this.board.height && source == null; y++) {
            for (int x = 0; x < this.board.width && source == null; x++) {
                source = this.board.giveBirth(x, y);
            }
        }
        Cell dest;
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                synchronized (this) {
                    dest = new Cell(source.getX() + this.dx, source.getY() + this.dy);
                }
                if (this.board.move(source, dest)) {
                    source = dest;
                }
            } catch (InterruptedException exception) {
                break;
            }
        }
    }

    synchronized void left() {
        this.dx = -1;
        this.dy = 0;
    }

    synchronized void right() {
        this.dx = 1;
        this.dy = 0;
    }

    synchronized void up() {
        this.dx = 0;
        this.dy = -1;
    }

    synchronized void down() {
        this.dx = 0;
        this.dy = 1;
    }
}
