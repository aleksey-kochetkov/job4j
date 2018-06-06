package parser;

public class BracketsPair {
    private char openChar;
    private char closeChar;
    private int openPosition;
    private int closePosition;

    public BracketsPair(char open, char close) {
        this.openChar = open;
        this.closeChar = close;
    }

    public char getOpenChar() {
        return this.openChar;
    }

    public char getCloseChar() {
        return this.closeChar;
    }

    public int getOpenPosition() {
        return this.openPosition;
    }

    public int getClosePosition() {
        return this.closePosition;
    }

    public void setOpenPosition(int position) {
        this.openPosition = position;
    }

    public void setClosePosition(int position) {
        this.closePosition = position;
    }

    /**
     * Понадобилось для теста.
     * @param object object
     * @return признак равенства
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof BracketsPair)) {
            return false;
        }
        return ((BracketsPair) object).openChar == this.openChar
               && ((BracketsPair) object).closeChar == this.closeChar
               && ((BracketsPair) object).openPosition == this.openPosition
               && ((BracketsPair) object).closePosition == this.closePosition;
    }

    /**
     * Не пропускает validate.
     * Но пока такая задача не ставилась.
     * Поэтому минимальная допустимая реализация.
     * @return 1
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
