package bomberman;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Board {
    private final int width = 3;
    private final int height = 3;
    private final ReentrantLock[][] board = new ReentrantLock[this.width][this.height];
    private final Runnable heroRunnable = () -> {
        Hero hero = new Hero(Board.this);
        hero.run();
    };

    public static void main(String[] args) {
        Board board = new Board();
        board.init();
        board.start();
    }

    void init() {
        for (ReentrantLock[] row : this.board) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new ReentrantLock();
            }
        }
    }

    void start() {
        Thread one = new Thread(this.heroRunnable);
        Thread two = new Thread(this.heroRunnable);
        one.start();
        two.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        one.interrupt();
        two.interrupt();
        try {
            one.join();
            two.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    Cell giveBirth() {
        int x = 0;
        while (!this.board[0][x].tryLock()) {
            x++;
        }
        return new Cell(x, 0);
    }

    boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
        if (dest.getX() < this.width && dest.getY() < this.height
                && dest.getX() >= 0 && dest.getY() >= 0) {
            ReentrantLock destLock = this.board[dest.getY()][dest.getX()];
            result = destLock.tryLock(500, TimeUnit.MILLISECONDS);
        }
        if (result) {
            this.board[source.getY()][source.getX()].unlock();
        }
        return result;
    }
}
