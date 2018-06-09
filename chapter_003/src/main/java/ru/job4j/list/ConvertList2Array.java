package ru.job4j.list;

import java.util.List;
import java.util.ArrayList;

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

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] sub : list) {
            for (int i : sub) {
                result.add(i);
            }
        }
        return result;
    }
}