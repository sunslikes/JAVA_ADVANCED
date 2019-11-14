package train;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 双向迭代输出
 */
public class IteratorTrain2 {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        all.add("me");
        all.add("Java");
        all.add("scau");
        ListIterator<String> iterator = all.listIterator();
        System.out.println("由前向后输出：");
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + "、");
        }
        System.out.println("右后向前输出：");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous() + "、");
        }
    }
}
