package train;

import java.util.Set;
import java.util.TreeSet;

public class ForeachOutput {
    public static void main(String[] args) {
        Set<String> all = new TreeSet<>(); // 无重复集合
        all.add("JAVA");
        all.add("java");
        all.add("JAVA");
        all.add("Hello");
        for (String string: all) {
            System.out.println(string);
        }
    }
}
