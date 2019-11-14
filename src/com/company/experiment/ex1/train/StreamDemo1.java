package train;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 使用Stream进行数据采集
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        Collections.addAll(all, "JAVA", "JavaScript", "JSP");
        Stream<String> stream = all.stream();
        System.out.println(stream.filter(e -> e.toLowerCase().contains("a")).count());
    }
}
