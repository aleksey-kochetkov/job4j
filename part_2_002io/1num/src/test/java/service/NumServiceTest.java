package service;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class NumServiceTest {

    @Test
    public void whenIsNumber() throws IOException {
        try (InputStream in = new ByteArrayInputStream(
                                            new byte[] {-17, 24, -37})) {
            in.read();
            assertTrue(new NumService().isNumber(in));
        }
    }

    @Test
    public void whenNotIsNumber() throws IOException {
        try (InputStream in = new ByteArrayInputStream(
                                            new byte[] {-18, 24, -37})) {
            in.read();
            in.read();
            assertFalse(new NumService().isNumber(in));
        }
    }
}