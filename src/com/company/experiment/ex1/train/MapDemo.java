package train;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("one", 11); // key重复，发生覆盖
        map.put(null, 0); // key为null
        map.put("zero", null); // value为null
        System.out.println(map.get("one"));
        System.out.println(map.get(null));
        System.out.println(map.get("ten"));
    }
}
