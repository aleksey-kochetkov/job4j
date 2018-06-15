package services;

/**
 * Tests a number whether it is prime.
 * @author Aleksey Kochetkov
 */
public class PrimalityTest {
    public boolean isPrime(int n) {
        boolean result;
        if (n <= 1) {
            result = false;
        } else if (n <= 3) {
            result = true;
        } else {
            result = n % 2 != 0;
            if (result) {
                for (int i = 3; i * i <= n; i += 2) {
                    if (n % i == 0) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
