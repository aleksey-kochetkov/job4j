package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        int counter = 0;
        for (int i : list) {
            array[counter / rows][counter++ % rows] = i;
        }
        return array;
    }
}