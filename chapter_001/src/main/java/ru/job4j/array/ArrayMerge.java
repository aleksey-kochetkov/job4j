package ru.job4j.array;

/**
 * Слить два отсортированных массива.
 * @author Aleksey Kochetkov
 */
public class ArrayMerge {
    public String[] merge(String[] one, String[] two) {
        String[] result = new String[one.length + two.length];
        int iOne = 0;
        int iTwo = 0;
        for (int i = 0; i < result.length; i++) {
            if (iOne == one.length) {
                System.arraycopy(two, iTwo, result, i, two.length - iTwo);
                break;
            }
            if (iTwo == two.length) {
                System.arraycopy(one, iOne, result, i, one.length - iOne);
                break;
            }
            result[i] = two[iTwo].compareTo(one[iOne]) > 0 ? one[iOne++] : two[iTwo++];
        }
        return result;
    }
}
