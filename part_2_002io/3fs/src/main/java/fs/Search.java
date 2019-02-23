package fs;

import java.io.File;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * В этом задании нужно написать метод, который возвращает список всех
 * файлов с конкретным расширением. Метод должен заходить во все
 * каталоги. Здесь нельзя использовать FileVisitor. Это задание на работу
 * с деревом каталогов.
 */
public class Search {
    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        File f = new File(parent);
        while (f != null) {
            if (f.isDirectory()) {
                queue.addAll(Arrays.asList(f.listFiles()));
            } else {
                String ext = f.getName();
                ext = ext.substring(ext.lastIndexOf('.') + 1,
                                                           ext.length());
                if (exts.contains(ext)) {
                    result.add(f);
                }
            }
            f = queue.poll();
        }
        return result;
    }
}
