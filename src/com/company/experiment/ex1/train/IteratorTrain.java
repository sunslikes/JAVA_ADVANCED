package train;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class IteratorTrain {
    public static void main(String[] args) {
//        Set<String> all = Set.of("Hello", "Scau", "ScauJAVA"); // jdk9才支持
        Set<String> all = new TreeSet<>();
        all.add("Hello");
        all.add("JAVA");
        all.add("Scau");
        Iterator<String> iterator = all.iterator(); // 实例化Iterator接口对象
        while (iterator.hasNext()) { // 集合是否有下一个数据
            String str = iterator.next(); // 获取下一个数据
            System.out.println(str + "、");
        }
    }
}
