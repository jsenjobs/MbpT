package com.jsen.test.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.ModuleColumnFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/22
 */
public class SqlParseTest {

    JSONObject tableFilters;
    String tableName;

    @Before
    public void before() {
        String data = "{\n" +
                "\t\t\"account\": [{\n" +
                "\t\t\t\"uuid\": \"c89b1783-5481-4838-8ebb-c7d2f52baedf\",\n" +
                "\t\t\t\"isGroup\": false,\n" +
                "\t\t\t\"left\": \"\",\n" +
                "\t\t\t\"right\": \"\",\n" +
                "\t\t\t\"_left\": [],\n" +
                "\t\t\t\"_right\": [],\n" +
                "\t\t\t\"func\": \"eq\"\n" +
                "\t\t}]\n" +
                "\t}";
        tableFilters = JSON.parseObject(data);
        tableName = "account";
    }

    @Test
    public void testParseToSql() {
        System.out.println(ModuleColumnFilter.parseFilterByTable(tableFilters, tableName));
    }
}
