package coffeemachine;

/**
 * @author Aleksey Kochetkov
 */
public class Coffeemachine {
    private int[] nominals = {10, 5, 2, 1};

    int[] changes(int value, int price) {
        int change = value - price;
        int[] mult = new int[this.nominals.length];
        int j = 0;
        for (int i = 0; i < this.nominals.length; i++) {
            mult[i] = change / this.nominals[i];
            j += mult[i];
            change = change % this.nominals[i];
        }
        int[] result = new int[j];
        j = 0;
        for (int i = 0; i < this.nominals.length; i++) {
            while (mult[i]-- > 0) {
                result[j++] = this.nominals[i];
            }
        }
        return result;
    }
}
