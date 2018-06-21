package list;

import java.util.Set;
import java.util.HashSet;

/**
 * Проверка наличия зацикленности в заданной последовательности элементов связанного списка.
 * По условиям учебной задачи "без использования коллекций".
 * @param <T>
 */
public class CycleDetector<T> {

    /**
     * Первый вариант.
     * @param first начало списка для проверки
     * @return признак наличия зацикленности
     */
    public boolean hasCycleTmp(Node<T> first) {
        Set<Node<T>> found = new HashSet<>();
        boolean result = false;
        while (first != null) {
            if (found.contains(first)) {
                result = true;
                break;
            }
            found.add(first);
            first = first.next;
        }
        return result;
    }

    /**
     * Перечитал задание, написано "без использования коллекций".
     * Второй вариант, совсем без коллекций.
     * @param first начало списка для проверки
     * @return признак наличия зацикленности
     */
    public boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node<T> current = first;
outer:
        while (current != null) {
            Node<T> previousNode = first;
            while (previousNode != current) {
                if (current.next == previousNode) {
                    result = true;
                    break outer;
                }
                previousNode = previousNode.next;
            }
            current = current.next;
        }
        return result;
    }
}
