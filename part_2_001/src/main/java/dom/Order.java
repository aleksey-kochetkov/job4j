package dom;

import java.math.BigDecimal;

public class Order {

    enum Type {
        ADD, DELETE
    }

    static private int nextId;
    private int id;
    /**
     * идентификатор ценной бумаги
     */
    private String book;
    /**
     * add/delete order
     */
    private Type type;
    /**
     * bid/ask
     */
    private Action action;
    private BigDecimal price;
    private int volume;

    Order() {
        this.id = nextId++;
    }

    public Order(String book, Type type, Action action, BigDecimal price, int volume) {
        this();
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        if (!(this.book == null ? order.book == null
                                       : this.book.equals(order.book))) {
            return false;
        }
        if (this.action != order.action) {
            return false;
        }
        if (!(this.price == null ? order.price == null
                                     : this.price.equals(order.price))) {
            return false;
        }
        return this.volume == order.volume;
    }

    @Override
    public int hashCode() {
        int result;
        result = this.book == null ? 0 : this.book.hashCode();
        result = 31 * result + (this.action == null ? 0 : this.action.hashCode());
        result = 31 * result + (this.price == null ? 0 : this.price.hashCode());
        result = 31 * result + this.volume;
        return result;
    }

    void setId(int id) {
        this.id = id;
    }

    int getId() {
        return this.id;
    }

    void setBook(String book) {
        this.book = book;
    }

    String getBook() {
        return this.book;
    }

    void setType(Type type) {
        this.type = type;
    }

    Type getType() {
        return this.type;
    }

    void setAction(Action action) {
        this.action = action;
    }

    Action getAction() {
        return this.action;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    BigDecimal getPrice() {
        return this.price;
    }

    void decreaseVolumeBy(int d) {
        this.volume -= d;
    }

    void setVolume(int volume) {
        this.volume = volume;
    }

    int getVolume() {
        return this.volume;
    }
}
