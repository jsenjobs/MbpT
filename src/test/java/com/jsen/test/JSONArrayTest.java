package com.jsen.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/24
 */
public class JSONArrayTest {
    @Test
    public void testArray() {
        JSONArray a1 = new JSONArray();
        a1.add(1);
        a1.add("2");
        a1.add(4);
        JSONArray a2 = new JSONArray();
        a2.add(6);
        a2.add("8");
        a2.add(4);
        System.out.println(a1);
        System.out.println(a2);
        JSONArray a3 = new JSONArray();
        a3.addAll(a1);
        a3.addAll(a2);
        System.out.println(a3);
    }

    @Test
    public void testObject() {
        JSONObject obj = new JSONObject();
        obj.put("A", "D");
        obj.put("B", "DDS");
        obj.put("C", "DR");
        JSONArray array = new JSONArray();
        array.add("1");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("hello");
        array.add(7);
        array.add(7);
        array.add(6);
        array.add(5);
        array.add(100);
        obj.put("array", array);

        JSONObject o1 = new JSONObject();
        o1.put("A1", "D");
        o1.put("B1", "DDS");
        o1.put("C1", "DR");

        JSONObject o2 = new JSONObject();
        o2.put("A2", "D");
        o2.put("B2", "DDS");
        o2.put("C2", "DR");
        obj.put("child1", o1);
        obj.put("child2", o2);

        System.err.println(obj);

        JSONObject o11 = obj.getJSONObject("child1");
        JSONObject o21 = obj.getJSONObject("child2");
        o11.put("A1", "DDDD");
        o11.put("A10", "DsfsaDDD");
        o21.put("A2", "werf");
        o21.put("A11", "afwefsd");
        System.err.println(obj);
    }
}
