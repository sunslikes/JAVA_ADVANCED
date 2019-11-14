package train;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 枚举输出
 */
public class EnumerationOutPut {
    public static void main(String[] args) {
        Vector<String> all = new Vector<>();
        all.add("me");
        all.add("you");
        all.add("you");
        Enumeration<String> enu = all.elements(); // 获取Enumeration实例
        while (enu.hasMoreElements()) {
            String string = enu.nextElement();
            System.out.println(string + "、");
        }
    }
}
