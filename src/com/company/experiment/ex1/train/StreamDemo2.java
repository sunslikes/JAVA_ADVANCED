package train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  使用Stream进行数据采集
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        Collections.addAll(all, "Java", "JavaScript", "JSP",
                "Json", "Python", "Ruby", "Go");
        Stream<String> stream = all.stream();
        List<String> result = stream.map(e -> e.toLowerCase())
                .filter(e -> e.contains("j")).skip(2).limit(2)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
