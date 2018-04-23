import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:")
public class Test001 {
    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("package%20com.jsen.test.jobhandler.quartz.dy%0A%0Aimport%20org.quartz.JobExecutionContext%0Aimport%20org.quartz.JobExecutionException%0Aimport%20org.slf4j.Logger%0Aimport%20org.slf4j.LoggerFactory%0A%0A/**%0A%20*%20<p>%0A%20*%20</p>%0A%20*%0A%20*%20@author%20$%7BUser%7D%0A%20*%20@since%202018/4/4%0A%20*/%0Aclass%20DynamicJob%20implements%20BaseJob%20%7B%0A%20%20%20%20private%20static%20final%20Logger%20LOGGER%20=%20LoggerFactory.getLogger(DynamicJob.class)%0A%0A%20%20%20%20@Override%0A%20%20%20%20void%20execute(JobExecutionContext%20context)%20throws%20JobExecutionException%20%7B%0A%20%20%20%20%20%20%20%20LOGGER.error(\"Dynamic%20Job执行时间:%20\"%20+%20new%20Date())%0A%20%20%20%20%7D%0A%7D".replaceAll("\\+", "%2B"), "UTF-8"));
    }

    @Test
    public void test2() {
        System.out.println("package com.jsen.test.jobhandler.quartz.dy\n" +
                "\n" +
                "import org.quartz.JobExecutionContext\n" +
                "import org.quartz.JobExecutionException\n" +
                "import org.slf4j.Logger\n" +
                "import org.slf4j.LoggerFactory\n" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " * </p>\n" +
                " *\n" +
                " * @author ${User}\n" +
                " * @since 2018/4/4\n" +
                " */\n" +
                "class DynamicJob implements BaseJob {\n" +
                "    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJob.class)\n" +
                "\n" +
                "    @Override\n" +
                "    void execute(JobExecutionContext context) throws JobExecutionException {\n" +
                "        LOGGER.error(\"Dynamic Job执行时间: \" + new Date())\n" +
                "    }\n" +
                "}\n".replaceAll("\r", "").replaceAll("\b", ""));
    }

    @Test
    public void testJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("sys:ui");
        jsonArray.add("sys:ui2");
        jsonArray.add("sys:ui3");
        jsonArray.add("sys:ui4");
        jsonArray.add("sys:ui4");
        for (Object o : jsonArray.toArray()) {
            System.err.println(o);
        }
        String[] s = new String[]{};
        for (String o : jsonArray.toArray(s)) {
            System.out.println(o);
        }
        for (String o : s) {
            System.err.println(o);
        }
    }

    @Test
    public void testJson2() {
        JSONArray jsonArray = JSON.parseArray("[3]");
    }
}
