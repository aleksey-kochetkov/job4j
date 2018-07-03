package dom;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Iterator;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProcessorTest {

    @Test
    public void whenFirstAddBid() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.20"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 3);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.33"), 2);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenFirstAddAsk() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.40"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.33"), 3);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 2);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenAddAskGreaterThanBid() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.20"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.33"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.32"), 3);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenAddBidGreaterThanAsk() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.40"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.32"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.33"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 3);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenAddAskGreaterThanBidSamePrice() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.20"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.33"), 2);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.33"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.32"), 2);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenAddBidGreaterThanAskSamePrice() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.40"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.33"), 1);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.33"), 2);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 2);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(2, result.size());
    }

    @Test
    public void whenAddAskGreaterThanBidSameVolume() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.20"), 4);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.33"), 2);
        processor.add(order);
        order = new Order("TM", Order.Type.ADD, Action.ASK, new BigDecimal("0.32"), 2);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(1, result.size());
    }

    @Test
    public void whenRemoveBid() {
        Processor processor = new Processor();
        Order order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.50"), 4);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.50"), 3);
        processor.add(order);
        Order removal = new Order("LKODL", Order.Type.DELETE, Action.BID, new BigDecimal("0.50"), 3);
        removal.setId(order.getId());
        processor.remove(removal);
        Map<BigDecimal, DOMRecord> result = processor.process("LKODL");
        assertEquals(1, result.size());
    }

    @Test
    public void whenRemoveAsk() {
        Processor processor = new Processor();
        Order order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.49"), 4);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.ASK, new BigDecimal("0.50"), 3);
        processor.add(order);
        Order removal = new Order("LKODL", Order.Type.DELETE, Action.ASK, new BigDecimal("0.50"), 3);
        removal.setId(order.getId());
        processor.remove(removal);
        Map<BigDecimal, DOMRecord> result = processor.process("LKODL");
        assertEquals(1, result.size());
    }

    @Test
    public void whenRemoveLast() {
        Processor processor = new Processor();
        Order order = new Order("TM", Order.Type.ADD, Action.BID, new BigDecimal("0.34"), 3);
        processor.add(order);
        processor.remove(order);
        Map<BigDecimal, DOMRecord> result = processor.process("TM");
        assertEquals(0, result.size());
    }

    @Test
    public void whenProcess() {
        Processor processor = new Processor();
        Order order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.49"), 2);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.50"), 3);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.ASK, new BigDecimal("0.51"), 2);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.ASK, new BigDecimal("0.52"), 1);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.ASK, new BigDecimal("0.51"), 2);
        processor.add(order);
        order = new Order("LKODL", Order.Type.ADD, Action.BID, new BigDecimal("0.49"), 3);
        processor.add(order);
        Map<BigDecimal, DOMRecord> result = processor.process("LKODL");
        Iterator<Map.Entry<BigDecimal, DOMRecord>> it = result.entrySet().iterator();
        Map.Entry<BigDecimal, DOMRecord> entry = it.next();
        assertThat(entry.getKey(), is(new BigDecimal("0.52")));
        assertEquals(1, entry.getValue().getAskVolume());
        entry = it.next();
        assertThat(entry.getKey(), is(new BigDecimal("0.51")));
        assertEquals(4, entry.getValue().getAskVolume());
        entry = it.next();
        assertThat(entry.getKey(), is(new BigDecimal("0.50")));
        assertEquals(3, entry.getValue().getBidVolume());
        entry = it.next();
        assertThat(entry.getKey(), is(new BigDecimal("0.49")));
        assertEquals(5, entry.getValue().getBidVolume());
    }
}
