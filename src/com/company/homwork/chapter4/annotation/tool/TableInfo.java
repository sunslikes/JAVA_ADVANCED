package com.company.homwork.chapter4.annotation.tool;

import com.company.homwork.chapter4.annotation.annotation.Column;
import com.company.homwork.chapter4.annotation.annotation.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation.tool
 * @ClassName: TableInfo
 * @Author: z8932
 * Date: 2019/11/6 0:07
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class TableInfo {
    private String tableName; // 表名
    private Class<?> clazz; // 该表对应的实体类型信息类
    private boolean needPersist = false; // 是否需要持久化存储
    private Map<String, ColumnInfo> columnInfoMap = new HashMap<>();

    public TableInfo parse(Class<?> clazz) {
        this.tableName = clazz.getSimpleName();
        this.clazz = clazz;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation: annotations) {
            if (annotation.annotationType().equals(Entity.class)) {
                this.needPersist = true;
                Entity entity = (Entity)annotation;
                if (!"".equals(entity.value())) {
                    this.tableName = entity.value();
                }
                break;
            }
        }
        if (this.needPersist) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo = columnInfo.parse(field);
                if (columnInfo != null) {
                    this.columnInfoMap.put(field.getName(), columnInfo);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    public String create() {
        StringBuilder sql = new StringBuilder();
        sql.append(Symbol.LINE);
        sql.append("create table ");
        sql.append(this.tableName + Symbol.BLANK);
        sql.append("(");
        for (ColumnInfo columnInfo: this.columnInfoMap.values()) {
            sql.append(Symbol.LINE);
            sql.append(Symbol.TAB);
            sql.append(columnInfo.toString());
        }
        sql.append(Symbol.LINE);
        sql.append(");");
        return  sql.toString();
    }
    public String delete() {
        return "drop " + this.getTableName() + ";";
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder();
        sql.append(Symbol.LINE);
        sql.append("create table ");
        sql.append(this.tableName + Symbol.BLANK);
        sql.append("(");
        for (ColumnInfo columnInfo: this.columnInfoMap.values()) {
            sql.append(Symbol.LINE);
            sql.append(Symbol.TAB);
            sql.append(columnInfo.toString());
        }
        sql.append(Symbol.LINE);
        sql.append(");");
        return  sql.toString();
    }

    public String getTableName() {
        return tableName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean isNeedPersist() {
        return needPersist;
    }

    public Map<String, ColumnInfo> getColumnInfoMap() {
        return columnInfoMap;
    }
}
