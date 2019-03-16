package fs;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class SearchTest {
    private static final String R;
    static {
        String tmp = System.getProperty("java.io.tmpdir");
//        tmp = "C:\\Users\\<...>\\AppData\\Local\\Temp";
        if (!tmp.endsWith(File.separator)) {
            tmp = tmp + File.separator;
        }
        R = tmp + "searchtest";
    }
    private File root;

    @Before
    public void setUp() throws IOException {
        this.root = new File(R);
        FileUtils.deleteDirectory(this.root);
        this.root.mkdir();
    }

    @Test
    public void whenSimple() throws IOException, InterruptedException,
                                                     ExecutionException {
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
        result = new ExecutorSearch().files(R, input);
        assertThat(result, containsInAnyOrder(expected));
        result = new TwoThreadsSearch().files(R, input);
        assertThat(result, containsInAnyOrder(expected));
    }

    private void populateDirectory(File directory) throws IOException {
        System.out.format("cp0:%s:%n", directory);
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
    public void whenEmpty() throws InterruptedException,
                                                     ExecutionException {
        List<String> input = Arrays.asList("1", "two", "three");
        List<File> result = new Search().files(R, input);
        assertTrue(result.isEmpty());
        result = new ExecutorSearch().files(R, input);
        assertTrue(result.isEmpty());
        result = new TwoThreadsSearch().files(R, input);
        assertTrue(result.isEmpty());
    }

    /**
     * Оценка времени выполнения.
     * Среднее по двум запускам для каждого варианта.
     * a) Тестовое дерево каталогов: 8 500 Files, 340 Folders.
     * T:single:406
     * T:executor:179
     * T:two threads:390
     * б) Тестовое дерево каталогов: 500 Files, 20 Folders.
     * T:single:23
     * T:executor:15
     * T:two threads:24
     * Вывод, разделение на два потока - "обходчик" и "отбор из очереди"
     * является неоправданным усложенением кода. А ипользоваение
     * FixedThreadPool(availableProcessors()) показало эффективность.
     */
    @Test
    public void whenTiming() throws IOException, InterruptedException,
                                                     ExecutionException {
        List<String> input = Arrays.asList("1", "two", "three");
        this.populateTiming(this.root, 0);
        int[] single = new int[2];
        int[] executor = new int[2];
        int[] two = new int[2];
        long start = System.currentTimeMillis();
        new Search().files(R, input);
        single[0] = (int) (System.currentTimeMillis() - start);
        System.out.format("0:single:%d%n", single[0]);
        start = System.currentTimeMillis();
        new ExecutorSearch().files(R, input);
        executor[0] = (int) (System.currentTimeMillis() - start);
        System.out.format("0:executor:%d%n", executor[0]);
        start = System.currentTimeMillis();
        new TwoThreadsSearch().files(R, input);
        two[0] = (int) (System.currentTimeMillis() - start);
        System.out.format("0:two threads:%d%n", two[0]);
        start = System.currentTimeMillis();
        new ExecutorSearch().files(R, input);
        executor[1] = (int) (System.currentTimeMillis() - start);
        System.out.format("1:executor:%d%n", executor[1]);
        start = System.currentTimeMillis();
        new TwoThreadsSearch().files(R, input);
        two[1] = (int) (System.currentTimeMillis() - start);
        System.out.format("1:two threads:%d%n", two[1]);
        start = System.currentTimeMillis();
        new Search().files(R, input);
        single[1] = (int) (System.currentTimeMillis() - start);
        System.out.format("1:single:%d%n", single[1]);
        System.out.format("T:single:%d%n", (single[0] + single[1]) / 2);
        System.out.format("T:executor:%d%n",
                                        (executor[0] + executor[1]) / 2);
        System.out.format("T:two threads:%d%n", (two[0] + two[1]) / 2);
    }

    private void populateTiming(File parent, int level)
                                                     throws IOException {
        final int ML = 2;
        final int ND = 4;
        final int NF = 100;
        for (int i = 0; i < NF; i++) {
            String name = String.format("%s%s%d.fil",
                                   parent.toString(), File.separator, i);
            File f = new File(name);
            f.createNewFile();
        }
        for (int i = 0; i < ND; i++) {
            String name = String.format("%s%s%d",
                              parent.toString(), File.separator, i);
            File f = new File(name);
            f.mkdir();
            if (level < ML) {
                populateTiming(f, level + 1);
            }
        }
    }
}
