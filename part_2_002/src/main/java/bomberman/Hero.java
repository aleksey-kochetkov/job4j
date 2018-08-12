package bomberman;

public class Hero {
    private final Board board;

    Hero(Board board) {
        this.board = board;
    }

    void run() {
        Cell source = this.board.giveBirth();
        Cell dest;
        int dx = 1;
        int dy = 0;
        System.out.format("%s:%s%n", Thread.currentThread().getName(), source.toString()); //test:
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
                System.out.format("%s:%s%n", Thread.currentThread().getName(), dest.toString()); //test:
            } catch (InterruptedException exception) {
                break;
            }
        }
        System.out.format("%s:The End.%n", Thread.currentThread().getName()); //test:
    }
}
