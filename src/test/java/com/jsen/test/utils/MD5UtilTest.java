package com.jsen.test.utils;

import org.junit.Before;
import org.junit.Test;


public class MD5UtilTest {

    MD5Util md5Util = null;
    @Before
    public void before() {
        md5Util = new MD5Util();
    }

    @Test
    public void MD5() {
        String password = "123456";
        System.out.println(MD5Util.MD5(password));
    }

    @Test
    public void generate() {
        String password = "123456";
        String passwe = MD5Util.MD5(password);
        System.out.println(passwe);
        System.out.println(MD5Util.generate(passwe));
    }

    @Test
    public void verify() {
        String password = "123456";
        String password2 = "1234567";
        String passwe = MD5Util.MD5(password);
        String passwe2 = MD5Util.MD5(password2);
        String en = MD5Util.generate(passwe);

        assert MD5Util.verify(passwe, en);
        assert !MD5Util.verify(passwe2, en);
    }
}