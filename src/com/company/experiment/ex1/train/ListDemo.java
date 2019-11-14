package train;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>(); // 为List父接口进行实例化
        all.add("JAVA"); // 保存数据
        all.add("JAVA"); // 保存重复数据
        all.add("www.scau.edu.cn");
        all.add("Mr.zhang");
        all.forEach((str) -> { // 集合输出
            System.out.println(str + "、");
        });
    }
}
