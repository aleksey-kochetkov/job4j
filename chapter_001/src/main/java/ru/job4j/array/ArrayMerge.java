package ru.job4j.array;

/**
 * Слить два отсортированных массива.
 * @author Aleksey Kochetkov
 */
public class ArrayMerge {
    public int[] merge(int[] one, int[] two) {
        int[] result = new int[one.length + two.length];
        int iOne = 0;
        int iTwo = 0;
        for (int i = 0; i < result.length; i++) {
            if (iOne == one.length) {
                result[i] = two[iTwo++];
            } else if (iTwo == two.length) {
                result[i] = one[iOne++];
            } else {
                result[i] = two[iTwo] > one[iOne] ? one[iOne++] : two[iTwo++];
            }
        }
        return result;
    }
}
