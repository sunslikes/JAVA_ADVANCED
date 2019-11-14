package train;

import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties prop = new Properties(); // 属性存储
        prop.setProperty("scau", "www.scau.edu.cn"); // 设置属性内容
        prop.setProperty("scaujava", "www.scau.edu.cn");
        System.out.printf(prop.getProperty("scau")); // 根据key查找属性
        System.out.println(prop.getProperty("yootk", "Nofound"));
        System.out.println(prop.getProperty("yootk"));
    }
}
