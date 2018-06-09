package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
//        int index;
        for (int index = 0; index < this.tasks.size(); index++) {
            if (this.tasks.get(index).getPriority() > task.getPriority()) {
                this.tasks.add(index, task);
                break;
            }
        }
// эта строка обрабатывала случай tasks.size() == 0,
// теперь тут ОШИБКА :) в пустой список не добавится
//        this.tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}