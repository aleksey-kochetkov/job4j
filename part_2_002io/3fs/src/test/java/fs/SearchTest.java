package fs;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class SearchTest {
    private static final String R = String.format("%s%ssearchtest",
                   System.getProperty("java.io.tmpdir"), File.separator);
    private File root;

    @Before
    public void setUp() throws IOException {
        this.root = new File(R);
        FileUtils.deleteDirectory(this.root);
        this.root.mkdir();
    }

    @Test
    public void whenSimple() throws IOException {
        List<String> input = Arrays.asList("1", "two", "three");
        File[] expected = {
           new File(String.format("%s%sfirstname.1", R, File.separator)),
           new File(String.format("%s%ssecondname.two",
                                                     R, File.separator)),
           new File(String.format("%s%stwo.name.1.three",
                                                     R, File.separator)),
           new File(String.format("%s%ssub.1%sfirstname.1",
                                     R, File.separator, File.separator)),
           new File(String.format("%s%ssub.1%ssecondname.two",
                                     R, File.separator, File.separator)),
           new File(String.format("%s%ssub.1%stwo.name.1.three",
                                     R, File.separator, File.separator)),
           new File(String.format("%s%ssub.two%sthree.sub%sfirstname.1",
                     R, File.separator, File.separator, File.separator)),
           new File(String.format("%s%ssub.two%sthree.sub%ssecondname.two",
                     R, File.separator, File.separator, File.separator)),
           new File(String.format("%s%ssub.two%sthree.sub%stwo.name.1.three",
                      R, File.separator, File.separator, File.separator))
        };
        this.populateDirectory(this.root);
        List<File> result = new Search().files(R, input);
        assertThat(result, containsInAnyOrder(expected));
    }

    private void populateDirectory(File directory) throws IOException {
        File current = directory;
        File subs;

        for (int i = 0; i < 3; i++) {
            String name = String.format("%s%sfirstname.1",
                                     current.toString(), File.separator);
            new File(name).createNewFile();
            name = String.format("%s%ssecondname.two",
                                     current.toString(), File.separator);
            new File(name).createNewFile();
            name = String.format("%s%stwo.name.1.ext",
                                     current.toString(), File.separator);
            new File(name).createNewFile();
            name = String.format("%s%stwo.name.1.three",
                                     current.toString(), File.separator);
            new File(name).createNewFile();
            if (i == 0) {
                name = String.format("%s%ssub.1",
                                   directory.toString(), File.separator);
                current = new File(name);
                current.mkdir();
            } else if (i == 1) {
                name = String.format("%s%ssub.two",
                                   directory.toString(), File.separator);
                current = new File(name);
                current.mkdir();
                name = String.format("%s%sthree.sub", name, File.separator);
                current = new File(name);
                current.mkdir();
            }
        }
    }

    @Test
    public void whenEmpty() {
        List<String> input = Arrays.asList("1", "two", "three");
        List<File> result = new Search().files(R, input);
        assertTrue(result.isEmpty());
    }
}
