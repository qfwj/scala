package javatest.seriaalize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测试兼容性
 * @author: zhbo
 * @date 2020/11/10 9:05
 */
public class TestJackson {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {


        write();
        read();
    }
    public static void read()  throws Exception {
        String ss = "{\"map\":{},\"testJasck1\":{\"i\":12}}\n";
        TestJasck re =  objectMapper.readValue(ss, TestJasck.class);
        System.out.println(re);
    }

    public static void write()  throws Exception {
        TestJasck testJackson = new TestJasck();
        byte[] re = objectMapper.writeValueAsBytes(testJackson);
        System.out.println(objectMapper.writeValueAsBytes(testJackson));
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
        //jsonGenerator.we(testJackson);
    }
}

class TestJasck {
    Map map = new HashMap();
    TestJasck1 testJasck1 = new TestJasck1();

    public Map getMap() {
        return map;
    }

    public TestJasck setMap(Map map) {
        this.map = map;
        return this;
    }

    public TestJasck1 getTestJasck1() {
        return testJasck1;
    }

    public TestJasck setTestJasck1(TestJasck1 testJasck1) {
        this.testJasck1 = testJasck1;
        return this;
    }


}

@JsonIgnoreProperties(ignoreUnknown = true)
class TestJasck1{

    List m = new ArrayList();

    public List getM() {
        return m;
    }

    public TestJasck1 setM(List m) {
        this.m = m;
        return this;
    }


}
