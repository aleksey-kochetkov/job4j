package services;

import java.util.Map;
import java.util.HashMap;

public class Groupper {

    /**
     * Дан список слов. Сгруппировать эти слова по общему префиксу
     * (префиксом считать общую часть ровно из 3х первых букв). Группой
     * считать только такую ситуацию, когда более одного слова имеют
     * общий префикс. Результат должен состоять из одного единственного
     * слова, имеющего максимальную длину среди тех слов, что вошли в
     * группы.
     * @param input список слов
     * @return длиннейшее слово из вошедших в группы
     */
    public String apply(String[] input) {
        Map<String, String> map = new HashMap<>();
        String resultPrefix = null;
        int resultLength = 0;
        for (String value : input) {
            String prefix = value.substring(0, 3);
            if (map.get(prefix) == null) {
                map.put(prefix, value);
            } else {
                if (map.get(prefix).length() > resultLength) {
                    resultPrefix = prefix;
                    resultLength = map.get(prefix).length();
                } else if (value.length() > resultLength) {
                    map.put(prefix, value);
                    resultPrefix = prefix;
                    resultLength = map.get(prefix).length();
                }
            }
        }
        return map.get(resultPrefix);
    }
}
