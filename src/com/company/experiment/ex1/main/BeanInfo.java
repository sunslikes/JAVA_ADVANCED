package com.company.experiment.ex1.main;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @PackgeName: com.company.experiment.ex1.main
 * @ClassName: BeanInfo
 * @Author: z8932
 * Date: 2019/11/13 15:00
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description: 用于描述注册在容器中的对象
 */
public class BeanInfo {
    //对象的标识
    private String id;
    // 对象的类型, 全类名
    private String type;
    // 对象的属性以及值的集合
    private Map<String, Object> properties = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[");
        sb.append("id: ");
        sb.append(id);
        sb.append("\n");
        sb.append("type: ");
        sb.append(type);
        sb.append("\n");
        sb.append("properties: \n");
        for (String name: properties.keySet()) {
            sb.append(name);
            sb.append("=");
            sb.append(properties.get(name));
            sb.append(", class=" + properties.get(name).getClass().getSimpleName());
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
