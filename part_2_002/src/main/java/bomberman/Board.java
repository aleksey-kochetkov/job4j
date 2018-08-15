package bomberman;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Board {
    final int width;
    final int height;
    final int numberOfBlocks;
    final int numberOfMonsters;
    private final ReentrantLock[][] board;
    private final Hero hero = new Hero(Board.this);
    private final Runnable hRunnable = Board.this.hero::run;
    private final Runnable mRunnable = () -> {
        Monster monster = new Monster(Board.this);
        monster.run();
    };

    public static void main(String[] args) {
        Board board = new Board(4, 4, 3, 3);
        board.init();
        board.start();
    }

    Board(int width, int height, int numberOfBlocks, int numberOfMonsters) {
        this.width = width;
        this.height = height;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfMonsters = numberOfMonsters;
        this.board = new ReentrantLock[this.width][this.height];
    }

    void init() {
        for (ReentrantLock[] row : this.board) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new ReentrantLock();
            }
        }
        putBlocks();
    }

    private void putBlocks() {
        Random random = new Random();
        for (int i = 0; i < this.numberOfBlocks; i++) {
            for (int j = 0; j < 0xFFFF; j++) {
                int x = random.nextInt(this.width);
                int y = random.nextInt(this.height);
                if (!this.board[y][x].isLocked()) {
                    this.board[y][x].lock();
                    System.out.format("block:{x=%d, y=%d}%n", x, y);
                    break;
                }
            }
        }
    }

    void start() {
        Thread hThread = new Thread(this.hRunnable, "hero");
        Thread[] mThreads = new Thread[this.numberOfMonsters];
        for (int i = 0; i < this.numberOfMonsters; i++) {
            mThreads[i] = new Thread(this.mRunnable);
            mThreads[i].start();
        }
        hThread.start();
        try {
            Thread.sleep(3000);
            this.hero.down();
            Thread.sleep(4000);
            this.hero.left();
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        for (Thread t : mThreads) {
            t.interrupt();
        }
        hThread.interrupt();
        try {
            for (Thread t : mThreads) {
                t.join();
            }
            hThread.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    Cell giveBirth(int x, int y) {
        Cell result = null;
        if (this.board[0][x].tryLock()) {
            result = new Cell(x, 0);
        }
        return result;
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
