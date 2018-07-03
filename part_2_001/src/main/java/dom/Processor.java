package dom;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Collections;
import java.util.function.Supplier;
import java.math.BigDecimal;

public class Processor {
    private Map<String, TreeMap<BigDecimal, List<Order>>> bidOrders = new HashMap<>();
    private Map<String, TreeMap<BigDecimal, List<Order>>> askOrders = new HashMap<>();

    public void add(Order order) {
        if (order.getAction() == Action.BID) {
            this.processOpposites(askOrders, order);
            if (order.getVolume() > 0) {
                this.add(bidOrders, order, this::getBidTreeMap);
            }
        } else {
            this.processOpposites(bidOrders, order);
            if (order.getVolume() > 0) {
                this.add(askOrders, order, this::getAskTreeMap);
            }
        }
    }

    public TreeMap<BigDecimal, List<Order>> getAskTreeMap() {
        return new TreeMap<>();
    }

    public TreeMap<BigDecimal, List<Order>> getBidTreeMap() {
        return new TreeMap<>(Collections.reverseOrder());
    }

    private void add(Map<String, TreeMap<BigDecimal, List<Order>>> orders,
                     Order order, Supplier<TreeMap<BigDecimal, List<Order>>> supplier) {
        TreeMap<BigDecimal, List<Order>> tree = orders.get(order.getBook());
        if (tree == null) {
            tree = supplier.get();
            orders.put(order.getBook(), tree);
        }
        List<Order> list = tree.computeIfAbsent(order.getPrice(), k -> new LinkedList<>());
        list.add(order);
    }

    public void remove(Order order) {
        if (order.getAction() == Action.BID) {
            this.remove(bidOrders, order);
        } else {
            this.remove(askOrders, order);
        }
    }

    private void remove(Map<String, TreeMap<BigDecimal, List<Order>>> orders,
                        Order order) {
        TreeMap<BigDecimal, List<Order>> tree = orders.get(order.getBook());
        List<Order> list = tree.get(order.getPrice());
        list.remove(order);
        if (list.size() == 0) {
            tree.remove(order.getPrice());
            if (tree.size() == 0) {
                orders.remove(order.getBook());
            }
        }
    }

    private void processOpposites(Map<String, TreeMap<BigDecimal, List<Order>>> opposites,
                                  Order order) {
        TreeMap<BigDecimal, List<Order>> filtered = opposites.get(order.getBook());
        if (filtered != null) {
            do {
                Map.Entry<BigDecimal, List<Order>> entry = filtered.firstEntry();
                if (order.getAction() == Action.BID
                    && order.getPrice().compareTo(entry.getKey()) >= 0
                    || order.getAction() == Action.ASK
                    && order.getPrice().compareTo(entry.getKey()) <= 0) {
                    do {
                        Order opposite = entry.getValue().get(0);
                        if (order.getVolume() < opposite.getVolume()) {
                            opposite.decreaseVolumeBy(order.getVolume());
                            order.decreaseVolumeBy(order.getVolume());
                        } else {
                            order.decreaseVolumeBy(opposite.getVolume());
                            this.remove(opposite);
                        }
                    } while (entry.getValue().size() > 0 && order.getVolume() > 0);
                } else {
                    break;
                }
            } while (filtered.size() > 0 && order.getVolume() > 0);
        }
    }

    Map<BigDecimal, DOMRecord> process(String book) {
        Map<BigDecimal, DOMRecord> result = new TreeMap<>(Collections.reverseOrder());
        if (this.bidOrders.get(book) != null) {
            for (List<Order> list : this.bidOrders.get(book).values()) {
                for (Order o : list) {
                    if (result.get(o.getPrice()) == null) {
                        result.put(o.getPrice(), new DOMRecord(o.getVolume(), 0));
                    } else {
                        result.get(o.getPrice()).increaseBidVolumeBy(o.getVolume());
                    }
                }
            }
        }
        if (this.askOrders.get(book) != null) {
            for (List<Order> list : this.askOrders.get(book).values()) {
                for (Order o : list) {
                    if (result.get(o.getPrice()) == null) {
                        result.put(o.getPrice(), new DOMRecord(0, o.getVolume()));
                    } else {
                        result.get(o.getPrice()).increaseAskVolumeBy(o.getVolume());
                    }
                }
            }
        }
        return result;
    }
}
