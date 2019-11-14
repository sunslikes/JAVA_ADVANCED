package com.company.homwork.chapter4.annotation.tool;

import com.company.homwork.chapter4.annotation.annotation.Column;
import com.company.homwork.chapter4.annotation.annotation.ID;
import javafx.scene.control.Tab;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation.tool
 * @ClassName: ColumnInfo
 * @Author: z8932
 * Date: 2019/11/5 23:20
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class ColumnInfo {

    private String columnName; // 字段名称
    private Class<?> type; // 字段类型
    private boolean isID = false; // 是否是主键
    private boolean nullable = true; // 是否可以为空
    private int length = 32; // 字段长度
    private boolean needPersist = false; // 该字段是否需要保存到数据库中

    /**
     * 根据属性描述对象Field，解析字段信息
     * 1、获取Field对象的名称，作为字段名称
     * 2、获取Field对象的类型，作为字段类型
     * 3、获取该属性上声明的注解集合，并遍历这些注解
     * 4、如果注解是@Column，则表明该属性应映射成数据库的字段
     * 5、如果注解是@ID，则表明该属性作为数据库中的主键
     * 6、判断该属性是否需要持久化，如需要返回解析后的字段信息对象，否则返回null
     * @param field
     * @return 返回解析后的字段信息对象（如果需要持久化的话
     */
    public ColumnInfo parse(Field field) {
        this.columnName = field.getName(); // 默认原属性名
        this.type = field.getType();
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation: annotations) {
            if (annotation.annotationType().equals(Column.class)) {
                this.needPersist = true; // 设置成持久化存储
                Column column = (Column)annotation;
                if (!"".equals(column.value())) { // 如果value不为空，则字段名设置成value的参数
                    this.columnName = column.value();
                }
                this.nullable = column.nullable();
                if (column.length() != -1) {
                    this.length = column.length();
                }
            } else if (annotation.annotationType().equals(ID.class)) {
                this.needPersist = true;
                this.isID = true;
                ID id = (ID)annotation;
                if (!"".equals(id.value())) {
                    this.columnName = id.value();
                }
            }
        }
        if (this.needPersist) {
            return this;
        } else {
            return null;
        }
    }

    public String create(TableInfo tableInfo) {
        StringBuilder sql = new StringBuilder();
        sql.append("alter table " + tableInfo.getTableName() + Symbol.BLANK +"add");
        sql.append(Symbol.BLANK + this.columnName);
        if (this.type.equals(String.class)) {
            sql.append(Symbol.BLANK + "varchar(" + this.length + ")");
        } else if (this.type.equals(Integer.class)) {
            sql.append(Symbol.BLANK + "int");
        }
        if (this.isID) {
            sql.append(Symbol.BLANK + "primary key");
        }
        if (!this.nullable) {
            sql.append(Symbol.BLANK + "not null");
        }
        sql.append(";");
        return sql.toString();
    }
    public String delete(TableInfo tableInfo) {
        StringBuilder sql = new StringBuilder();
        sql.append("alter table " + tableInfo.getTableName() + Symbol.BLANK);
        sql.append("drop column " + this.columnName + ";");
        return sql.toString();
    }
    public String change(TableInfo tableInfo, String newColumnName) {
        StringBuilder sql = new StringBuilder();
        sql.append("EXEC  sp_rename   '" + tableInfo.getTableName() + "." + this.columnName +"' , " + newColumnName + ";");
        this.columnName = newColumnName;
        return sql.toString();
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder(this.columnName);
        if (this.type.equals(String.class)) {
            sql.append(Symbol.BLANK + "varchar(" + this.length + ")");
        } else if (this.type.equals(Integer.class)) {
            sql.append(Symbol.BLANK + "int");
        }
        if (this.isID) {
            sql.append(Symbol.BLANK + "primary key");
        }
        if (!this.nullable) {
            sql.append(Symbol.BLANK + "not null");
        }
        sql.append(";");
        return sql.toString();
    }

    public String getColumnName() {
        return columnName;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isID() {
        return isID;
    }

    public boolean isNullable() {
        return nullable;
    }

    public int getLength() {
        return length;
    }

    public boolean isNeedPersist() {
        return needPersist;
    }
}
