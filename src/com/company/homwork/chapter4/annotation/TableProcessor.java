package com.company.homwork.chapter4.annotation;

import com.company.homwork.chapter4.annotation.tool.ColumnInfo;
import com.company.homwork.chapter4.annotation.tool.TableInfo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

/**
 * @PackgeName: com.company.homwork.chapter4.annotation
 * @ClassName: TableProcessor
 * @Author: z8932
 * Date: 2019/11/6 0:32
 * project name: JAVA_ADVANCED
 * @Version:
 * @Description:
 */
public class TableProcessor implements MyProcessor {

    @Override
    public void process() throws Exception {
        Class<Person> personClass = Person.class;
        TableInfo tableInfo = new TableInfo();
        tableInfo = tableInfo.parse(personClass);
        System.out.println("创建表");
        System.out.println(tableInfo.create());
        System.out.println("删除表");
        System.out.println(tableInfo.delete());
        for (ColumnInfo columnInfo: tableInfo.getColumnInfoMap().values()) {
            System.out.println("添加字段 " + columnInfo.getColumnName());
            System.out.println(columnInfo.create(tableInfo));
            System.out.println("删除字段 " + columnInfo.getColumnName());
            System.out.println(columnInfo.delete(tableInfo));
            System.out.println("修改字段 " + columnInfo.getColumnName());
            System.out.println(columnInfo.change(tableInfo, "test"));
        }
    }
}
